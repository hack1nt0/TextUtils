package com.xiaomi.nlp.classification.spamsms.smsspam.preprocess;

import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;
import com.xiaomi.nlp.classification.spamsms.smsspam.Utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Assume the length of spam of SMS is longer than normal
public class SmsLength extends RuleUpper {
    List<int[]> mStatics = null;
    int mTotal = 0;
    int mSpamCount = 0;
    int mDivideLength = 0;

    private static final int HIT = 0;
    private static final int MISS = 1;

    @Override
    public boolean doFitting(Corpus cps, int[] vals, int start){
        if(length(cps) >= mDivideLength){
            vals[start]++;
            return true;
        }
        return false;
    }

    @Override
    public void firstStepDone(){
        List<Double> igs = new ArrayList<Double>();
        double entropySpam = Utils.getEntropy(mTotal, mSpamCount);

        for(int i = 0; i < mStatics.size(); ++i){
            int[][] counts = new int[Utils.CLASS_COUNT][Utils.CLASS_COUNT];
            for(int j = 0; j < i; ++j){
                counts[Utils.NORMAL][HIT] += mStatics.get(j)[Utils.NORMAL];
                counts[Utils.SPAM][MISS] += mStatics.get(j)[Utils.SPAM];
            }
            for(int j = i; j < mStatics.size(); ++j){
                counts[Utils.SPAM][HIT] += mStatics.get(j)[Utils.SPAM];
                counts[Utils.NORMAL][MISS] += mStatics.get(j)[Utils.NORMAL];
            }
            int lessCount = counts[Utils.NORMAL][HIT] + counts[Utils.SPAM][MISS];
            int moreCount = counts[Utils.SPAM][HIT] + counts[Utils.NORMAL][MISS];
            double entropy = entropySpam - (1.0 * lessCount / mTotal) * Utils.getEntropy(lessCount, counts[Utils.NORMAL][HIT])
                    - (1.0 * moreCount / mTotal) * Utils.getEntropy(moreCount, counts[Utils.SPAM][HIT]);
            igs.add(entropy);
        }
        double maxEntropy = 0.0d;
        for(int i = 0; i < igs.size(); ++i){
            if(igs.get(i) > maxEntropy){
                maxEntropy = igs.get(i);
                mDivideLength = i;
            }
        }
        //System.out.println("Max entropy is:" + igs.get(mDivideLength) + "  \twith length:" + mDivideLength);
    }

    private int length(Corpus cps){
        return cps.getOrigBody().length();
    }

    @Override
    protected void doFirstStep(Corpus cps){
        int len = length(cps);
        int classId = cps.isSpam() ? Utils.SPAM : Utils.NORMAL;
        if(null == mStatics){
            mStatics = new ArrayList<int[]>();
        }
        while(mStatics.size() < len + 1){
            mStatics.add(new int[Utils.CLASS_COUNT]);
        }
        mStatics.get(len)[classId]++;
        mTotal++;
        if(cps.isSpam()){
            mSpamCount++;
        }
    }

    @Override
    public String getName() {
        return "SmsLength";
    }

    @Override
    public void readDef(DataInputStream dataIn) throws IOException {
        mDivideLength = dataIn.readInt();
    }

    @Override
    public void writeDef(DataOutputStream dataOut) throws IOException {
        dataOut.writeInt(mDivideLength);
    }
}
