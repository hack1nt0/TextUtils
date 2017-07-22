package mlearn;

/**
 * @author dy[jealousing@gmail.com] on 17-5-5.
 */
public abstract class Classifier {

    public abstract Classifier fit(Object trains);

    public abstract ClassificationResult classify(Object tests);

}
