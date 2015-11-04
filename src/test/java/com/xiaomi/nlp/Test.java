package com.xiaomi.nlp;

import java.util.Arrays;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dy on 15-8-20.
 */
public class Test {
    static abstract class A {
        public A() {System.out.println("A");}
    }
    static class B extends A {
        //public B() {System.out.println("B");}
    }
    public static void main(String[] args) {

        //String str = "尊敬的北京移动客户：北京移动网站5月购机三重特惠火热来袭！一重特惠：周杰伦演唱会门票等你来拿！凡是5月17日前通过网站或拨打1008686成功购买并签收3G手机的客户均可参加抽奖，100%中奖，大奖为580元周杰伦演唱会门票，每天15张，其它为10元电子充值卡。二重特惠：在网站参加预存话费0元换手机独家赠送超市电子券，最高300元！三重特惠：天语小芒果T621安卓智能手机特惠购，仅需299元，不用承诺话费，不怕比价！还有三星智能机全面降价，5寸大屏4核千元智能机惊爆上市，赶快登录www.bj.10086.cn进入“网上营业厅”的“优惠购机”一起疯狂抢购吧！";
        String str = "【 苏宁 】 苏宁 易购 8 月 18 日起 五天 五夜 店庆 狂";
        String S = "xiaomimiliao";
        System.out.println(rearrange(S));

        Pattern pattern = Pattern.compile("\\-[\\\\]");
        Matcher matcher = pattern.matcher("-\\");
        System.out.println(matcher.matches());
        System.out.println(matcher.group(0));
    }

    public static String rearrange(String S) {
        //special cases
        if (S.length() <= 1) return S;

        //find the middle one(s)
        char[] s = S.toCharArray();
        Arrays.sort(s);//for brevity, where the bucket sorting can be used
        int lMid = -1, rMid = -1;
        if (s.length % 2 == 0) {
            lMid = s.length / 2 - 1;
            rMid = lMid + 1;
        } else {
            lMid = s.length / 2 - 1;
            rMid = lMid + 2;
        }
        int lNum = 0, rNum = 0, i = 0;
        for (i = lMid; i >= 0 && s[i] == s[lMid]; --i, ++lNum);
        for (i = rMid; i < s.length && s[i] == s[rMid]; ++i, ++rNum);

        //the middle one(s) are split
        if (s[lMid] != s[rMid] || lNum == 0 || rNum == 0) return new String(s);

        //the middle one(s) aren't split
        for (int j = rMid; j < Math.min(lNum, rNum); ++j, ++i) {
            if (i >= s.length) return null;//无解的情况
            char tmp = s[j];
            s[j] = s[i];
            s[i] = tmp;
        }
        return new String(s);
    }
}
