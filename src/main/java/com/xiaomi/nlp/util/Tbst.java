package com.xiaomi.nlp.util;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by DY on 15/3/28.
 * Threaded Binary Search Tree, find k-th min and k-th max in O(k), insert and delete in O(lgN)
 * additional constant factor due to rotation for balance
 * motivation: a capacity-restrict priority queue
 */
public class Tbst <E extends Comparable<E>> implements Iterable<E> {

    public static void main(String[] args) {
        Tbst<Integer> tbst = new Tbst<Integer>();
        tbst.add(2);
        tbst.add(1);
        tbst.add(3);
        for (int i : tbst) System.out.print(i + " ");
        System.out.println();
        System.out.println(tbst.first());
        System.out.println(tbst.last());
        tbst.poll();
        tbst.poll();
        System.out.println(tbst.size());
        System.out.println(tbst.first());
        System.out.println(tbst.last());
    }

    public TreeSet<Node<E>> Bst = new TreeSet<Node<E>>();
    public Node<E> firstNode, lastNode;

    public static class Node<E extends Comparable<E>> implements Comparable<Node<E>> {
        E item;
        Node<E> next;
        Node<E> prev;
        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }

        @Override
        public int compareTo(Node<E> o) {
            return item.compareTo(o.item);
        }
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Iterator<Node<E>> fIterator = Bst.iterator();
            @Override
            public boolean hasNext() {
                return fIterator.hasNext();
            }

            @Override
            public E next() {
                return fIterator.next().item;
            }
        };
    }

    public void clear() {
        Bst.clear();
    }

    public E last() {
        return lastNode.item;
    }

    //mimic the priority queue
    public E poll() {
        return this.pollFirst();
    }

    public E pollFirst() {
        Node<E> resNode = Bst.pollFirst();
        resNode.prev.next = resNode.next;
        resNode.next.prev = resNode.prev;
        firstNode = firstNode.next;
        return resNode.item;
    }

    public int size() {
        return Bst.size();
    }

    public boolean add(E nItem) {
        Node<E> nNode = new Node<E>(null, nItem, null);
        boolean res = Bst.add(nNode);
        if (!res) return false;//duplicate

        nNode.prev = Bst.lower(nNode);
        nNode.next = Bst.higher(nNode);
        if (this.size() == 1) {
            lastNode = firstNode = nNode;
            firstNode.prev = lastNode;
            lastNode.next = firstNode;
        } else {
            boolean smallThanFirst = nNode.compareTo(firstNode) < 0;
            boolean largerThanLast = nNode.compareTo(lastNode) > 0;
            if (smallThanFirst) firstNode = nNode;
            if (largerThanLast) lastNode = nNode;
            Node<E> prevNode = smallThanFirst ? lastNode : Bst.lower(nNode);
            Node<E> nextNode = largerThanLast ? firstNode : Bst.higher(nNode);
            nNode.prev = prevNode;
            prevNode.next = nNode;
            nNode.next = nextNode;
            nextNode.prev = nNode;
        }
        return res;
    }

    public E pollLast() {
        Node<E> resNode = Bst.pollLast();
        resNode.prev.next = resNode.next;
        resNode.next.prev = resNode.prev;
        lastNode = lastNode.prev;
        return resNode.item;
    }

    public E first() {
        return firstNode.item;
    }

    public boolean isEmpty() {
        return Bst.isEmpty();
    }
}
