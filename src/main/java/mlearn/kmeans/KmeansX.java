package mlearn.kmeans;

import mlearn.*;
import template.debug.ScannerUTF8;
import template.debug.RandomUtils;
import template.numbers.DoubleUtils;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author dy[jealousing@gmail.com] on 17-5-14.
 */

/**
 * (Elkan) Using the Triangle Inequality to Accelerate k-Means
 * drawback : floating unstable, the lower bound may be larger than actual value (not satisfied lower_bound <= v <= upper_bound)
 */
public class KmeansX extends Clusterer {
    @Override
    public ClusterResult cluster(Object data) {
        throw new UnsupportedOperationException();
    }

    public KmeansResult cluster(DocumentTermMatrix dtm) {
        return cluster(dtm, 10, 10);
    }

    public static KmeansResult cluster(DocumentTermMatrix dtm, int k, int maxIter) {
        System.err.printf("KmeansX(r=%d, c=%d, k = %d, rn = %d, maxItr = %d)...\n", dtm.rows(), dtm.cols(), k, 1, maxIter);
        long beginKmeans = System.currentTimeMillis();
        int n = dtm.rows();
        int m = dtm.cols();
        //for which
        double[][] centroid = new double[k][m];
        double[][] centroidAcc = new double[k][m];
        int[] capacity = new int[k];
        double[][] distBtwCentroids = new double[k][k];
        double[] distBtwItr = new double[k];
        int[][] clazzDiff = new int[k][n];
        boolean[] clazzUpdated = new boolean[k];

        //for data point
        int[] clazz = new int[n];
        double[][] lower = new double[n][k];
        double[] upper = new double[n];
        double[] distToClosestCentroid = new double[n];
        boolean[] isUpperTight = new boolean[n];

        //for stat
        int[] invokes = new int[n];
        boolean[] changed = new boolean[n];
        int actualItr = maxIter;

        //centroids initialization by kmeans++
        kmeansPP(k, dtm, clazz, distToClosestCentroid, centroid, centroidAcc, capacity, lower, upper);

        System.err.printf("Iteration\t(Upper).Squared.cost\tData.changed(%%)\tDist.invoking.counts(%%)\tCost(s)\n");
        for (int iter = 0; iter < maxIter; ++iter) {
            long beginItr = System.currentTimeMillis();
            Arrays.fill(invokes, 0);
            Arrays.fill(changed, false);

            updateDistBtwCentroid(centroid, distBtwCentroids);

            IntStream.range(0, n)
                    .parallel()
                    .forEach(di -> {
                        Document d = dtm.get(di);
                        for (int ki = 0; ki < k; ++ki) {
                            if (ki == clazz[di]) continue;
                            if (upper[di] <= lower[di][ki] || upper[di] * 2 <= distBtwCentroids[clazz[di]][ki]) continue;
                            if (!isUpperTight[di]) {
                                isUpperTight[di] = true;
                                distToClosestCentroid[di] = upper[di] = lower[di][clazz[di]] = d.squaredDistance(centroid[clazz[di]]);
                                invokes[di]++;
                            }
                            if (distToClosestCentroid[di] <= lower[di][ki] || distToClosestCentroid[di] * 2 <= distBtwCentroids[clazz[di]][ki]) continue;
                            double distToKi = d.squaredDistance(centroid[ki]);
//                            if (DoubleUtils.compare(lower[di][ki], distToKi, 1e-2) > 0) {
//                                System.out.printf("%f %f\capacity", lower[di][ki], distToKi);
//                                throw new RuntimeException();
//                            }
                            lower[di][ki] = distToKi;
                            invokes[di]++;
                            if (distToKi < distToClosestCentroid[di]) {
                                distToClosestCentroid[di] = upper[di] = distToKi;
                                clazzDiff[clazz[di]][di]--;
                                clazz[di] = ki;
                                clazzDiff[clazz[di]][di]++;
                                changed[di] = true;
                            }
                        }
            });

            Arrays.fill(clazzUpdated, false);
            for (int ki = 0; ki < k; ++ki) {
                for (int di = 0; di < n; ++di) {
                    if (clazzDiff[ki][di] == 0) continue;
                    clazzUpdated[ki] = true;
                    Document d = dtm.get(di);
                    if (clazzDiff[ki][di] < 0) {
                        capacity[ki]--;
                        for (int i = 0; i < d.size; ++i) centroidAcc[ki][d.index[i]] -= d.data[i];
                    } else {
                        capacity[ki]++;
                        for (int i = 0; i < d.size; ++i) centroidAcc[ki][d.index[i]] += d.data[i];
                    }
                    clazzDiff[ki][di] = 0;
                }
//                if (capacity[ki] < 0) {
//                    System.out.println(capacity[ki]);
//                    throw new RuntimeException();
//                }
                distBtwItr[ki] = 0.;
                if (!clazzUpdated[ki]) continue;
                if (capacity[ki] == 0) {
                    Arrays.fill(centroid[ki], 0);
                    continue;
                }
                for (int mi = 0; mi < m; ++mi) {
                    double nv = centroidAcc[ki][mi] / capacity[ki];
                    distBtwItr[ki] += (nv - centroid[ki][mi]) * (nv - centroid[ki][mi]);
                    centroid[ki][mi] = nv;
                }
            }

            for (int di = 0; di < n; ++di) {
                if (clazzUpdated[clazz[di]]) {
                    upper[di] += distBtwItr[clazz[di]];
                    isUpperTight[di] = false;
                }
                for (int ki = 0; ki < k; ++ki) lower[di][ki] = Math.max(0, lower[di][ki] - distBtwItr[ki]);
            }

            int totInvokes = Arrays.stream(invokes).sum();
            int totChanged = 0;
            for (int di = 0; di < n; ++di) if (changed[di]) totChanged++;
            System.err.printf("%5d\t%25.5f\t%10.3f\t", iter, Arrays.stream(upper).sum(), totChanged * 100. / n);
            System.err.printf("%15.3f\t", totInvokes * 100. / k / n);
            System.err.printf("%15.3f\n", (System.currentTimeMillis() - beginItr) / 1000.);
            if (totChanged == 0) {
                actualItr = iter + 1;
                break;
            }
        }

        //summary
        for (int di = 0; di < n; ++di) if (!isUpperTight[di]) distToClosestCentroid[di] = dtm.get(di).squaredDistance(centroid[clazz[di]]);
        double actualSquaredCost = Arrays.stream(distToClosestCentroid).sum();
        System.err.printf("%5d\t%25.5f\n", actualItr, actualSquaredCost);
        return new KmeansResult(clazz, Arrays.asList(centroid), actualSquaredCost, dtm);
    }

    private static void kmeansPP(int k, DocumentTermMatrix dtm, int[] clazz, double[] distToClosestCentroid, double[][] centroid, double[][] centroidAcc, int[] capacity, double[][] lower, double[] upper) {
        System.err.println("kmeans++...");
        long begin = System.currentTimeMillis();
        int n = dtm.rows();
        int m = dtm.cols();
        int[] chosen = new int[k];
        IntStream.range(0, n)
                .parallel()
                .forEach(di -> dtm.get(di).normalize());

        Arrays.fill(distToClosestCentroid, Double.MAX_VALUE);
        Arrays.fill(clazz, -1);
        Arrays.fill(chosen, -1);
        boolean[] used = new boolean[n];
        for (int ki = 0; ki < k; ++ki) {
            if (ki == 0) {
                int cdi = RandomUtils.uniform(n);
                used[cdi] = true;
                chosen[ki] = cdi;
                Document cd = dtm.get(cdi);
                for (int di = 0; di < n; ++di) {
                    clazz[di] = ki;
                    distToClosestCentroid[di] = upper[di] = lower[di][ki] = cd.squaredDistance(dtm.get(di));
                }
                capacity[ki] = n;
                continue;
            }
            while (true) {
//                System.err.println(Arrays.toString(distToClosestCentroid));
                int cdi = RandomUtils.discreteX(distToClosestCentroid);
                if (used[cdi]) continue;
                used[cdi] = true;
                chosen[ki] = cdi;
                Document cd = dtm.get(cdi);
                for (int di = 0; di < n; ++di) {
                    double dist2Ki = dtm.get(di).squaredDistance(cd);
                    lower[di][ki] = dist2Ki;
                    if (used[di]) continue;
                    if (DoubleUtils.compare(dist2Ki, distToClosestCentroid[di]) < 0) {
                        distToClosestCentroid[di] = upper[di] = dist2Ki;
                        capacity[clazz[di]]--;
                        clazz[di] = ki;
                        capacity[clazz[di]]++;
                    }
                }
                break;
            }
        }
        for (int di = 0; di < n; ++di) {
            Document d = dtm.get(di);
            int ki = clazz[di];
            for (int i = 0; i < d.size; ++i) centroidAcc[ki][d.index[i]] += d.data[i];
        }
        for (int ki = 0; ki < k; ++ki) {
            if (capacity[ki] == 0) continue;
            for (int mi = 0; mi < m; ++mi) centroid[ki][mi] = centroidAcc[ki][mi] / capacity[ki];
            double distBtwItr = dtm.get(chosen[ki]).squaredDistance(centroid[ki]);
            for (int di = 0; di < n; ++di) {
                if (clazz[di] == ki) upper[di] += distBtwItr;
                double nv = Math.max(0, lower[di][ki] - distBtwItr);
//                if (!(nv <= dtm.get(di).squaredDistance(centroid[ki]))) {
//                    System.out.println(nv + " " + dtm.get(di).squaredDistance(centroid[ki]) + " " + lower[di][ki] + " " + distBtwItr);
//                    throw new RuntimeException();
//                }
                lower[di][ki] = nv;
            }
        }
        Arrays.sort(chosen);
        System.err.printf("Centroids %s, cost %.3fs \n", Arrays.toString(chosen), (System.currentTimeMillis() - begin) / 1000.);
        System.err.println("Capacities " + Arrays.toString(capacity));
    }

    private static void updateDistBtwCentroid(double[][] centroid, double[][] res) {
        int k = centroid.length;
        int m = centroid[0].length;
        for (int from = 0; from < k; ++from)
            for (int to = from + 1; to < k; ++to) {
                for (int mi = 0; mi < m; ++mi) {
                    res[from][to] += (centroid[from][mi] - centroid[to][mi]) * (centroid[from][mi] - centroid[to][mi]);
                }
                res[to][from] = res[from][to];
            }
    }

    public static void main(String[] args) {
        DocumentTermMatrix dtm = DocumentTermMatrix.read(new ScannerUTF8(System.in));
        KmeansResult kmeansResult = KmeansX.cluster(dtm, Integer.valueOf(args[0]), Integer.valueOf(args[1]));
        System.err.println(kmeansResult);
    }
}
