package com.xiaomi.nlp.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dy on 15-5-28.
 */
public class LazyList<T> implements Iterable<T>{
    public List<T> innerList = null;

    public LazyList() {}

    public LazyList(List<T> o){
        innerList = o;
    }

    public boolean add(T o) {
        if (innerList == null)
            innerList = new ArrayList<T>();
        return innerList.add(o);
    }

    public T get(int cur) {
        return innerList.get(cur);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Iterator<T> iterator = innerList == null ? null : innerList.iterator();

            public boolean hasNext() {
                if (innerList == null) return false;
                return iterator.hasNext();
            }

            @Override
            public T next() {
                return iterator.next();
            }
        };
    }

    public int size() {
        return innerList == null ? 0 : innerList.size();
    }
}
