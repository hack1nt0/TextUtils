package mlearn;

import mlearn.dataframe.DataFrame;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author dy[jealousing@gmail.com] on 17-5-6.
 *
 * DTM as Compressed sparse row (CSR)
 */
public class BowDocumentTermMatrix implements Iterable<BagOfWords>, Serializable {
    public BagOfWords[] matrix;
    public ConcurrentIndexer termIndexer;
    public List<String> classes;
    public int nrow, ncol;
    public double constructionTime;

    private BowDocumentTermMatrix(List<String> txts, List<String> classes, List<Integer> ids) {
        assert txts.size() == classes.size();
        long begin = System.currentTimeMillis();
        this.classes = classes;
        this.nrow = txts.size();
        this.matrix = new BagOfWords[nrow];
        this.termIndexer = new ConcurrentIndexer();
        EnglishTokenizer tokenizer = EnglishTokenizer.asInstance();
        /* tf */
        IntStream.range(0, txts.size())
                .parallel()
                .forEach(r -> {
                    List<String> words = tokenizer.english(txts.get(r));
                    int[] bag = new int[words.size()];
                    for (int i = 0; i < words.size(); ++i) {
                        int wid = termIndexer.getOrAdd(words.get(i));
                        bag[i] = wid;
                    }
                    matrix[r] = new BagOfWords(bag, termIndexer, classes.get(r), ids != null ? ids.get(r): r);
                });
        this.ncol = Arrays.stream(matrix).mapToInt(d -> d.n).max().getAsInt();
        constructionTime = (System.currentTimeMillis() - begin) / 1000.;
    }

    private BowDocumentTermMatrix(List<String> txts, List<String> classes, BowDocumentTermMatrix trains, List<Integer> ids) {
        assert txts.size() == classes.size();
        long begin = System.currentTimeMillis();
        this.classes = classes;
        this.classes = classes;
        this.nrow = txts.size();
        this.matrix = new BagOfWords[nrow];
        this.termIndexer = trains.termIndexer;
        EnglishTokenizer tokenizer = EnglishTokenizer.asInstance();
        IntStream.range(0, txts.size())
                .parallel()
                .forEach(r -> {
                    List<String> words = tokenizer.english(txts.get(r));
                    int[] bag = new int[words.size()];
                    for (int i = 0; i < words.size(); ++i) {
                        String w = words.get(i);
                        if (!termIndexer.containsTerm(w)) continue;
                        int wid = termIndexer.getId(w);
                        bag[i] = wid;
                    }
                    matrix[r] = new BagOfWords(bag, termIndexer, classes.get(r), ids != null ? ids.get(r) : r);
                });
        this.ncol = Arrays.stream(matrix).mapToInt(d -> d.n).max().getAsInt();
        constructionTime = (System.currentTimeMillis() - begin) / 1000.;
    }

    public static BowDocumentTermMatrix asTrain(List<String> txts, List<String> classes, List<Integer> ids) {
        return new BowDocumentTermMatrix(txts, classes, ids);
    }

    public static BowDocumentTermMatrix asTrain(List<String> txts, List<String> classes) {
        return new BowDocumentTermMatrix(txts, classes, null);
    }

    public static BowDocumentTermMatrix asTest(List<String> txts, List<String> classes, BowDocumentTermMatrix trains, List<Integer> ids) {
        return new BowDocumentTermMatrix(txts, classes, trains, ids);
    }

    public static BowDocumentTermMatrix asTest(List<String> txts, List<String> classes, BowDocumentTermMatrix trains) {
        return new BowDocumentTermMatrix(txts, classes, trains, null);
    }

    public BagOfWords get(int docId) {
        return matrix[docId];
    }

    public String getClass(int docId) { return classes.get(docId); }

    public String toString() {
        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);
        double density = 0;
        for (BagOfWords d : matrix) density += d.n;
        density = density * 100. / this.nrow / this.ncol;
        out.printf("Document-Term Matrix(dtm) %d documents x %d words, density %.3f%%, time cost %.3fs \n", this.nrow, this.ncol, density, constructionTime);
        return null;
    }

    @Override
    public Iterator<BagOfWords> iterator() {
        return Arrays.asList(matrix).iterator();
    }
}
