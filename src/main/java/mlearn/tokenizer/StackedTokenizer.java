package mlearn.tokenizer;

import template.concurrency.TaskScheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dy[jealousing@gmail.com] on 17-7-27.
 */
public class StackedTokenizer extends Tokenizer{
    Tokenizer tokenizer = new LmHmmTokenizer();
    TokenFilter tokenFilter = new ChineseStopwordsFilter();

    public List<Word> split0(List<Word> text) {
        throw new UnsupportedOperationException();
    }

    public List<String> split(String text) {
        List<Word> list = new ArrayList<>(1);
        list.add(new Word(text.replaceAll("[\\u0000-\\u0020]+", " ")));
        List<Word> words = tokenizer.split0(list);

        return tokenFilter.filter(words);
    }

    public static List<List<String>> split(List<String> texts) {
        List<String>[] tokens = new List[texts.size()];
        int cores = Runtime.getRuntime().availableProcessors() / 2;
        Runnable[] tasks = new Runnable[cores];
        for (int i = 0; i < cores; ++i) {
            final int from = i * texts.size() / cores;
            final int to = (i + 1) * texts.size() / cores;
            tasks[i] = () -> {
                Tokenizer tokenizer = new StackedTokenizer();
                for (int r = from; r < to; ++r) {
                    String text = texts.get(r);
                    tokens[r] = tokenizer.split(text);
                }
            };
        }
        TaskScheduler.parallel(tasks);
        return Arrays.asList(tokens);
    }
}
