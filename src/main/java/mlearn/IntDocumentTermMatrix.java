package mlearn;

import mlearn.dataframe.DataFrame;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.stream.IntStream;

/**
 * @author dy[jealousing@gmail.com] on 17-5-6.
 *
 * DTM as Compressed sparse row (CSR)
 */
public class IntDocumentTermMatrix implements Iterable<IntDocument>{
    IntDocument[] matrix;
    public ConcurrentIndexer termIndexer;
    public List<String> classes;
    public int nrow, ncol;
    double[] idf;
    double constructionTime;

    private IntDocumentTermMatrix(List<String> txts, List<String> classes, List<Integer> ids) {
        assert txts.size() == classes.size();
        long begin = System.currentTimeMillis();
        this.classes = classes;
        this.nrow = txts.size();
        this.matrix = new IntDocument[nrow];
        this.termIndexer = new ConcurrentIndexer();
        EnglishTokenizer tokenizer = EnglishTokenizer.asInstance();
        /* tf */
        IntStream.range(0, txts.size())
                .parallel()
                .forEach(r -> {
                    Map<Integer, Integer> wordCnt = new TreeMap<>();
                    for (String w : tokenizer.english(txts.get(r))) {
                        int wid = termIndexer.getOrAdd(w);
                        wordCnt.put(wid, wordCnt.getOrDefault(wid, 0) + 1);
                    }
                    matrix[r] = new IntDocument(wordCnt.keySet(), wordCnt.values(), termIndexer, classes.get(r), ids != null ? ids.get(r): r);
                });
        this.ncol = termIndexer.size();
        constructionTime = (System.currentTimeMillis() - begin) / 1000.;
    }

    private IntDocumentTermMatrix(List<String> txts, List<String> classes, IntDocumentTermMatrix trains, List<Integer> ids) {
        assert txts.size() == classes.size();
        long begin = System.currentTimeMillis();
        this.classes = classes;
        this.classes = classes;
        this.nrow = txts.size();
        this.matrix = new IntDocument[nrow];
        this.termIndexer = trains.termIndexer;
        this.idf = trains.idf;
        EnglishTokenizer tokenizer = EnglishTokenizer.asInstance();
        IntStream.range(0, txts.size())
                .parallel()
                .forEach(r -> {
                    Map<Integer, Integer> wordCnt = new TreeMap<>();
                    for (String w : tokenizer.english(txts.get(r))) {
                        if (!termIndexer.containsTerm(w)) continue;
                        int wid = termIndexer.getId(w);
                        wordCnt.put(wid, wordCnt.getOrDefault(wid, 0) + 1);
                    }
                    matrix[r] = new IntDocument(wordCnt.keySet(), wordCnt.values(), termIndexer, classes.get(r), ids != null ? ids.get(r) : r);
                });
        this.ncol = termIndexer.size();
        constructionTime = (System.currentTimeMillis() - begin) / 1000.;
    }

    public static IntDocumentTermMatrix asTrain(List<String> txts, List<String> classes, List<Integer> ids) {
        return new IntDocumentTermMatrix(txts, classes, ids);
    }

    public static IntDocumentTermMatrix asTrain(List<String> txts, List<String> classes) {
        return new IntDocumentTermMatrix(txts, classes, null);
    }

    public static IntDocumentTermMatrix asTest(List<String> txts, List<String> classes, IntDocumentTermMatrix trains, List<Integer> ids) {
        return new IntDocumentTermMatrix(txts, classes, trains, ids);
    }

    public static IntDocumentTermMatrix asTest(List<String> txts, List<String> classes, IntDocumentTermMatrix trains) {
        return new IntDocumentTermMatrix(txts, classes, trains, null);
    }

    public IntDocument get(int docId) {
        return matrix[docId];
    }

    public String getClass(int docId) { return classes.get(docId); }

    @Override
    public String toString() {
        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);
        double density = 0;
        for (IntDocument d : matrix) density += d.n;
        density = density * 100. / this.nrow / this.ncol;
        out.printf("Document-Term Matrix(dtm) %d documents x %d words, density %.3f%%, time cost %.3fs \n", this.nrow, this.ncol, density, constructionTime);
        int rows = Math.min(this.nrow, nrow);
        for (int i = 0; i < rows; ++i) {
            out.println(matrix[i]);
            out.println("----------------------");
        }
        out.close();
        return stringWriter.toString();
    }

    @Override
    public Iterator<IntDocument> iterator() {
        return Arrays.asList(matrix).iterator();
    }

}
