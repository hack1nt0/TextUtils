package com.xiaomi.nlp.util.regular;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by dy on 15-8-6.
 */
public class TestRegex {


    @org.junit.Test
    public void testLongText() throws IOException {
        String corpus = ".*向你.*付款.*[^\\d]{0,1}(?<money0>(\\d{1,3},)*(\\d{1,10})\\.\\d{2})元";
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
