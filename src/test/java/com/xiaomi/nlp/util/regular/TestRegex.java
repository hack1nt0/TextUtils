package com.xiaomi.nlp.util.regular;

import com.xiaomi.nlp.pattern.SynonymDict;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by dy on 15-8-6.
 */
public class TestRegex {


    @org.junit.Test
    public void testLongText() throws IOException {
        System.out.println(Arrays.asList("<#m><*>.<#m>".split(">|<")));
        //String corpus = ".*向你.*付款.*[^\\d]{0,1}(?<money0>(\\d{1,3},)*(\\d{1,10})\\.\\d{2})元";
        //String corpus = "收款(((人民币)|(RMB)|(￥))[^\\d]{0,2})?(?<money0>(\\d{1,3},)*(\\d{1,10})\\.?\\d{1,2})((元)|(人民币)|(RMB))，.*";
        //String corpus = "工商银行收入.*(((人民币)|(RMB)|(￥))[^\\d]{0,2})?(?<money0>(\\d{1,3},)*(\\d{1,10})\\.?\\d{1,2})((元)|(人民币)|(RMB))。.*";
        //String corpus = "工商银行收入（工资）(((人民币)|(RMB)|(￥))[^\\d]{0,2})?(?<money0>(\\d{1,3},)*(\\d{1,10})\\.?\\d{1,2})((元)|(人民币)|(RMB))，";
        String corpus = "您((卡号)|(尾号)|(尾数)|(账号)|(帐户)|(账户)|(账号)|(末.{1}位)|(最后\\d位)|(贵卡)|(卡))[^\\d*]{0,1}(?<card0>[\\d|*]{2,6})([^\\d]{0,2}(卡))?(?<time0>(\\d{2,4}年)?(\\d{1,2}月)?\\d{1,2}[日|号].?\\d{1,2}[：|:]\\d{1,2}).*银行支出\\(跨行汇款\\)(((人民币)|(RMB)|(￥))[^\\d]{0,2})?(?<money0>(\\d{1,3},)*(\\d{1,10})\\.?\\d{1,2})((元)|(人民币)|(RMB))，余额(((人民币)|(RMB)|(￥))[^\\d]{0,2})?(?<money1>(\\d{1,3},)*(\\d{1,10})\\.?\\d{1,2})((元)|(人民币)|(RMB))。.*";
        ANTLRInputStream input = null;
        try {
            input = new ANTLRInputStream(new StringReader(corpus));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create a lexer that feeds off of input CharStream
        RegexLexer regexLexer = new RegexLexer(input);
        // create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(regexLexer);

        // create a parser that feeds off the tokens buffer
        RegexParser regexParser = new RegexParser(tokens);
        ParseTree tree = regexParser.s(); // begin parsing at init rule

        // Create a generic parse tree walker that can trigger callbacks
        ParseTreeWalker walker = new ParseTreeWalker();
        // Walk the tree created during the parse, trigger callbacks
        RegexListenerImpl regexListener = new RegexListenerImpl();
        walker.walk(regexListener, tree);
        System.out.println(regexListener.res);
    }
}
