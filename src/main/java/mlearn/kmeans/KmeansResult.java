package mlearn.kmeans;

import mlearn.ClusterResult;
import mlearn.DocumentTermMatrix;
import mlearn.Pair;
import mlearn.dataframe.*;
import template.numbers.DoubleUtils;

import java.util.*;

/**
 * @author dy[jealousing@gmail.com] on 17-5-14.
 */
public class KmeansResult extends ClusterResult {
    double[][] centroid;
    int[] capacity;
    int[] which;
    double squaredCost;
    DocumentTermMatrix dtm;
    int MAX_TOPICS = 10;

    public KmeansResult(int[] which, double[][] centroid, int[] capacity, double squaredCost, DocumentTermMatrix dtm) {
        this.which = which;
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
        for (int i = 0; i < centroid[clusterId].length; ++i) {
            Pair<String, Double> pair = new Pair<>(dtm.termIndexer.getTerm(i), centroid[clusterId][i]);
            if (topWords.size() < MAX_TOPICS || topWords.last().getValue() < pair.getValue()) {
                if (topWords.size() == MAX_TOPICS) topWords.pollLast();
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

    @Override
    public String toString() {
        // cluster id, members count, topics
        int rows = size();
        DataFrame dataFrame = new DataFrame(new AbstractList<Integer>() {
            @Override
            public Integer get(int index) {
                return index;
            }

            @Override
            public int size() {
                return rows;
            }
        }, new AbstractList<Integer>() {

            @Override
            public int size() {
                return rows;
            }

            @Override
            public Integer get(int index) {
                return getMembers(index);
            }
        }, new AbstractList<String>() {
            @Override
            public String get(int index) {
                return Arrays.toString(getTopic(index).stream().map(p -> p.getKey()).toArray(String[]::new));
            }

            @Override
            public int size() {
                return rows;
            }
        });
        dataFrame.setColNames("Cluster.id", "Capacity", "Topics");
        return dataFrame.toString();
    }
}
