package com.xiaomi.nlp.classification.spamsms.smsspam.train.remark;

import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;
import com.xiaomi.nlp.classification.spamsms.smsspam.feature.FeatureManager;
import com.xiaomi.nlp.classification.spamsms.smsspam.preprocess.RuleManager;
import com.xiaomi.nlp.classification.spamsms.smsspam.train.PCVals;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestFeature {

    public static void main(String[] args) {
        FeatureManager manager = new FeatureManager();
        try {
//            List<Corpus> smses = PCVals.readPreprocessSMS(PCVals.TrainDir + "test_feat", new RuleManager());
            List<Corpus> smses = PCVals.readPreprocessSMS(PCVals.TrainFile, new RuleManager());

            InputStream ins = new FileInputStream(PCVals.PatterFile + ".test");
//            manager.loadFeature(ins);

            int count = 0;
            int countDis = 0;
            for(Corpus cps : smses){
                int type = manager.smsType(cps);
                final int TP = 134217728;
//                if(match && (type == TP)){
//                    count++;
//                }
//                else if(match || (type == TP)){
//                    countDis++;
//                    System.out.println("Type:[" + type + "]" + cps.toString());
//                }

                if(type == TP){
                    System.out.println("Type:[" + type + "]" + cps.toString());
                    count++;
                }
            }

            System.out.println("Total match count:" + count + "   countDis:" + countDis);
            
            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
