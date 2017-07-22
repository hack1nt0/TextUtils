package mlearn.tokenizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DY on 15/8/26.
 */
public abstract class Tokenizer {

    public abstract List<Word> split(List<Word> text);

    public List<String> split(String text) {
        List<Word> list = new ArrayList<>(1);
        list.add(new Word(text.replaceAll("[\\u0000-\\u0020]+", " ")));
        return TokenFilter.filter(split(list));
    }
}
