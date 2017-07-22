package mlearn.knn;
import mlearn.ClassificationResult;
import mlearn.Classifier;
import mlearn.Document;
import mlearn.DocumentTermMatrix;
import template.debug.RandomUtils;

import java.util.*;
import java.util.stream.IntStream;

/**
 * @author dy[jealousing@gmail.com] on 17-5-13.
 *
 * (multi table single lookup) Local Sensiive Hashing for KNN
 * h : num of splitting hyperplanes 0 < h <= 32, smaller value is better, but cost more time
 *
 *  See postive means nearest terms are in the same bin, then
 *  lower h --> lower false negative --> higher false positive
 *
 * m : num of hash table for NN, larger is better, but cost more time
 *
 */
public class Lsh extends Classifier {
    public int h = 5;
    public int m = 15; // num of hash table for NN
    public double[][][] splits = new double[m][h][]; //How to MAKE SURE random ?
    public AbstractMap<Integer, List<Document>>[] bins = new HashMap[m];

    @Override
    public Classifier fit(Object trains) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ClassificationResult classify(Object tests) {
        throw new UnsupportedOperationException();
    }

    public Classifier fit(DocumentTermMatrix trains) {
        IntStream.range(0, m)
                .parallel()
                .forEach(mi -> {
                    bins[mi] = new HashMap<Integer, List<Document>>();
                    for (int hi = 0; hi < h; ++hi) {
                        splits[mi][hi] = new double[trains.cols()];
                        for (int i = 0; i < splits[mi][hi].length; ++i)
                            splits[mi][hi][i] = RandomUtils.gaussian();
                    }
                    for (int di = 0; di < trains.rows(); ++di) {
                        int dhash = 0;
                        for (int hi = 0; hi < h; ++hi) {
                            double dot = trains.get(di).dot(splits[mi][hi]);
                            if (dot > 0.0) dhash |= 1 << hi;
                        }
                        List<Document> documents = bins[mi].getOrDefault(dhash, new ArrayList<>());
                        documents.add(trains.get(di));
                        bins[mi].put(dhash, documents);
                    }
                });
        return this;
    }

    public KnnResult classify(DocumentTermMatrix tests, int k) {
        return classify(tests, k, h, m);
    }

    public KnnResult classify(DocumentTermMatrix tests, int k, int h, int m) {
        PriorityQueue<KnnNeighbor>[] nn = new PriorityQueue[tests.rows()];
        IntStream.range(0, tests.rows())
                .parallel()
                .forEach(ti -> {
                    PriorityQueue<KnnNeighbor> tnn = new PriorityQueue<KnnNeighbor>(Collections.reverseOrder());
                    Set<Document> visited = new HashSet<Document>();
                    for (int mi = 0; mi < m; ++mi) {
                        int mhash = 0;
                        for (int hi = 0; hi < h; ++hi) {
                            double dot = tests.get(ti).dot(splits[mi][hi]);
                            if (dot > 0.0) mhash |= 1 << hi;
                        }
                        List<Document> mNeighbor = bins[mi].get(mhash);
                        if (mNeighbor != null) {
                            for (Document doc : mNeighbor) {
                                if (visited.contains(doc)) continue;
                                visited.add(doc);
                                double dist = doc.dotDistance(tests.get(ti));
                                if (tnn.size() < k || Double.compare(tnn.peek().dist, dist) > 0) {
                                    if (tnn.size() == k) tnn.poll();
                                    tnn.add(new KnnNeighbor(doc, dist));
                                }
                            }
                        }
                    }
                    nn[ti] = tnn;
                });
        return new KnnResult(nn, tests);
    }
}
