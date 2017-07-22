package mlearn.knn;

/**
 * @author dy[jealousing@gmail.com] on 17-5-5.
 */

import mlearn.ClassificationResult;
import mlearn.Classifier;
import mlearn.Document;
import mlearn.DocumentTermMatrix;
import template.numbers.DoubleUtils;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * Brute-force K Nearest Neighbors for classification
 *
 * Default Params
 * distance function: dot Distance
 * include.all: false
 * classification: maximum voting
 */
public class KnnBrute extends Classifier {
    DocumentTermMatrix trains;

    private KnnBrute() {}

    @Override
    public Classifier fit(Object trains) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ClassificationResult classify(Object tests) {
        throw new UnsupportedOperationException();
    }

    public Classifier fit(DocumentTermMatrix trains) {
        this.trains = trains;
        return this;
    }

    public ClassificationResult classify(DocumentTermMatrix tests, int k) {
        return classify(this.trains, tests, k);
    }

    public static ClassificationResult classify(DocumentTermMatrix trains, DocumentTermMatrix tests, int k) {
        PriorityQueue<KnnNeighbor>[] nnsList = new PriorityQueue[tests.rows()];
        IntStream.range(0, tests.rows())
                .parallel()
                .forEach(ti -> {
                    Document test = tests.get(ti);
                    PriorityQueue<KnnNeighbor> nns = new PriorityQueue<>(k, Collections.reverseOrder());
                    for (Document train : trains) {
                        double dist = test.dotDistance(train);
                        if (nns.size() < k || DoubleUtils.compare(nns.peek().dist, dist) > 0) {
                            if (nns.size() == k) nns.poll();
                            nns.add(new KnnNeighbor(train, dist));
                        }
                    }
                    nnsList[ti] = nns;
                });
        return new KnnResult(nnsList, tests);
    }
}
