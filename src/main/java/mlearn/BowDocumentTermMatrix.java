package mlearn;

import mlearn.tokenizer.Tokenizer;

import java.io.PrintWriter;
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
public class BowDocumentTermMatrix implements Iterable<BagOfWords> {
    public BagOfWords[] matrix;
    public ConcurrentIndexer termIndexer;
    public int nrow, ncol;
    public double constructionTime;

    public BowDocumentTermMatrix(List<List<String>> txts) {
        long begin = System.currentTimeMillis();
        this.nrow = txts.size();
        this.matrix = new BagOfWords[nrow];
        this.termIndexer = new ConcurrentIndexer();
        /* tf */
        IntStream.range(0, txts.size())
                .parallel()
                .forEach(r -> {
                    List<String> words = txts.get(r);
                    int[] bag = new int[words.size()];
                    for (int i = 0; i < words.size(); ++i) {
                        int wid = termIndexer.getOrAdd(words.get(i));
                        bag[i] = wid;
                    }
                    matrix[r] = new BagOfWords(bag, termIndexer);
                });
        this.ncol = Arrays.stream(matrix).mapToInt(d -> d.size).max().getAsInt();
        constructionTime = (System.currentTimeMillis() - begin) / 1000.;
    }

    public BowDocumentTermMatrix(List<List<String>> txts, BowDocumentTermMatrix trains) {
        long begin = System.currentTimeMillis();
        this.nrow = txts.size();
        this.matrix = new BagOfWords[nrow];
        this.termIndexer = trains.termIndexer;
        IntStream.range(0, txts.size())
                .parallel()
                .forEach(r -> {
                    List<String> words = txts.get(r);
                    int[] bag = new int[words.size()];
                    for (int i = 0; i < words.size(); ++i) {
                        String w = words.get(i);
                        if (!termIndexer.containsTerm(w)) continue;
                        int wid = termIndexer.getId(w);
                        bag[i] = wid;
                    }
                    matrix[r] = new BagOfWords(bag, termIndexer);
                });
        this.ncol = Arrays.stream(matrix).mapToInt(d -> d.size).max().getAsInt();
        constructionTime = (System.currentTimeMillis() - begin) / 1000.;
    }


    public BagOfWords get(int docId) {
        return matrix[docId];
    }

    @Override
    public Iterator<BagOfWords> iterator() {
        return Arrays.asList(matrix).iterator();
    }

    public String toString() {
        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);
        double density = 0;
        for (BagOfWords d : matrix) density += d.size;
        density = density * 100. / this.nrow / this.ncol;
        out.printf("Document-Term Matrix(dtm) %d documents x %d words, density %.3f%%, time cost %.3fs \n", this.nrow, this.ncol, density, constructionTime);
        return stringWriter.toString();
    }
}
