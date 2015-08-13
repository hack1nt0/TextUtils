package com.xiaomi.nlp.classification.spamsms.smsspam.algSingle;


import com.xiaomi.nlp.classification.spamsms.android.util.Log;
import com.xiaomi.nlp.classification.spamsms.smsspam.Classifier;
import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;
import com.xiaomi.nlp.classification.spamsms.smsspam.train.PCVals;

import java.util.List;

public class TestSingle {
    public static void main(String[] args) {
        Classifier classifier = new Classifier();

        // 利用读取的模型进行分类，同时提取20000个样本的特征，存入feature.txt中，存为稀疏矩阵
        int normalMiss = 0;
        int normalHit = 0;
        int spamMiss = 0;
        int spamHit = 0;

//        List<Corpus> allMsm = PCVals.readPreprocessSMS(PCVals.TestFile, new RuleManager());
        List<Corpus> allMsm = PCVals.readSMS(PCVals.TestFile);

        long t0 = System.currentTimeMillis();
        for (int i = 0; i < allMsm.size(); ++i) {
            Corpus cps = allMsm.get(i);
            boolean clf = classifier.classify(null, cps.getAddress(), cps.getOrigBody());
            if (clf) {
                if (cps.isSpam()) {
                    spamHit++;
                } else {
                    normalMiss++;
                }
            } else {
                if (!cps.isSpam()) {
                    normalHit++;
                } else {
                    spamMiss++;
                }
            }
            if(clf != cps.isSpam()){
                Log.d("" + i, cps.toString());
            }
        }
        Log.d("TIME__", "Total time cost:" + (System.currentTimeMillis() - t0));
        double recall = 1.0 * spamHit / (spamHit + spamMiss);
        double precision = 1.0 * spamHit / (spamHit + normalMiss);

        System.out.println("spamHit:" + spamHit + "\tspamMiss:" + spamMiss
                + "\tnormalHit:" + normalHit + "\tnormalMiss:" + normalMiss);
        System.out.println("Recall:" + String.valueOf(recall)
                + "    Precision:" + String.valueOf(precision));

        Log.d("TestSingle", "Classify");
    }
}
