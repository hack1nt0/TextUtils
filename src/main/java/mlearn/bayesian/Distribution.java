package mlearn.bayesian;

/**
 * @author dy[jealousing@gmail.com] on 17-7-23.
 */
public abstract class Distribution<T> {

    public abstract double pdf(T i);

    public abstract T sample();
}
