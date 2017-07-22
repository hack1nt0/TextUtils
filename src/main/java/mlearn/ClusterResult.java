package mlearn;

import java.util.List;

/**
 * @author dy[jealousing@gmail.com] on 17-5-14.
 */
public abstract class ClusterResult {
    public abstract int size();

    public abstract Object getTopic(int clusterId);

    public abstract int getMembers(int clusterId);

    public abstract List<Object> getPartitions();

    public abstract double getHeterogeneity();

    public abstract double getHomogenicity();
}
