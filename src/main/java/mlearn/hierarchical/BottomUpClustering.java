package mlearn.hierarchical;

import mlearn.ClusterResult;
import mlearn.Clusterer;
import mlearn.DocumentTermMatrix;
import mlearn.FileUtils;
import mlearn.r.R;
import template.collection.sets.UnionFind;
import template.debug.Stopwatch;
import template.numbers.DoubleUtils;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * @author dy[jealousing@gmail.com] on 17-5-22.
 *
 *
 */
public class BottomUpClustering extends Clusterer {

    //for cluster
    public int[][] child;
    public double[] height;

    //for data point
    public int n;
    public String[] labels;

    public String linkage = "min";
    public String call = "BottomUpClustering(data=dtm, distFunction='euclidean', linkage='min')";
    public String distFunction = "dot distance";

    @Override
    public ClusterResult cluster(Object data) {
        throw new UnsupportedOperationException();
    }

    public ClusterResult cluster(DocumentTermMatrix dtm) {
        this.n = dtm.rows();
        labels = dtm.getClazz();
        child = new int[n - 1][2];
        height = new double[n - 1];
        IntStream.range(0, n).parallel().forEach(di -> dtm.get(di).normalize());

        PriorityQueue<Path> heap = new PriorityQueue<Path>();
        for (int to = 1; to < n; ++to) heap.add(new Path(0, to, dtm.get(0).euclideanDistance(dtm.get(to))));
        System.out.println('h');
        boolean[] visited = new boolean[n];
        visited[0] = true;
        Path[] mst = new Path[n - 1];
        int pathIndex = 0;
        for (int e = 0; e < n - 1; ++e) {
            Path minPath = heap.poll();
            while (visited[minPath.to]) minPath = heap.poll();
            mst[pathIndex++] = minPath;
            int from = minPath.to;
            visited[from] = true;
            for (int to = 0; to < n; ++to) if (!visited[to]) heap.add(new Path(from, to, dtm.get(from).euclideanDistance(dtm.get(to))));
            System.out.println(e);
        }
        System.out.println('h');
        Arrays.sort(mst);
        UnionFind uf = new UnionFind(n);
        int[] clazz = new int[n];
        Arrays.fill(clazz, -1);
        for (int ci = 0; ci < mst.length; ++ci) {
            Path path = mst[ci];
            int l = uf.find(path.from);
            int r = uf.find(path.to);
            child[ci][0] = clazz[l] == -1 ? -path.from - 1 : clazz[l];
            child[ci][1] = clazz[r] == -1 ? -path.to - 1 : clazz[r];
            uf.union(l, r);
            clazz[uf.find(l)] = ci;
            height[ci] = path.dist;
        }
        return null;
    }

    private class Path implements Comparable<Path>{
        int from, to;
        double dist;
        public Path(int from, int to, double dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
        @Override
        public int compareTo(Path o) {
            return DoubleUtils.compare(dist, o.dist);
        }
    }

    public static void main(String[] args) {
//        List<String>[] csv = FileUtils.readCsv("/Users/dy/Downloads/people_wiki.csv", true);
//        DocumentTermMatrix trains = new DocumentTermMatrix(csv[2].subList(0, 100));
//        System.out.println(trains);
//        BottomUpClustering bottomUpClustering = new BottomUpClustering();
//        Stopwatch.tic();
//        bottomUpClustering.cluster(trains);
//        Stopwatch.toc();
//        R.plot(bottomUpClustering);
    }
}
