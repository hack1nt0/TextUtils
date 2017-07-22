package mlearn.tokenizer;

import java.util.List;
import java.util.Set;

/**
 * @author dy[jealousing@gmail.com] on 17-7-20.
 *
 * Polymerization(聚合性), expandability(扩展性)
 *
 * Polym : how likely the char segments to 'combine' to be a new word;
 * Expan : how likely the char segments be a new word in the global view.
 */
public class NewWordFinderPE {
    public int MIN_FREQ = 1;
    public int MIN_LEN = 1, MAX_LEN = 6;
    public double MIN_PLYMRZ = 50.;
    public double MIN_EXPAND = 1.;

    List<String> findNewWord(List<List<String>> txts, Set<String> dictionary) {
        return null;
    }
}
