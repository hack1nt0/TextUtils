package mlearn.tsne;

import mlearn.DocumentTermMatrix;
import template.debug.ScannerUTF8;
import template.debug.Stopwatch;

import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * Created by dy on 17-9-30.
 *
 * Unfinished.
 */
public class BHTsne {
    static class Dist implements Comparable<Dist> {
        int to;
        double d;

        public Dist(int to, double d) {
            this.to = to;
            this.d = d;
        }

        @Override
        public int compareTo(Dist o) {
            return Double.compare(d, o.d);
        }

        @Override
        public String toString() {
            return "Dist{" +
                    "to=" + to +
                    ", d=" + d +
                    '}';
        }
    }
    PriorityQueue<Dist>[] nn;

    public BHTsne(DocumentTermMatrix dtm, int perplexity) {
        int k = perplexity * 3;
        int n = dtm.rows();
        nn = new PriorityQueue[n];

        IntStream.range(0, n).parallel().forEach(i -> {
            nn[i] = new PriorityQueue<>();
            for (int j = 0; j < n; ++j) {
                if (j == i) continue;
                double d = dtm.get(i).euclideanDistance(dtm.get(j));
                Dist dist = new Dist(j, d);
                if (nn[i].size() >= k && dist.compareTo(nn[i].peek()) < 0) {
                    nn[i].poll();
                }
                nn[i].add(dist);
            }
        });
    }

    public static void main(String[] args) {
        DocumentTermMatrix dtm = DocumentTermMatrix.read(ScannerUTF8.fromPath("/Users/dy/TextUtils/data/train/spamsms.dtm"));
        int perplexity = 30;
        Stopwatch timer = new Stopwatch();
        timer.start();
        BHTsne tsne = new BHTsne(dtm, perplexity);
        timer.stop();
        PriorityQueue<Dist> nn0 = tsne.nn[0];
        while (nn0.size() > 0) {
            System.out.println(nn0.poll());
        }

    }
}
