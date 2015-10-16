package com.xiaomi.nlp.tokenizer;

import com.xiaomi.nlp.util.ACAutomation;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by dy on 14-11-20.
 */

public class MyTokenizer implements ITokenizer {
    private static MyTokenizer INSTANCE;
    private static Dict dict;
    private static HMM hmm;
    private static Logger logger = Logger.getLogger(MyTokenizer.class);
    private static ACAutomation acAutomation;
    private static String strongDictPath = "com/xiaomi/nlp/tokenizer/strong-dict.txt";
    private static List<String> ps = new ArrayList<String>();
    private static HashSet<String> stopwords;
    private static String stopwordDictPath = "com/xiaomi/nlp/tokenizer/chinese-stopwords.txt";

    static {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(MyTokenizer.class.getClassLoader().getResourceAsStream(strongDictPath)));
            while (true) {
                String line = in.readLine();
                if (line == null) break;
                ps.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        acAutomation = new ACAutomation(ps);
    }

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

    public static HMM getHmm() {
        return hmm;
    }

    public static void setHmm(HMM hmm) {
        MyTokenizer.hmm = hmm;
    }

    private static String lBrackets = "【[(<";
    private static String rBrackets = "】])>";

    public List<String> removeStopwords(List<String> raw) {
        if (stopwords == null) {
            stopwords = new HashSet<String>();
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(MyTokenizer.class.getClassLoader().getResourceAsStream(stopwordDictPath)));
                while (true) {
                    String line = in.readLine();
                    if (line == null) break;
                    stopwords.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        List<String> res = new ArrayList<String>();
        for (String word: raw) if (!stopwords.contains(word)) res.add(word);
        return res;
    }

    private List<WordWithDebugInfo> extractTextInBrackets(String text) {
        List<WordWithDebugInfo> res = new ArrayList<WordWithDebugInfo>();
        StringBuffer buf = new StringBuffer();
        boolean findL = false;
        for (int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            if (!findL && lBrackets.indexOf(c) >= 0) {
                findL = true;
                if (buf.length() > 0) {
                    res.add(new WordWithDebugInfo(buf.toString()));
                    buf.setLength(0);
                }
                buf.append(c);
                continue;
            }
            buf.append(c);
            if (findL && rBrackets.indexOf(c) >= 0)  {
                findL = false;
                res.add(new WordWithDebugInfo(buf.toString(), "BRACKET"));
                buf.setLength(0);
                continue;
            }
            if (i == text.length() - 1 && buf.length() > 0) {
                res.add(new WordWithDebugInfo(buf.toString()));
            }
        }
        return res;
    }

    public List<String> getTokens(String text) {
        List<String> ret = new ArrayList<String>();
        List<int[]> spots = acAutomation.find(text);
        spots.add(new int[]{-1, text.length(), text.length()});

        for (int si = 0, segL = 0; si < spots.size(); ++si) {
            int segR = spots.get(si)[1];
            if (segL >= segR) continue;

            for (WordWithDebugInfo seg: extractTextInBrackets(text.substring(segL, segR))) {
                if (seg.source.equals("BRACKET")) {
                    ret.add(seg.word);
                    continue;
                }
                char[] arr = seg.word.toCharArray();
                int N = arr.length;
                double[] dp = new double[N + 1];
                dp[N] = 0.0;
                int[] nxt = new int[N + 1];
                for (int i = N - 1; i >= 0; --i) nxt[i] = i + 1;
                for (int i = N - 1; i >= 0; --i) {
                    dp[i] = dict.getMinLogFreq() * 2 + dp[i + 1];
                    //dp[i] = Double.NEGATIVE_INFINITY; todo no sense
                    for (int j = i + 1; j <= N; ++j) {
                        int index = dict.contains(arr, i, j);
                        if (index == -1) continue;
                        double tmp = dict.getLogFreq(index) + dp[j];
                        if (tmp > dp[i]) {
                            nxt[i] = j;
                            dp[i] = tmp;
                        }
                    }
                }
                for (int i = 0; i < N; ) {
                    if (nxt[i] == i + 1) {
                        int j = i;
                        for (; j < N && nxt[j] == j + 1; ++j) ;
                        for (int l = i, r = i; l < j; ) {
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
            }
            if (spots.get(si)[0] == -1) continue;
            ret.add(ps.get(spots.get(si)[0]));
            segL = spots.get(si)[2] + 1;
        }
        return ret;
    }

    public static class WordWithDebugInfo {
        public String word;
        public String source = "HMM"; //or "Dict" or "ASCII"

        public WordWithDebugInfo(String word) {
            this.word = word;
        }

        public WordWithDebugInfo(String word, String source) {
            this.source = source;
            this.word = word;
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer("");
            sb.append("-----------\n");
            if (word.length() == 1) return sb.append(word + "\tS\t" + source + "\n").toString();
            for (int i = 0; i < word.length(); ++i) {
                if (i == 0) sb.append(word.charAt(i) + "\tB\t" + source + "\n");
                else if (i == word.length() - 1) sb.append(word.charAt(i) + "\tE\n");
                else sb.append(word.charAt(i) + "\tM\n");
            }
            return sb.toString();
        }
    }

    public List<WordWithDebugInfo> getTokensWithDebugInfo(String text) {

        List<WordWithDebugInfo> ret = new ArrayList<WordWithDebugInfo>();
        List<int[]> spots = acAutomation.find(text);
        spots.add(new int[]{-1, text.length(), text.length()});
        for (int si = 0, segL = 0; si < spots.size(); ++si) {
            int segR = spots.get(si)[1];
            if (segL >= segR) continue;

            for (WordWithDebugInfo seg: extractTextInBrackets(text.substring(segL, segR))) {
                if (seg.source.equals("BRACKET")) {
                    ret.add(seg);
                    continue;
                }
                char[] arr = seg.word.toCharArray();
                int N = arr.length;
                double[] dp = new double[N + 1];
                dp[N] = 0.0;
                int[] nxt = new int[N + 1];
                for (int i = N - 1; i >= 0; --i) nxt[i] = i + 1;
                for (int i = N - 1; i >= 0; --i) {
                    dp[i] = dict.getMinLogFreq() * 2 + dp[i + 1];
                    //dp[i] = Double.NEGATIVE_INFINITY; todo no sense
                    for (int j = i + 1; j <= N; ++j) {
                        int index = dict.contains(arr, i, j);
                        if (index == -1) continue;
                        double tmp = dict.getLogFreq(index) + dp[j];
                        if (tmp > dp[i]) {
                            nxt[i] = j;
                            dp[i] = tmp;
                        }
                    }
                }
                for (int i = 0; i < N; ) {
                    if (nxt[i] == i + 1) {
                        int j = i;
                        for (; j < N && nxt[j] == j + 1; ++j) ;
                        for (int l = i, r = i; l < j; ) {
                            while (r < j && !isASCII(arr[r])) ++r;
                            if (l < r) {
                                for (String w : hmm.getTokens(arr, l, r))
                                    ret.add(new WordWithDebugInfo(w, "HMM"));
                            }
                            l = r;
                            while (r < j && isASCII(arr[r])) ++r;
                            if (l < r && l < j) ret.add(new WordWithDebugInfo(new String(arr, l, r - l), "ASCII"));
                            l = r;
                        }
                        i = j;
                        continue;
                    }
                    ret.add(new WordWithDebugInfo(new String(arr, i, nxt[i] - i), "DICT"));
                    i = nxt[i];
                }
            }
            if (spots.get(si)[0] == -1) continue;
            ret.add(new WordWithDebugInfo(ps.get(spots.get(si)[0]), "STRONG_DICT"));
            segL = spots.get(si)[2] + 1;
        }

        return ret;
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

            Arrays.fill(PPx, MINVALUE);
            for (int i = 0; i < Pxixj.length; ++i) Arrays.fill(Pxixj[i], MINVALUE);
            for (int i = 0; i < Pxiyi.length; ++i) Arrays.fill(Pxiyi[i], MINVALUE);

            while (true) {
                String line = in.readLine();
                if (line == null) break;
                if (line.charAt(0) == '#') continue;
                String[] tmp = line.split(" ");
                for (int i = 0; i < PPx.length; ++i) PPx[i] = Math.max(PPx[i], Double.valueOf(tmp[i]));
                line = in.readLine();
                for (int i = 0; i < XN; ++i) {
                    line = in.readLine();
                    tmp = line.split(" ");
                    for (int j = 0; j < XN; ++j) Pxixj[i][j] = Math.max(Pxixj[i][j], Double.valueOf(tmp[j]));
                }

                for (int i = 0; i < XN;) {
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

    public void printTokenWithTransProb(String token, String xs) {
        assert (token.length() == xs.length());
        System.out.println("-----------");
        double totFullProb = 0;
        for (int i = 0;i < token.length(); ++i) {
            int curX = decode.indexOf(xs.charAt(i));
            if (i > 0) {
                int preX = decode.indexOf(xs.charAt(i - 1));
                System.out.println("\t\t" + Pxixj[preX][curX]);
                totFullProb += Pxixj[preX][curX];
            }
            System.out.println(token.charAt(i) + "\t" + xs.charAt(i) + "\t" + Pxiyi[curX][token.charAt(i)]);
            totFullProb += Pxiyi[curX][token.charAt(i)];
        }
        System.out.println("\t\t" + totFullProb);
    }

    public List<String> getTokens(char[] text, int L, int R) {
        if (dp == null || dp.length < text.length) dp = new double[text.length][XN];//todo amortized analysis
        if (path == null || path.length < text.length) path = new int[text.length][XN];

        List<String> ret = new ArrayList<String>();
        int M = R - L;
        boolean isHead = true;
        for (int i = 0; i < M;) {
            if (i == 0) {
                for (int j = 0; j < XN; ++j) dp[i][j] = PPx[j] + Pxiyi[j][text[i + L]];
                ++i;
                continue;
            }

            for (int j = 0; j < XN; ++j) {
                double res = MINVALUE;
                for (int k = 0; k < XN; ++k) {
                    if (dp[i - 1][k] <= MINVALUE || Pxixj[k][j] <= MINVALUE) continue;
                    //if (dp[i + 1][k] == HMM.MINVALUE || Pxixj[j][k] == HMM.MINVALUE) continue;
                    Double tmp = dp[i - 1][k] + Pxixj[k][j];
                    if (tmp > res) {
                        res = tmp;
                        path[i][j] = k;
                    }
                }
                if (res <= MINVALUE) {
                    path[i][j] = dp[i - 1][1] > dp[i - 1][3] ? 1 : 3;
                }
                dp[i][j] = res + Pxiyi[j][text[i + L]];
            }

            boolean allTotProbMinInf = true;
            for (int j = 0; j < XN; ++j) if (dp[i][j] > MINVALUE) allTotProbMinInf = false;
            if (allTotProbMinInf) {
                for (int j = 0; j < XN; ++j) {
                    dp[i][j] = PPx[j] + Pxiyi[j][text[i + L]];
                    if (i > 0) path[i][j] = dp[i - 1][1] >= dp[i - 1][3] ? 1 : 3;
                }
            }
            ++i;
        }
        //for (int i = 0; i < XN; ++i) dp[0][i] += PPx[i];
        int xi = dp[M - 1][1] > dp[M - 1][3] ? 1 : 3;
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
