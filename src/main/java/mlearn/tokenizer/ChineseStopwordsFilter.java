package mlearn.tokenizer;

import template.debug.ScannerUTF8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author dy[jealousing@gmail.com] on 17-7-27.
 */
public class ChineseStopwordsFilter extends TokenFilter{
    Set<String> stops = new HashSet<>();

    public ChineseStopwordsFilter() {
        ScannerUTF8 in = ScannerUTF8.fromResource("/tokenizer/stopwords/chinese.txt");
        while (!in.isExhausted()) {
            String st = in.nextLine();
            stops.add(st);
        }
        in.close();
    }

    @Override
    public List<String> filter(List<Word> words) {
        List<String> filtered = new ArrayList<>();
        for (Word word : words) {
            if (!stops.contains(word.word)) {
                filtered.add(word.word);
            }
        }
        return filtered;
    }
}
