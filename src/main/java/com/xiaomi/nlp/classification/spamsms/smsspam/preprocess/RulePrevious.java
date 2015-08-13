package com.xiaomi.nlp.classification.spamsms.smsspam.preprocess;

import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;

import java.util.ArrayList;
import java.util.List;

public abstract class RulePrevious extends Rule {
    
    protected boolean mHit = false;

    protected void reset(){
        mHit = false;
    }

    public boolean fit(Corpus cps, int[] vals, int start){
        if(null == vals || start >= vals.length){
            return false;
        }
        return doFitting(cps, vals, start);
    }

    public abstract boolean doFitting(Corpus cps, int[] vals, int start);

    protected List<String> process(String str){
        List<String> ret = new ArrayList<String>();
        ret.add(str);
        return ret;
    }

    public List<String> process(List<String> strs){
        List<String> ret = new ArrayList<String>();
        for(String s : strs){
            ret.addAll(process(s));
        }
        return ret;
    }
}
