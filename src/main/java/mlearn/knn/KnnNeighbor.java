package mlearn.knn;

import mlearn.Document;
import template.numbers.DoubleUtils;

/**
 * @author dy[jealousing@gmail.com] on 17-5-13.
 */
public class KnnNeighbor implements Comparable<KnnNeighbor> {
    Document to;
    double dist;
    int hash;
    public KnnNeighbor(Document to, double dist) {
        this.to = to;
        this.dist = dist;
    }

    @Override
    public int compareTo(KnnNeighbor o) {
        return DoubleUtils.compare(dist, o.dist);
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        KnnNeighbor neighbor = (KnnNeighbor) o;
//        return to != null ? to.clazz.equals(neighbor.to.clazz) : neighbor.to == null;
//
//    }
//
//    @Override
//    public int hashCode() {
//        if (hash == 0) hash = to != null ? to.clazz.hashCode() : 0;
//        return hash;
//    }
}
