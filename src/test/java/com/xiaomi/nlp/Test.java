package com.xiaomi.nlp;

import com.xiaomi.nlp.tokenizer.MyTokenizer;

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

        String str = "尊敬的北京移动客户：北京移动网站5月购机三重特惠火热来袭！一重特惠：周杰伦演唱会门票等你来拿！凡是5月17日前通过网站或拨打1008686成功购买并签收3G手机的客户均可参加抽奖，100%中奖，大奖为580元周杰伦演唱会门票，每天15张，其它为10元电子充值卡。二重特惠：在网站参加预存话费0元换手机独家赠送超市电子券，最高300元！三重特惠：天语小芒果T621安卓智能手机特惠购，仅需299元，不用承诺话费，不怕比价！还有三星智能机全面降价，5寸大屏4核千元智能机惊爆上市，赶快登录www.bj.10086.cn进入“网上营业厅”的“优惠购机”一起疯狂抢购吧！";
        //String str = "1，234，432";
        MyTokenizer myTokenizer = MyTokenizer.getInstance();
//        for (MyTokenizer.WordWithDebugInfo res: myTokenizer.getTokensWithDebugInfo(str))
//            System.out.println(res);
    }

}
