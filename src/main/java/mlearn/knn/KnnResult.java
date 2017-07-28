package mlearn.knn;

import mlearn.ClassificationResult;
import mlearn.Counter;
import mlearn.DocumentTermMatrix;
import mlearn.dataframe.*;
import template.debug.RandomUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * @author dy[jealousing@gmail.com] on 17-5-13.
 */
public class KnnResult extends ClassificationResult {
    PriorityQueue<KnnNeighbor>[] nns;
    DocumentTermMatrix tests;
    String[] clazz;
    long[] maxVote;
    final int n;

    public KnnResult(PriorityQueue<KnnNeighbor>[] nns, DocumentTermMatrix tests) {
        this.nns = nns;
        this.tests = tests;
        this.n = nns.length;
        clazz = new String[n];
        maxVote = new long[n];
        IntStream.range(0, n)
                .parallel()
                .forEach(i -> {
                    Counter<KnnNeighbor> counter = new Counter<>();
                    for (KnnNeighbor nn : nns[i]) counter.add(nn);
                    Map.Entry<Long, List<KnnNeighbor>> mostVoted = counter.reverse().firstEntry();
//                    clazz[i] = RandomUtils.choose(mostVoted.getValue()).to.getClazz();
                    maxVote[i] = mostVoted.getKey();
                });
    }

//    @Override
//    public String toString(int nrow) {
//        StringWriter stringWriter = new StringWriter();
//        PrintWriter out = new PrintWriter(stringWriter);
//        if (size == 1) {
//            KnnNeighbor[] sortedNns = nns[0].toArray(new KnnNeighbor[0]);
//            Arrays.sort(sortedNns);
//            out.printf("knn result single test of %d nearest neighbors \size", sortedNns.length);
//            int rows = Math.min(nrow, sortedNns.length);
//            out.println(DataFrame.of(
//                    StringFrame.of("test id", new StringList() {
//                        @Override
//                        public int size() {
//                            return rows;
//                        }
//
//                        @Override
//                        public String get(int i) {
//                            return String.valueOf(sortedNns[i].to.getId());
//                        }
//                    }), StringFrame.of("neighbor class", new StringList() {
//                        @Override
//                        public String get(int i) {
//                            return sortedNns[i].to.clazz;
//                        }
//
//                        @Override
//                        public int size() {
//                            return rows;
//                        }
//                    }), DoubleFrame.of("dist", new DoubleList() {
//                        @Override
//                        public double get(int i) {
//                            return sortedNns[i].dist;
//                        }
//
//                        @Override
//                        public int size() {
//                            return rows;
//                        }
//                    }), StringFrame.of("neighbor id", new StringList() {
//                        @Override
//                        public String get(int i) {
//                            return String.valueOf(sortedNns[i].to.id);
//                        }
//
//                        @Override
//                        public int size() {
//                            return rows;
//                        }
//                    }), StringFrame.of("top 10 common words", new StringList() {
//                        @Override
//                        public String get(int i) {
//                            return tests.get(0).intersect(sortedNns[i].to).getTopWords(10).toString();
//                        }
//
//                        @Override
//                        public int size() {
//                            return rows;
//                        }
//                    })));
//        } else {
//            out.printf("knn result multiple tests of %d documents \size", tests.rows());
//            int rows = Math.min(nrow, tests.rows());
//            out.println(DataFrame.of(
//                    StringFrame.of("test id", new StringList() {
//                        @Override
//                        public int size() {
//                            return rows;
//                        }
//
//                        @Override
//                        public String get(int i) {
//                            return String.valueOf(tests.get(i).getId());
//                        }
//                    }), StringFrame.of("correct class", new StringList() {
//                        @Override
//                        public int size() {
//                            return rows;
//                        }
//
//                        @Override
//                        public String get(int i) {
//                            return tests.get(i).getClazz();
//                        }
//                    }), StringFrame.of("classified class", new StringList() {
//                        @Override
//                        public int size() {
//                            return rows;
//                        }
//                        @Override
//                        public String get(int i) {
//                            return clazz[i];
//                        }
//
//                    }), StringFrame.of("votes", new StringList() {
//                        @Override
//                        public int size() {
//                            return rows;
//                        }
//
//                        @Override
//                        public String get(int i) {
//                            return String.valueOf(maxVote[i]);
//                        }
//
//                    }), StringFrame.of("top 10 common words(with the nearest neighbor of classified class)", new StringList() {
//                        @Override
//                        public String get(int i) {
//                            KnnNeighbor[] sortedNns = nns[i].toArray(new KnnNeighbor[0]);
//                            Arrays.sort(sortedNns);
//                            for (KnnNeighbor nn : sortedNns) {
//                                if (nn.to.getClazz().equals(clazz[i]))
//                                    return tests.get(i).intersect(nn.to).getTopWords(10).toString();
//                            }
//                            throw new RuntimeException();
//                        }
//                        @Override
//                        public int size() {
//                            return rows;
//                        }
//                    })));
//        }
//        return stringWriter.toString();
//    }
}
