package mlearn.bayesian;

import mlearn.*;
import mlearn.kmeans.KmeansResult;
import template.debug.PrintWriterUTF8;
import template.debug.ScannerUTF8;
import template.debug.Stopwatch;
import template.numbers.DoubleUtils;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author dy[jealousing@gmail.com] on 17-5-20.
 */
public class GaussianMixtures extends Model {
    int n, m;
    double[] clzWeight;
    MultiDiagNormVariable[] norm;
    double[][] resp; // resp[i,j] : responsibility of clazz j to data i
    double[] docLikelihood;
    int validItr;
    double clzWeightSmoothing = 1e-5;
    double varianceSmoothing = 1e-5;

    @Override
    public ClusterResult fit(Object... params) {
        DocumentTermMatrix dtm = (DocumentTermMatrix)params[0];
        int k = (int)params[1];
        int maxItr = (int)params[2];
        n = dtm.rows();
        m = dtm.cols();
        System.err.printf("GaussianMixtures(r=%d, c=%d, k=%d, maxItr=%d, clzWeightSmoothing=%f, varianceSmoothing=%f)...\n", n, m, k, maxItr, clzWeightSmoothing, varianceSmoothing);
        norm = new MultiDiagNormVariable[k];
        for (int i = 0; i < k; ++i) norm[i] = new MultiDiagNormVariable(m);
        clzWeight = new double[k];
        Arrays.fill(clzWeight, 1.0 / k);
        resp = new double[n][k];
        docLikelihood = new double[n];
        IntStream.range(0, n).parallel().forEach(di -> dtm.get(di).normalize());

        System.err.printf("Iteration\t(Average).Log.likelihood\tExpectation(s)\tMaximization(s)\n");
        double oldLikelihood = 0;
        for (int itr = 0; itr < maxItr; ++itr) {
            long t1 = System.currentTimeMillis();
            //Expectation
            //for (int di = 0; di < capacity; ++di) {
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
            for (int i = 0; i < Math.min(10, n); ++i) {
                for (int j = 0; j < Math.min(10, k); ++j) {
                    System.out.printf("%e\t", resp[i][j]);
                }
                System.out.println();
            }
            long t2 = System.currentTimeMillis();
            double newLikelihood = Arrays.stream(docLikelihood).sum() / n;
            System.err.printf("%5d\t%30.5f\t%20.3f\t",   itr, newLikelihood, (t2 - t1) / 1000.);
            if (itr > 0 && DoubleUtils.compare(newLikelihood, oldLikelihood, 1e-4) == 0) {
                validItr = itr;
                System.err.printf("%10s\n", "NA");
                break;
            }
            oldLikelihood = newLikelihood;

            //Maximization

            /**
             * mean[ki, mi] = \SUM_di (resp[di, ki] * data[di, mi])
             *
             * var[ki, mi] = \SUM_di resp[di, ki] * (mean[ki, mi]^2 + data[di, mi] ^ 2 - data[di, mi] * mean[ki, mi] * 2)
             *
             * var[ki, mi] = \SUM_di resp[di, ki] * (data[di, mi] - mean[ki, mi]) ^ 2
             */


            //for (int ci = 0; ci < k; ++ci) {
            IntStream.range(0, k)
                    .parallel()
                    .forEach(ci -> {
                        double count = 0;
                        double[] mean = norm[ci].mean;
                        Arrays.fill(mean, 0);
                        double[] variance = norm[ci].variance;
                        Arrays.fill(variance, 0);
                        for (int di = 0; di < n; ++di) {
                            count += resp[di][ci];
                            Document d = dtm.get(di);
                            for (int i = 0; i < d.size; ++i) {
                                mean[d.index[i]] += resp[di][ci] * d.data[i];
                            }
                        }
                        clzWeight[ci] = Math.max(count, clzWeightSmoothing);
                        for (int mi = 0; mi < m; ++mi) mean[mi] /= clzWeight[ci];

                        for (int di = 0; di < n; ++di) {
                            Document d = dtm.get(di);
                            int[] index = d.index;
                            double[] data = d.data;
                            for (int mi = 0; mi < m; ++mi) variance[mi] += resp[di][ci] * mean[mi] * mean[mi];
                            for (int i = 0; i < d.size; ++i) variance[index[i]] += resp[di][ci] * data[i] * (data[i] - 2 * mean[index[i]]);
                        }
                        for (int mi = 0; mi < m; ++mi) {
                            variance[mi] = variance[mi] / clzWeight[ci] + varianceSmoothing;
                        }
                        norm[ci].update();
                    });
            double totClzWeight = Arrays.stream(clzWeight).sum();
            for (int ci = 0; ci < k; ++ci) clzWeight[ci] /= totClzWeight;
            System.err.printf("%10.3f\n", (System.currentTimeMillis() - t2) / 1000.);

            for (int i = 0; i < k; ++i) {
//                for (int j = 0; j < norm[i].variance.length; ++j) {
//                    if (norm[i].variance[j] < 0) {
//                        System.err.println("found negative variance " + norm[i].variance[j]);
//                    }
//                }
                System.out.println("[mean]");
//                for (int j = 0; j < Math.min(norm[i].mean.length, 10); ++j) {
//                    System.err.printf("%e\t", norm[i].mean[j]);
//                }
                System.out.println("max mean " + Arrays.stream(norm[i].mean).max().getAsDouble());
                System.out.println();
                System.out.println("[variance]");
//                for (int j = 0; j < Math.min(norm[i].variance.length, 10); ++j) {
//                    System.err.printf("%e\t", norm[i].variance[j]);
//                }
                System.out.println("min var " + Arrays.stream(norm[i].variance).min().getAsDouble());
                System.out.println();
            }
        }
        return new KmeansResult(resp, new AbstractList<double[]>() {
            @Override
            public double[] get(int index) {
                return norm[index].mean;
            }

            @Override
            public int size() {
                return k;
            }
        }, oldLikelihood, dtm);
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
        DocumentTermMatrix trains = DocumentTermMatrix.read(new ScannerUTF8(System.in));
        GaussianMixtures gmm = new GaussianMixtures();
        ClusterResult result = gmm.fit(trains, Integer.valueOf(args[0]), Integer.valueOf(args[1]));
        System.err.println(result);
    }
}
