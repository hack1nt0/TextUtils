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
public class IntDocument {
    public int[] index;  // MUST be sorted.
    public int[] data;
    boolean normalized;
    public double norm2;
    public ConcurrentIndexer termIndexer;
    public String clazz;
    public int n, id;

    public IntDocument(int[] index, int[] data, ConcurrentIndexer termIndexer, String clazz, int id) {
        assert Sorter.sorted(index);
        this.index = index;
        this.data = data;
        for (double d : data) norm2 += d * d;
        norm2 = Math.sqrt(norm2);

        this.termIndexer = termIndexer;
        this.clazz = clazz;
        this.n = index.length;
        this.id = id;
    }

    public IntDocument(Collection<Integer> index, Collection<Integer> data, ConcurrentIndexer termIndexer, String clazz, int id) {
        this(CollectionUtils.toIntArray(index), CollectionUtils.toIntArray(data), termIndexer, clazz, id);
    }

    public double euclideanDistance(IntDocument that) {
        if (termIndexer != that.termIndexer) throw new IllegalArgumentException();
        return Math.sqrt(squaredDistance(that));
    }

    public double squaredDistance(IntDocument that) {
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

    public double dot(IntDocument that) {
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

    public double dotDistance(IntDocument that) {
        double dist = dot(that);
        if (!this.normalized) dist /= this.norm2;
        if (!that.normalized) dist /= that.norm2;
        return 1.0 - dist;
    }

    public String toString() {
        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);
        int rows = Math.min(10, n);
        List<Pair<String, Integer>> tws = getTopWords(rows);
        out.printf("single document of class %s, word count of %d, and top words: \n", clazz, n);
        for (int i = 0; i < rows; ++i) {
            out.println(tws);
            out.close();
        }
        return stringWriter.toString();
    }

    public IntDocument intersect(IntDocument that) {
        if (termIndexer != that.termIndexer) throw new IllegalArgumentException();
        List<Integer> index = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
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
        return new IntDocument(index, data, this.termIndexer, "INTERSECTED", -1);
    }

    public IntDocument union(IntDocument that) {
        if (termIndexer != that.termIndexer) throw new IllegalArgumentException();
        List<Integer> index = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
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
        return new IntDocument(index, data, this.termIndexer, "UNION", -1);
    }


    public String getClazz() { return clazz; }

    public int getWordCount() { return n; }

    public int getId() { return id; }

    public List<Pair<String, Integer>> getTopWords(int k) {
        return getTopWords().subList(0, k);
    }

    public List<Pair<String, Integer>> getTopWords() {
        List<Pair<String, Integer>> res = new ArrayList<>();
        for (int i = 0; i < index.length; ++i)  res.add(new Pair<>(termIndexer.getTerm(index[i]), data[i]));
        Collections.sort(res, (o1, o2) -> {
                int cmp = DoubleUtils.compare(o1.getValue(), o2.getValue());
                if (cmp != 0) return -cmp;
                return o1.getKey().compareTo(o2.getKey());
            });
        return res;
    }
}

