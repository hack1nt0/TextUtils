package com.xiaomi.nlp.util;

import com.xiaomi.nlp.tokenizer.MyTokenizer;

import java.io.*;
import java.util.*;

/**
 * Created by dy on 15-5-12.
 */
public class Lda {
    int M, N, K;

    List<List<String>> docs;
    Map<String, Double>[] wn; // word number of topic
    Map<Integer, Double>[] tn; //topic number of docs
    double[] sumWn;
    double[] sumTn;

    Map<String, Double> beta;
    Map<Integer, Double> alpha;

    //to inference
    Map<String, Double>[] phi; //prob of word of topic
    Map<Integer, Double>[] theta; // prob of topic of doc
    //to sample
    int[][] z; // topic of word of doc

    public Lda(String filePath, int K) {
        docs = new ArrayList<List<String>>();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            MyTokenizer tokenizer = MyTokenizer.getInstance();
            while (true) {
                String line = in.readLine();
                if (line == null) break;
                String text = line.split("\\t")[2];
                docs.add(tokenizer.getTokens(text));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.K = K;
        this.M = docs.size();
        wn = new HashMap[K];
        for (int i = 0; i < wn.length; ++i) wn[i] = new HashMap<String, Double>();
        tn = new HashMap[M];
        for (int i = 0; i < tn.length; ++i) tn[i] = new HashMap<Integer, Double>();
        Set<String> words = new HashSet<String>();
        for (List<String> doc: docs)
            for (String word: doc) words.add(word);
        this.N = words.size();
        beta = new HashMap<String, Double>();
        alpha = new HashMap<Integer, Double>();
        for (String word: words) beta.put(word, 1.0);
        for (int i = 0; i < K; ++i) alpha.put(i, 1.0);

        phi = new HashMap[K];
        for (int i = 0; i < phi.length; ++i) phi[i] = new HashMap<String, Double>();
        theta = new HashMap[M];
        for (int i = 0; i < theta.length; ++i) theta[i] = new HashMap<Integer, Double>();
        z = new int[M][];
        for (int i = 0; i < M; ++i) {
            z[i] = new int[docs.get(i).size()];
            for (int j = 0; j < z[i].length; ++j) {
                int k = (int) (Math.random() * K);
                z[i][j] = k;
                String w = docs.get(i).get(j);
                wn[k].put(w, get(wn[k], w) + 1);
                tn[i].put(k, get(tn[i], k) + 1);
            }
        }
        sumWn = new double[K];
        sumTn = new double[M];
        for (int i = 0; i < K; ++i) sumWn[i] = sum(wn[i], beta);
        for (int i = 0; i < M; ++i) sumTn[i] = sum(tn[i], alpha);


        System.out.println("N " + N);
        System.out.println("M " + M);
        System.out.println("K " + K);
    }

    public static <K, V extends Number> V get(Map<K, V> map, K key) {
        return map.containsKey(key) ? map.get(key) : (V) new Double(0);
    }

    public void samplez(int maxIter) {
        //collapsed theta and phi
        for (int iter = 0; iter < maxIter; ++iter) {
            System.out.println(iter);
            if (iter % 50 == 0) {
                toc("iter" + iter);
            }

            for (int i = 0; i < M; ++i)
                for (int j = 0; j < z[i].length; ++j) {
                    int curK = z[i][j];
                    String curWord = docs.get(i).get(j);
                    wn[curK].put(curWord, wn[curK].get(curWord) - 1);
                    tn[i].put(curK, tn[i].get(curK) - 1);
                    --sumWn[curK];
                    --sumTn[i];

                    double[] pk = new double[K];
                    for (int k = 0; k < K; ++k)
                        pk[k] = (get(wn[k], curWord) + beta.get(curWord)) / sumWn[k] *
                                (get(tn[i], k) + alpha.get(k)) / sumTn[i];
                    for (int k = 1; k < K - 1; ++k) {
                        pk[k] += pk[k - 1];
                    }
                    pk[K - 1] = 1;
                    double sample = Math.random();
                    int newK = -1;
                    for (int k = K - 1; k >= 0; --k)
                        if (sample <= pk[k]) newK = k;

                    z[i][j] = newK;
                    wn[newK].put(curWord, get(wn[newK], curWord) + 1);
                    tn[i].put(newK, get(tn[i], newK) + 1);
                    ++sumWn[newK];
                    ++sumTn[i];
                }
        }

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data/thetaAndPhi"));
            //collapsed w and z
            //inference theta and phi
            for (int i = 0; i < M; ++i) {
                double base = sum(alpha, tn[i]);
                for (int k = 0; k < K; ++k) {
                    theta[i].put(k, (alpha.get(k) + get(tn[i], k)) / base);
                }
                out.writeObject(theta[i]);
            }
            for (int k = 0; k < K; ++k) {
                double base = sum(beta, wn[k]);
                for (String w : beta.keySet()) {
                    phi[k].put(w, (beta.get(w) + get(wn[k], w)) / base);
                }
                out.writeObject(phi[k]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double sum(Map A, Map B) {
        double res = 0;
        for (Object key: A.keySet())
            res += (Double)get(A, key) + (Double)get(B, key);
        return res;
    }

    static class Tuple2 implements Comparable<Tuple2> {
        String k;
        double v;

        public Tuple2(String k, double v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public int compareTo(Tuple2 o) {
            if (v > o.v) return 1;
            if (v == o.v) return 0;
            return -1;
        }

        @Override
        public String toString() {
            return k + "\t" + v;
        }
    }

    private static long startTime;
    public static void tic() {
        startTime = System.currentTimeMillis();
    }
    public static void toc(String msg) {
        long endTime = System.currentTimeMillis();
        System.out.println(msg + " consumed " + (endTime - startTime) / 1000.0 + "s");
        startTime = endTime;
    }

    public static void main(String[] args) {
        int K = 10, MAX_ITER = 1000;
        tic();
        Lda lda = new Lda("data/allCorpus001.txt.UTF-8", K);
        toc("initial");
        tic();
        lda.samplez(MAX_ITER);
        toc("samplez");
        int LIMIT = 10;
        for (int i = 0; i < K; ++i) {
            PriorityQueue<Tuple2> priorityQueue = new PriorityQueue<Tuple2>();
            for (Map.Entry<String, Double> entry: lda.phi[i].entrySet()) {
                Tuple2 cand = new Tuple2(entry.getKey(), entry.getValue());
                if (priorityQueue.size() < LIMIT) {
                    priorityQueue.add(cand);
                    continue;
                }
                if (priorityQueue.peek().compareTo(cand) < 0) {
                    priorityQueue.poll();
                    priorityQueue.add(cand);
                }
            }
            System.out.println("K = " + i + ":");
            while (!priorityQueue.isEmpty()) {
                System.out.println(priorityQueue.poll());
            }
            System.out.println("-------------------------");
        }
//
//        Map<String, Integer> map = new HashMap<String, Integer>();
//        map.put("1", 1);
//        System.out.println(get(map, "1"));
//        System.out.println(get(map, ""));
    }
}
