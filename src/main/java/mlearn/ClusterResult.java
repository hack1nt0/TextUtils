package mlearn;

import java.util.List;

/**
 * @author dy[jealousing@gmail.com] on 17-5-14.
 */
public abstract class ClusterResult {
    public abstract int size();

    public abstract Object getTopic(int index);

    public abstract int capacity(int index);

    // Return members of specified cluster.
    public abstract List<Integer> get(int index);

    public abstract double getHeterogeneity();

    public abstract double getHomogenicity();
}
