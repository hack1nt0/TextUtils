package com.xiaomi.nlp.classification.spamsms.smsspam;

import java.util.ArrayList;
import java.util.List;

public class SundriesManager {
    public static boolean test(List<Corpus> allMsm){
        int count = 0;
        for(Corpus sms : allMsm){
            String str = tryRemove(sms.getOrigBody());
            if(!sms.getOrigBody().equals(str)){
                System.out.println(sms.getOrigBody());
                System.out.println("---------------------");
                System.out.println(str);
                System.out.println("--------------------------------------------------------------------------");
                count++;
            }
        }
        System.out.println("Total:" + allMsm.size() + "  Sundries count:" + count);
        return true;
    }

    public static String tryRemove(String body){
        int endPos = body.length() - 1;
        while(endPos >= 0){
            char c = body.charAt(endPos);
            if(c == 0x0 || c == '\n' || c == ' ' || c == '\t'){
                endPos--;
            }else{
                break;
            }
        }
        body = body.substring(0, endPos + 1);
        List<String> segs = new ArrayList<String>();
        List<Character> splits = new ArrayList<Character>();
        int start = 0, pos = 0;
        while(pos < body.length()){
            if(isCommonMark(body.charAt(pos))){
                segs.add(body.substring(start, pos));
                splits.add(body.charAt(pos));
                start = pos + 1;
            }
            pos++;
        }
        if(start < pos){
            segs.add(body.substring(start, pos));
        }
        boolean has = false;
        for(int i = 0; i < segs.size(); ++i){
            String seg = segs.get(i);
            String sundriesClean = hasSundries(seg);
            if(sundriesClean != null){
                has = true;
                segs.set(i, sundriesClean);
            }
        }

        if(has){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < splits.size(); ++i){
                sb.append(segs.get(i)).append(splits.get(i));
            }
            if(splits.size() < segs.size()){
                sb.append(segs.get(segs.size() - 1));
            }
            return sb.toString();
        }
        return body;
    }

    private static String hasSundries(String str){
        int commCount = 0, sundCount = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); ++i){
            if(Utils.isCommonSymbol(str.charAt(i))){
                commCount++;
                sb.append(str.charAt(i));
                if((i == 0 || !Utils.isCommonSymbol(str.charAt(i - 1))) &&
                   (i == str.length() - 1 || !Utils.isCommonSymbol(str.charAt(i + 1)))){
                    sundCount++;
                }
            }
        }
        boolean has = (sundCount >= 3) && ((1.0 * sundCount / commCount) >= 0.5f);
        if(has){
            return sb.toString();
        }else{
            return null;
        }
    }

    private static boolean isCommonMark(char c){
        return c == ',' || c == '.' || c == ';' || c == ':' || c == '!' ||
               c == '，' || c == '。' || c == '；' || c == '、' || c == '：' || c == '！';
    }
}
