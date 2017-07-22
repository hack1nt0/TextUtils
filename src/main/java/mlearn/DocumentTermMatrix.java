package mlearn;

import mlearn.tokenizer.LmHmmTokenizer;
import template.collection.CollectionUtils;
import template.collection.sequence.ArrayUtils;
import mlearn.dataframe.DataFrame;
import template.concurrency.TaskScheduler;
import template.debug.Stopwatch;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

/**
 * @author dy[jealousing@gmail.com] on 17-5-6.
 *
 * DTM as Compressed sparse row (CSR)
 */
public class DocumentTermMatrix implements Iterable<Document>{
    private Document[] matrix;
    public ConcurrentIndexer termIndexer;
    private String[] clazz; // for classification
    private int[] cluster; // for clustering
    private int nrow, ncol, n;
    public double[] idf;
    private double constructionTime;
    private int[] index; // for slicing(filtering)

    protected DocumentTermMatrix() {}

    public DocumentTermMatrix(List<List<String>> txts) {
        long begin = System.currentTimeMillis();
        this.nrow = txts.size();
        this.matrix = new Document[nrow];
        this.termIndexer = new ConcurrentIndexer();
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
                .forEach(i -> { idf[i] = Math.log((double)nrow / df.get(i));});

        /* tf-idf */
        IntStream.range(0, txts.size())
                .parallel()
                .forEach(r -> {
                    Document doc = matrix[r];
                    for (int i = 0; i < doc.n; ++i) doc.data[i] *= idf[doc.index[i]];
                });
        this.ncol = termIndexer.size();
        this.index = ArrayUtils.index(txts.size());
        for (Document doc : matrix) this.n += doc.n;
        constructionTime = (System.currentTimeMillis() - begin) / 1000.;
    }

    public DocumentTermMatrix(List<List<String>> txts, DocumentTermMatrix trains) {
        long begin = System.currentTimeMillis();
        this.nrow = txts.size();
        this.matrix = new Document[nrow];
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
        this.ncol = termIndexer.size();
        this.index = ArrayUtils.index(txts.size());
        constructionTime = (System.currentTimeMillis() - begin) / 1000.;
    }

    public int size() {
        return rows();
    }

    public int rows() {
        return getIndex().length;
    }

    public int cols() {
        return termIndexer.size();
    }

    public int[] getIndex() {
        return index;
    }

    public Document get(int docId) {
        return matrix[getIndex()[docId]];
    }

    public int[] getCluster() {
        return cluster;
    }

    public int getCluster(int i) {
        return cluster[getIndex()[i]];
    }

    public void setCluster(int i, int clusterId) {
        cluster[getIndex()[i]] = clusterId;
    }

    public String getClazz(int i) {
        return clazz[getIndex()[i]];
    }

    public String[] getClazz() {
        return clazz;
    }

    public void setClazz(int i, String clazz) {
        this.clazz[getIndex()[i]] = clazz;
    }

    @Override
    public String toString() {
        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);
        double density = 0;
        for (Document d : this) density += d.n;
        density = density * 100. / this.rows() / this.cols();
        out.printf("Document-Term Matrix(dtm) %d documents x %d words, density %.3f%%, time cost %.3fs \n", this.nrow, this.ncol, density, constructionTime);
        int rows = Math.min(this.rows(), 10);
        for (int i = 0; i < rows; ++i) {
            out.println(matrix[i]);
            out.println("----------------------");
        }
        out.close();
        return stringWriter.toString();
    }

    public void writeToFile(PrintWriter out) {
        out.printf("%d %d %d %f %f\n", this.nrow, this.ncol, this.n, (double)this.n / (this.nrow * this.ncol), this.constructionTime);

        for (String t : termIndexer.mapToTerm) out.println(t);

        for (Document doc : matrix) {
            for (int i : doc.index) {
                out.printf("%d ", i);
            }
        }
        out.println();
        for (Document doc : matrix) {
            for (double i : doc.data) {
                out.printf("%f ", i);
            }
        }
        out.println();
        int offset = 0;
        for (Document doc : matrix) {
            out.printf("%d ", offset);
            offset += doc.n;
        }


//        Collections.sort(termIndexer.mapToTerm);
//        for (int i = 0; i < Math.min(1000, termIndexer.mapToTerm.size()); ++i) {
//            out.print(i + "\t\"");
//            String t = termIndexer.mapToTerm.get(i);
//            int[] cs = new int[t.length()];
//            for (int j = 0; j < t.length(); ++j) cs[j] = t.charAt(j);
//            String[] bytes = Arrays.stream(cs).mapToObj(Integer::toHexString).toArray(String[]::new);
//            out.println(t + "\"\t" + termIndexer.mapToTerm.get(i).length() + "\t" + Arrays.toString(bytes));
//        }
    }


    @Override
    public Iterator<Document> iterator() {
        return new Iterator<Document>() {
            int i = 0;
            int[] index = getIndex();
            @Override
            public boolean hasNext() {
                return i < index.length;
            }

            @Override
            public Document next() {
                return matrix[index[i++]];
            }
        };
    }

    public SubDocumentTermMatrix filter(IntPredicate predicate) {
        List<Integer> validIndex = new ArrayList<>();
        int[] oldIndex = getIndex();
        for (int i : oldIndex) if (predicate.test(i)) validIndex.add(i);
        return new SubDocumentTermMatrix(this, CollectionUtils.toIntArray(validIndex));
    }

    public static void main(String[] args) {
        DataFrame df = new DataFrame(new BufferedInputStream(System.in));
        List<String> rawTexts = df.get(1);
        List<String>[] tokens = new List[df.rows()];
        int cores = Runtime.getRuntime().availableProcessors() / 2;
        Runnable[] tasks = new Runnable[cores];
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        for (int i = 0; i < cores; ++i) {
            final int from = i * rawTexts.size() / cores;
            final int to = (i + 1) * rawTexts.size() / cores;
            tasks[i] = () -> {
                LmHmmTokenizer tokenizer = new LmHmmTokenizer();
                for (int r = from; r < to; ++r) {
                    String text = rawTexts.get(r);
                    tokens[r] = tokenizer.split(text);
                }
            };
        }
        TaskScheduler.parallel(tasks);
        stopwatch.stop();
        DocumentTermMatrix dtm = new DocumentTermMatrix(Arrays.asList(tokens));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        dtm.writeToFile(out);
        out.close();
    }
}
