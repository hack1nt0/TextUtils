package mlearn.tokenizer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dy[jealousing@gmail.com] on 17-7-23.
 */
public abstract class TokenFilter {
    public static List<String> filterNothing(List<Word> words) {
        List<String> result = new ArrayList<>(words.size());
        for (Word word : words) result.add(word.word);
        return result;
    }

    public abstract List<String> filter(List<Word> words);
}
