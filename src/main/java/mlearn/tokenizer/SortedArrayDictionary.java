package mlearn.tokenizer;

import template.debug.ScannerUTF8;

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

    public SortedArrayDictionary() {
        this(ScannerUTF8.fromResource("/tokenizer/jieba.sorted.dict"));
    }

    public SortedArrayDictionary(ScannerUTF8 in) {
        //List<String> lines = new ArrayList<String>();
        arrs = new char[MAXN][];
        logFreq = new float[MAXN];
        int ln = 0;
        while (!in.isExhausted()) {
            String line = in.nextLine();
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


    public static void main(String[] args) throws Exception {
        SortedArrayDictionary sortedArrayDictionary = new SortedArrayDictionary();
        System.err.println(sortedArrayDictionary);
    }
}
