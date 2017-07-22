package mlearn.tokenizer;

/**
 * @author dy[jealousing@gmail.com] on 17-7-22.
 */
public class Word {
    public String word;
    public enum SOURCE {ENTITY, DICTIONARY, ASCII, HMM};
    public SOURCE source; //ENTITY, DICT, ASCII, HMM

    public Word(String word) {
        this.word = word;
    }

    public Word(String word, SOURCE source) {
        this.source = source;
        this.word = word;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("");
        sb.append("-----------\n");
        if (word.length() == 1) return sb.append(word + "\tS\t" + source + "\n").toString();
        for (int i = 0; i < word.length(); ++i) {
            if (i == 0) sb.append(word.charAt(i) + "\tB\t" + source + "\n");
            else if (i == word.length() - 1) sb.append(word.charAt(i) + "\tE\n");
            else sb.append(word.charAt(i) + "\tM\n");
        }
        return sb.toString();
    }
}
