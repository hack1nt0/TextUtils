package com.xiaomi.nlp.pattern;

import com.xiaomi.nlp.util.LazyList;
import com.xiaomi.nlp.util.sms.SmsBaseListener;
import com.xiaomi.nlp.util.sms.SmsLexer;
import com.xiaomi.nlp.util.sms.SmsParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * Created by DY on 15/3/20.
 */
public abstract class SmsPattern implements Comparable<SmsPattern> {
    private static Logger logger = Logger.getLogger(MiningPatterns.class);
    static {
        logger.setLevel(Level.ERROR);
    }
    public LazyList<SmsPattern> chds = new LazyList<SmsPattern>();
    public Map<Integer,Map<String,List<Integer>>> wildcards; // [pos, [content, corpusId]]
    //public HashSet<SmsPattern> sourceIndex = new HashSet<SmsPattern>(Arrays.asList(this)); // all corpus containing this tokens, whose size indicates the tokens's support rating
    public BitSet sourceIndex = new BitSet(0);
    public SmsPattern lc, rc;
    public int sizeInToken;
    public int id;
    public int corpusId = -1;
    public boolean used = false;
    //public int baseSup;
    public SmsPattern origin;//pattern's origin(for the purpose of updating the wildcards)

    //for hashCode and equals
    public String plainString;

    public void reset() {
        used = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmsPattern that = (SmsPattern) o;

        if (this.plainString == null) plainString = this.toString();
        if (that.plainString == null) plainString = that.toString();
        return plainString.equals(that.plainString);

    }

    @Override
    public int hashCode() {
        if (this.plainString == null) plainString = this.toString();
        return plainString.hashCode();
    }

    public BitSet getSource() {
        return sourceIndex;
    }

    public void setSource(BitSet sourceIndex) {
        this.sourceIndex = sourceIndex;
    }

    public void addSource(BitSet deltaSourceIndex) {
        this.sourceIndex.or(deltaSourceIndex);
    }

    public void addSource(int singleSource) {
        this.sourceIndex.set(singleSource);
    }

    public SmsPattern get(int cur) {
        return chds.get(cur);
    }

    public void add(SmsPattern cur) {
        chds.add(cur);
        sizeInToken += cur.getSizeInToken();
    }

    public void addAll(List<BulletSent> list) {
        for (BulletSent bulletSent : list)
            this.add(bulletSent);
    }

    public int size() {
        /*
        if (chds == null) {
            System.out.println("spot:[" + this + "]");
        }*/
        //return chds == null ? 0 : chds.size();
        return chds.size();
    }

    public int getSizeInToken() {
        if (sizeInToken != 0) return sizeInToken;
        int res = 0;
        //if (chds == null) return 0;
        for (SmsPattern smsPattern: chds)
            res += smsPattern.getSizeInToken();
        sizeInToken = res;
        return res;
    }

    private int finalSup;
    public void setFinalSup(int value) {
        this.finalSup = value;
    }

    public int getSup() {
        return this.finalSup;
    }

    public int getSup(Map<Integer, Integer> baseSupMap, int from) {
        //if (sourceIndex == null) return baseSup;
        int res = 0;
        for (int cand = sourceIndex.nextSetBit(0); cand >= 0; cand = sourceIndex.nextSetBit(cand + 1))
            res += baseSupMap.get(cand + from);
        return res;
    }

    public String toString() {return "[abstract smsPattern]";}

    @Override
    public int compareTo(SmsPattern o) {
//        int la = (int)Math.sqrt(getSizeInToken()) * getSup();
//        int lb = (int)Math.sqrt(o.getSizeInToken()) * o.getSup();
        int la = this.getSizeInToken();
        int lb = o.getSizeInToken();
        return lb - la;
    }

    public boolean isSubpatOfFacade(SmsPattern other) {
        logger.info("Began predicting sub-pattern relation of\n" + this + "\n" + other);
        boolean ret = this.isSubpatOf(other);
        logger.info("Ended predicting sub-pattern relation of\n" + this + "\n" + other);
        return ret;
    }

    public boolean isSubpatOf(SmsPattern other) {
        if (this.size() > other.size()) return false;
        for (int i = 0, j = 0; i < this.size();) {
            while (j < other.size() && !this.get(i).isSubpatOf(other.get(j)))
                ++j;
            if (j == other.size()) break;
            ++i;
            if (i == this.size()) return true;
        }
        return false;
    }

    public void updWildcards() {
        for (int i = 0; i < this.size(); ++i)
            this.get(i).updWildcards();
    }

    public boolean isSubstrOfFacade(SmsPattern other) {
        logger.info("Began predicting sub-str relation of\n" + this + "\n" + other);
        boolean ret = this.isSubstrOf(other);
        logger.info("Ended predicting sub-str relation of\n" + this + "\n" + other);
        return ret;
    }

    public boolean isSubstrOf(SmsPattern other) {
        SmsPattern A = this, B = other;
        int N = A.size(), M = B.size();
        if (N == 0) return true;
        if (N > M) return false;
        for (int i = 0, j = 0; i < N; ) {
            if (A.get(i).size() == 0) {
                ++i;
                continue;
            }
            while (j < M && (!A.get(i).isSubstrOf(B.get(j)) || B.get(j).size() == 0)) ++j;
            if (M <= j) {
                return false;
            }
            ++i;
            ++j;
        }
        return true;
    }

    public static SmsPattern getNew(String corpus) {
        ANTLRInputStream input = null;
        try {
            input = new ANTLRInputStream(new StringReader(corpus));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create a lexer that feeds off of input CharStream
        SmsLexer smsLexer = new SmsLexer(input);
        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(smsLexer);
        for (int i = 0; i < tokens.size(); ++i)
            System.out.println(tokens.get(i));

        // create a parser that feeds off the tokens buffer
        SmsParser smsParser = new SmsParser(tokens);
        ParseTree tree = smsParser.s(); // begin parsing at init rule

        // Create a generic parse tree walker that can trigger callbacks
        ParseTreeWalker walker = new ParseTreeWalker();
        // Walk the tree created during the parse, trigger callbacks
        SmsPattern sms = new Sms();
        walker.walk(new TreeLinear(sms), tree);

        try {
            sms = nest(0, true, sms.chds.size(), sms);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return sms;
    }

    private static SmsPattern nest(int L, boolean lBulletValid, int R, SmsPattern sms) throws IllegalAccessException, InstantiationException {
        SmsPattern ret = new Sms();
        if (L + 1 == R) {
            SmsPattern cur = sms.get(L);
            if (lBulletValid) return cur;
            else ret = new OrdSent();
            ret.chds = cur.chds;
            return ret;
        }

        int nL = L;

        while (nL < R) {
            SmsPattern cur = sms.get(nL);
            if ((nL != L || lBulletValid) && cur instanceof BulletSent) break;
            if (nL == L && !lBulletValid) {
                cur = new OrdSent();
                cur.chds = sms.get(L).chds;
            }
            ret.add(cur);
            ++nL;
        }

        if (nL < R) {

            List<BulletSent> list = new ArrayList<BulletSent>();
            BulletSent preBullet = (BulletSent) sms.get(nL);
            //list.add(preBullet);
            List<Integer> bulletIndex = new ArrayList<Integer>();
            bulletIndex.add(nL);

            //int candR = R;
            for (int i = nL + 1; i < R; ++i) {
                if (!(sms.get(i) instanceof BulletSent)) continue;
                BulletSent cur = (BulletSent) sms.get(i);
                if (!isNextBullet(cur.bullet, preBullet.bullet)) {
                    //candR = i;
                    continue;
                }
                preBullet = cur;
                bulletIndex.add(i);
            }
            //if (candR < bulletIndex.get(bulletIndex.size() - 1)) candR = R;
            bulletIndex.add(R);

            for (int i = 0; i < bulletIndex.size() - 1; ++i) {
                SmsPattern tmp = nest(bulletIndex.get(i), false, bulletIndex.get(i + 1), sms);
                BulletSent nbulletSent = new BulletSent();
                nbulletSent.bullet = ((BulletSent)(sms.get(bulletIndex.get(i)))).bullet;
                nbulletSent.chds = new LazyList<SmsPattern>(Arrays.asList(tmp));
                list.add(nbulletSent);
            }
            SmsPattern nSms = new Sms();
            nSms.addAll(list);
            ret.add(nSms);
            //nL = candR;
        }

        return ret;
    }

    private static boolean isNextBullet(String curBulletText, String preBulletText) {
        curBulletText = curBulletText.trim();
        preBulletText = preBulletText.trim();
        int La = curBulletText.length();
        int Lb = preBulletText.length();
        int na = 0, nb = 0;
        for (int la = 0, lb = 0; la < La && lb < Lb;) {
            while (la < La && Character.isDigit(curBulletText.charAt(la))) {
                na = na * 10 + curBulletText.charAt(la) - '0';
                ++la;
            }
            while (lb < Lb && Character.isDigit(preBulletText.charAt(lb))) {
                nb = nb * 10 + preBulletText.charAt(lb) - '0';
                ++lb;
            }
            if (la < La && lb == Lb || la == La && lb < Lb) return false;
            if (la == La && lb == Lb) break;
            char ca = curBulletText.charAt(la);
            char cb = preBulletText.charAt(lb);
            if (ca != cb) return false;
            ++la;
            ++lb;
        }
        return na == nb + 1;
    }

    static class TreeLinear extends SmsBaseListener {
        SmsPattern sms = null;
        public TreeLinear(SmsPattern sms) {
            this.sms = sms;
        }

        @Override
        public void enterColonSent(@NotNull SmsParser.ColonSentContext ctx) {
            if (ctx.token() == null || ctx.token().size() == 0) return;
            ColonSent colonSent = new ColonSent();
            for (SmsParser.TokenContext tokenContext: ctx.token()) {
                colonSent.add(new Token(tokenContext.getText()));
            }
            colonSent.COLONTEXT = ctx.colonToken().getText();
            sms.add(colonSent);
        }

        @Override
        public void enterBulletSent(@NotNull SmsParser.BulletSentContext ctx) {
            if (ctx.token() == null || ctx.token().size() == 0) return;
            BulletSent bulletSent = new BulletSent();
            for (SmsParser.TokenContext tokenContext: ctx.token()) {
                bulletSent.add(new Token(tokenContext.getText()));
            }
            bulletSent.bullet = ctx.bullet().getText();
            sms.add(bulletSent);
        }

        @Override
        public void enterOrdSent(@NotNull SmsParser.OrdSentContext ctx) {
            if (ctx.token() == null || ctx.token().size() == 0) return;
            OrdSent ordSent = new OrdSent();
            for (SmsParser.TokenContext tokenContext: ctx.token()) {
                ordSent.add(new Token(tokenContext.getText()));
            }
            sms.add(ordSent);
        }
    }

    public SmsPattern getLctpWithFacade(SmsPattern other) {
        logger.info("Began Lctp extraction of\n" + this + "\n" + other);
        SmsPattern ret = this.getLctpWith(other);
        logger.info("Ended Lctp extraction of\n" + this + "\n" + other);
        return ret;
    }

    public SmsPattern getLctpWith(SmsPattern other) {
        return null;
    }
}

class Token extends SmsPattern {
    public String text;
    public final static Token TOKEN_NULL = new Token("");

    public Token(String text) {
        super();
        this.text = text;
    }

    @Override
    public int compareTo(SmsPattern o) {
        return text.compareTo(((Token)o).text);
    }

    @Override
    public String toString() {
        return text + ' ';
    }

    public String toString(boolean x) {
        return this.toString();
    }

    @Override
    public int getSizeInToken() {
        //return Math.min(text.length(), 1);
        return 1;
    }

    @Override
    public SmsPattern getLctpWith(SmsPattern other) {
        if (other instanceof Token)
            return this.compareTo(other) == 0 ? this : TOKEN_NULL;
        return other.getLctpWith(this);
    }
}

class OrdSent extends SmsPattern {
    //public static String DELIMITER = ",";
    public final static OrdSent EMPTY_ORDSENT = new OrdSent();
    public String punc;

    public String getPunc() {
        return punc;
    }

    public void setPunc(String punc) {
        this.punc = punc;
    }

    @Override
    public boolean isSubstrOf(SmsPattern other) {
        if (!(other instanceof OrdSent)) return false;
        return SmsPatterns.isSubstr(this, other);
    }

    @Override
    public boolean isSubpatOf(SmsPattern other) {
        if (other instanceof Sms) {
            for (SmsPattern o: other.chds)
                if (this.isSubpatOf(o)) return true;
            return false;
        }
        if (!(other instanceof OrdSent)) return false;
        return SmsPatterns.isSubpat(this, other);
    }

    @Override
    public void updWildcards() {
        SmsPatterns.updWildcards(this, this.origin);
    }


    @Override
    public int getSizeInToken() {
        return chds.size();
    }

    @Override
    public String toString() {
        //if (this.size() == 0) return "<Empty OrdSent>+";
        //StringBuffer res = new StringBuffer("<ordSent>");
        if (this.size() == 0) return "[]";
        StringBuffer res = new StringBuffer("\"");
        for (int i = 0; i <= this.size(); ++i) {
            if (wildcards != null && wildcards.containsKey(i) && wildcards.get(i).size() > 1) {
                res.append("<*: ");
//                for (String candW : wildcards.get(i).keySet())
//                    res.append(candW).append("|");
                res.replace(res.length() - 1, res.length(), ">");
            }
            if (i < this.size()) res.append(this.get(i));
        }
        //res.append("</ordSent>");
        res.append("\"");
        //res.append(DELIMITER);
        return res.toString();
    }


    @Override
    public SmsPattern getLctpWith(SmsPattern other) {
        if (other instanceof Sms) {
            SmsPattern res = OrdSent.EMPTY_ORDSENT;
            for (SmsPattern o: other.chds) {
                SmsPattern tmp = this.getLctpWith(o);
                if (tmp.compareTo(res) > 0) res = tmp;
            }
            return res;
        }
        if (!(other instanceof OrdSent))
            return OrdSent.EMPTY_ORDSENT;
        return SmsPatterns.getLcp(this, other);
    }
}

class ColonSent extends SmsPattern {
    String COLONTEXT = null;
    String COLONNORM = "<:> ";

    @Override
    public boolean isSubstrOf(SmsPattern other) {
        if (!(other instanceof ColonSent)) return false;
        return SmsPatterns.isSubstr(this, other);
    }

    @Override
    public boolean isSubpatOf(SmsPattern other) {
        if (other instanceof Sms) {
            for (SmsPattern o: other.chds)
                if (this.isSubpatOf(o)) return true;
            return false;
        }
        if (!(other instanceof ColonSent)) return false;
        return SmsPatterns.isSubpat(this, other);
    }

    @Override
    public void updWildcards() {
        SmsPatterns.updWildcards(this, this.origin);
    }

    @Override
    public int getSizeInToken() {
        return chds.size();
    }

    @Override
    public String toString() {
        //if (this.size() == 0) return "<Empty ColonSent>+";
        //StringBuffer res = new StringBuffer("<colonSent>");

        if (this.size() == 0) return "[]";
        StringBuffer res = new StringBuffer("\"");
        for (int i = 0; i <= this.size(); ++i) {
            if (wildcards != null && wildcards.containsKey(i) && wildcards.get(i).size() > 1) {
                res.append("<*: ");
//                for (String candW : wildcards.get(i).keySet())
//                    res.append(candW).append("|");
                res.replace(res.length() - 1, res.length(), ">");
            }
            if (i < this.size()) res.append(this.get(i));
        }
        res.append(COLONNORM);
        res.append("\"");
        //res.append(",");
        //res.append("</colonSent>");
        return res.toString();
    }

    @Override
    public SmsPattern getLctpWith(SmsPattern other) {
        if (other instanceof Sms) {
            SmsPattern res = OrdSent.EMPTY_ORDSENT;
            for (SmsPattern o: other.chds) {
                SmsPattern tmp = this.getLctpWith(o);
                if (tmp.compareTo(res) > 0) res = tmp;
            }
            return res;
        }
        if (!(other instanceof ColonSent))
            return OrdSent.EMPTY_ORDSENT;
        return SmsPatterns.getLcp(this, other);
    }
}

class BulletSent extends SmsPattern {
    String bullet;

    @Override
    public boolean isSubstrOf(SmsPattern other) {
        if (other instanceof Sms) {
            for (SmsPattern o: other.chds)
                if (this.isSubstrOf(o)) return true;
            return false;
        }
        if (!(other instanceof BulletSent)) return false;
        return super.isSubstrOf(other);
    }

    @Override
    public boolean isSubpatOf(SmsPattern other) {
        if (other instanceof Sms) {
            for (SmsPattern o: other.chds)
                if (this.isSubpatOf(o)) return true;
            return false;
        }
        if (!(other instanceof BulletSent)) return false;
        return super.isSubpatOf(other);
    }



    @Override
    public int getSizeInToken() {
        return chds.size();
    }

    @Override
    public String toString() {
        //StringBuffer res = new StringBuffer("<bulletSent>");
        if (this.size() == 0) return "{}";
        StringBuffer res = new StringBuffer("{");
        //res.append("<bullet:" + bullet + "> ");
        res.append(bullet == null ? "\"BULLET\"": "\"" + bullet + "\"");
        res.append(":");
        for (int i = 0; i < this.size(); ++i)
            res.append(this.get(i));
        //res.append("</bulletSent>");
        res.append("}");
        return res.toString();
    }

    @Override
    public SmsPattern getLctpWith(SmsPattern other) {
        if (other instanceof Sms) {
            SmsPattern res = OrdSent.EMPTY_ORDSENT;
            for (SmsPattern o: other.chds) {
                SmsPattern tmp = this.getLctpWith(o);
                if (tmp.compareTo(res) > 0) res = tmp;
            }
            return res;
        }
        if (!(other instanceof BulletSent))
            return OrdSent.EMPTY_ORDSENT;
        //return super.getLctpWith(other);
        SmsPattern A = this, B = other;
        int N = A.size(), M = B.size();
        int[][] Dp = new int[N + 2][M + 2];
        SmsPattern[][] commonTokens = new SmsPattern[N][M];
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < M; ++j) commonTokens[i][j] = A.get(i).getLctpWith(B.get(j));

        for (int i = 1; i <= A.size(); ++i) {
            for (int j = 1; j <= B.size(); ++j) {
                int res = 0;
                res = Math.max(Dp[i - 1][j - 1] + commonTokens[i - 1][j - 1].getSizeInToken(), res);
                res = Math.max(Dp[i - 1][j], res);
                res = Math.max(Dp[i][j - 1], res);
                Dp[i][j] = res;
            }
        }

        List<SmsPattern> smsPatternList = new ArrayList<SmsPattern>();

        for (int i = A.size(), j = B.size(); 0 < i && 0 < j; ) {
            if (Dp[i][j] == Dp[i - 1][j - 1] + commonTokens[i - 1][j - 1].getSizeInToken()) {
                smsPatternList.add(commonTokens[i - 1][j - 1]);
                --i;
                --j;
                continue;
            }
            if (Dp[i][j] == Dp[i - 1][j]) --i;
            else --j;
        }

        Collections.reverse(smsPatternList);

        if (A.sourceIndex == null)
            if (B.sourceIndex != null) throw new RuntimeException("sourceIndex of A and B isn't consistent!");

        SmsPattern res = new BulletSent();
        res.chds = new LazyList<SmsPattern>(smsPatternList);
        //res.addSource(A.sourceIndex);
        //res.addSource(B.sourceIndex);
        res.lc = A;
        res.rc = B;
        return res;
    }
}

class Sms extends SmsPattern {

    @Override
    public boolean isSubstrOf(SmsPattern other) {
        if (!(other instanceof Sms))
            return other.isSubstrOf(this);
        return super.isSubstrOf(other);
    }

    @Override
    public boolean isSubpatOf(SmsPattern other) {
        if (!(other instanceof Sms))
            return other.isSubpatOf(this);
        return super.isSubpatOf(other);
    }

    @Override
    public String toString() {
        //StringBuffer res = new StringBuffer("<sms>");
        if (this.size() == 0) return "[]";
        StringBuffer res = new StringBuffer("[");
        for (int i = 0; i < this.size(); ++i) {
            res.append(this.get(i));
            if (i != this.size() - 1) res.append(",");
        }
        //res.append("</sms>");
        res.append("]");
        return res.toString();
    }

    @Override
    public int getSizeInToken() {
        int res = 0;
        for (SmsPattern chd: chds)
            res += chd.getSizeInToken();
        return res;
    }

    @Override
    public SmsPattern getLctpWith(SmsPattern other) {
        if (!(other instanceof Sms))
            return OrdSent.EMPTY_ORDSENT;
        //return super.getLctpWith(other);
        SmsPattern A = this, B = other;
        int N = A.size(), M = B.size();
        int[][] Dp = new int[N + 2][M + 2];
        SmsPattern[][] commonTokens = new SmsPattern[N][M];
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < M; ++j) commonTokens[i][j] = A.get(i).getLctpWith(B.get(j));

        for (int i = 1; i <= A.size(); ++i) {
            for (int j = 1; j <= B.size(); ++j) {
                int res = 0;
                res = Math.max(Dp[i - 1][j - 1] + commonTokens[i - 1][j - 1].getSizeInToken(), res);
                res = Math.max(Dp[i - 1][j], res);
                res = Math.max(Dp[i][j - 1], res);
                Dp[i][j] = res;
            }
        }

        List<SmsPattern> smsPatternList = new ArrayList<SmsPattern>();

        for (int i = A.size(), j = B.size(); 0 < i && 0 < j; ) {
            if (Dp[i][j] == Dp[i - 1][j - 1] + commonTokens[i - 1][j - 1].getSizeInToken()) {
                smsPatternList.add(commonTokens[i - 1][j - 1]);
                --i;
                --j;
                continue;
            }
            if (Dp[i][j] == Dp[i - 1][j]) --i;
            else --j;
        }

        Collections.reverse(smsPatternList);

        if (A.sourceIndex == null)
            if (B.sourceIndex != null) throw new RuntimeException("sourceIndex of A and B isn't consistent!");

        SmsPattern res = new Sms();
        res.chds = new LazyList<SmsPattern>(smsPatternList);
        //res.addSource(A.sourceIndex);
        //res.addSource(B.sourceIndex);
        res.lc = A;
        res.rc = B;
        return res;
    }

}


