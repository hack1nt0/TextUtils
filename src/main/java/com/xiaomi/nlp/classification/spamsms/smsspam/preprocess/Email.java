package com.xiaomi.nlp.classification.spamsms.smsspam.preprocess;

import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Email extends RulePrevious {
    private static String REGEX_MAIL;

    static {
        REGEX_MAIL = "([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)";
    }

    private Pattern mPattern;

    private List<String> mMails = new ArrayList<String>();

    public Email(){
        mPattern = Pattern.compile(REGEX_MAIL);
    }


    protected void reset(){
        super.reset();
        mMails.clear();
    }

    @Override
    public boolean doFitting(Corpus cps, int[] vals, int start) {
        if(mMails.size() > 0){
            vals[start]++;
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
            mMails.add(str.substring(start, end));
            lastEnd = end;
        }
        if(lastEnd < str.length() - 1){
            ret.add(str.substring(lastEnd));
        }

        return ret;
    }

    
    @Override
    public String getName() {
        return "Email";
    }
}
