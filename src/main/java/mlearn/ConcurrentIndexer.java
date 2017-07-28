package mlearn;

import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dy[jealousing@gmail.com] on 17-5-7.
 */
public class ConcurrentIndexer implements Serializable{
    Object writeLock = new Object();
    ConcurrentHashMap<String, Integer> mapToId = new ConcurrentHashMap<>();
    public ArrayList<String> mapToTerm = new ArrayList<>();
    //ConcurrentSkipListMap<String, Integer> mapToId = new ConcurrentSkipListMap<>(); //RACE CONDITION? NO stopping

    public int getOrAdd(String term) {
        if (!mapToId.containsKey(term)) {
            synchronized (writeLock) {
                if (!mapToId.containsKey(term)) {
                    mapToId.put(term, mapToId.size());
                    mapToTerm.add(term);
                    return mapToId.size() - 1;
                }
            }
        }
        return mapToId.get(term);
    }

    public void put(String term) {
//        if (mapToId.containsKey(term)) throw new RuntimeException("duplicated " + term);
        mapToId.put(term, mapToId.size());
        mapToTerm.add(term);
    }

    public int size() { return mapToTerm.size(); }

    public boolean containsTerm(String term) {
        return mapToId.containsKey(term);
    }

    public int getId(String term) {
        int id = mapToId.get(term);
        if (!mapToTerm.get(id).equals(term)) throw new RuntimeException();
        return id;
    }

    public String getTerm(int id) {
        String w = mapToTerm.get(id);
//        if (mapToId.get(w) != id) throw new RuntimeException(id + " != " + mapToId.get(w));
        return w;
    }


}
