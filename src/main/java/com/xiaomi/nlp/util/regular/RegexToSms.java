package com.xiaomi.nlp.util.regular;

import com.xiaomi.nlp.tokenizer.MyTokenizer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.*;
import java.util.*;

/**
 * Created by DY on 15/11/8.
 */
public class RegexToSms {

    static class Knowledge {
        String name;
        Set<String> lower_word_feat = new HashSet<String>();
        String tag_name;
        Set<String> upper_word_feat = new HashSet<String>();
        int lower_len;
        int upper_len;
        int lower_margin;
        int upper_margin;

        public Knowledge(String name, String lower_word_feat, String tag_name, String upper_word_feat, int lower_len, int upper_len, int lower_margin, int upper_margin) {
            this.name = name;
            for (String w: lower_word_feat.split("\\|")) this.lower_word_feat.add(w);
            this.tag_name = tag_name;
            for (String w: upper_word_feat.split("\\|")) this.upper_word_feat.add(w);
            this.lower_len = lower_len;
            this.upper_len = upper_len;
            this.lower_margin = lower_margin;
            this.upper_margin = upper_margin;
        }

        public boolean match(List<RE_SEG> KL_ARR, int k, int i, int l) {
            RE_LITERALS re_literals_pre = k < 0 ? null : (RE_LITERALS)KL_ARR.get(k);
            RE_LITERALS re_literals_after = l >= KL_ARR.size() ? null : (RE_LITERALS)KL_ARR.get(l);
            RE_SEG re_seg = KL_ARR.get(i);

            if (re_seg instanceof S_TAG) {
                S_TAG s_tag = (S_TAG)re_seg;
                if (!s_tag.tag_name.equals(tag_name)) return false;
                if (lower_word_feat.size() != 0) {
                    if (re_literals_pre == null) return false;
                    List<String> re_wild_pre_words = re_literals_pre.words;
                    boolean find = false;
                    for (String w: re_wild_pre_words) if (lower_word_feat.contains(w)) find = true;
                    if (!find) return false;
                }
                if (upper_word_feat.size() != 0) {
                    if (re_literals_after == null) return false;
                    List<String> re_wild_after_words = re_literals_after.words;
                    boolean find = false;
                    for (String w: re_wild_after_words) if (upper_word_feat.contains(w)) find = true;
                    if (!find) return false;
                }
                return true;
            }
            return true;
        }

        public String toString(int start_pos, int span) {
            if (start_pos < 0) throw new IllegalArgumentException("start_pos < 0 !");
            if (span <= 0) throw new IllegalArgumentException("span <= 0 !");
            StringBuffer sb = new StringBuffer();
            sb.append(name + ':');
            String span_seq = "";
            for (int i = 0; i < span; ++i) {
                if (i > 1) span_seq += "+";
                span_seq += start_pos + i;
            }
            sb.append(span_seq);
            sb.append("\t");
            if (lower_len != -1) sb.append("CharLenLonger:" + span_seq + ":" + lower_len);
            sb.append("\t");
            if (upper_len != -1) sb.append("CharLenLess:" + span_seq + ":" + upper_len);
            sb.append("\t");
            if (upper_len != -1) sb.append("CharLenLess:" + span_seq + ":" + upper_len);
            sb.append("\t");
            if (lower_margin != -1) sb.append("CharLenLonger:" + (start_pos - 1) + ":" + lower_margin);
            sb.append("\t");
            if (upper_margin != -1) sb.append("CharLenLess:" + (start_pos + span) + ":" + upper_margin);
            return sb.toString();
        }
    }

    static String segPunc = "，。";
    static String task = "null";
    static String version = "2.1.2";

    static ArrayList<Knowledge> knowledges = new ArrayList<Knowledge>();
    static {
        knowledges.add(new Knowledge("Ext_RuZhangJinE", "money0", "收入|入账", "", 6, -1, -1, 3));
        knowledges.add(new Knowledge("Ext_RuZhangJinE", "money1", "收入|入账", "", 6, -1, -1, 3));

    }

    static HashMap<Integer[], String> ident2KL = new HashMap<Integer[], String>();
    static String default_ident = "<?DEFAULT>";
    static {
        ident2KL.put(new Integer[]{0}, "<?KXRZ>");
    }

    public static void main(String[] args) throws Exception {
        String inputFile = "GongShangYinHang";
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("data/train/syntax-trans/" + inputFile)));
        PrintWriter out = new PrintWriter(new FileOutputStream("data/ret/syntax-trans/" + inputFile));
        LinkedList<String> res = new LinkedList<String>();

        res.add("<!segPunctution> ::= " + segPunc);
        res.add("<!task> ::= " + task);
        res.add("<!version> ::= " + version);

        while (true) {
            String line = in.readLine();
            if (line == null) break;
            for (String seg: line.split(segPunc)) {
                ANTLRInputStream input = new ANTLRInputStream(new StringReader(seg));
                RegexLexer regexLexer = new RegexLexer(input);
                CommonTokenStream tokens = new CommonTokenStream(regexLexer);
                RegexParser regexParser = new RegexParser(tokens);
                ParseTree tree = regexParser.s();
                ParseTreeWalker walker = new ParseTreeWalker();
                RegexListenerImpl regexListener = new RegexListenerImpl();
                walker.walk(regexListener, tree);
                //pos -> knowledge id
                List<Integer[]> demands = extractKL(regexListener.res);
                if (demands.size() == 0) continue;
                String identifier = identify(demands);
                StringBuffer sb = new StringBuffer();
                //apd left and right text
                sb.append(identifier + " ::= " + regexListener + "\t");
                //apd knowledge
                for (Integer[] p: demands) sb.append(knowledges.get(p[2]).toString(p[0], p[1]) + "\t");
                //apd score
                sb.append("Score:1:1.0");
                res.add(sb.toString());
            }
        }

        int idx = 2;
        for (String group_tag : S_GROUP.group_tags.keySet())
            res.add(idx++, S_GROUP.group_tags.get(group_tag) + " ::= " + group_tag);

        for (int i = 0; i < res.size(); ++i) out.println(res.get(i));
    }

    private static List<Integer[]> extractKL(ArrayList<RE_SEG> klArr) {
        ArrayList<Integer[]> res = new ArrayList<Integer[]>();
        int start_pos = 0;
        for (int i = 0; i < klArr.size(); ++i) {
            for (int j = 0; j < knowledges.size(); ++j) {
                int k, l;
                k = l = -1;
                for (k = i - 1; k >= 0; --k) if (klArr.get(k) instanceof RE_LITERALS) break;
                for (l = i + 1; l < klArr.size(); ++l) if (klArr.get(l) instanceof RE_LITERALS) break;
                if (!knowledges.get(j).match(klArr, k, i, l)) continue;
                res.add(new Integer[]{start_pos + klArr.get(i).offset, klArr.get(i).offset_span, j});
            }
            start_pos += klArr.get(i).span;
        }
        return res;
    }

    private static String identify(List<Integer[]> demands) {
        return default_ident;
    }

}

class RE_SEG {
    int span = 1;
    int offset = 0;
    int offset_span = 1;
}

class S_TAG extends RE_SEG {
    String tag_name;
    String sub;

    public S_TAG(String tag_name) {
        this.tag_name = tag_name;
        if (tag_name.equals("money0")) {
            sub = "<#m><*>.<#m>";
            offset_span = 4;
            span += offset_span - 1;
        }
    }

    @Override
    public String toString() {
        if (sub != null) return sub;
        return "<no substitution for tag_name: " + tag_name + ">";
    }
}

class S_CLASS extends RE_SEG {

}

class S_GROUP extends RE_SEG {
    String choice;
    String left_margin;
    String right_margin;
    String quant;
    static HashMap<String, String> group_tags = new HashMap<String, String>();

    public S_GROUP(String choice, String left_margin, String right_margin, String quant) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < choice.length(); ++i) {
            char c = choice.charAt(i);
            if (c == '(' || c == ')') continue;
            sb.append(c);
        }
        this.choice = sb.toString();
        this.left_margin = left_margin;
        this.right_margin = right_margin;
        this.quant = quant;

        if (!group_tags.containsKey(this.choice)) {
            String tag = this.choice.split("\\|")[0];
            group_tags.put(this.choice, "<!" + tag + ">");
        }

        if (this.left_margin != null) {
            offset++;
            ++span;
        }

        if (this.right_margin != null) {
            ++span;
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if (left_margin != null) {
            sb.append("<*>");
        }
        String tag = group_tags.get(choice);
        if (quant != null && quant.equals("?")) tag = tag.substring(0, tag.length() - 1) + "|!空>";
        sb.append(tag);
        if (right_margin != null) {
            sb.append("<*>");
        }
        return sb.toString();
    }
}

class S_WILD extends RE_SEG {

    @Override
    public String toString() {
        return "<*>";
    }
}

class RE_LITERALS extends RE_SEG {
    public StringBuffer literals;
    public List<String> words;

    public RE_LITERALS(StringBuffer literals) {
        this.literals = literals;
        words = MyTokenizer.getInstance().getTokens(literals.toString());
    }

    @Override
    public String toString() {
        return literals.toString();
    }
}
