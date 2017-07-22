package mlearn.r;

import mlearn.hierarchical.BottomUpClustering;
import org.rosuda.JRI.Rengine;

/**
 * @author dy[jealousing@gmail.com] on 17-5-23.
 */
public class R {
    public static Rengine rengine = new Rengine(new String[] { "--vanilla" }, false, null);
    static {
        rengine.eval("Sys.setenv('JAVAGD_CLASS_NAME'='template/ml/r/RjavaGDInterface')");
        rengine.eval("library(JavaGD)");
        rengine.eval("JavaGD()");
    }

    public static void plot(int[] xs) {
        rengine.eval("plot(xs)");
    }

    public static void plot(BottomUpClustering bottomUpClustering) {
        /**
         * $ merge      : int [1:49, 1:2] -15 -17 -14 -13 -35 -36 -7 -19 -49 -21 ...
         $ height     : num [1:49] 2.29 3.83 3.93 6.24 6.64 ...
         $ order      : int [1:50] 9 33 8 1 18 2 24 40 20 3 ...
         $ labels     : chr [1:50] "Alabama" "Alaska" "Arizona" "Arkansas" ...
         $ Linkage     : chr "complete"
         $ call       : language hclust(d = dist(USArrests))
         $ dist.Linkage: chr "euclidean"
         - attr(*, "class")= chr "hclust"
         */
        int n = bottomUpClustering.n;
        int[][] child = bottomUpClustering.child;
        int nclust = n - 1;
        int[] merge = new int[nclust * 2];
        for (int i = 0; i < merge.length; ++i) {
            merge[i] = bottomUpClustering.child[i / 2][i % 2];
            if (merge[i] >= 0) merge[i]++;
        }
        int[] index = new int[n];
        int[] stack = new int[n - 1];
        stack[0] = n - 2;
        int t = 1, ii = 0;
        while (t > 0) {
            int ci = stack[--t];
            for (int i = 0; i < 2; ++i) {
                if (child[ci][i] < 0) index[ii++] = -child[ci][i];
                else stack[t++] = child[ci][i];
            }
        }
        if (ii != n) {
            throw new RuntimeException();
        }

        rengine.eval("hc <- list()");
        rengine.assign("merge", merge);
        rengine.eval("merge <- matrix(merge, nrow=" + nclust + ", ncol=2, byrow=TRUE)");
        rengine.assign("height", bottomUpClustering.height);
        rengine.assign("order", index);
        rengine.assign("labels", bottomUpClustering.labels);
        rengine.assign("method", bottomUpClustering.linkage);
        rengine.assign("call", bottomUpClustering.call);
        rengine.assign("dist.method", bottomUpClustering.distFunction);
        rengine.eval("hc$merge <- merge");
        rengine.eval("hc$height <- height");
        rengine.eval("hc$order <- order");
        rengine.eval("hc$labels <- labels");
        rengine.eval("hc$method <- method");
        rengine.eval("hc$call <- call");
        rengine.eval("hc$dist.method <- dist.method");
        rengine.eval("attr(hc, 'class') <- 'hclust'");
        System.out.println(rengine.eval("class(hc)"));
        rengine.eval("plot(hc, hang=-1)");
    }

    public static void main(String[] args) {
        int[] xs = {1,3, 3, 4, 5, 3, 2};
        R.plot(xs);
    }
}
