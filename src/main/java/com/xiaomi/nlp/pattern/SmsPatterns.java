package com.xiaomi.nlp.pattern;

import com.xiaomi.nlp.util.ACAutomation;
import com.xiaomi.nlp.util.LazyList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by DY on 15/3/20.
 */
public class SmsPatterns {
    private static int MAXTOKENLEN = 1000;//maximum token-length of sms todo need to be dynamic reset
    private static int[][] Dp = new int[MAXTOKENLEN][MAXTOKENLEN];
    private static int[][] Path = new int[MAXTOKENLEN][MAXTOKENLEN];
    private static int[] LenA = new int[MAXTOKENLEN];
    private static int[] LenB = new int[MAXTOKENLEN];
    private static String INVALIDWILDCARDDICTPATH = "com/xiaomi/nlp/pattern/invalidWildcardDict.txt"; //todo token those cannot be generalized
    private static ACAutomation InvalidBoundWildcardDict = null;
    private static ACAutomation InvalidWildcardDict = null;
    private static String TOKEN_WEIGHTS_DICT_PATH = "com/xiaomi/nlp/pattern/token-weights-dict.txt"; //todo token those cannot be generalized
    private static HashMap<Token, Float> weights;
    static float getOrElse(HashMap<Token, Float> weights, Token key) {
        if (!weights.containsKey(key)) return 0.4f;
        return weights.get(key);
    }

    static {

        BufferedReader in = new BufferedReader(new InputStreamReader(MiningPatterns.class.getClassLoader().getResourceAsStream(INVALIDWILDCARDDICTPATH)));
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        while (true) {
            String line = null;
            try {
                line = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) break;
            String[] tokens = line.split(" ");
            if (tokens.length > 1) list2.add(tokens[0]);
            list1.add(tokens[0]);
        }
        InvalidWildcardDict = new ACAutomation(list1);
        InvalidBoundWildcardDict = new ACAutomation(list2);

        in = new BufferedReader(new InputStreamReader(MiningPatterns.class.getClassLoader().getResourceAsStream(TOKEN_WEIGHTS_DICT_PATH)));
        weights = new HashMap<Token, Float>();
        while (true) {
            String line = null;
            try {
                line = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) break;
            String[] tokens = line.split("[ ]+");
            weights.put(new Token(tokens[0]), Float.valueOf(tokens[1]));
        }
    }

    public static SmsPattern getLcpWithWeights(OrdSent A, OrdSent B) {
        int N = A.size(), M = B.size();
        char[][] preAction = new char[N][M];
        float[][] totWeights = new float[N][M];
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < M; ++j) {
                float res = 0;
                if (A.get(i).equals(B.get(j))) {
                    res = getOrElse(weights, (Token) A.get(i));
                    if (i > 0 && j > 0) res += totWeights[i - 1][j - 1];
                    preAction[i][j] = 1;
                }
                if (i > 0 && totWeights[i - 1][j] > res) {
                    preAction[i][j] = 2;
                    res = totWeights[i - 1][j];
                }
                if (j > 0 && totWeights[i][j - 1] > res) {
                    preAction[i][j] = 3;
                    res = totWeights[i][j - 1];
                }
                totWeights[i][j] = res;
            }

        SmsPattern ret = new OrdSent();
        for (int i = N - 1, j = M - 1; i >= 0 && j >= 0;) {
            if (preAction[i][j] == 0) break;
            if (preAction[i][j] == 1) {
                ret.add(A.get(i));
                --i;
                --j;
            }
            else if (preAction[i][j] == 2) --i;
            else if (preAction[i][j] == 3) --j;
        }
        if (ret.chds.size() != 0) Collections.reverse(ret.chds.innerList);
        return ret;
    }


    public static boolean updWildcardsWithWeights(SmsPattern A, SmsPattern B) {
        if (A == null) return true;
        if (B == null) return false;
        int N = A.size(), M = B.size();

        for (int i = 0, j = 0; i <= N; ++i) {
            StringBuilder sb = new StringBuilder("");
            for (; j < M && (i == N || !B.get(j).equals(A.get(i))); ++j) sb.append(B.get(j));
            ++j;
            if (sb.length() == 0) continue;

            int p = i;
            String content = sb.toString();
            int sourceId = B.corpusId;
            if (A.wildcards == null) A.wildcards = new HashMap<Integer, Map<String, List<Integer>>>();
            if (!A.wildcards.containsKey(p)) A.wildcards.put(p, new HashMap<String, List<Integer>>());
            if (!A.wildcards.get(p).containsKey(content)) A.wildcards.get(p).put(content, new ArrayList<Integer>());
            A.wildcards.get(p).get(content).add(sourceId);
        }

        if (A.puncWildcards == null) A.puncWildcards = new HashSet<String>();
        A.puncWildcards.add(B.punc);
        return true;
}


    public static SmsPattern getLcp(SmsPattern A, SmsPattern B) {
        SmsPattern cand = getLcs(A, B);
        int L = cand.getSizeInToken();
        if (L <= 0) return cand;
        int N = A.getSizeInToken();
        int M = B.getSizeInToken();
        for (int i = 0; i < L + 2; ++i) {
            LenA[i] = 0;
            for (int j = 0; j < N + 2; ++j) Dp[i][j] = 0;
        }
        getMaxLen(cand, A, Dp, LenA);

        /*
        int[][] dp2 = new int[N][MB];
        int[] len2 = new int[N];
        */
        for (int i = 0; i < L + 2; ++i) {
            LenB[i] = 0;
            for (int j = 0; j < M + 2; ++j) Dp[i][j] = 0;
        }
        getMaxLen(cand, B, Dp, LenB);

        for (int i = 1; i <= L; ++i) LenA[i] = Math.min(LenB[i], LenA[i]);
        int candi = 0;
        for (int i = 1; i <= L; ++i) if (LenA[i] > LenA[candi]) candi = i;

        List<SmsPattern> nPatternList = new ArrayList<SmsPattern>();
        for (int i = candi; i < candi + LenA[candi] && i <= L; ++i) {
            nPatternList.add(cand.get(i - 1));
        }
        cand.chds = new LazyList<SmsPattern>(nPatternList);
        //cand.baseSup = A.baseSup + B.baseSup;
        return cand;
    }

    private static void getMaxLen(SmsPattern cand, SmsPattern A, int[][] dp, int[] len) {
        int an = A.getSizeInToken();
        int cn = cand.getSizeInToken();

        int N = cn + 2, M = an + 2;
        dp[N - 1][M - 1] = 1;
        for (int i = cn; i >= 1; --i)
            for (int j = an; j >= 1; --j) {
                if (cand.get(i - 1).compareTo(A.get(j - 1)) != 0) continue;

                int res = 1;
                StringBuffer sb = new StringBuffer("");
                boolean find = false;
                for (int k = j + 1; k <= an; sb.append(A.get(k - 1)), ++k) {
                    if (!isValidWildcard(A, j, k, sb.toString())) continue;
                    if (i + 1 <= cn && A.get(k - 1).compareTo(cand.get(i)) == 0 && dp[i + 1][k] > 0) {
                        find = true;
                        res = Math.max(dp[i + 1][k] + 1, res);
                    }
                    //sb.append(A.get(k - 1));
                }
                if (!find) {
                    sb.setLength(0);
                    for (int k = j + 1; k <= an; ++k) sb.append(A.get(k - 1));
                    if (!isValidWildcard(A, j, an + 1, sb.toString())) res = -1;
                }
                dp[i][j] = res;
            }

        for (int i = 1; i <= cn; ++i) {
            StringBuffer sb = new StringBuffer("");
            for (int j = 1; j <= an; sb.append(A.get(j - 1)), ++j) {
                if (!isValidWildcard(A, 0, j, sb.toString())) continue;
                //sb.append(A.get(j - 1));
                len[i] = Math.max(dp[i][j], len[i]);
            }
        }
    }


    //token is valid in the context of (A[L], A[R])
    private static boolean isValidWildcard(SmsPattern A, int L, int R, String wildcard) {
        //(L, [A], R), extended A's tokens[]
        if (wildcard == null || wildcard.length() == 0) return true;
        if (L == 0 || R == A.getSizeInToken() + 1)
            //return InvalidBoundWildcardDict.match(wildcard, 0).size() == 0;
            return true;
        return InvalidWildcardDict.match(wildcard, 0).size() == 0;
    }

    public static SmsPattern getLcs(SmsPattern A, SmsPattern B) {
        int N = A.getSizeInToken();
        int M = B.getSizeInToken();
        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= M; ++j) {
                int res = 0;
                if (A.get(i - 1).compareTo(B.get(j - 1)) == 0) res = Math.max(Dp[i - 1][j - 1] + 1, res);
                else res = Math.max(Dp[i - 1][j], Dp[i][j - 1]);
                Dp[i][j] = res;
            }
        }

        SmsPattern res = null;
        try {
            res = A.getClass().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        res.chds = new LazyList<SmsPattern>(new ArrayList<SmsPattern>());
        res.lc = A;
        res.rc = B;
        //todo

        for (int i = N, j = M; 0 < i && 0 < j; ) {
            if (A.get(i - 1).compareTo(B.get(j - 1)) == 0) {
                res.add(A.get(i - 1));
                --i;
                --j;
                continue;
            }
            if (Dp[i][j] == Dp[i - 1][j]) --i;
            else --j;
        }
        Collections.reverse(res.chds.innerList);
        return res;
    }

    public static boolean isSubstr(SmsPattern A, SmsPattern B) {
        int N = A.size(), M = B.size();
        if (N > M) return false;
        for (int i = 0, j = 0; i < N; ) {
            while (j < M && B.get(j).compareTo(A.get(i)) != 0) ++j;
            if (M <= j) {
                return false;
            }
            ++i;
            ++j;
        }
        A.origin = B;
        return true;
    }

    //check whether A is a substring of B or not, and update Wildcards
    public static boolean isSubpat(SmsPattern A, SmsPattern B) {
        int N = A.size(), M = B.size();
        if (N == 0) return true;
        if (N > M) return false;

        for (int i = 0; i < N + 2; ++i)
            for (int j = 0; j < M + 2; ++j) Dp[i][j] = 0;
        Dp[N + 1][M + 1] = 1;

        for (int i = N; i >= 0; --i) {//A index
            if (i == N) {
                StringBuffer sb = new StringBuffer("");
                for (int j = M; j >= 1; sb.append(B.get(j - 1)), --j) {
                    if (!isValidWildcard(B, j, M + 1, sb.toString())) continue;
                    if (A.get(i - 1).compareTo(B.get(j - 1)) == 0) {
                        Dp[i][j] = 1;
                        Path[i][j] = M + 1;
                    }
                    //sb.append(B.get(j - 1));
                }
                continue;
            }
            for (int j = M; j >= 0; --j) {// B index
                if (!(i == 0 && j == 0 || 0 < i && 0 < j && A.get(i - 1).compareTo(B.get(j - 1)) == 0)) continue;

                int res = 0;
                StringBuffer sb = new StringBuffer("");
                for (int nj = j + 1; nj <= M; sb.append(B.get(nj - 1)), ++nj) {
                    if (!isValidWildcard(B, j, nj, sb.toString())) continue;
                    if (i + 1 <= N && B.get(nj - 1).compareTo(A.get(i)) == 0 && Dp[i + 1][nj] == 1) {
                        res = 1;
                        Path[i][j] = nj;
                        break;
                    }
                    //sb.append(B.get(nj - 1));
                }
                Dp[i][j] = res;
            }
        }
        if (Dp[0][0] == 0) return false;
        A.origin = B;
        return true;
    }

    public static boolean updWildcards(SmsPattern A, SmsPattern B) {
        if (B == null) return true; //due to A.chds = empty
        int N = A.size(), M = B.size();

        for (int i = 0; i < N + 2; ++i)
            for (int j = 0; j < M + 2; ++j) Dp[i][j] = 0;
        Dp[N + 1][M + 1] = 1;

        for (int i = N; i >= 0; --i) {//A index
            if (i == N) {
                StringBuffer sb = new StringBuffer("");
                for (int j = M; j >= 1; sb.append(B.get(j - 1)), --j) {
                    if (!isValidWildcard(B, j, M + 1, sb.toString())) continue;
                    if (A.get(i - 1).compareTo(B.get(j - 1)) == 0) {
                        Dp[i][j] = 1;
                        Path[i][j] = M + 1;
                    }
                    //sb.append(B.get(j - 1));
                }
                continue;
            }
            for (int j = M; j >= 0; --j) {// B index
                if (!(i == 0 && j == 0 || 0 < i && 0 < j && A.get(i - 1).compareTo(B.get(j - 1)) == 0)) continue;

                int res = 0;
                StringBuffer sb = new StringBuffer("");
                for (int nj = j + 1; nj <= M; sb.append(B.get(nj - 1)), ++nj) {
                    if (!isValidWildcard(B, j, nj, sb.toString())) continue;
                    if (i + 1 <= N && B.get(nj - 1).compareTo(A.get(i)) == 0 && Dp[i + 1][nj] == 1) {
                        res = 1;
                        Path[i][j] = nj;
                        break;
                    }
                    //sb.append(B.get(nj - 1));
                }
                Dp[i][j] = res;
            }
        }
        if (Dp[0][0] == 0) {
            return false;
            //throw new RuntimeException("cannot apply updateWildcards due to A is not a valid pattern of B");
        }

        //update wildcards info
        int j = 0;
        for (int i = 0; i <= N; ++i) {
            StringBuilder sb = new StringBuilder("");
            for (int k = j + 1; k <= M && k < Path[i][j]; ++k) sb.append(B.get(k - 1));
            int p = i;
            String content = sb.toString();
            int sourceId = B.corpusId;

            if (A.wildcards == null) A.wildcards = new HashMap<Integer, Map<String, List<Integer>>>();
            if (!A.wildcards.containsKey(p)) A.wildcards.put(p, new HashMap<String, List<Integer>>());
            if (!A.wildcards.get(p).containsKey(content)) A.wildcards.get(p).put(content, new ArrayList<Integer>());
            A.wildcards.get(p).get(content).add(sourceId);

            j = Path[i][j];
        }

        if (A.puncWildcards == null) A.puncWildcards = new HashSet<String>();
        A.puncWildcards.add(B.punc);
        return true;
    }

}
