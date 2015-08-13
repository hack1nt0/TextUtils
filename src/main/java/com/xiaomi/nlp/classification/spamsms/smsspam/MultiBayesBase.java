package com.xiaomi.nlp.classification.spamsms.smsspam;

import com.xiaomi.nlp.classification.spamsms.android.content.Context;
import com.xiaomi.nlp.classification.spamsms.smsspam.feature.FeatureManager;

public abstract class MultiBayesBase {
    protected static int misCount = 0;
    protected static int hitCount = 0;
    protected static int hitMore = 0;
    protected FeatureManager mFeatureManager;

    protected static boolean letGoBeforeProcess(Corpus cps){
        int smsType = cps.getSMSType();
        boolean patternMatch = ((smsType & FeatureManager.TYPE_LET_GO) != 0);

        return patternMatch;
    }

    protected static boolean blockBeforeProcess(Corpus cps){
        int smsType = cps.getSMSType();
        boolean patternMatch = ((smsType & FeatureManager.TYPE_BLOCK) != 0);

        return patternMatch;
    }

    protected double[] updateThreshs(double[] threshes, Corpus cps){
        int type = cps.getSMSType();
        int weightSpam = FeatureManager.getSpamWeight(type);
        int weightNormal = FeatureManager.getNormalWeight(type);
        if((type & FeatureManager.TYPE_HAS) == 0 || weightSpam == weightNormal){
            return threshes;
        }
        double[] newThreshes = new double[threshes.length];
        System.arraycopy(threshes, 0, newThreshes, 0, threshes.length);

        double unit = (newThreshes[0] - newThreshes[1]);
        double modif = ((weightNormal - weightSpam) * unit) / FeatureManager.WEIGHT_UNIT;
        for(int i = 0; i < newThreshes.length; ++i){
            newThreshes[i] += modif;
        }
        return newThreshes;
    }

    /**
     * Struct type will be used in pattern's feature,
     * so it should be invoked before apply pattern's feature
     * @param cps
     * @param context
     */
    public void structType(Corpus cps, Context context){
        if(Utils.allSmallFragment(cps.getProccessedSplits())){
            cps.addStructType(Corpus.STRUCTURE_TYPE_ALL_SMALL_SEGS);
        }

        if(hasPhish(cps, context)){
            cps.addStructType(Corpus.STRUCTURE_TYPE_HAS_PHISH);
        }
        return;
    }

    protected String getPattern(Context context){
        return ModelLoader.getPattern(context);
    }

    protected void applyPattern(Corpus cps, Context context){
        if(mFeatureManager == null){
            String pattern = getPattern(context);
            if(pattern != null){
                mFeatureManager = new FeatureManager();
                mFeatureManager.loadFeature(pattern);
            }
        }

        if(mFeatureManager != null && mFeatureManager.inited()){
            int type = mFeatureManager.smsType(cps);
            cps.setSMSType(type);
        }
    }

    protected abstract boolean hasPhish(Corpus cps, Context context);
}
