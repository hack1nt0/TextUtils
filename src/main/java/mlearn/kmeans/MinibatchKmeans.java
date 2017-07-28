package mlearn.kmeans;

import mlearn.*;
import template.collection.EArrayList;
import template.debug.RandomUtils;
import template.debug.Stopwatch;

import java.util.Arrays;
import java.util.List;

/**
 * @author dy[jealousing@gmail.com] on 17-5-17.
 */
public class MinibatchKmeans extends Clusterer {
    @Override
    public ClusterResult cluster(Object data) {
        throw new UnsupportedOperationException();
    }

    public ClusterResult cluster(DocumentTermMatrix dtm) {
        int k = 10;
        int b = dtm.rows() / 500;
        int maxItr = 10;
        return cluster(dtm, k, b, maxItr);
    }

    public ClusterResult cluster(DocumentTermMatrix dtm, int k, int b, int maxItr) {
        System.out.printf("mini-batch k-means of clazzes %d, batch size %d and max iterations %d \n", k, b, maxItr);
        int n = dtm.rows();
        int m = dtm.cols();
        double[][] centroid = new double[k][m];
        double[] capacity = new double[k]; // using long to avoid overflow
        int[] clazz = new int[n];
        double[] distToCentroid = new double[n];
        Arrays.fill(distToCentroid, Double.MAX_VALUE);
        int[] initCentroid = new int[k];
        double maxLr = 0.04;
        double minLr = maxLr * 0.01;
        int minItr = (int)(maxItr * 0.9); // time to make the learning rate constant

        //initialization
        Stopwatch.toc();


        for (int itr = 0; itr < maxItr; ++itr) {
            double alpha = (double) itr / minItr;
            double lr = maxLr * (1 - alpha) + minLr * alpha;
            if (itr > minItr) lr = minLr;
            System.out.printf("iteration %d, learning rate %.6f \n", itr, lr);
            Stopwatch.tic();
            int[] batch = RandomUtils.chooseKElems(n, b);
            //int[] batch = new int[b];
            //for (int i = 0; i < b; ++i) batch[i] = RandomUtils.uniform(size);

            for (int di : batch) {
                distToCentroid[di] = Double.MAX_VALUE;
                Document d = dtm.get(di);
                for (int ki = 0; ki < k; ++ki) {
                    double distToKi = d.squaredDistance(centroid[ki]);
                    if (distToKi < distToCentroid[di]) {
                        distToCentroid[di] = distToKi;
                        clazz[di] = ki;
                    }
                }
            }
            Stopwatch.toc();

            Stopwatch.tic();
//            for (int di : batch) {
//                Document d = dtm.get(di);
//                int ki = which[di];
//                capacity[ki]++;
//                double eta = 1 / capacity[ki];
//                //for (int i = 0; i < d.size; ++i) centroid[ki][d.index[i]] = (1 - eta) * centroid[ki][d.index[i]] + eta * d.data[i]; // for 0 to d.size OR for 0 to m
//                average(d, centroid[ki], eta);
//            }
            for (int ki = 0; ki < k; ++ki) {
                int capacityKi = 0;
                double[] acc = new double[m];
                for (int di : batch) {
                    if (clazz[di] != ki) continue;
                    capacityKi++;
                    Document d = dtm.get(di);
                    for (int i = 0; i < d.size; ++i) acc[d.index[i]] += d.data[i];
                }
                if (capacityKi > 0) {
                    for (int mi = 0; mi < m; ++mi) centroid[ki][mi] = centroid[ki][mi] * (1 - lr) + acc[mi] * lr / capacityKi;
                }
            }
            Stopwatch.toc();

        }


        List<Document>[] members = new EArrayList[k];
        for (int ki = 0; ki < k; ++ki) members[ki] = new EArrayList<>();
        for (int di = 0; di < n; ++di) members[clazz[di]].add(dtm.get(di));
        //return new KmeansResult(members, distToCentroid);
        return null;
    }

    private void average(Document d, double[] centroid, double eta) {
        int i = 0, j = 0;
        while (i < centroid.length) {
            if (j < d.size && i == d.index[j]) {
                centroid[i] = (1 - eta) * centroid[i] + eta * d.data[j];
                i++;
                j++;
            } else {
                centroid[i] *= 1 - eta;
                i++;
            }
        }
    }

    public static void main(String[] args) {
//        Stopwatch.tic();
//        List<String>[] csv = FileUtils.readCsv("/Users/dy/Downloads/people_wiki.csv", true);
//        Stopwatch.toc();
//        DocumentTermMatrix trains = DocumentTermMatrix.asTrain(csv[2], csv[1]);
//        System.out.println(trains);
//        Stopwatch.tic();
//        ClusterResult result = new MinibatchKmeans().cluster(trains);
//        Stopwatch.toc();
//        System.out.println(result);
    }
}
