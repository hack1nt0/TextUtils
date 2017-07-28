package mlearn;

import mlearn.tokenizer.StackedTokenizer;
import mlearn.tokenizer.Tokenizer;
import template.collection.CollectionUtils;
import mlearn.dataframe.DataFrame;
import template.concurrency.TaskScheduler;
import template.debug.ScannerUTF8;
import template.debug.PrintWriterUTF8;
import template.debug.Stopwatch;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.stream.IntStream;

/**
 * @author dy[jealousing@gmail.com] on 17-5-6.
 *
 * DTM as Compressed sparse row (CSR)
 */
public class DocumentTermMatrix implements Iterable<Document>{
    private Document[] matrix;
    public ConcurrentIndexer termIndexer = new ConcurrentIndexer();
    private int rows, cols, size;
    private double[] idf;
    private double constructionTime, sparsity;

    protected DocumentTermMatrix() {}

    public DocumentTermMatrix(List<List<String>> txts) {
        long begin = System.currentTimeMillis();
        this.rows = txts.size();
        this.matrix = new Document[rows];
        /* tf */
        IntStream.range(0, txts.size())
                .parallel()
                .forEach(r -> {
                    Map<Integer, Double> wordCnt = new TreeMap<>();
                    for (String w : txts.get(r)) {
                        int wid = termIndexer.getOrAdd(w);
                        wordCnt.put(wid, wordCnt.getOrDefault(wid, 0.0) + 1);
                    }
                    matrix[r] = new Document(wordCnt.keySet(), wordCnt.values(), termIndexer);
                });
        /* idf */
        int terms = termIndexer.size();
        if (terms >= 1e8) throw new RuntimeException("terms too many... " + terms + " > 1e8");
        AtomicIntegerArray df = new AtomicIntegerArray(terms);
        this.idf = new double[terms];
        IntStream.range(0, txts.size())
                .parallel()
                .forEach(r -> {
                            for (int w : matrix[r].index) df.getAndIncrement(w);
                        });
        IntStream.range(0, terms)
                .parallel()
                .forEach(i -> { idf[i] = Math.log((double) rows / df.get(i));});

        /* tf-idf */
        IntStream.range(0, txts.size())
                .parallel()
                .forEach(r -> {
                    Document doc = matrix[r];
                    for (int i = 0; i < doc.size; ++i) doc.data[i] *= idf[doc.index[i]];
                });
        this.cols = termIndexer.size();
        for (Document doc : matrix) this.size += doc.size;
        constructionTime = (System.currentTimeMillis() - begin) / 1000.;
        sparsity = (double)this.size / (this.rows * this.cols);
    }

    public DocumentTermMatrix(List<List<String>> txts, DocumentTermMatrix trains) {
        long begin = System.currentTimeMillis();
        this.rows = txts.size();
        this.matrix = new Document[rows];
        this.termIndexer = trains.termIndexer;
        this.idf = trains.idf;
        IntStream.range(0, txts.size())
                .parallel()
                .forEach(r -> {
                    Map<Integer, Double> wordCnt = new TreeMap<>();
                    for (String w : txts.get(r)) {
                        if (!termIndexer.containsTerm(w)) continue;
                        int wid = termIndexer.getId(w);
                        wordCnt.put(wid, wordCnt.getOrDefault(wid, 0.0) + 1);
                    }
                    int[] index = CollectionUtils.toIntArray(wordCnt.keySet());
                    double[] tfidf = CollectionUtils.toDoubleArray(wordCnt.values());
                    for (int i = 0; i < tfidf.length; ++i) tfidf[i] *= idf[index[i]];
                    matrix[r] = new Document(index, tfidf, termIndexer);
                });
        this.cols = termIndexer.size();
        constructionTime = (System.currentTimeMillis() - begin) / 1000.;
    }

    public int size() {
        return rows;
    }

    public int rows() {
        return rows;
    }

    public int cols() {
        return termIndexer.size();
    }

    public Document get(int index) {
        return matrix[index];
    }

    @Override
    public String toString() {
        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);
        double density = 0;
        for (Document d : this) density += d.size;
        density = density * 100. / this.rows() / this.cols();
        out.printf("Document-Term Matrix(dtm) %d documents x %d words, density %.3f%%, time cost %.3fs \n", this.rows, this.cols, density, constructionTime);
        int rows = Math.min(this.rows(), 10);
        for (int i = 0; i < rows; ++i) {
            out.println(matrix[i]);
            out.println("----------------------");
        }
        out.close();
        return stringWriter.toString();
    }

    public void write(PrintWriterUTF8 out) {
        out.println(this.rows);
        out.println(this.cols);
        out.println(this.size);
        out.println(this.sparsity);
        out.println(this.constructionTime);

        for (int i = 0; i < this.cols; ++i) {
            out.println(termIndexer.getTerm(i));
        }
        for (int i = 0; i < this.cols; ++i) {
            out.print(idf[i]);
            out.print(" ");
        }
        out.println();

        int offset = 0;
        for (Document doc : matrix) {
            offset += doc.size;
            out.print(offset);
            out.print(" ");
        }
        out.println();

        for (Document doc : matrix) {
            for (int i : doc.index) {
                out.print(i);
                out.print(" ");
            }
        }
        out.println();
        for (Document doc : matrix) {
            for (double i : doc.data) {
                out.print(i);
                out.print(" ");
            }
        }
        out.println();
    }

    public static DocumentTermMatrix read(ScannerUTF8 in) {
        DocumentTermMatrix dtm = new DocumentTermMatrix();
        dtm.rows = in.nextInt();
        dtm.cols = in.nextInt();
        dtm.size = in.nextInt();
        dtm.sparsity = in.nextDouble();
        dtm.constructionTime = in.nextDouble();
        for (int i = 0; i < dtm.cols; ++i) {
            dtm.termIndexer.put(in.nextLine());//// TODO: 17-7-25  put method not works.
        }
        dtm.idf = new double[dtm.cols];
        for (int i = 0; i < dtm.cols; ++i) dtm.idf[i] = in.nextDouble();
        dtm.matrix = new Document[dtm.rows];
        int from = 0;
        for (int i = 0; i < dtm.rows; ++i) {
            int to = in.nextInt();
            int[] index = new int[to - from];
            double[] data = new double[to - from];
            Document document = new Document(index, data, dtm.termIndexer);
            dtm.matrix[i] = document;
            from = to;
        }
        for (int i = 0; i < dtm.rows; ++i) {
            int[] index = dtm.get(i).index;
            for (int j = 0; j < index.length; ++j) index[j] = in.nextInt();
        }
        for (int i = 0; i < dtm.rows; ++i) {
            double[] data = dtm.get(i).data;
            for (int j = 0; j < data.length; ++j) data[j] = in.nextDouble();
        }
        return dtm;
    }


    @Override
    public Iterator<Document> iterator() {
        return new Iterator<Document>() {
            int i = 0;
            @Override
            public boolean hasNext() {
                return i < rows;
            }

            @Override
            public Document next() {
                return matrix[i++];
            }
        };
    }

    public static void main(String[] args) throws IOException {
        DataFrame df = new DataFrame(new BufferedInputStream(System.in));
        DocumentTermMatrix dtm = new DocumentTermMatrix(StackedTokenizer.split(df.get(1)));
        PrintWriterUTF8 out = new PrintWriterUTF8(System.out);
        dtm.write(out);
        out.close();
    }
}
