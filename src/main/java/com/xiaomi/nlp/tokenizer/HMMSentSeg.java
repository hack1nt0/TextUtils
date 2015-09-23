package com.xiaomi.nlp.tokenizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy on 15-9-23.
 */
public class HMMSentSeg implements ISentSeg {
    private static MyTokenizer tokenizer = MyTokenizer.getInstance();
    public static String puncs = "[，。！？：；]|…{1,}";

    @Override
    public List<Sentence> getSents(String text) {
        MyTokenizer.WordWithDebugInfo[] tokens = tokenizer.getTokensWithDebugInfo(text);
        List<Sentence> ret = new ArrayList<Sentence>();
        List<String> buf = new ArrayList<String>();
        for (int i = 0; i < tokens.length; ++i) {
            MyTokenizer.WordWithDebugInfo curToken = tokens[i];
            if (curToken.source.equals("HMM") && allPunc(curToken.word)) {
                Sentence newSent = new Sentence(new ArrayList<String>(buf), curToken.word);
                ret.add(newSent);
                buf.clear();
                continue;
            }
            buf.add(curToken.word);
        }
        return ret;
    }

    public boolean allPunc(String str) {
        return str.split(puncs).length == 0;
    }
}
