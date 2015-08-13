package com.xiaomi.nlp.classification.spamsms.smsspam.algBatch;

import com.xiaomi.nlp.classification.spamsms.android.util.Log;
import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;
import com.xiaomi.nlp.classification.spamsms.smsspam.SundriesManager;
import com.xiaomi.nlp.classification.spamsms.smsspam.preprocess.RuleManager;
import com.xiaomi.nlp.classification.spamsms.smsspam.train.PCVals;

import java.util.ArrayList;
import java.util.List;

public class TestMultiBayes {

    public static void main(String[] args) {

        MultiBayesTest mbs = new MultiBayesTest();

        //利用读取的模型进行分类，同时提取20000个样本的特征，存入feature.txt中，存为稀疏矩阵
        int normalMiss = 0;
        int normalHit = 0;
        int spamMiss = 0;
        int spamHit = 0;

        List<Corpus> corrctList = new ArrayList<Corpus>();
        List<Corpus> wrongList = new ArrayList<Corpus>();

//        List<Corpus> allMsm = PCVals.readPreprocessSMS(PCVals.TrainFile, new RuleManager());
        List<Corpus> allMsm = PCVals.readPreprocessSMS(PCVals.TestFile, new RuleManager());

        if(SundriesManager.test(allMsm)){
//            return;
        }
        long t0 = System.currentTimeMillis();
        for(int i = 0; i < allMsm.size(); ++i){
            Corpus cps = allMsm.get(i);

            boolean clf = mbs.classify(cps, null);
            if(clf){
                if(cps.isSpam()){
                    spamHit++;
                    corrctList.add(cps);
                }else{
                    normalMiss++;
                    wrongList.add(cps);
                }
            }else{
                if(!cps.isSpam()){
                    corrctList.add(cps);
                    normalHit++;
                }else{
                    spamMiss++;
                    wrongList.add(cps);
                }
            }
            if(clf != cps.isSpam()){
                Log.d("" + i, cps.toString());
            }
        }
        Log.d("Time", "Total time:\t" + (System.currentTimeMillis() - t0));
        double recall = 1.0 * spamHit/(spamHit+spamMiss);
        double precision = 1.0 * spamHit/(spamHit+normalMiss);

        System.out.println("spamHit:" + spamHit + "\tspamMiss:" + spamMiss + "\tnormalHit:" + normalHit + "\tnormalMiss:" + normalMiss);
        System.out.println("Recall:"+String.valueOf(recall)+"    Precision:"+String.valueOf(precision));
        mbs.print();
        Log.d("MultiBayesTest", "Classify");

//        Utils.saveSmses(PCVals.TrainFile + ".wro", wrongList);
//        Utils.saveSmses(PCVals.TrainFile + ".cor", corrctList);
        
    }
}
