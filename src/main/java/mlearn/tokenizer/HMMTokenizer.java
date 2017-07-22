package mlearn.tokenizer;

import template.debug.InputReaderUnicode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dy[jealousing@gmail.com] on 17-7-23.
 */

public class HmmTokenizer extends Tokenizer {
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

    public HmmTokenizer(InputReaderUnicode in) {
        Arrays.fill(PPx, MINVALUE);
        for (int i = 0; i < Pxixj.length; ++i) Arrays.fill(Pxixj[i], MINVALUE);
        for (int i = 0; i < Pxiyi.length; ++i) Arrays.fill(Pxiyi[i], MINVALUE);

        while (!in.isExhausted()) {
            String line = in.readLine();
            if (line.charAt(0) == '#') continue;
            String[] tmp = line.split(" ");
            for (int i = 0; i < PPx.length; ++i) PPx[i] = Math.max(PPx[i], Double.valueOf(tmp[i]));
            line = in.readLine();
            for (int i = 0; i < XN; ++i) {
                line = in.readLine();
                tmp = line.split(" ");
                for (int j = 0; j < XN; ++j) Pxixj[i][j] = Math.max(Pxixj[i][j], Double.valueOf(tmp[j]));
            }

            for (int i = 0; i < XN; ) {
                line = in.readLine();
                if (line == null) break;
                if (line.charAt(0) == '#') continue;
                // StringReader sr = new StringReader(line);
                for (int j = 0; j < line.length(); ) {
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
    }

    public void printTokenWithTransProb(String token, String xs) {
        assert (token.length() == xs.length());
        System.out.println("-----------");
        double totFullProb = 0;
        for (int i = 0; i < token.length(); ++i) {
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
        for (int i = 0; i < M; ) {
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

        int[] xs = new int[M];
        xs[M - 1] = xi;
        for (int i = M - 2; i >= 0; --i) xs[i] = path[i + 1][xs[i + 1]];

        //for (int i = 0; i < M; ++i) System.out.print(decode.charAt(xs[i]));
        //System.out.println();

        for (int i = 0, autoS = 0; i < M; ) {
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

    @Override
    public List<Word> split(List<Word> text) {
        throw new UnsupportedOperationException();
    }
}
