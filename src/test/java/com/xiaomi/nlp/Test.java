package com.xiaomi.nlp;

import java.util.BitSet;

/**
 * Created by dy on 15-8-20.
 */
public class Test {
    static abstract class A {
        public A() {System.out.println("A");}
    }
    static class B extends A {
        //public B() {System.out.println("B");}
    }
    public static void main(String[] args) {
        new B();
        BitSet bitSet = new BitSet(0);

        System.out.println(bitSet.nextSetBit(2343242));

        for (int cand = bitSet.nextSetBit(0); cand >= 0; cand = bitSet.nextSetBit(cand + 1)) {
            System.out.println(cand);
        }
    }
}
