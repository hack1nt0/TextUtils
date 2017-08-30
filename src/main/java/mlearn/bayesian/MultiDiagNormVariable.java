package mlearn.bayesian;

import mlearn.Document;
import template.debug.RandomUtils;
import template.debug.Stopwatch;

/**
 * @author dy[jealousing@gmail.com] on 17-5-27.
 */
public class MultiDiagNormVariable {
    double[] mean, variance;
    double[] inverse;
    double determinant; //log
    int n;
    double PDF_CONSTANT_DIVIDEND; //log


    //random instance
    public MultiDiagNormVariable(int n) {
        this.n = n;
        this.mean = new double[n];
        this.variance = new double[n];
        for (int i = 0; i < n; ++i) {
            mean[i] = RandomUtils.uniform();
            variance[i] = RandomUtils.uniform(1,50);
        }
        this.inverse = new double[n];
        this.determinant = 0;
        for (int i = 0; i < n; ++i) {
            this.inverse[i] = 1.0 / variance[i];
            this.determinant += Math.log(variance[i]);
        }
        this.PDF_CONSTANT_DIVIDEND = (n * Math.log(2 * Math.PI) + determinant) / -2;
    }

    public void setMean(double[] mean) {
        assert mean.length == n;
        System.arraycopy(mean, 0, this.mean, 0, n);
    }

    public void setVariance(double[] variance) {
        assert variance.length == n;
        System.arraycopy(variance, 0, this.variance, 0, n);
        this.determinant = 0;
        for (int i = 0; i < n; ++i) {
            this.inverse[i] = 1.0 / variance[i];
            this.determinant += Math.log(variance[i]);
        }
        this.PDF_CONSTANT_DIVIDEND = (n * Math.log(2 * Math.PI) + determinant) / -2;
    }

    public void update() {
        this.n = mean.length;
        this.determinant = 0;
        for (int i = 0; i < n; ++i) {
            this.inverse[i] = 1.0 / variance[i];
            this.determinant += Math.log(variance[i]);
        }
        this.PDF_CONSTANT_DIVIDEND = (n * Math.log(2 * Math.PI) + determinant) / -2;
    }

    public double pdf(double[] x) {
        double exponent = 0;
        for (int i = 0; i < n; ++i) exponent += (x[i] - mean[i]) * inverse[i] * (x[i] - mean[i]);
        return -exponent / 2 + PDF_CONSTANT_DIVIDEND;
    }

    public double pdf(Document d) {
        assert d.termIndexer.size() == n;
        double exponent = 0;
        int di = 0, i = 0;
        while (i < n) {
            if (di < d.size && i == d.index[di]) {
                exponent += (d.data[di] - mean[i]) * inverse[i] * (d.data[di] - mean[i]);
                di++;
                i++;
            } else {
                exponent += mean[i] * inverse[i] * mean[i];
                i++;
            }
        }
//        if (-exponent / 2 + PDF_CONSTANT_DIVIDEND > 0) {
//            di = i = 0;
//            exponent = 0;
//            while (i < capacity) {
//                if (di < d.capacity && i == d.index[di]) {
//                    exponent += (d.data[di] - mean[i]) * inverse[i] * (d.data[di] - mean[i]);
//                    di++;
//                    i++;
//                } else {
//                    exponent += mean[i] * inverse[i] * mean[i];
//                    i++;
//                }
//                System.out.println(exponent);
//            }
//            throw new RuntimeException();
//        }
        double logProb = -exponent / 2 + PDF_CONSTANT_DIVIDEND;
        if (logProb > 0) {
            //System.out.println(logProb + " " + Math.exp(logProb));
            //logProb = 0;
        }
        return logProb;
    }

    public static void main(String[] args) {
        MultiDiagNormVariable norm = new MultiDiagNormVariable(1);
        System.out.println(norm.pdf(new double[]{0}));
        System.out.println(norm.pdf(new double[]{1}));
        System.out.println(norm.pdf(new double[]{0.5}));
        System.out.println(norm.pdf(new double[]{-0.001}));
        Stopwatch.tic();
        System.out.println(norm.pdf(new double[]{3}));
        Stopwatch.toc();
    }
}
