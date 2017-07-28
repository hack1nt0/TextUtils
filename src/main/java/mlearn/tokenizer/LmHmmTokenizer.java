package mlearn.tokenizer;

import template.debug.ScannerUTF8;

import java.io.*;
import java.util.*;

/**
 * Created by dy on 14-11-20.
 */

public class LmHmmTokenizer extends Tokenizer {
    private Dictionary dictionary;
    private HmmTokenizer hmm;

    public LmHmmTokenizer() {
        dictionary = new SortedArrayDictionary(ScannerUTF8.fromResource("/tokenizer/jieba.sorted.dict"));
//        dictionary = new TrieDictionary(ScannerUTF8.fromResource("/dictionary/jieba.sorted.dict"));
        hmm =  new HmmTokenizer(ScannerUTF8.fromResource("/tokenizer/hmm.model"));
    }

    @Override
    public List<Word> split0(List<Word> text) {
        List<Word> words = new ArrayList<>();
        for (Word segment : text) {
            if (segment.source != null) {
                words.add(segment);
                continue;
            }
            char[] arr = segment.word.toCharArray();
            int N = arr.length;
            double[] dp = new double[N + 1];
            dp[N] = 0.0;
            int[] nxt = new int[N + 1];
            for (int i = N - 1; i >= 0; --i) nxt[i] = i + 1;
            for (int i = N - 1; i >= 0; --i) {
                dp[i] = dictionary.getMinLogFreq() * 2 + dp[i + 1];
                //dp[i] = Double.NEGATIVE_INFINITY; todo no sense
                for (int j = i + 1; j <= N; ++j) {
                    int index = dictionary.contains(arr, i, j);
                    if (index == -1) continue;
                    double tmp = dictionary.getLogFreq(index) + dp[j];
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
                            List<String> hmmWords = hmm.getTokens(arr, l, r);
                            for (String hw : hmmWords) words.add(new Word(hw, Word.SOURCE.HMM));
                        }
                        l = r;
                        if (l >= j) break;
                        while (r < j && isASCII(arr[r])) ++r;
                        int count = r - l;
                        if (arr[l] == ' ') {
                            l++;
                            count--;
                        }
                        if (arr[r - 1] == ' ') {
                            count--;
                        }
                        if (count > 0) {
                            words.add(new Word(new String(arr, l, count), Word.SOURCE.ASCII));
                        }
                        l = r;
                    }
                    i = j;
                    continue;
                }
                words.add(new Word(new String(arr, i, nxt[i] - i), Word.SOURCE.DICTIONARY));
                i = nxt[i];
            }
        }
        return words;
    }

    private boolean isASCII(char c) {
        return c < 128;
    }

    public static void main(String[] args) {
        String str = "我爱北京天安门。";
        Scanner in = new Scanner(new BufferedInputStream(System.in), "UTF-8");
        in.useDelimiter("\\A");
        String text = in.next();
        in.useDelimiter("");
        System.out.println(text);
        System.out.println(new LmHmmTokenizer().split(text));
    }
}

