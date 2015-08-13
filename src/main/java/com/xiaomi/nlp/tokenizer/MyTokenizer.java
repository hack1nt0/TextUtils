package com.xiaomi.nlp.tokenizer;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dy on 14-11-20.
 */

public class MyTokenizer {
    private static MyTokenizer INSTANCE;
    private static Dict dict;
    private static HMM hmm;
    private static Logger logger = Logger.getLogger(MyTokenizer.class);

    public static MyTokenizer getInstance() {
        if (INSTANCE == null) INSTANCE = new MyTokenizer();
        return INSTANCE;
    }

    private MyTokenizer() {
        dict = new AlphabeticalOrderArray("com/xiaomi/nlp/tokenizer/jieba.dict.utf8.sorted");
        hmm =  new HMM("com/xiaomi/nlp/tokenizer/hmm_model.utf8");
    }

    protected MyTokenizer(String dictFilePath, String HMMFilePath) {
        dict = new AlphabeticalOrderArray(dictFilePath);
        //dict = new Trie(dictFilePath);
        hmm = new HMM(HMMFilePath);
    }

    public String[] getTokens(String text) {
        List<String> ret = new ArrayList<String>();
        char[] arr = text.toCharArray();
        int N = arr.length;

        Double[] dp = new Double[N + 1];
        dp[N] = 0.0;
        int[] nxt = new int[N + 1];
        for (int i = N - 1; i >= 0; --i) nxt[i] = i + 1;
        for (int i = N - 1; i >= 0; --i) {
            dp[i] = dict.getMinLogFreq() * 2 + dp[i + 1];
            //dp[i] = Double.NEGATIVE_INFINITY; todo no sense
            for (int j = i + 1; j <= N; ++j) {
                int index = dict.contains(arr, i, j);
                if (index == -1) continue;
                Double tmp = dict.getLogFreq(index) + dp[j];
                if (tmp > dp[i]) {
                    nxt[i] = j;
                    dp[i] = tmp;
                }
            }
        }
        for (int i = 0; i < N;) {
            if (nxt[i] == i + 1) {
                int j = i;
                for (; j < N && nxt[j] == j + 1; ++j);
                for (int l = i, r = i; l < j;) {
                    while (r < j && !isASCII(arr[r])) ++r;
                    if (l < r) ret.addAll(hmm.getTokens(arr, l, r));
                    l = r;
                    while (r < j && isASCII(arr[r])) ++r;
                    if (l < r && l < j) ret.add(new String(arr, l, r - l));
                    l = r;
                }
                i = j;
                continue;
            }
            ret.add(new String(arr, i, nxt[i] - i));
            i = nxt[i];
        }
        return ret.toArray(new String[0]); //ToDO
    }

    private boolean isASCII(char c) {
        return 0 <= c && c < 128;
    }

    public boolean inDict(String token){
        int index = dict.contains(token.toCharArray(), 0, token.length());
        return index >= 0;
    }

    public void destroy() {
        INSTANCE = null;
    }
}

class HMM {
    private static Logger logger = Logger.getLogger(HMM.class);
    int YN = Character.MAX_VALUE + 1;
    int XN = 4;
    double[] PPx = new double[XN];
    double[][] Pxixj = new double[XN][XN];
    double[][] Pxiyi = new double[XN][YN];
    double[][] dp; //= new double[YN][XN];
    int[][] path; //= new int[YN][XN];
    String decode = "BEMS";
    //B E M S
    int[][] autoM = {
            {1, 0, 0, 0},
            {2, 0, 1, 2},
    };
    static double MINVALUE = -3.14e100;

    public HMM(String filePath) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(HMM.class.getClassLoader().getResourceAsStream(filePath)));
            long beforeM = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            long beforeT = System.currentTimeMillis();

            while (true) {
                String line = in.readLine();
                if (line == null) break;
                if (line.charAt(0) == '#') continue;
                String[] tmp = line.split(" ");
                for (int i = 0; i < PPx.length; ++i) PPx[i] = Double.valueOf(tmp[i]);
                line = in.readLine();
                for (int i = 0; i < XN; ++i) {
                    line = in.readLine();
                    tmp = line.split(" ");
                    for (int j = 0; j < XN; ++j) Pxixj[i][j] = Double.valueOf(tmp[j]);
                }

                for (int i = 0; i < XN;) {
                    Arrays.fill(Pxiyi[i], HMM.MINVALUE);
                    line = in.readLine();
                    if (line == null) break;
                    if (line.charAt(0) == '#') continue;
                   // StringReader sr = new StringReader(line);
                    for (int j = 0; j < line.length();) {
                        char y = line.charAt(j);
                        j += 2;
                        int k = j;
                        while (k < line.length() && line.charAt(k) != ',') ++k;
                        Double logFreq = Double.valueOf(line.substring(j, k));
                        Pxiyi[i][y] = logFreq;
                        j = k > line.length() ? k : k + 1;
                    }
                    ++i;
                }
            }
            in.close();
            System.gc();
            long afterM = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            long afterT = System.currentTimeMillis();
            double usedM = (afterM - beforeM) / 1024.0 / 1024;
            double usedT = (afterT - beforeT) / 1000.0;
            System.out.println("Loaded HMM Model.");
            System.out.println("Consumed Time: " + usedT + "s");
            System.out.println("Consumed Mem: " + usedM + "MB");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getTokens(char[] text, int L, int R) {
        if (dp == null || dp.length < text.length) dp = new double[text.length][XN];
        if (path == null || path.length < text.length) path = new int[text.length][XN];

        List<String> ret = new ArrayList<String>();
        int M = R - L;
        //Arrays.fill(dp[M], 0.0);
        for (int i = 0; i < M; ++i) {
            if (i == 0) {
                for (int j = 0; j < XN; ++j) dp[i][j] = PPx[j] + Pxiyi[j][text[i + L]];
                continue;
            }
            for (int j = 0; j < XN; ++j) {
                Double res = HMM.MINVALUE;
                for (int k = 0; k < XN; ++k) {
                    //if (dp[i + 1][k] == HMM.MINVALUE || Pxixj[j][k] == HMM.MINVALUE) continue;
                    Double tmp = dp[i - 1][k] + Pxixj[k][j];
                    if (tmp > res) {
                        res = tmp;
                        path[i][j] = k;
                    }
                }
                if (res == HMM.MINVALUE || Pxiyi[j][text[i + L]] == HMM.MINVALUE) {
                //if (res == HMM.MINVALUE) {
                    //int maxPPxi = 0;
                    //for (int k = 0; k < XN; ++k) if (PPx[maxPPxi] < PPx[k]) maxPPxi = k;
                    path[i][j] = 1; //E
                    //res = HMM.MINVALUE;
                }
                dp[i][j] = res + Pxiyi[j][text[i + L]];
            }
        }
        //for (int i = 0; i < XN; ++i) dp[0][i] += PPx[i];
        int xi = dp[M - 1][1] <= dp[M - 1][3] ? 3 : 1;
        //for (int i = 0; i < XN; ++i) if (dp[0][i] > dp[0][xi]) xi = i;

        int[] xs = new int[M]; xs[M - 1] = xi;
        for (int i = M - 2; i >= 0; --i) xs[i] = path[i + 1][xs[i + 1]];

        //for (int i = 0; i < M; ++i) System.out.print(decode.charAt(xs[i]));
        //System.out.println();

        for (int i = 0, autoS = 0; i < M;) {
            autoS = autoM[autoS][xs[i]];
            int j = i + 1;
            while (j < M && autoS == 1) {
                autoS = autoM[autoS][xs[j]];
                ++j;
            }

            if (autoS == 0) {
                ret.add(new String(text, L + i, j - i));
                i = j;
            } else if (j >= M) {//TODO
                for (int k = i; k < M; ++k) ret.add(new String(text, L + k, 1));
                break;
            } else {
                for (int k = i; k < j - 1; ++k) ret.add(new String(text, L + k, 1));
                i = j - 1;
            }
            autoS = 0;
        }
        return ret;
    }
}
