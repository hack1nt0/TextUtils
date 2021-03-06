package mlearn.tokenizer;

/**
 * Created by root on 14-9-5.
 */
public abstract class Dictionary {

    public abstract int contains(char[] token, int L, int R);

    public int contains(char[] token) {
        return this.contains(token, 0, token.length);
    }

    public abstract float getLogFreq(int index);
    public abstract float getMinLogFreq();

}

