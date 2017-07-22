package mlearn.tokenizer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dy[jealousing@gmail.com] on 17-7-23.
 */
public class TokenFilter {
    public static List<String> filter(List<Word> words) {
        List<String> result = new ArrayList<>(words.size());
        for (Word word : words) result.add(word.word);
        return result;
    }
}
