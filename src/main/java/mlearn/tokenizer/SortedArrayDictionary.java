package mlearn.tokenizer;

import template.debug.InputReaderUnicode;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author dy[jealousing@gmail.com] on 17-7-23.
 */
class SortedArrayDictionary extends Dictionary {

    int MAXN = 350000;
    char[][] arrs;
    float[] logFreq;
    float minLogFreq = 0;
    int realSize = 0;
    static String DICT_SPLIT = " ";

    //sort the dict
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(SortedArrayDictionary.class.getClassLoader().getResourceAsStream("com/xiaomi/nlp/tokenizer/jieba.dict.utf8")));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream("data/jieba.dict.utf8.sorted")));
        class Line implements Comparable<Line>{
            String word;
            int freq;
            String tag;

            public Line(String word, int freq, String tag) {
                this.word = word;
                this.freq = freq;
                this.tag = tag;
            }

            @Override
            public int compareTo(Line o) {
                return word.compareTo(o.word);
            }

            @Override
            public String toString() {
                return word + DICT_SPLIT + freq + DICT_SPLIT + tag;
            }
        }
        List<Line> lines = new ArrayList<Line>();
        int originCnt = 0;
        while (true) {
            String line = in.readLine();
            if (line == null) break;
            ++originCnt;
            String[] tmp = line.split("[ ]+");
            lines.add(new Line(tmp[0], Integer.parseInt(tmp[1]), tmp[2]));
        }
        System.out.println(originCnt);
        System.out.println(lines.size());
        Collections.sort(lines);
        System.out.println(lines.size());

        //test
        for (int i = 0; i < lines.size() - 1; ++i) {
            if (lines.get(i).word.compareTo(lines.get(i + 1).word) <= 0) continue;
            throw new Exception(lines.get(i) + " > " + lines.get(i + 1));
        }

        for (Line line: lines) out.println(line);
        out.close();
        in.close();
    }

    public SortedArrayDictionary(InputReaderUnicode in) {
        //List<String> lines = new ArrayList<String>();
        arrs = new char[MAXN][];
        logFreq = new float[MAXN];
        while (!in.isExhausted()) {
            String line = in.readLine();
            //lines.add(line);
            String[] vecBuf = line.split(" ");
            if (2 > vecBuf.length) {
                continue;
            }
            if (4 < vecBuf.length) {
                continue;
            }
            arrs[realSize] = vecBuf[0].toCharArray();
            logFreq[realSize] = Float.valueOf(vecBuf[1]);
            if (3 <= vecBuf.length) {
                //token.tag = vecBuf[2];
            }
            ++realSize;
        }
        Double totFreq = 0.0;
        for (int i = 0; i < realSize; ++i) totFreq += logFreq[i];
        for (int i = 0; i < realSize; ++i) {
            logFreq[i] = (float)Math.log(logFreq[i] / totFreq);
            minLogFreq = Math.min(logFreq[i], minLogFreq);
        }

        in.close();
    }

    @Override
    public int contains(char[] token, int L, int R) {
        int l = 0, r = realSize;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int cmp = charArrCmp(token, L, R, arrs[mid]);
            if (cmp == 0) return mid;
            else if (cmp > 0) l = mid + 1;
            else r = mid;
        }
        return -1;
    }

    @Override
    public float getLogFreq(int index) {
        return logFreq[index];
    }

    @Override
    public float getMinLogFreq() {
        return minLogFreq;
    }

    public int charArrCmp(char[] A, int L, int R, char[] B) {
        for (int i = 0; i + L < R && i < B.length; ++i) {
            if (A[i + L] == B[i]) continue;
            return A[i + L] - B[i];
        }
        return R - L - B.length;
    }

    public int charArrCmp(char[] a, char[] b) {
        return charArrCmp(a, 0, a.length, b);
    }

}
