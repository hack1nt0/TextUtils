package mlearn.bayesian;

import mlearn.*;
import template.debug.Stopwatch;
import template.numbers.DoubleUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author dy[jealousing@gmail.com] on 17-5-20.
 */
public class GaussianMixtures extends Clusterer {
    @Override
    public ClusterResult cluster(Object data) {
        throw new UnsupportedOperationException();
    }

    int k = 10;
    int n, m;
    double[] clzWeight;
    MultiDiagNormVariable[] norm;
    double[][] resp; // resp[i,j] : responsibility of clazz i to data j
    double[] docLikelihood;
    int maxItr = 10;
    int validItr;
    double clzWeightSmoothing = 1e-5;
    double varianceSmoothing = 1e-5;

    public ClusterResult cluster(DocumentTermMatrix dtm) {
        n = dtm.rows();
        m = dtm.cols();
        norm = new MultiDiagNormVariable[k];
        for (int i = 0; i < k; ++i) norm[i] = new MultiDiagNormVariable(m);
        clzWeight = new double[k];
        Arrays.fill(clzWeight, 1.0 / k);
        resp = new double[n][k];
        docLikelihood = new double[n];
        IntStream.range(0, n).parallel().forEach(di -> dtm.get(di).normalize());

        double oldLikelihood = 0;
        for (int itr = 0; itr < maxItr; ++itr) {
            //Expectation
            //for (int di = 0; di < n; ++di) {
            Stopwatch.tic();
            IntStream.range(0, n)
                    .parallel()
                    .forEach(di -> {
                        double diLikelihood = 0;
                        double dividend = 0;
                        double maxResp = -Double.MAX_VALUE;
                        for (int ci = 0; ci < k; ++ci) {
                            resp[di][ci] = Math.log(clzWeight[ci]) + norm[ci].pdf(dtm.get(di));
                            if (Double.isNaN(resp[di][ci]) || Double.isInfinite(resp[di][ci])) {
                                throw new RuntimeException();
                            }
                            if (resp[di][ci] > maxResp) maxResp = resp[di][ci];
                        }
                        for (int ci = 0; ci < k; ++ci) dividend += Math.exp(resp[di][ci] - maxResp);
                        dividend = maxResp + Math.log(dividend);
                        diLikelihood = dividend;

                        for (int ci = 0; ci < k; ++ci) {
                            //resp[di][ci] -= maxResp;
                            resp[di][ci] = Math.exp(resp[di][ci] - dividend);
                            if (Double.isNaN(resp[di][ci]) || Double.isInfinite(resp[di][ci])) {
                                throw new RuntimeException();
                            }
                        }
                        docLikelihood[di] = diLikelihood;
            });
            Stopwatch.toc();
            double newLikelihood = Arrays.stream(docLikelihood).sum() / n;
            System.out.printf("iteration %d, average-log-likelihood %.3f\n", itr, newLikelihood);
            if (itr > 0 && DoubleUtils.compare(newLikelihood, oldLikelihood, 1e-4) == 0) {
                validItr = itr;
                oldLikelihood = newLikelihood;
                break;
            }
            oldLikelihood = newLikelihood;

            //Maximization

            //for (int ci = 0; ci < k; ++ci) {
            Stopwatch.tic();
            IntStream.range(0, k)
                    .parallel()
                    .forEach(ci -> {
//                        long t1 = System.currentTimeMillis();
                        double count = 0;
                        double[] mean = norm[ci].mean;
                        Arrays.fill(mean, 0);
                        double[] variance = norm[ci].variance;
                        Arrays.fill(variance, 0);
                        for (int di = 0; di < n; ++di) {
                            count += resp[di][ci];
                            Document d = dtm.get(di);
                            for (int i = 0; i < d.n; ++i) {
                                mean[d.index[i]] += resp[di][ci] * d.data[i];
                            }
                        }
                        clzWeight[ci] = Math.max(count, clzWeightSmoothing);
                        for (int mi = 0; mi < m; ++mi) mean[mi] /= clzWeight[ci];
//                        System.out.println("1 " + (System.currentTimeMillis() - t1) / 1000.);

//                        long t2 = System.currentTimeMillis();
                        for (int di = 0; di < n; ++di) {
                            Document d = dtm.get(di);
                            int[] index = d.index;
                            double[] data = d.data;
                            for (int mi = 0; mi < m; ++mi) variance[mi] += resp[di][ci] * mean[mi] * mean[mi];
                            for (int i = 0; i < d.n; ++i) variance[index[i]] += resp[di][ci] * data[i] * (data[i] - 2 * mean[index[i]]);
                        }
                        for (int mi = 0; mi < m; ++mi) {
                            variance[mi] = variance[mi] / clzWeight[ci] + varianceSmoothing;
                        }
                        norm[ci].update();
//                        System.out.println("2 " + (System.currentTimeMillis() - t2) / 1000.);
                    });
            Stopwatch.toc();
            double totClzWeight = Arrays.stream(clzWeight).sum();
            for (int ci = 0; ci < k; ++ci) clzWeight[ci] /= totClzWeight;

        }
        System.out.printf("GMM with maxItr %d, validItr %d, maximal average-log-likelihood %.3f\n", maxItr, validItr, oldLikelihood);
        return null;
    }

    public static void main(String[] args) {
//        List<String>[] csv = FileUtils.readCsv("/Users/dy/Downloads/people_wiki.csv", true);
//        DocumentTermMatrix trains = DocumentTermMatrix.asTrain(csv[2], csv[1]);
//        System.out.println(trains);
//        GaussianMixtures gmm = new GaussianMixtures();
//        Stopwatch.tic();
//        gmm.cluster(trains);
//        Stopwatch.toc();
    }
}
