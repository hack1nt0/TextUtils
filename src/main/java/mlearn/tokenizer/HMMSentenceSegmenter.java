package mlearn.tokenizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy on 15-9-23.
 */
public class HmmSentenceSegmenter implements ISentenceSegmenter {
    private static LmHmmTokenizer tokenizer = new LmHmmTokenizer();
    public static String puncs = "[，。！？：；]|…{1,}";

    @Override
    public List<Sentence> split(String text) {
//        List<Word> tokens = tokenizer.split0(text);
//        List<Sentence> ret = new ArrayList<Sentence>();
//        List<String> buf = new ArrayList<String>();
//        for (int i = 0; i < tokens.capacity(); ++i) {
//            Word curToken = tokens.get(i);
//            if (curToken.source.equals("HMM") && allPunc(curToken.word)) {
//                Sentence newSent = new Sentence(new ArrayList<String>(buf), curToken.word);
//                ret.add(newSent);
//                buf.clear();
//                continue;
//            }
//            buf.add(curToken.word);
//        }
//        if (buf.capacity() > 0) ret.add(new Sentence(buf, "</SENT_END>"));
//        return ret;
        throw new UnsupportedOperationException();
    }

    public boolean allPunc(String str) {
        return str.split(puncs).length == 0;
    }
}
