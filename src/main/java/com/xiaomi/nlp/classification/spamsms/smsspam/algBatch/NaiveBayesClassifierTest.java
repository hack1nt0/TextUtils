package com.xiaomi.nlp.classification.spamsms.smsspam.algBatch;

import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;
import com.xiaomi.nlp.classification.spamsms.smsspam.Utils;
import com.xiaomi.nlp.classification.spamsms.smsspam.preprocess.AddressType;
import com.xiaomi.nlp.classification.spamsms.smsspam.preprocess.RuleManager;
import com.xiaomi.nlp.classification.spamsms.smsspam.preprocess.RuleUpper;
import com.xiaomi.nlp.classification.spamsms.smsspam.train.NaiveBayesClassifierBase;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;


public class NaiveBayesClassifierTest extends NaiveBayesClassifierBase {
    private double mClassRuleProbability[][];
    private Map<String, double[]> mClassConditionlProbability;
    private Map<String, double[]> mNewPhraseProbability;

    private double mAddressTypeProbability[][];

    public double classify(Corpus cps)
    {
        mRuleManager.processForClassify(cps);
        return probDiff(cps);
    }

    public int wordCount(){
        return mClassConditionlProbability.size();
    }

    public int phraseCount(){
        return mNewPhraseProbability.size();
    }

    public double probDiff(Corpus cps){
        int[] rules = cps.getRules();
        double[] probs = new double[Utils.CLASS_COUNT];

        probs[Utils.SPAM] = 0;
        probs[Utils.NORMAL] = 0;

        for(int j = 0; j < rules.length; ++j){
            probs[Utils.SPAM] += rules[j] > 0 ? mClassRuleProbability[Utils.SPAM][j] :
                                                Utils.negLog(mClassRuleProbability[Utils.SPAM][j]);
            probs[Utils.NORMAL] += rules[j] > 0 ? mClassRuleProbability[Utils.NORMAL][j] :
                                                Utils.negLog(mClassRuleProbability[Utils.NORMAL][j]);
        }

        Set<String> terms = cps.getSegments();
        Iterator<Entry<String, double[]>> itor = mClassConditionlProbability.entrySet().iterator();
        int termIndex = 0;
        while(itor.hasNext()){
            Entry<String, double[]> entry = (Entry<String, double[]>)itor.next();
            String key = entry.getKey();
            double[] values = entry.getValue();
            int index = termIndex;
            boolean contain = terms.contains(key);
            if(contain){
                termIndex = index + 1;
            }
            probs[Utils.SPAM] += contain ? values[Utils.SPAM] :
                                           Utils.negLog(values[Utils.SPAM]);
            probs[Utils.NORMAL] += contain ? values[Utils.NORMAL] :
                                             Utils.negLog(values[Utils.NORMAL]);
        }

        itor = mNewPhraseProbability.entrySet().iterator();
        String body = cps.getOrigBody();
        while(itor.hasNext()){
            Entry<String, double[]> entry = (Entry<String, double[]>)itor.next();
            String key = entry.getKey();
            double[] values = entry.getValue();
            boolean contain = body.indexOf(key) != -1;
            probs[Utils.SPAM] += contain ? values[Utils.SPAM] :
                                           Utils.negLog(values[Utils.SPAM]);
            probs[Utils.NORMAL] += contain ? values[Utils.NORMAL] :
                                             Utils.negLog(values[Utils.NORMAL]);
        }
        int addressType = cps.getAddressType();
        probs[Utils.SPAM] += mAddressTypeProbability[Utils.SPAM][addressType];
        probs[Utils.NORMAL] += mAddressTypeProbability[Utils.NORMAL][addressType];

        return (probs[Utils.SPAM] - probs[Utils.NORMAL]);
    }

    public void readModel(DataInputStream dataIn) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        // ruler
        int ruleCount = dataIn.readInt();
        mClassRuleProbability = new double[Utils.CLASS_COUNT][ruleCount];
        mRuleManager = new RuleManager();
        RuleUpper[] ru = mRuleManager.getRulesUpper();
        for(int i = 0; i < ru.length; i++){
            ru[i].readDef(dataIn);
        }
        mRuleManager.initUpperRules();

        for(int i = 0; i < ruleCount; i++){
            mClassRuleProbability[0][i] = dataIn.readDouble();
            mClassRuleProbability[1][i] = dataIn.readDouble();
        }

        // 高IG词
        int wordCount = dataIn.readInt();
        mClassConditionlProbability = new TreeMap<String, double[]>();

        char[] cb = new char[1024];
        int pos = 0;
        char c;
        for(int i = 0; i < wordCount; i++){
            pos = 0;
            while((c = dataIn.readChar())!='\n'){
                cb[pos++] = c;
            }
            double d1 = dataIn.readDouble();
            double d2 = dataIn.readDouble();
            mClassConditionlProbability.put(String.valueOf(cb, 0, pos), new double[]{d1, d2});
        }

        // 高IG phrase
        int phraseCount = dataIn.readInt();
        mNewPhraseProbability = new TreeMap<String, double[]>();

        for(int i = 0; i < phraseCount; ++i){
            pos = 0;
            while((c = dataIn.readChar()) != '\n'){
                cb[pos++] = c;
            }
            String phs = String.valueOf(cb, 0, pos);
            double d1 = dataIn.readDouble();
            double d2 = dataIn.readDouble();
            mNewPhraseProbability.put(phs, new double[]{d1, d2});
        }

        mAddressTypeProbability = new double[Utils.CLASS_COUNT][AddressType.getTypeCount()];
        for(int i = 0; i < AddressType.getTypeCount(); ++i){
            mAddressTypeProbability[0][i] = dataIn.readDouble();
            mAddressTypeProbability[1][i] = dataIn.readDouble();
        }
    }
}