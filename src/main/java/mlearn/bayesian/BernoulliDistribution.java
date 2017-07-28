package mlearn.bayesian;

import template.debug.RandomUtils;

/**
 * @author dy[jealousing@gmail.com] on 17-7-23.
 */
public class BernoulliDistribution<T> extends Distribution<T>{
    public double p;
    public T a, b;

    public BernoulliDistribution(double p, T a, T b) {
        this.p = p;
        this.a = a;
        this.b = b;
    }

    @Override
    public double pdf(T i) {
        return i.equals(a) ? p : 1 - p;
    }

    @Override
    public T sample() {
        return RandomUtils.uniform() < p ? a : b;
    }
}
