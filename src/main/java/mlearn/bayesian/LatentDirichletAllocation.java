package mlearn.bayesian;

import com.sun.javafx.binding.StringFormatter;
import mlearn.*;
import mlearn.dataframe.DataFrame;
import mlearn.tokenizer.StackedTokenizer;
import template.collection.Sorter;
import template.collection.sequence.ArrayUtils;
import template.debug.PrintWriterUTF8;
import template.debug.RandomUtils;
import template.debug.ScannerUTF8;
import template.numbers.DoubleUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dy[jealousing@gmail.com] on 17-5-20.
 */
public class LatentDirichletAllocation extends Model{
    public int[][] documentTopicCount;
    public int[][] topicWordCount;
    public int[] topicCapacity;
    public int[][] documentWordAllocation;
    public final double alpha = 0.5, beta = 0.1;
    public int k;
    public String[] names;
    public int n, m;
    public BowDocumentTermMatrix dtm;
    private double[] allocationProb;

    @Override
    public Object fit(Object...params) {
        this.dtm = (BowDocumentTermMatrix)params[0];
        this.k = (int)params[1];
        int maxItr = (int)params[2];
        System.err.printf("lda(k=%d, maxItr=%d, alpha=%f, beta=%f)...\n", k, maxItr, alpha, beta);
        System.err.printf("Iteration   (Log).Joint.Prob         cost(s)\n");

        this.names = new String[k];
        for (int i = 0; i < k; ++i) names[i] = "Topic." + (i + 1);
        n = dtm.nrow;
        m = dtm.termIndexer.size();
        documentTopicCount = new int[n][k];
        topicWordCount = new int[k][m];
        topicCapacity = new int[k];
        documentWordAllocation = new int[n][];
        allocationProb = new double[k];
        for (int di = 0; di < n; ++di) {
            BagOfWords d = dtm.get(di);
            documentWordAllocation[di] = new int[d.size];
            for (int i = 0; i < d.size; ++i) {
                int wi = d.index[i];
                int ki = RandomUtils.uniform(k);
                topicWordCount[ki][wi]++;
                topicCapacity[ki]++;
                documentTopicCount[di][ki]++;
                documentWordAllocation[di][i] = ki;
            }
        }
        for (int itr = 0; itr < maxItr; ++itr) {
            long begin = System.currentTimeMillis();
            for (int di = 0; di < n; ++di) {
                BagOfWords d = dtm.get(di);
                for (int i = 0; i < d.size; ++i) {
                    int wi = d.index[i];
                    int oldKi = documentWordAllocation[di][i];
                    documentTopicCount[di][oldKi]--;
                    topicWordCount[oldKi][wi]--;
                    topicCapacity[oldKi]--;
                    for (int ki = 0; ki < k; ++ki) {
                        allocationProb[ki] = (documentTopicCount[di][ki] + alpha) * (topicWordCount[ki][wi] + beta) / (topicCapacity[ki] + m * beta);
                    }
                    int newKi = RandomUtils.discreteX(allocationProb);
                    documentWordAllocation[di][i] = newKi;
                    documentTopicCount[di][newKi]++;
                    topicWordCount[newKi][wi]++;
                    topicCapacity[newKi]++;
                }
            }
            if (itr % 10 == 0) {
                System.err.printf("%5d\t%20.3f\t%10.3f\n", itr, jointProb(), (System.currentTimeMillis() - begin) / 1000.);
            }
        }
        return null;
    }

    public double[][] getDocumentTopicDistribution() {
        int dn = dtm.nrow;
        double[][] res = new double[dn][k];
        for (int di = 0; di < dn; ++di) {
            double sum = 0;
            for (int ki = 0; ki < k; ++ki) {
                res[di][ki] = documentTopicCount[di][ki] + alpha;
                sum += documentTopicCount[di][ki] + alpha;
            }
            for (int ki = 0; ki < k; ++ki) res[di][ki] /= sum;
        }
        return res;
    }

    public double[][] getTopicWordDistribution() {
        double[][] res = new double[k][m];
        for (int ki = 0; ki < k; ++ki) {
            double sum = 0;
            for (int mi = 0; mi < m; ++mi) {
                res[ki][mi] = topicWordCount[ki][mi] + beta;
                sum += topicWordCount[ki][mi] + beta;
            }
            for (int mi = 0; mi < m; ++mi) {
                res[ki][mi] /= sum;
            }
        }
        return res;
    }

    public int[][] getWordTopicAllocation() {
        return documentWordAllocation;
    }

    public double jointProb() {
        double[][] documentTopicProb = getDocumentTopicDistribution();
        double[][] topicWordProb = getTopicWordDistribution();
        double logJointProb = 0;
        for (int di = 0; di < dtm.nrow; ++di) {
            BagOfWords d = dtm.get(di);
            for (int i = 0; i < d.size; ++i) {
                int wi = d.index[i];
                int wki = documentWordAllocation[di][i];
                double p = documentTopicProb[di][wki] * topicWordProb[wki][wi];
                logJointProb += Math.log(p);
            }
        }
        return logJointProb;
    }

    private class StringDoublePair {
        String key;
        double value;

        public StringDoublePair(String key, double value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return StringFormatter.format("%-25s\t:%.4e", key, value).get();
        }
    }

    public List<StringDoublePair>[] getTopics(int wn) {
        List<StringDoublePair>[] res = new ArrayList[k];
        double[][] topicWordProb = getTopicWordDistribution();
        for (int ki = 0; ki < k; ++ki) {
            int topic = ki;
            int[] index = ArrayUtils.index(m);
            Sorter.sort(index, (i, j) -> -DoubleUtils.compare(topicWordProb[topic][i], topicWordProb[topic][j]));
            List<StringDoublePair> words = new ArrayList<>();
            for (int i = 0; i < wn; ++i) words.add(new StringDoublePair(dtm.termIndexer.getTerm(index[i]), topicWordProb[topic][index[i]]));
            res[ki] = words;
        }
        return res;
    }

    public void printTopics(int wn) {
        List<StringDoublePair>[] topics = getTopics(wn);
        Arrays.sort(topics, (t1, t2) -> t1.get(0).key.compareTo(t2.get(0).key));
        for (int i = 0; i < k; ++i) {
            System.err.println(names[i]);
            for (int j = 0; j < wn; ++j)
                System.err.println(topics[i].get(j));
            System.err.println("------------------------------------");
        }
    }

    @Override
    public Object apply(Object... params) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Model read(ScannerUTF8 in) {
        return null;
    }

    @Override
    public void write(PrintWriterUTF8 in) {

    }

    public static void main(String[] args) {
        DataFrame dataFrame = new DataFrame(System.in);
        BowDocumentTermMatrix bowDtm = new BowDocumentTermMatrix(StackedTokenizer.split(dataFrame.get(1)));
        LatentDirichletAllocation lda = new LatentDirichletAllocation();
        lda.fit(bowDtm, Integer.valueOf(args[0]), Integer.valueOf(args[1]));
        lda.printTopics(Integer.valueOf(args[2]));
    }
}
