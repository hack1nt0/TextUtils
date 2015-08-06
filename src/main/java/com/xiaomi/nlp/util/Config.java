package com.xiaomi.nlp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by DY on 15/2/28.
 */
public class Config {
    static List<String> Colons = new ArrayList<String>();
    static List<String> SentencesSeparators = new ArrayList<String>();
    static List<String> Bullets = new ArrayList<String>();
    static List<String> PriorTemplates = new ArrayList<String>();
    static ACAutomation PriorTemplatesDic;

    String Colon = "(;|；)";
    String Number = "([0-9]+|一|二|三|四|五|六|七|八|九|十)";

    static final String COLON = "<colon>";
    final String COLONBEFORE = "colonBefore";
    final String COLONIS = "colonIs";

    final String SENTENCESEPARATOR = "<sentenceSeparator>";

    static final String BULLET = "<bullet>";

    final String PRIORTEMPLATE = "<priorTemplate>";

    final static String OR = "\\|";
    final static String AND = "\\,";

    static class SentenceIterator implements Iterator<String> {
        String corpus;
        int p = 0, q = -1;
        String nSentence = null;
        private final Pattern sentencePattern;
        private final Matcher sentenceMatcher;

        SentenceIterator(String corpus) {
            this.corpus = corpus;
            sentencePattern = Pattern.compile(join2OrRegExp(SentencesSeparators));
            sentenceMatcher = sentencePattern.matcher(corpus);
        }

        @Override
        public boolean hasNext() {
            while (sentenceMatcher.find()) {
                q = sentenceMatcher.start();
                if (corpus.charAt(q) == '.' && (q == 0 || Character.isDigit(corpus.charAt(q - 1))
                        && (q == corpus.length() - 1 || Character.isDigit(corpus.charAt(q + 1)))))
                    continue;
                nSentence = corpus.substring(p, q);
                p = q + 1;
                return true;
            }
            if (p < corpus.length()) {
                nSentence = corpus.substring(p, corpus.length());
                p = corpus.length();
                return true;
            }
            return false;
        }

        @Override
        public String next() {
            return nSentence;
        }

        @Override
        public void remove() {

        }
    }

    public static List<List<String>> norm(String corpus) {
        List<List<String>> res = new ArrayList<List<String>>();
        SentenceIterator sentenceIterator = new SentenceIterator(corpus);
        while (sentenceIterator.hasNext()) {
            String sentence = sentenceIterator.next();
            if (sentence.trim().length() == 0) continue;

            //有空格作为项目符号后缀特征的情况
            //sentence.replaceAll(" |\\t", "");//todo

            sentence.replaceAll(join2OrRegExp(Colons), COLON);

            //overlay Bullets
            Pattern colonPattern = Pattern.compile(join2OrRegExp(Bullets));
            Matcher colonMatcher = colonPattern.matcher(sentence);
            StringBuffer tmpSentence = new StringBuffer("");
            while (colonMatcher.find()) {
                int end = colonMatcher.end();
                if (sentence.charAt(end - 1) == '.' && (end == sentence.length() || Character.isDigit(sentence.charAt(end))))
                    continue;
                colonMatcher.appendReplacement(tmpSentence, BULLET);
            }
            colonMatcher.appendTail(tmpSentence);
            sentence = tmpSentence.toString();

            //Tokenize PriorTemplates
            List<int[]> tris = PriorTemplatesDic.find(sentence);
            List<String> tokens = new ArrayList<String>();
            for (int i = 0, trii = 0; i < sentence.length();) {
                if (sentence.startsWith(COLON, i)) {
                    tokens.add(COLON);
                    i += COLON.length();
                    continue;
                }
                if (sentence.startsWith(BULLET, i)) {
                    tokens.add(BULLET);
                    i += BULLET.length();
                    continue;
                }
                if (trii < tris.size()) {
                    int[] tri = tris.get(trii);
                    if (i == tri[1]) {
                        tokens.add(PriorTemplates.get(tri[0]));
                        i = tri[2] + 1;
                        ++trii;
                        continue;
                    }
                }
                tokens.add(sentence.charAt(i++) + "");
            }
            res.add(tokens);
        }
        return res;
    }

    public static String join2OrRegExp(List<String> list) {
        StringBuffer sb = new StringBuffer("");
        sb.append(list.get(0));
        for (int i = 1; i < list.size(); ++i) sb.append("|" + list.get(i));
        return sb.toString();
    }

    public Config(String filePath) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(Config.class.getClassLoader().getResourceAsStream(filePath)));
            while (true) {
                String line = in.readLine();
                if (line == null) break;
                if (line.startsWith(COLON)) {
                    while (true) {
                        line = in.readLine();
                        if (line.equals(COLON)) break;
                        String[] tri = line.split(" ");
                        String[] contents = tri[1].split(OR);
                        if (tri[0].startsWith(COLONBEFORE)) {
                            for (String content : contents)
                                Colons.add(content + Colon);
                        } else if (tri[0].startsWith(COLONIS)) {
                            for (String content : contents)
                                Colons.add(content);
                        }
                    }
                } else if (line.startsWith(SENTENCESEPARATOR)) {
                    while (true) {
                        line = in.readLine();
                        if (line.equals(SENTENCESEPARATOR)) break;
                        String[] contents = line.split(OR);
                        for (String c : contents)
                            SentencesSeparators.add(c);
                    }
                } else if (line.startsWith(BULLET)) {
                    while (true) {
                        line = in.readLine();
                        if (line.equals(BULLET)) break;
                        String[] contents = line.split(OR);
                        for (String bullet : contents)
                            Bullets.add(bullet.replaceAll("<number>", Number));
                    }
                } else if (line.startsWith(PRIORTEMPLATE)) {
                    while (true) {
                        line = in.readLine().trim();
                        if (line.length() == 0) continue;
                        if (line.equals(PRIORTEMPLATE)) break;
                        String[] contents = line.split(OR);
                        for (String para : contents) {
                            String[] singles = para.split(AND);
                            for (String s : singles)
                                PriorTemplates.add(s);
                        }
                    }
                }
            }
            PriorTemplatesDic = new ACAutomation(PriorTemplates);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Config config = new Config("string/normDict.txt");
        List<List<String>> res = config.norm("1.234");
        System.out.println(res);
    }
}

