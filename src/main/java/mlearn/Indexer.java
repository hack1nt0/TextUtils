package mlearn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author dy[jealousing@gmail.com] on 17-5-7.
 */
public class Indexer {
    HashMap<String, Integer> mapToId = new HashMap<>();
    ArrayList<String> mapToTerm = new ArrayList<>();

    public int getId(String term) { return mapToId.get(term); }

    public synchronized int getOrAdd(String term) {
        if (!mapToId.containsKey(term)) {
            mapToId.put(term, mapToId.size());
            mapToTerm.add(term);
            return mapToId.size() - 1;
        }
        return mapToId.get(term);
    }

    public String getTerm(int id) { return mapToTerm.get(id); }

    public int size() { return mapToId.size(); }

    public boolean containsTerm(String term) {
        return mapToId.containsKey(term);
    }

    public static Indexer of(List<String> terms) {
        Indexer indexer = new Indexer();
        for (String t : terms) indexer.getOrAdd(t);
        return indexer;
    }
}
