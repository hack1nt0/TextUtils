package mlearn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dy[jealousing@gmail.com] on 17-5-21.
 */
public class BagOfWords implements Serializable{
    public ConcurrentIndexer termIndexer;
    public int[] index;
    public String clazz;
    public int id, n;

    public BagOfWords(List<String> words, ConcurrentIndexer termIndexer, String clazz, int id) {
        this.index = new int[words.size()];
        for (int i = 0; i < index.length; ++i) index[i] = termIndexer.getId(words.get(i));
        this.termIndexer = termIndexer;
        this.clazz = clazz;
        this.id = id;
        this.n = index.length;
    }

    public BagOfWords(int[] index, ConcurrentIndexer termIndexer, String clazz, int id) {
        this.index = index;
        this.termIndexer = termIndexer;
        this.clazz = clazz;
        this.id = id;
        this.n = index.length;
    }

    public List<Pair<String, Long>> getTopWords(int wn) {
        List<Pair<Integer, Long>> counter = Counter.from(index).toList();
        List<Pair<String, Long>> res = new ArrayList<>();
        for (int i = 0; i < Math.min(wn, counter.size()); ++i) {
            Pair<Integer, Long> pair = counter.get(i);
            res.add(new Pair<>(termIndexer.getTerm(pair.getKey()), pair.getValue()));
        }
        return res;
    }
}
