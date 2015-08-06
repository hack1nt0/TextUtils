package com.xiaomi.nlp.tokenizer;

import java.io.*;

/**
 * Created by dy on 15-8-6.
 */
public class Test {
    public static void main(String[] args) throws IOException{
        //MyTokenizer tokenizer = new MyTokenizer("com/xiaomi/nlp/tokenizer/jieba.dict.utf8.sorted", "com/xiaomi/nlp/tokenizer/hmm_model.utf8");
        MyTokenizer tokenizer = new MyTokenizer();
        String testFilePath = "data/testTokenizer.txt";
        String contrastFilePath = "data/tokenizerRet.txt";
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(testFilePath)));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream(contrastFilePath)));
        while (true) {
            String line = in.readLine();
            if (line == null) break;
            out.println(line);
            if (line.trim().length() == 0) continue;
            out.print("[");
            for (String token: tokenizer.getTokens(line)) {
                if (token.trim().length() == 0) continue;
                out.print(token + ", ");
            }
            out.println("]");
        }
        out.close();
        in.close();
    }
}
