package com.xiaomi.nlp.tokenizer;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by dy on 15-9-9.
 */
public class TestTokenizer {
    @Test
    public void test() throws IOException {
        String corpus = "支出";

        System.out.println(MyTokenizer.getInstance().getTokens(corpus));

    }

}
