package mlearn.kmeans;

import mlearn.ClusterResult;
import mlearn.DocumentTermMatrix;
import mlearn.Pair;
import mlearn.dataframe.*;
import template.collection.sequence.ArrayUtils;
import template.numbers.DoubleUtils;

import java.util.*;

/**
 * @author dy[jealousing@gmail.com] on 17-5-14.
 */
public class KmeansResult extends ClusterResult {
    List<double[]> centroid;
    List<Integer>[] members;
    double heterogeneity;
    DocumentTermMatrix dtm;
    int MAX_TOPICS = 10, k;

    //for kmeans
    public KmeansResult(int[] which, List<double[]> centroid, double squaredCost, DocumentTermMatrix dtm) {
        k = centroid.size();
        members = new List[k];
        for (int i = 0; i < k; ++i) members[i] = new ArrayList<>();
        for (int i = 0; i < which.length; ++i) members[which[i]].add(i);
        this.centroid = centroid;
        this.heterogeneity = squaredCost;
        this.dtm = dtm;
    }

    //for gmm
    public KmeansResult(double[][] resp, List<double[]> centroid, double logLikelihood, DocumentTermMatrix dtm) {
        assert (resp[0].length == centroid.size());
        k = centroid.size();
        members = new List[k];
        for (int i = 0; i < k; ++i) members[i] = new ArrayList<>();
        for (int i = 0; i < resp.length; ++i) members[ArrayUtils.minIndex(resp[i])].add(i);
        this.centroid = centroid;
        this.heterogeneity = logLikelihood;
        this.dtm = dtm;
    }


    @Override
    public int size() {
        return k;
    }

    @Override
    public Collection<Pair<String, Double>> getTopic(int index) {
        //PriorityQueue<Pair<String, Double>> topWords = new PriorityQueue<>((a, b) -> DoubleUtils.compare(a.getValue(), b.getValue()));
        TreeSet<Pair<String, Double>> topWords = new TreeSet<>((a, b) -> -DoubleUtils.compare(a.getValue(), b.getValue()));
        int terms = centroid.get(0).length;
        for (int i = 0; i < terms; ++i) {
            Pair<String, Double> pair = new Pair<>(dtm.termIndexer.getTerm(i), centroid.get(index)[i]);
            if (topWords.size() < MAX_TOPICS || topWords.last().getValue() < pair.getValue()) {
                if (topWords.size() == MAX_TOPICS) topWords.pollLast();
                topWords.add(pair);
            }
        }
        return topWords;
    }

    @Override
    public int capacity(int index) {
        return get(index).size();
    }

    @Override
    public List<Integer> get(int index) {
        return members[index];
    }

    @Override
    public double getHeterogeneity() {
        return heterogeneity;
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
                return members[index].size();
            }
        }, new AbstractList<String>() {

            @Override
            public String get(int index) {
                return Arrays.toString(getTopic(index).stream().map(Pair::getKey).toArray(String[]::new));
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
