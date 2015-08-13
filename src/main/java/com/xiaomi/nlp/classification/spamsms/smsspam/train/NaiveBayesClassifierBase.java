package com.xiaomi.nlp.classification.spamsms.smsspam.train;

import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;
import com.xiaomi.nlp.classification.spamsms.smsspam.preprocess.RuleManager;

public abstract class NaiveBayesClassifierBase {

    protected RuleManager mRuleManager;

    protected double[] mThresh;

    public RuleManager getRuleManager() {
        return mRuleManager;
    }

    public void setRuleManager(RuleManager ruleManager) {
        mRuleManager = ruleManager;
    }

    public double[] getThresh() {
        return mThresh;
    }

    public void setThresh(double[] thresh) {
        mThresh = thresh;
    }

    public abstract double probDiff(Corpus cps);
}
