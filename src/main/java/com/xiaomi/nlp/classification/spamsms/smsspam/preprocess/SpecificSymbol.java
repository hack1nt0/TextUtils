package com.xiaomi.nlp.classification.spamsms.smsspam.preprocess;

import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;
import com.xiaomi.nlp.classification.spamsms.smsspam.Utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpecificSymbol extends RuleUpper {
    private int[][] mStatics = null;
    private List<Character> mSymbols = null;
    int mTotal = 0;
    int mSpamCount = 0;

    @Override
    protected int subClassCount(){
        return mSymbols.size();
    }

    @Override
    public String getClassName(int i){
        if(i < subClassCount() && i >= 0){
            String name = super.getClassName(getClass());
            name += "_";
            name += mSymbols.get(i);
            return padding(name);
        }else{
            return null;
        }
    }

    @Override
    public boolean doFitting(Corpus cps, int[] vals, int start) {
        boolean flag = false;
        String body = cps.getOrigBody();
        for(int i = 0; i < mSymbols.size(); ++i){
            if(body.indexOf(mSymbols.get(i)) != -1){
                vals[start + i]++;
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public void firstStepDone(){
        if(mSymbols == null || mSymbols.isEmpty())
        {
            double[] igs = new double[Character.MAX_VALUE + 1];
            double entropySpam = Utils.getEntropy(mTotal, mSpamCount);

            for(int i = 0; i < mStatics.length; ++i){
                if(Utils.isCommonChar((char)i)){
                    continue;
                }
                int hasCount = mStatics[i][Utils.SPAM] + mStatics[i][Utils.NORMAL];
                int noCount = mTotal - hasCount;
                double entropy = entropySpam - (1.0 * hasCount / mTotal) * Utils.getEntropy(hasCount, mStatics[i][Utils.SPAM])
                        - (1.0 * noCount / mTotal) * Utils.getEntropy(noCount, mSpamCount - mStatics[i][Utils.SPAM]);
                igs[i] = entropy;
                
            }

            mSymbols = new ArrayList<Character>();
            List<Double> maxIg = new ArrayList<Double>();
            for(int i = 1; i < igs.length; ++i){
                if(Utils.goodInfo(igs[i], mStatics[i][Utils.SPAM], mStatics[i][Utils.NORMAL], mTotal)){
                    mSymbols.add((char)i);
                    maxIg.add(igs[i]);
                }
            }
        }
    }

    @Override
    protected void doFirstStep(Corpus cps){
        if(null == mStatics){
            mStatics = new int[Character.MAX_VALUE + 1][Utils.CLASS_COUNT];
        }
        String str = cps.getOrigBody();
        List<Character> lst = new ArrayList<Character>();
        int classId = cps.isSpam() ? Utils.SPAM : Utils.NORMAL;
        for(int i = 0; i < str.length(); ++i){
            if(Utils.isCommonSymbol(str.charAt(i))){
                continue;
            }
            boolean dup = false;
            for(int j = 0; j < lst.size(); ++j){
                if(str.charAt(i) == lst.get(j)){
                    dup = true;
                    break;
                }
            }
            if(!dup){
                lst.add(str.charAt(i));
                mStatics[str.charAt(i)][classId]++;
            }
        }

        mTotal++;
        if(cps.isSpam()){
            mSpamCount++;
        }
    }

    @Override
    public String getName() {
        return "SpecificSymbol";
    }

    @Override
    public void readDef(DataInputStream dataIn) throws IOException {
        mSymbols = new ArrayList<Character>();
        int s = dataIn.readInt();
        for(int i = 0; i < s; i++)
        {
            char c = dataIn.readChar();
            mSymbols.add(c);
        }
        
    }

    @Override
    public void writeDef(DataOutputStream dataOut) throws IOException {
        dataOut.writeInt(mSymbols.size());
        for(int i=0; i < mSymbols.size(); i++)
        {
            dataOut.writeChar(mSymbols.get(i));
        }
    }
}
