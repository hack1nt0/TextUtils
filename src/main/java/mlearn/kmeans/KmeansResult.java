package mlearn.kmeans;

import mlearn.ClusterResult;
import mlearn.DocumentTermMatrix;
import mlearn.Pair;
import mlearn.dataframe.*;
import template.numbers.DoubleUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

/**
 * @author dy[jealousing@gmail.com] on 17-5-14.
 */
public class KmeansResult extends ClusterResult {
    double[][] centroid;
    int[] capacity;
    int[] clazz;
    double squaredCost;
    DocumentTermMatrix dtm;

    public KmeansResult(int[] clazz, double[][] centroid, int[] capacity, double squaredCost, DocumentTermMatrix dtm) {
        this.clazz = clazz;
        this.centroid = centroid;
        this.capacity = capacity;
        this.squaredCost = squaredCost;
        this.dtm = dtm;
    }

    @Override
    public int size() {
        return centroid.length;
    }

    @Override
    public Collection<Pair<String, Double>> getTopic(int clusterId) {
        if (getMembers(clusterId) == 0) return null;
        //PriorityQueue<Pair<String, Double>> topWords = new PriorityQueue<>((a, b) -> DoubleUtils.compare(a.getValue(), b.getValue()));
        TreeSet<Pair<String, Double>> topWords = new TreeSet<>((a, b) -> -DoubleUtils.compare(a.getValue(), b.getValue()));
        int n = 10;
        for (int i = 0; i < centroid[clusterId].length; ++i) {
            Pair<String, Double> pair = new Pair<>(dtm.termIndexer.getTerm(i), centroid[clusterId][i]);
            if (topWords.size() < n || topWords.last().getValue() < pair.getValue()) {
                if (topWords.size() == n) topWords.pollLast();
                topWords.add(pair);
            }
        }
        return topWords;
    }

    @Override
    public int getMembers(int clusterId) {
        return capacity[clusterId];
    }

    @Override
    public List<Object> getPartitions() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double getHeterogeneity() {
        return squaredCost;
    }

    @Override
    public double getHomogenicity() {
        throw new UnsupportedOperationException();
    }

//    @Override
//    public String toString() {
//        return toString(FrameUtils.DISPLAY_ROWS);
//    }
//
//    public String toString(int nrow) {
//        // cluster id, members count, topics
//        int rows = Math.min(nrow, size());
//        StringWriter stringWriter = new StringWriter();
//        PrintWriter out = new PrintWriter(stringWriter);
//        out.println(DataFrame.of(
//                IntegerFrame.of("cluster id", rows),
//                IntegerFrame.of("members", new IntegerList() {
//                    @Override
//                    public int size() {
//                        return rows;
//                    }
//
//                    @Override
//                    public int get(int i) {
//                        return getMembers(i);
//                    }
//                }),
//                StringFrame.of("topic", new StringList() {
//                    @Override
//                    public int size() {
//                        return rows;
//                    }
//
//                    @Override
//                    public String get(int i) {
//                        //List<Pair<String, Double>> topic = new ArrayList<Pair<String, Double>>();
//                        Collection<Pair<String, Double>> topic = getTopic(i);
//                        return topic == null ? "NA" : topic.toString();
//                    }
//                })));
//        return stringWriter.toString();
//    }
}
