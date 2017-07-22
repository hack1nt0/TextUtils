package mlearn;

import java.util.*;

/**
 * @author dy[jealousing@gmail.com] on 17-5-10.
 */
public class Counter<K> {
    HashMap<K, Long> map = new HashMap<>();

    public long add(K key) {
        long old = map.getOrDefault(key, 0L);
        map.put(key, old + 1);
        return old + 1;
    }

    public void add(K key, long delta) { map.put(key, get(key) + delta); }

    public Long get(K key) { return map.getOrDefault(key, 0L); }

    @Override
    public String toString() {
        return reverse().toString();
    }

    public TreeMap<Long, List<K>> reverse() {
        TreeMap<Long, List<K>> res = new TreeMap<>(Collections.reverseOrder());
        for (K key : map.keySet()) {
            Long old = map.get(key);
            List<K> list = res.getOrDefault(old, new ArrayList<K>());
            list.add(key);
            res.put(old, list);
        }
        return res;
    }

    public List<Pair<K, Long>> toList() {
        List<Pair<K, Long>> res = new ArrayList<>();
        for (Map.Entry<K, Long> entry : map.entrySet()) res.add(new Pair<K, Long>(entry.getKey(), entry.getValue()));
        Collections.sort(res, (i, j) -> {
            int cmp = i.getValue().compareTo(j.getValue());
            if (cmp != 0) return -cmp;
            return i.getKey().toString().compareTo(j.getKey().toString());
        });
        return res;
    }

    public int size() {
        return map.size();
    }

    public static <K> Counter<K> from(K[] terms) {
        Counter<K> counter = new Counter<K>();
        for (K term : terms) counter.add(term);
        return counter;
    }

    public static Counter<Integer> from(int[] terms) {
        Counter<Integer> counter = new Counter<>();
        for (int term : terms) counter.add(term);
        return counter;
    }

    public static <K> Counter<K> from(List<K> terms) {
        Counter<K> counter = new Counter<K>();
        for (K term : terms) counter.add(term);
        return counter;
    }
}
