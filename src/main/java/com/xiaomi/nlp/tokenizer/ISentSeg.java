package com.xiaomi.nlp.tokenizer;

import java.util.List;

/**
 * Created by dy on 15-9-23.
 */
public interface ISentSeg {
    public static class Sentence {
        public List<String> words;
        public String punc;

        public Sentence(List<String> words, String punc) {
            this.words = words;
            this.punc = punc;
        }

        @Override
        public String toString() {
            StringBuffer res = new StringBuffer("");
            for (int i = 0; i < words.size(); ++i)
                res.append(words.get(i) + (i < words.size() - 1 ? " " : punc));
            return res.toString();
        }
    }

    public List<Sentence> getSents(String text);
}
