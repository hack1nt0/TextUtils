package com.xiaomi.nlp.classification.spamsms.smsspam.train;

import com.xiaomi.nlp.classification.spamsms.android.content.Context;
import com.xiaomi.nlp.classification.spamsms.android.util.Log;
import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;
import com.xiaomi.nlp.classification.spamsms.smsspam.MultiBayesBase;
import com.xiaomi.nlp.classification.spamsms.smsspam.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class MultiBayesBatch extends MultiBayesBase {
    protected List<NaiveBayesClassifierBase> mNbys = new ArrayList<NaiveBayesClassifierBase>();

    protected int[][] mCounts;
    protected int[][] mFeatureCounts = new int[Utils.CLASS_COUNT][2]; // 0:hit, 1:miss
    private Set<String> mPhishUrls;

    final static int SPM_HIT = 0;
    final static int SPM_MIS = 1;
    final static int NOR_HIT = 2;
    final static int NOR_MIS = 3;

    List<Corpus> wroLst = new ArrayList<Corpus>();
    public boolean classify(Corpus cps, Context context){
        if(mPhishUrls == null){
            loadPhish();
        }

        // Do not switch the invoking sequence of structType & applyPattern
        structType(cps, null);
        applyPattern(cps, null);

        if(letGoBeforeProcess(cps)){
            if(cps.isSpam()){
                mFeatureCounts[Utils.NORMAL][1]++;
                Log.d("letThrough_WRONG", cps.toString());
                wroLst.add(cps);
            }else{
                mFeatureCounts[Utils.NORMAL][0]++;
            }
            return false;
        }

        if(blockBeforeProcess(cps)){
            if(!cps.isSpam()){
                mFeatureCounts[Utils.SPAM][1]++;
                Log.d("blockBefore_WRONG", cps.toString());
                wroLst.add(cps);
            }else{
                mFeatureCounts[Utils.SPAM][0]++;
            }
            return true;
        }

        int spamHitCount = 0;

        double[] vals = new double[mNbys.size()];

        for(int i = 0; i < mNbys.size(); ++i){
            NaiveBayesClassifierBase bys = mNbys.get(i);

            bys.getRuleManager().processForClassify(cps);

            double val = bys.probDiff(cps);

            double[] threshes = updateThreshs(bys.getThresh(), cps);
            if(val < threshes[1]){
                updateCount(cps, i, false);
                return false;
            }else if(val > threshes[0]){
                updateCount(cps, i, true);
                return true;
            }else{
                vals[i] = val - threshes[2];
                if(vals[i] > 0){
                    spamHitCount++;
                }
            }
        }

        int normalHitCount = mNbys.size() - spamHitCount;
        if(spamHitCount > normalHitCount){
            updateCount(cps, mNbys.size(), true);
            return true;
        }else if(spamHitCount < normalHitCount){
            updateCount(cps, mNbys.size(), false);
            return false;
        }else{
            double allVals = 0;
            for(double v : vals){
                allVals += v;
            }
            boolean ret = allVals > 0;
            updateCount(cps, mNbys.size() + 1, ret);
            return ret;
        }
    }

    protected void updateCount(Corpus cps, int lev, boolean classify){
        if(cps.isSpam()){
            if(classify){
                mCounts[lev][SPM_HIT]++;
            }else{
                mCounts[lev][SPM_MIS]++;
            }
        }else{
            if(classify){
                mCounts[lev][NOR_MIS]++;
            }else{
                mCounts[lev][NOR_HIT]++;
            }
        }
    }

    private void loadPhish(){
        try{
            InputStream fs = this.getClass().getClassLoader().getResourceAsStream(PCVals.PhishFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            String str = null;
            mPhishUrls = new HashSet<String>();

            // Skip the first line, it's version
            br.readLine();

            while((str = br.readLine()) != null) {
                mPhishUrls.add(str);
            }
            br.close();
            fs.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    protected boolean hasPhish(Corpus cps, Context context){
        if(cps.mUrls == null){
            return false;
        }
        for(String url : cps.mUrls){
            String mainUrl = Utils.getUrlMain(url);
            if(mPhishUrls.contains(mainUrl)){
                return true;
            }
        }
        return false;
    }

    public void print(){
        System.out.println("-----------------statistics------------");
        System.out.println("Feature:  Spam[hit:miss] [" + mFeatureCounts[0][0] + "," + mFeatureCounts[0][1] +
                           "] , Normal[hit:miss] [" + mFeatureCounts[1][0] + ", " + mFeatureCounts[1][1] + "]\n");
        for(int i = 0; i < mCounts.length; ++i){
            int total = 0;
            for(int n : mCounts[i]){
                total += n;
            }
            StringBuffer sb = new StringBuffer();
            sb.append("Lev-" + i + ":" + total + " ");
            if(mCounts[i][SPM_HIT] + mCounts[i][SPM_MIS] > 0){
                sb.append(" spam[" + mCounts[i][SPM_HIT] + "/" + (mCounts[i][SPM_HIT] + mCounts[i][SPM_MIS]) + "] " +
                                    ((1.0 * mCounts[i][SPM_HIT]) / (mCounts[i][SPM_HIT] + mCounts[i][SPM_MIS])));
            }
            if(mCounts[i][NOR_HIT] + mCounts[i][NOR_MIS] > 0){
                sb.append("\tnormal[" + mCounts[i][NOR_HIT] + "/" + (mCounts[i][NOR_HIT] + mCounts[i][NOR_MIS]) + "] " +
                        ((1.0 * mCounts[i][NOR_HIT]) / (mCounts[i][NOR_HIT] + mCounts[i][NOR_MIS])));
            }
            System.out.println(sb.toString());
        }
        
        PCVals.saveSmses(PCVals.TrainFile + ".featureWro", wroLst);
    }
}
