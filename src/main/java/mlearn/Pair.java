package mlearn;

/**
 * @author dy[jealousing@gmail.com] on 17-5-9.
 */

/**
 * Uncomparable tuple2, with double value truncated to 3 float digits.
 */
public class Pair<K, V> {
    K key;
    V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() { return key; }

    public V getValue() { return value; }

    @Override
    public String toString() {
        return "(" + key + ':' + value + ')';
    }
}
