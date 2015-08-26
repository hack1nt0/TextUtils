package com.xiaomi.nlp.tokenizer;

import org.junit.Test;

import java.io.*;

/**
 * Created by dy on 15-8-6.
 */
public class TestTokenizer {


    @Test
    public void testLongText() throws IOException {
        //MyTokenizer tokenizer = new MyTokenizer("com/xiaomi/nlp/tokenizer/jieba.dict.utf8.sorted", "com/xiaomi/nlp/tokenizer/hmm_model.utf8");
        MyTokenizer tokenizer = MyTokenizer.getInstance();
        String testFilePath = "data/test/testTokenizer.txt";
        String contrastFilePath = "data/ret/tokenizerRet2.txt";
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

    @Test //how to deal with punctuation
    public void testTag() {
        String str = "刘黑虎道：";
        MyTokenizer tokenizer = MyTokenizer.getInstance();
        MyTokenizer.WordWithDebugInfo[] tokens = tokenizer.getTokensWithDebugInfo(str);
        for (MyTokenizer.WordWithDebugInfo w: tokens) System.out.print(w);
        tokenizer.getHmm().printTokenWithTransProb(str, "BMMES");
        tokenizer.getHmm().printTokenWithTransProb(str, "BMESS");
        System.out.println(-1.2603623820268226 - 8.336217 - 0.33344856811948514 - 5.298996);
        System.out.println(-0.33344856811948514-8.823106-0.8085250474669937-5.361877);
        str = "尊敬的客户，,www.baidu.com32 tom hello 中华流前邀您体验\u201c精心购物、倾心享受、贴心服务\u201d的全新乐趣！凭此免费短信可享受以下商铺优惠：\n\n1.【眼镜88】镜架、太阳镜低至5折，购强生博士伦隐形可享折，更有机会获赠三星平板，中华广场3339铺。\n\n2.【熹钰珠宝】凭短信到中华广场首层1052店消费即获得六五折优惠，购满500元可获得88元礼品一份。\n\n3.【力美健】\u2014您最贴心的健身专家！八楼到店贵宾可自选一堂免费体验课程，办理会员卡享全单九五折。\n\n本信息七天内有效，其最终解释权归商铺所有，祝您在中华流前购物愉快。如不愿再次接受本信息，请直接回复N。\u0000\u0000\u0000\u0000\u0000\u0000";
        tokens = tokenizer.getTokensWithDebugInfo(str);
        for (MyTokenizer.WordWithDebugInfo w: tokens) System.out.print(w);
    }
}
