package com.xiaomi.nlp.classification.spamsms.smsspam.preprocess;

import com.xiaomi.nlp.classification.spamsms.android.util.Log;
import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;
import com.xiaomi.nlp.classification.spamsms.smsspam.Options;
import com.xiaomi.nlp.classification.spamsms.smsspam.Utils;
import com.xiaomi.nlp.tokenizer.MyTokenizer;

import java.util.*;

public class RuleManager {
    private MyTokenizer mSeg = MyTokenizer.getInstance();

    public void setSegment(MyTokenizer seg){
        mSeg = seg;
    }

    public MyTokenizer getSeg(){
        return mSeg;
    }

    private static RulePrevious[] mRulesPrevious = null;
    public RuleUpper[] mRulesUpper;

    private int mRulePreCount;
    private int[] mPreStartIndex;

    private int mRuleUpperCount;
    private int[] mUpperStartIndex;

    private ArrayList<String> mNames;

    private boolean mInitialized = false;

    public RuleManager() {
        if(null == mRulesPrevious){
            mRulesPrevious = new RulePrevious[]{
                    new Url(),
                    //new Email(),
                    new Numbers(),
                   };
        }

        mRulesUpper = new RuleUpper[]{
                new SpecificSymbol(),
                new SmsLength()
        };

        if(Options.DEBUG){
            mNames = new ArrayList<String>();
        }
        mRulePreCount = 0;

        mPreStartIndex = new int[mRulesPrevious.length];
        for(int i = 0; i < mRulesPrevious.length; ++i){
            int subCount = mRulesPrevious[i].subClassCount();

            if(Options.DEBUG){
                for(int j = 0; j < subCount; ++j){
                    mNames.add(mRulesPrevious[i].getClassName(j));
                }
            }

            mPreStartIndex[i] = mRulePreCount;
            mRulePreCount += subCount;
        }
    }

    public RuleUpper[] getRulesUpper(){
        return mRulesUpper;
    }

    public String getRuleName(int index){
        return mNames.get(index);
    }

    public int getRuleCount(){
        if(mInitialized){
            return mRuleUpperCount + mRulePreCount;
        }
        else{
            Log.e("RuleManager", "getRuleCount failed for having not be initialized.");
            return 0;
        }
    }

    // Should be called, after load parameters from model file
    public void initUpperRules(){
        mRuleUpperCount = 0;
        mUpperStartIndex = new int[mRulesUpper.length];
        for(int i = 0; i < mRulesUpper.length; ++i){
            int subCount = mRulesUpper[i].subClassCount();

            if(Options.DEBUG){
                for(int j = 0; j < subCount; ++j){
                    mNames.add(mRulesUpper[i].getClassName(j));
                }
            }
            mUpperStartIndex[i] = mRuleUpperCount;
            mRuleUpperCount += subCount;
        }

        mInitialized = true;
    }

    public void preprocess(Corpus cps){
        List<String> splits = new ArrayList<String>();
        splits.add(cps.getOrigBody());
        int[] ruleHits = new int[mRulePreCount];
        for(int i = 0; i < mRulesPrevious.length; ++i){
            mRulesPrevious[i].reset();
            splits = mRulesPrevious[i].process(splits);
            mRulesPrevious[i].fit(cps, ruleHits, mPreStartIndex[i]);
        }
        cps.setProccessedSplits(splits);
        cps.setRules(ruleHits);
    }

    private void doSegment(Corpus cps){
        List<String> splits = cps.getProccessedSplits();
        Set<String> segsList = new TreeSet<String>();
        for(String split : splits){
            String[] segs = mSeg.getTokens(split);
            for(String seg : segs){
                if(Utils.allChineseChar(seg) || mSeg.inDict(seg)){
                    segsList.add(seg);
                }
            }
        }
        cps.setSegments(segsList);
    }

    public void processForTraining(List<Corpus> cpses){
        for(Corpus cps : cpses){
            // Segment should be processed only once, at first bayes-level
            if(cps.getSegments() == null){
                doSegment(cps);
            }
        }

        // Need to do some statics if upper rules haven't been initialized
        for(Corpus cps : cpses){
            for(int i = 0; i < mRulesUpper.length; ++i){
                mRulesUpper[i].doFirstStep(cps);
            }
        }

        // now init Upper rules
        for(int i = 0; i < mRulesUpper.length; ++i){
            mRulesUpper[i].firstStepDone();
        }

        // init upper rule counts
        initUpperRules();

        for(Corpus cps : cpses){
            // Merge previous rules into upper rules
            int[] ruleHits = new int[getRuleCount()];
            int[] pre = cps.getRules();
            for(int i = 0; i < mRulePreCount; ++i){
                ruleHits[i] = pre[i];
            }
            for(int i = 0; i < mRulesUpper.length; ++i){
                mRulesUpper[i].fit(cps, ruleHits, mRulePreCount + mUpperStartIndex[i]);
            }
            cps.setRules(ruleHits);
        }
    }

    /**
     * This method must be called after all rules are ready.
     * getRuleCount() can get right count.
     *
     * @param cps
     */
    public void processForClassify(Corpus cps){
        if(cps.getSegments() == null){
            doSegment(cps);
            List<String> segs = new ArrayList<String>();
            segs.addAll(cps.getSegments());
            Collections.sort(segs);
            cps.setSortedSegments(segs);
        }

        int[] ruleHits = new int[getRuleCount()];
        int[] oldRuleHits = cps.getRules();
        ruleHits = new int[getRuleCount()];
        for(int i = 0; i < mRulePreCount; ++i){
            ruleHits[i] = oldRuleHits[i];
        }

        for(int i = 0; i < mRulesUpper.length; ++i){
            mRulesUpper[i].fit(cps, ruleHits, mRulePreCount + mUpperStartIndex[i]);
        }
        cps.setRules(ruleHits);
    }
}
