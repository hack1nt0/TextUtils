package com.xiaomi.nlp.classification.spamsms.smsspam;

import com.xiaomi.nlp.classification.spamsms.android.content.Context;
import com.xiaomi.nlp.classification.spamsms.android.util.Log;
import com.xiaomi.nlp.classification.spamsms.smsspam.preprocess.AddressType;
import com.xiaomi.nlp.classification.spamsms.smsspam.preprocess.RuleManager;

import java.io.IOException;

public class Classifier {

    public boolean classify(Context context, String address, String body){
        long startTime = System.currentTimeMillis();
        Corpus cps = new Corpus(address, body);
        new RuleManager().preprocess(cps);

        boolean isSpam = false;

        if(Options.DEBUG){
            Log.d("TIME__", "Step-1 Preprocess:\t" + (System.currentTimeMillis() - startTime));
        }

        try{
            MultiBayes classifier = getMultiBayes();
            isSpam = classifier.classify(cps, context);
            if(isSpam && AddressType.isPersonalAddress(cps.getAddressType())){
                if(RelationManager.isAddressRelated(context, cps.getAddress())){
                    isSpam = false;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return isSpam;
    }

    protected MultiBayes getMultiBayes(){
        return new MultiBayes();
    }
}
