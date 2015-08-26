package com.xiaomi.nlp.classification.spamsms.smsspam;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    
    public static final int SPAM = 0;
    public static final int NORMAL = 1;
    public static final int CLASS_COUNT = 2;

    private static final double MIN_DOUBLE = 0.0000000000001;
    public static double getEntropy(int total, int spam){
        double pSpam = 1.0 * spam / total;
        double pNormal = 1.0 * (total - spam) / total;
        double entropy = -pSpam * log2(pSpam) - pNormal * log2(pNormal);
        return entropy;
    }

    public static double log2(double v){
        if(Math.abs(v) < MIN_DOUBLE){
            return Math.log(MIN_DOUBLE) / Math.log(2);
        }
        return Math.log(v) / Math.log(2);
    }

    public static double negLog(double val){
        return Math.log(1 - Math.exp(val));
    }

    public static boolean goodInfo(double ig, int s, int n, int total){
        int big = s > n ? s : n;
        int small = s > n ? n : s;
        small = small > 0 ? small : 1;
        double r = 1.0 * big / small;
        if((ig > 0.01 && r >= 2) || (ig > 0.001 && r >= 4) ||
                (ig > 0.0001 && r >= 8) ||(1.0 * (s + n) / total > 0.01 && r > 8)){
            return true;
        }
        return false;
    }

    public static boolean isCommonSymbol(char c){
        return (0x4E00 <= c && c <= 0x9FFF) || ('0' <= c && c <= '9')
                || ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z')
                || ('０' <= c && c <= '９');
    }

    public static boolean isChineseChar(char c){
        return (0x4E00 <= c && c <= 0x9FFF);
    }

    public static boolean isCommonChar(char c){
        return isCommonSymbol(c) || c == '.' || c == ' ' || c == ' ';
    }

    public static boolean isDigit(char c){
        return c >= '0' && c <= '9';
    }

    public static boolean allChineseChar(String str){
        for(int i = 0; i < str.length(); ++i){
            if(!isChineseChar(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    public static boolean startWidth(String addr, String reg){
        int i = 0;
        while( i < addr.length() && (addr.charAt(i) < '0' || addr.charAt(i) > '9')){
            i++;
        }
        if(i >= addr.length()){
            return false;
        }
        boolean ret = true;
        for(int j = 0; j < reg.length() && j + i < addr.length(); ++j){
            if(addr.charAt(i + j) != reg.charAt(j)){
                ret = false;
                break;
            }
        }
        return ret;
    }

    public static String getUrlMain(String url){
        String newUrl = url;
        if(url.indexOf("//") != -1){
            newUrl = url.substring(url.indexOf("//") + 2);
        }
        if(newUrl.indexOf("/") != -1){
            newUrl = newUrl.substring(0, newUrl.indexOf("/"));
        }
        return newUrl;
    }

    public static boolean allSmallFragment(List<String> strs){
        List<String> strsChar = new ArrayList<String>();
        List<String> strsSymb = new ArrayList<String>();
        int len = 0;
        for(String s : strs){
            if (s == null || s.length() == 0) continue; //todo dy
            len += s.length();
            int pos = 0, last = 0;
            boolean flag = Utils.isCommonChar(s.charAt(pos));
            pos++;
            while(pos < s.length()){
                if(flag != Utils.isCommonChar(s.charAt(pos))){
                    if(flag){
                        strsChar.add(s.substring(last, pos));
                    }else{
                        strsSymb.add(s.substring(last, pos));
                    }
                    flag = !flag;
                    last = pos;
                }
                pos++;
            }
            if(flag){
                strsChar.add(s.substring(last));
            }else{
                strsSymb.add(s.substring(last));
            }
        }

        int longCount = 0;
        int longest = 0;

        int charCount = 0;
        for(String s : strsChar){
            if(longest < s.length()){
                longest = s.length();
            }
            if(s.length() > 3){
                longCount++;
            }
            charCount += s.length();
        }
        int serialSymb = 0;
        for(String s : strsSymb){
            if(s.length() > 1){
                serialSymb++;
            }
        }
        if((strsChar.size() > len / 4 || charCount * 3 < len) && longCount <= 1 && longest < 8
                && serialSymb * 2 > strsSymb.size()){
            return true;
        }

        return false;
    }
}

class LongFloat{
    private int mE;
    private double mD;
    public LongFloat(int e, double d){
        mD = d;
        mE = e;
    }

    public LongFloat(double d){
        mD = d;
        mE = 0;
        if(mD > 0.0){
            while(mD < 1.0){
                mD *= 10;
                mE--;
            }
            while(mD > 10){
                mD /= 10;
                mE++;
            }
        }
    }

    public double div(LongFloat lf){
        int e = mE - lf.mE;
        double d = mD / lf.mD;
        while(e < 0){
            d /= 10;
            e++;
        }
        while(e > 0){
            d *= 10;
            e--;
        }
        return d;
    }

    public void multiply(double d){
        mD *= d;
        while(mD < 1.0){
            mD *= 10;
            mE--;
        }
        while(mD > 10){
            mD /= 10;
            mE++;
        }
    }

    public boolean compare(LongFloat lf){
        if(mE > lf.mE){
            return true;
        }else if(mE < lf.mE){
            return false;
        }else{
            return mD > lf.mD;
        }
    }

    protected Object copy() {
        LongFloat lf = new LongFloat(0.0);
        lf.mD = this.mD;
        lf.mE = this.mE;
        return lf;
    }

    @Override
    public String toString() {
        return "[" + mD + "," + mE + "]";
    }
    
}

