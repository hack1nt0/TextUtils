package mlearn.tokenizer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dy[jealousing@gmail.com] on 17-7-23.
 */
public class BracketEntityRecognizer extends EntityRecognizer {
    private static String LEFT_BRACKETS = "【[(<";
    private static String RIGHT_BRACKETS = "】])>";

    @Override
    public List<Word> split(List<Word> text) {
        List<Word> words = new ArrayList<>();
        for (Word word : text) {
            if (word.source != null) {
                words.add(word);
                continue;
            }
            StringBuffer buf = new StringBuffer();
            boolean findL = false;
            String segment = word.word;
            for (int i = 0; i < segment.length(); ++i) {
                char c = segment.charAt(i);
                if (!findL && LEFT_BRACKETS.indexOf(c) >= 0) {
                    findL = true;
                    if (buf.length() > 0) {
                        words.add(new Word(buf.toString()));
                        buf.setLength(0);
                    }
                    buf.append(c);
                    continue;
                }
                buf.append(c);
                if (findL && RIGHT_BRACKETS.indexOf(c) >= 0)  {
                    findL = false;
                    words.add(new Word(buf.toString(), Word.SOURCE.ENTITY));
                    buf.setLength(0);
                    continue;
                }
                if (i == segment.length() - 1 && buf.length() > 0) {
                    words.add(new Word(buf.toString()));
                }
            }
        }
        return words;
    }
}
