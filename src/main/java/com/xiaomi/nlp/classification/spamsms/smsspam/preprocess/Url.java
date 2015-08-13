package com.xiaomi.nlp.classification.spamsms.smsspam.preprocess;

import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Url extends RulePrevious {
    private static String REGEX_URL = "((https?|ftp|file)://)?(?<![@|[A-Za-z0-9_]])([[A-Za-z0-9_]-_]+[.])+([a-zA-Z]+)"
            + "(:[1-9]\\d*)?([/][[A-Za-z0-9_]+&#%?=.~_|!]*)*";

    private static String REGEX_IP_URL="(((http(s?)|ftp|file):)?//)?((25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})"
            + "(:[1-9]\\d*)?([/][[A-Za-z0-9_]+&#%?=.~_|!]*)*";

    private Pattern mPattern;
    private Pattern mPatternIP;
    private List<String> mUrls = new ArrayList<String>();

    public Url(){
        mPattern = Pattern.compile(REGEX_URL, Pattern.CASE_INSENSITIVE);
        mPatternIP = Pattern.compile(REGEX_IP_URL, Pattern.CASE_INSENSITIVE);
    }

    protected void reset(){
        super.reset();
        mUrls.clear();
    }

    @Override
    public boolean doFitting(Corpus cps, int[] vals, int start) {
        if(mUrls.size() > 0){
            vals[start]++;
            cps.mUrls = new ArrayList<String>();
            cps.mUrls.addAll(mUrls);
            return true;
        }
        return false;
    }

    protected List<String> process(String str) {
        Matcher matcher = mPattern.matcher(str);
        List<String> ret = new ArrayList<String>();
        int lastEnd = 0;
        while (matcher.find()){
            int start = matcher.start();
            int end = matcher.end();
            if(start > lastEnd){
                ret.add(str.substring(lastEnd, start));
            }
            mUrls.add(str.substring(start, end));
            lastEnd = end;
        }
        if(lastEnd < str.length() - 1){
            ret.add(str.substring(lastEnd));
        }

        List<String> ret1 = new ArrayList<String>();
        for(String s : ret){
            matcher = mPatternIP.matcher(s);
            lastEnd = 0;
            while (matcher.find()){
                int start = matcher.start();
                int end = matcher.end();
                if(start > lastEnd){
                    ret1.add(s.substring(lastEnd, start));
                }
                mUrls.add(s.substring(start, end));
                lastEnd = end;
            }
            if(lastEnd < s.length() - 1){
                ret1.add(s.substring(lastEnd));
            }
        }
        return ret1;
    }

    @Override
    public String getName() {
        return "Url";
    }
}
