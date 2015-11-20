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
        int len_lower_bound;
        int len_upper_bound;
        int margin_len_lower_bound;
        int margin_len_upper_bound;
        int offset; //offset from head of sms pattern

        public Knowledge(String name, String tag_name, String lower_word_feat, String upper_word_feat, int len_lower_bound, int len_upper_bound, int margin_len_lower_bound, int margin_len_upper_bound) {
            this.name = name;
            if (lower_word_feat != null)
                for (String w: lower_word_feat.split("\\|")) if (w.trim().length() != 0) this.lower_word_feat.add(w);
            this.tag_name = tag_name;
            if (upper_word_feat != null)
                for (String w: upper_word_feat.split("\\|")) if (w.trim().length() != 0) this.upper_word_feat.add(w);
            this.len_lower_bound = len_lower_bound;
            this.len_upper_bound = len_upper_bound;
            this.margin_len_lower_bound = margin_len_lower_bound;
            this.margin_len_upper_bound = margin_len_upper_bound;
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
                    List<String> re_wild_pre_words = re_literals_pre.getWords();
                    boolean find = false;
                    for (String w: re_wild_pre_words) if (lower_word_feat.contains(w)) find = true;
                    if (!find) return false;
                }
                if (upper_word_feat.size() != 0) {
                    if (re_literals_after == null) return false;
                    List<String> re_wild_after_words = re_literals_after.getWords();
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
                span_seq += start_pos + i;
                if (i < span - 1) span_seq += "+";
            }
            sb.append(span_seq);
            sb.append("\t");
            if (len_lower_bound != -1) sb.append("CharLenLonger:" + span_seq + ":" + len_lower_bound);
            sb.append("\t");
            if (len_upper_bound != -1) sb.append("CharLenLess:" + span_seq + ":" + len_upper_bound);
            sb.append("\t");
            if (margin_len_lower_bound != -1) sb.append("CharLenLonger:" + (start_pos - 1) + ":" + margin_len_lower_bound);
            sb.append("\t");
            if (margin_len_upper_bound != -1) sb.append("CharLenLess:" + (start_pos + span) + ":" + margin_len_upper_bound);
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
        knowledges.add(new Knowledge("Ext_XiaoFeiJinE", "money0", "支出|消费", "", 6, -1, -1, 3));
        knowledges.add(new Knowledge("Ext_XiaoFeiJinE", "money1", "支出|消费", "", 6, -1, -1, 3));


    }

    static HashMap<Integer[], String> ident2KL = new HashMap<Integer[], String>();
    static String default_ident = "<?DEFAULT>";
    static {
        ident2KL.put(new Integer[]{0}, "<?KXRZ>");
    }

    public static void main(String[] args) throws Exception {
        String filePath = "data/train/syntax-trans/X";
        regexToSms(filePath, null);
    }

    public static void regexToSms(String filePath, ArrayList<Knowledge> KL_list) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        PrintWriter out = new PrintWriter(new FileOutputStream(filePath + ".pattern1"));
        LinkedList<String> ret = new LinkedList<String>();

        ret.add("<!segPunctution> ::= null");
        ret.add("<!task> ::= " + task);
        ret.add("<!version> ::= " + version);
        ret.add("\n");

        while (true) {
            String line = in.readLine();
            if (line == null) break;
            //for (String seg: line.split("[" + segPunc + "]")) {
            String seg = line;
            ANTLRInputStream input = new ANTLRInputStream(new StringReader(seg));
            RegexLexer regexLexer = new RegexLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(regexLexer);
            RegexParser regexParser = new RegexParser(tokens);
            ParseTree tree = regexParser.s();
            ParseTreeWalker walker = new ParseTreeWalker();
            RegexListenerImpl regexListener = new RegexListenerImpl();
            walker.walk(regexListener, tree);

            StringBuffer patternText = new StringBuffer();
            ArrayList<RE_SEG> s_arr = regexListener.res;
            if (KL_list == null) {
                for (int i = 0; i < s_arr.size(); ++i) {
                    if (i == s_arr.size() - 1 && (s_arr.get(i) instanceof S_WILD)) continue;
                    patternText.append(s_arr.get(i).toString(patternText));
                }
                ret.add("<!DEFAULT> ::= " + patternText.toString() + "\tScore:1:1.0");

            } else {

                List<Integer[]> demands = extractKL(s_arr, KL_list);
                if (demands.size() == 0) continue;

                String identifier = identify(demands);
                StringBuffer patternConstrains = new StringBuffer();
                int s_idx = 0;
                int expand_offset = 0;

                for (int i = 0; i < demands.size(); ++i) {
                    Integer[] interval = demands.get(i);
                    int s_pos = interval[0];
                    int kl_id = interval[1];
                    int l_literal_pos = interval[2];
                    int r_literal_pos = interval[3];

                    if (0 < s_idx && s_idx < l_literal_pos && !endWith(patternText, "<*>")) {
                        patternText.append(new S_WILD());
                        expand_offset++;
                    }

                    s_idx = Math.max(s_idx, l_literal_pos);

                    while (s_idx < r_literal_pos) {
                        RE_SEG re_seg = s_arr.get(s_idx);
                        patternText.append(re_seg.toString(patternText));
                        if (s_idx == s_pos) {
                            patternConstrains.append(KL_list.get(kl_id).toString(expand_offset + re_seg.offset(patternText), re_seg.offset_span));
                        }
                        expand_offset += re_seg.span(patternText);

                        s_idx++;
                    }
                }
                StringBuffer res = new StringBuffer();
                res.append(identifier + " ::= ");
                res.append(patternText);
                res.append("\t");
                if (patternConstrains.length() != 0) {
                    res.append(patternConstrains);
                    res.append("\t");
                }
                res.append("Score:1:1.0");
                ret.add(res.toString());
            }
        }

        int idx = 2;
        for (String group_tag : S_GROUP.group_tags.keySet())
            ret.add(idx++, S_GROUP.group_tags.get(group_tag) + " ::= " + group_tag);

        ret.add("<??金额> ::= <#m>.<#m>|||<#m>");

        for (int i = 0; i < ret.size(); ++i) out.println(ret.get(i));
        out.close();
    }

    private static List<Integer[]> extractKL(ArrayList<RE_SEG> s_arr, ArrayList<Knowledge> KL_list) {
        ArrayList<Integer[]> res = new ArrayList<Integer[]>();
        int start_pos = 0;
        for (int i = 0; i <  s_arr.size(); ++i) {
            if (!( s_arr.get(i) instanceof S_TAG)) continue;
            for (int j = 0; j < KL_list.size(); ++j) {
                int k, l;
                for (k = i - 1; k >= 0; --k) if ( s_arr.get(k) instanceof RE_LITERALS) break;
                for (l = i + 1; l <  s_arr.size(); ++l) if ( s_arr.get(l) instanceof RE_LITERALS) break;
                if (!KL_list.get(j).match( s_arr, k, i, l)) continue;
                //pos, KL-id, L-literal, R-literal
                res.add(new Integer[]{i, j, k, l});
            }
            start_pos +=  s_arr.get(i).span;
        }
        return res;
    }

    private static String identify(List<Integer[]> demands) {
        return default_ident;
    }

    public static boolean endWith(StringBuffer pre, String tail) {
        for (int i = 0; i < tail.length(); ++i) {
            if (pre.length() - 1 - i < 0 || pre.charAt(pre.length() - 1 - i) != tail.charAt(tail.length() - 1 - i)) return false;
        }
        return true;
    }
}

class RE_SEG {
    int span = 1;
    int offset = 0;
    int offset_span = 1;

    public int span(StringBuffer pre) {
        return span;
    }

    public int offset(StringBuffer pre) {
        return offset;
    }

    public String toString(StringBuffer pre) {
        return this.toString();
    }

    public boolean match(String featWord) {
        return true;
    }

    public boolean matchAny(Iterable<String> featWords) {
        for (String featWord: featWords) if (match(featWord)) return true;
        return false;
    }
}

class S_TAG extends RE_SEG {
    String tag_name;
    String sub;

    public S_TAG(String tag_name) {
        this.tag_name = tag_name;
        if (tag_name.startsWith("money")) {
            sub = "<#m><*>.<#m>";
            offset_span = 4;
            span = 4;
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
    String[] choices;
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
        this.choices = this.choice.split("\\|");
        this.left_margin = left_margin;
        this.right_margin = right_margin;
        this.quant = quant;


        if (this.left_margin != null) {
            offset++;
            ++span;
        }

        if (this.right_margin != null) {
            ++span;
        }
    }

    @Override
    public int span(StringBuffer pre) {
        return left_margin != null && RegexToSms.endWith(pre, "<*>") ? span - 1 : span;
    }

    @Override
    public int offset(StringBuffer pre) {
        return left_margin != null && RegexToSms.endWith(pre, "<*>") ? offset - 1 : offset;
    }

    @Override
    public boolean match(String featWord) {
        for (String word: choices) if (word.equals(featWord)) return true;
        return false;
    }

    @Override
    public String toString(StringBuffer pre) {
        //side effect, don't call it unless necessary. todo
        if (!group_tags.containsKey(this.choice)) {
            String tag = this.choices[0];
            if (this.choices.length > 1) tag = "<!" + tag + ">";
            group_tags.put(this.choice, tag);
        }

        StringBuffer sb = new StringBuffer();
        if (left_margin != null && RegexToSms.endWith(pre, "<*>")) {
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

    @Override
    public String toString() {
        //side effect, don't call it unless necessary. todo
        if (!group_tags.containsKey(this.choice)) {
            String tag = this.choices[0];
            if (this.choices.length > 1) tag = "<!" + tag + ">";
            group_tags.put(this.choice, tag);
        }

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

    @Override
    public String toString(StringBuffer pre) {
        if (pre.length() == 0 || RegexToSms.endWith(pre, "<*>")) return "";
        return "<*>";
    }
}

class RE_LITERALS extends RE_SEG {
    public StringBuffer literals;
    public List<String> words;

    public RE_LITERALS(StringBuffer literals) {
        this.literals = literals;
    }

    public List<String> getWords() {
        if (words != null) return words;
        words = MyTokenizer.getInstance().getTokens(literals.toString());
        return words;
    }

    @Override
    public boolean match(String featWord) {
        for (String word: words) if (word.equals(featWord)) return true;
        return false;
    }

    @Override
    public String toString() {
        return literals.toString();
    }
}
