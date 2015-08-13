package com.xiaomi.nlp.classification.spamsms.smsspam.train;

import com.xiaomi.nlp.classification.spamsms.android.util.Log;
import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;
import com.xiaomi.nlp.classification.spamsms.smsspam.preprocess.RuleManager;

import java.util.ArrayList;
import java.util.List;

public class TrainAndTestMultiBayes {

    public static void main(String[] args) {
        List<Corpus> allMsm = PCVals.readPreprocessSMS(PCVals.TrainFile, new RuleManager());

        System.loadLibrary("WordsSegment");

        MultiBayesTrain mbs = new MultiBayesTrain();
        mbs.startTraining(allMsm);
        Log.d("MultiBayesTest", "Training");

        mbs.saveModel(PCVals.ModelFile);

        //利用读取的模型进行分类，同时提取20000个样本的特征，存入feature.txt中，存为稀疏矩阵
        int normalMiss = 0;
        int normalHit = 0;
        int spamMiss = 0;
        int spamHit = 0;

        List<Corpus> corrctList = new ArrayList<Corpus>();
        List<Corpus> wrongList = new ArrayList<Corpus>();

        allMsm = PCVals.readPreprocessSMS(PCVals.TestFile, new RuleManager());
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
//            System.out.println("classify res:" + clf);
        }
        double recall = 1.0 * spamHit/(spamHit+spamMiss);
        double precision = 1.0 * spamHit/(spamHit+normalMiss);

        System.out.println("spamHit:" + spamHit + "\tspamMiss:" + spamMiss + "\tnormalHit:" + normalHit + "\tnormalMiss:" + normalMiss);
        System.out.println("Recall:"+String.valueOf(recall)+"    Precision:"+String.valueOf(precision));
        mbs.print();
        Log.d("MultiBayesTest", "Classify");

//        Utils.saveSmses("/home/qinqiuping/nt/files/spamsms/version4/filtled.corrt", corrctList);
//        Utils.saveSmses("/home/qinqiuping/nt/files/spamsms/version4/filtled.wrong", wrongList);
    }

}
