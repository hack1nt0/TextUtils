package mlearn.bayesian;

import mlearn.*;
import mlearn.dataframe.DataFrame;
import template.collection.Sorter;
import template.collection.sequence.ArrayUtils;
import template.debug.RandomUtils;
import template.numbers.DoubleUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dy[jealousing@gmail.com] on 17-5-20.
 */
public class LatentDirichletAllocation implements Serializable {
    public int[][] documentTopicCount;
    public int[][] topicWordCount;
    public int[] topicCapacity;
    public int[][] documentWordAllocation;
    public double alpha = 0.5, beta = 0.1;
    public int k, maxItr = 20;
    public String[] names;
    public int n, m;

    public BowDocumentTermMatrix dtm;
    private double[] allocationProb;

    private LatentDirichletAllocation() {}

    public void fit(BowDocumentTermMatrix dtm, int k) {
        long fitBegin = System.currentTimeMillis();
        this.dtm = dtm;
        this.k = k;
        this.names = new String[k];
        for (int i = 0; i < k; ++i) names[i] = "topic " + (i + 1);
        n = dtm.nrow;
        m = dtm.termIndexer.size();
        documentTopicCount = new int[n][k];
        topicWordCount = new int[k][m];
        topicCapacity = new int[k];
        documentWordAllocation = new int[n][];
        allocationProb = new double[k];
        for (int di = 0; di < n; ++di) {
            BagOfWords d = dtm.get(di);
            documentWordAllocation[di] = new int[d.n];
            for (int i = 0; i < d.n; ++i) {
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
                for (int i = 0; i < d.n; ++i) {
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
                System.out.printf("iteration %5d, time cost %10.3fs, ", itr, (System.currentTimeMillis() - begin) / 1000.);
                System.out.printf("(log) joint probability %20.3f \n", jointProb());
            }
        }

        System.out.printf("lda fitting, maxItr = %d, time cost %.3fs \n", maxItr, (System.currentTimeMillis() - fitBegin) / 1000.);
    }

    public double[][] classify(IntDocumentTermMatrix tests) {
        throw new IllegalArgumentException();
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
            for (int i = 0; i < d.n; ++i) {
                int wi = d.index[i];
                int wki = documentWordAllocation[di][i];
                double p = documentTopicProb[di][wki] * topicWordProb[wki][wi];
                logJointProb += Math.log(p);
            }
        }
        return logJointProb;
    }

    public List<Pair<String, Double>>[] getTopics(int wn) {
        List<Pair<String, Double>>[] res = new ArrayList[k];
        double[][] topicWordProb = getTopicWordDistribution();
        for (int ki = 0; ki < k; ++ki) {
            int topic = ki;
            int[] index = ArrayUtils.index(m);
            Sorter.sort(index, (i, j) -> -DoubleUtils.compare(topicWordProb[topic][i], topicWordProb[topic][j]));
            List<Pair<String, Double>> words = new ArrayList<>();
            for (int i = 0; i < wn; ++i) words.add(new Pair<>(dtm.termIndexer.getTerm(index[i]), topicWordProb[topic][index[i]]));
            res[ki] = words;
        }
        return res;
    }

    public void printTopics(int wn) {
//        List<Pair<String, Double>>[] topics = getTopics(wn);
//        StringDoubleFrame[] frames = new StringDoubleFrame[k];
//        for (int i = 0; i < k; ++i) frames[i] = StringDoubleFrame.of(names[i], topics[i]);
//        System.out.println(DataFrame.of(frames));
    }

    public void asModel(String path) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            asModel(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void asModel(ObjectOutputStream out) throws IOException {
//        out.writeObject(documentTopicCount);
//        out.writeObject(topicWordCount);
//        out.writeObject(topicCapacity);
//        out.writeObject(documentWordAllocation);
//        out.writeDouble(alpha);
//        out.writeDouble(beta);
//        out.writeInt(k);
//        out.writeInt(maxItr);
//        out.writeInt(n);
//        out.writeInt(m);
//        out.writeObject(names);
        out.writeObject(this);
    }

    public static LatentDirichletAllocation ofModel(String path) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            LatentDirichletAllocation lda = ofModel(in);
            in.close();
            return lda;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static LatentDirichletAllocation ofModel(ObjectInputStream in) throws Exception {
//        LatentDirichletAllocation lda = new LatentDirichletAllocation();
//        lda.documentTopicCount = (int[][]) in.readObject();
//        lda.topicWordCount = (int[][]) in.readObject();
//        lda.topicCapacity = (int[]) in.readObject();
//        lda.documentWordAllocation = (int[][]) in.readObject();
//        lda.alpha = in.readDouble();
//        lda.beta = in.readDouble();
//        lda.k = in.readInt();
//        lda.maxItr = in.readInt();
//        lda.n = in.readInt();
//        lda.m = in.readInt();
//        lda.names = (String[]) in.readObject();
        LatentDirichletAllocation lda = (LatentDirichletAllocation)in.readObject();
        return lda;
    }



    public static void main(String[] args) {
        List<String>[] csv = FileUtils.readCsv("/Users/dy/Downloads/people_wiki.csv", true);
        BowDocumentTermMatrix trains = BowDocumentTermMatrix.asTrain(csv[2], csv[1]);
        System.out.println(trains);
        LatentDirichletAllocation lda = new LatentDirichletAllocation();
        lda.fit(trains, 10);
        lda.printTopics(10);
        lda.asModel("output/tmp.txt");
        LatentDirichletAllocation lda2 = LatentDirichletAllocation.ofModel("output/tmp.txt");
        lda2.printTopics(10);
    }
}
