package mlearn;

import mlearn.dataframe.*;
import template.collection.CollectionUtils;
import template.collection.Sorter;
import template.numbers.DoubleUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author dy[jealousing@gmail.com] on 17-5-5.
 */
public class Document{
    public int[] index;  // MUST be sorted.
    public double[] data;
    boolean normalized;
    public double norm2;
    public ConcurrentIndexer termIndexer;
    public int n;

    Document(int[] index, double[] data, ConcurrentIndexer termIndexer) {
        assert Sorter.sorted(index);
        this.index = index;
        this.data = data;
        for (double d : data) norm2 += d * d;
        norm2 = Math.sqrt(norm2);
        this.termIndexer = termIndexer;
        this.n = index.length;
    }

    Document(Collection<Integer> index, Collection<Double> data, ConcurrentIndexer termIndexer) {
        this(CollectionUtils.toIntArray(index), CollectionUtils.toDoubleArray(data), termIndexer);
    }

    public double euclideanDistance(Document that) {
        if (termIndexer != that.termIndexer) throw new IllegalArgumentException();
        return Math.sqrt(squaredDistance(that));
    }

    public double squaredDistance(Document that) {
        if (termIndexer != that.termIndexer) throw new IllegalArgumentException();
        double dist = 0.0;
        for (int i = 0, j = 0; i < n && j < that.n; ) {
            int cmp = index[i] - that.index[j];
            if (cmp == 0) {
                dist += (data[i] - that.data[j]) * (data[i] - that.data[j]);
                i++;
                j++;
            } else if (cmp > 0) {
                dist += that.data[j] * that.data[j];
                j++;
            } else {
                dist += data[i] * data[i];
                i++;
            }
        }
        return dist;
    }

    public double squaredDistance(double[] that) {
        assert termIndexer.size() == that.length;
        double dist = 0.0;
        for (int i = 0, j = 0; i < that.length; ++i) {
            if (j == index.length || i != index[j]) dist += that[i] * that[i];
            else {
                dist += (that[i] - data[j]) * (that[i] - data[j]);
                j++;
            }
        }
        return dist;
    }

    public double dot(Document that) {
        if (termIndexer != that.termIndexer) throw new IllegalArgumentException();
        double dot = 0.0;
        for (int i = 0, j = 0; i < n && j < that.n; ) {
            int cmp = index[i] - that.index[j];
            if (cmp == 0) {
                dot += data[i] * that.data[j];
                i++;
                j++;
            } else if (cmp > 0) j++;
            else i++;
        }
        return dot;
    }

    public double dot(double[] that) {
        if (termIndexer.size() != that.length) throw new IllegalArgumentException();
        double dot = 0.0;
        for (int i = 0; i < n; ++i) dot += data[i] * that[index[i]];
        return dot;
    }

    public void normalize() {
        if (normalized) return;
        normalized = true;
        for (int i = 0; i < data.length; ++i) data[i] /= norm2;
    }

    public double dotDistance(Document that) {
        double dist = dot(that);
        if (!this.normalized) dist /= this.norm2;
        if (!that.normalized) dist /= that.norm2;
        return 1.0 - dist;
    }

    public String toString() {
        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);
        int rows = Math.min(10, n);
        List<Pair<String, Double>> tws = getTopWords(rows);
        out.printf("single document of word count %d, top words: \n", n);
        out.println(tws);
        out.close();
        return stringWriter.toString();
    }

    public Document intersect(Document that) {
        if (termIndexer != that.termIndexer) throw new IllegalArgumentException();
        List<Integer> index = new ArrayList<>();
        List<Double> data = new ArrayList<>();
        for (int i = 0, j = 0; i < n && j < that.n; ) {
            int cmp = this.index[i] - that.index[j];
            if (cmp == 0) {
                index.add(this.index[i]);
                data.add(Math.max(this.data[i], that.data[j]));
                i++;
                j++;
            }
            else if (cmp > 0) j++;
            else i++;
        }
        return new Document(index, data, this.termIndexer);
    }

    public Document union(Document that) {
        if (termIndexer != that.termIndexer) throw new IllegalArgumentException();
        List<Integer> index = new ArrayList<>();
        List<Double> data = new ArrayList<>();
        for (int i = 0, j = 0; i < n && j < that.n; ) {
            int cmp = this.index[i] - that.index[j];
            if (cmp == 0) {
                index.add(this.index[i]);
                data.add(Math.max(this.data[i], that.data[j]));
                i++;
                j++;
            }
            else if (cmp > 0) {
                index.add(that.index[j]);
                data.add(that.data[j]);
                j++;
            }
            else {
                index.add(this.index[i]);
                data.add(this.data[i]);
                i++;
            }
        }
        return new Document(index, data, this.termIndexer);
    }

    public int getWordCount() { return n; }

    public List<Pair<String, Double>> getTopWords(int k) {
        return getTopWords().subList(0, k);
    }

    public List<Pair<String, Double>> getTopWords() {
        List<Pair<String, Double>> res = new ArrayList<>();
        for (int i = 0; i < index.length; ++i)  res.add(new Pair<>(termIndexer.getTerm(index[i]), data[i]));
        Collections.sort(res, (o1, o2) -> {
                int cmp = DoubleUtils.compare(o1.getValue(), o2.getValue());
                if (cmp != 0) return -cmp;
                return o1.getKey().compareTo(o2.getKey());
            });
        return res;
    }
}

