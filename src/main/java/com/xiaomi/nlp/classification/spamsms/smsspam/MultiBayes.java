package com.xiaomi.nlp.classification.spamsms.smsspam;

import com.xiaomi.nlp.classification.spamsms.android.content.Context;
import com.xiaomi.nlp.classification.spamsms.android.util.Log;
import com.xiaomi.nlp.classification.spamsms.smsspam.preprocess.AddressType;
import com.xiaomi.nlp.classification.spamsms.smsspam.preprocess.RuleManager;
import com.xiaomi.nlp.classification.spamsms.smsspam.preprocess.RuleUpper;
import com.xiaomi.nlp.tokenizer.MyTokenizer;

import java.io.*;
import java.util.List;

public class MultiBayes extends MultiBayesBase{

    private static final String TAG = MultiBayes.class.getName();

    @Override
    protected boolean hasPhish(Corpus cps, Context context) {
        boolean found = false;
        if(cps.mUrls != null && cps.mUrls.size() > 0){
            String[] urls = new String[cps.mUrls.size()];
            for(int i = 0; i < cps.mUrls.size(); ++i){
                urls[i] = Utils.getUrlMain(cps.mUrls.get(i));
            }

            try{
                InputStream ins = ModelLoader.open(context, ModelLoader.MODEL_PHISH);
                BufferedReader br = new BufferedReader(new InputStreamReader(ins));
                String str = null;

                // Skip the first line, it's version
                br.readLine();

                while(!found && (str = br.readLine()) != null) {
                    for(String url : urls){
                        if(url.equalsIgnoreCase(str)){
                            found = true;
                            break;
                        }
                    }
                }
                br.close();
                ins.close();
            }
            catch(IOException e){
                Log.e(TAG, "Exception on read phish.");
            }
        }
        return found;
    }

    protected MyTokenizer getSegment(Context context){
        return MyTokenizer.getInstance();
    }

    private long mLastLogTime = 0;

    public boolean classify(Corpus cps, Context context) throws IOException{
        if(Options.DEBUG){
            mLastLogTime = System.currentTimeMillis();
        }
        // Do not switch the invoking sequence of structType & applyPattern
        structType(cps, context);
        applyPattern(cps, context);

        if(letGoBeforeProcess(cps)){
            return false;
        }

        if(blockBeforeProcess(cps)){
            return true;
        }

        boolean isSpam = false;
        boolean definite = false;
        if(Options.DEBUG){
            long now = System.currentTimeMillis();
            Log.d("TIME__", "Step-2:\t" + (now - mLastLogTime));
            mLastLogTime = now;
        }

        MyTokenizer seg = getSegment(context);

        RuleManager ruleManager = new RuleManager();
        ruleManager.setSegment(seg);

        if(Options.DEBUG){
            long now = System.currentTimeMillis();
            Log.d("TIME__", "Step-3:\t" + (now - mLastLogTime));
            mLastLogTime = now;
        }

        InputStream ins = ModelLoader.open(context, ModelLoader.MODEL_MBYS);
        DataInputStream dataIn = new DataInputStream(ins);

        // Version numbers
        dataIn.readInt(); // Big Version
        dataIn.readInt(); // Small Version

        int levelCount = dataIn.readInt();
        double[] vals = new double[levelCount];
        double[] probs = new double[Utils.CLASS_COUNT];

        int spamHitCount = 0;
        for(int level = 0; level < levelCount && !definite; ++level){
            for (int cIndex = 0; cIndex < Utils.CLASS_COUNT; ++cIndex) {
                probs[cIndex] = 0;
            }

            // Init rules first
            int ruleCount = dataIn.readInt();
            double[][] classRuleProbability = new double[Utils.CLASS_COUNT][ruleCount];

            RuleUpper[] ru = ruleManager.getRulesUpper();
            for(int j = 0; j < ru.length; j++){
                ru[j].readDef(dataIn);
            }
            ruleManager.initUpperRules();
            for(int j = 0; j < ruleCount; j++){
                classRuleProbability[0][j] = dataIn.readDouble();
                classRuleProbability[1][j] = dataIn.readDouble();
            }

            // process sms
            ruleManager.processForClassify(cps);

            // rules
            int[] rules = cps.getRules();
            for(int j = 0; j < rules.length; ++j){
                probs[Utils.SPAM] += rules[j] > 0 ? classRuleProbability[Utils.SPAM][j] :
                                                    Utils.negLog(classRuleProbability[Utils.SPAM][j]);
                probs[Utils.NORMAL] += rules[j] > 0 ? classRuleProbability[Utils.NORMAL][j] :
                                                    Utils.negLog(classRuleProbability[Utils.NORMAL][j]);
            }

            // 高IG词
            List<String> terms = cps.getSortedSegments();
            int termIndex = 0;
            int termCount = terms.size();
            int wordCount = dataIn.readInt();
            char[] cb = new char[64];
            int pos = 0;
            char c;
            for(int j = 0; j < wordCount; j++){
                pos = 0;
                while((c = dataIn.readChar())!='\n'){
                    cb[pos++] = c;
                }
                int index = containTerm(terms, cb, pos, termIndex);
                boolean contain = index < termCount;
                if(contain){
                    termIndex = index + 1;
                }
                for(int k = 0; k < Utils.CLASS_COUNT; ++k){
                    double v = dataIn.readDouble();
                    probs[k] += contain ? v : Utils.negLog(v);
                }
            }

            if(Options.DEBUG){
                long now = System.currentTimeMillis();
                Log.d("TIME__", "Step-4-" + level + "-word:\t" + (now - mLastLogTime));
                mLastLogTime = now;
            }

            // 高IG phrase
            int phraseCount = dataIn.readInt();
            String body = cps.getOrigBody();
            for(int j = 0; j < phraseCount; ++j){
                pos = 0;
                while((c = dataIn.readChar()) != '\n'){
                    cb[pos++] = c;
                }
                boolean contain = containPhrase(body, cb, pos);
                for(int k = 0; k < Utils.CLASS_COUNT; ++k){
                    double v = dataIn.readDouble();
                    probs[k] += contain ? v : Utils.negLog(v);
                }
            }

            // AddressType
            double[][] addressTypeProbability = new double[Utils.CLASS_COUNT][AddressType.getTypeCount()];
            for(int j = 0; j < AddressType.getTypeCount(); ++j){
                addressTypeProbability[0][j] = dataIn.readDouble();
                addressTypeProbability[1][j] = dataIn.readDouble();
            }
            int addressType = cps.getAddressType();
            for(int k = 0; k < Utils.CLASS_COUNT; ++k){
                probs[k] += addressTypeProbability[k][addressType];
            }

            double[] threshes = new double[3];
            threshes[0] = dataIn.readDouble();
            threshes[1] = dataIn.readDouble();
            threshes[2] = dataIn.readDouble();

            double val = probs[Utils.SPAM] - probs[Utils.NORMAL];
            threshes = updateThreshs(threshes, cps);

            if(Options.DEBUG){
                long now = System.currentTimeMillis();
                Log.d("TIME__", "Step-5-" + level + ":\t" + (now - mLastLogTime));
                mLastLogTime = now;
            }

            if(val < threshes[1]){
                isSpam = false;
                definite = true;
                break;
            }else if(val > threshes[0]){
                isSpam = true;
                definite = true;
                break;
            }else {
                vals[level] = val - threshes[2];
                if(vals[level] > 0){
                    spamHitCount++;
                }
            }
        }

        //seg.destroy();
        dataIn.close();
        ins.close();

        if(Options.DEBUG){
            long now = System.currentTimeMillis();
            Log.d("TIME__", "Step-6:\t" + (mLastLogTime - now));
            mLastLogTime = now;
        }

        if(!definite){
            int normalHitCount = levelCount - spamHitCount;
            if(spamHitCount > normalHitCount){
                isSpam = true;
            }else if(spamHitCount < normalHitCount){
                isSpam = false;
            }else{
                double allVals = 0;
                for(double v : vals){
                    allVals += v;
                }
                isSpam = allVals > 0;
            }
        }
        return isSpam;
    }

    private int containTerm(List<String> terms, char[] buf, int len, int index){
        while(index < terms.size()){
            String term = terms.get(index);
            if(term.length() == len){
                boolean contain = true;
                for(int j = 0; j < len; ++j){
                    if(term.charAt(j) != buf[j]){
                        contain = false;
                        break;
                    }
                }
                if(contain){
                    return index;
                }
            }
            index++;
        }
        return index;
    }

    private boolean containPhrase(String body, char[] buf, int len){
        for(int i = 0; i <= body.length() - len; ++i){
            boolean contain = true;
            for(int j = 0; j < len; ++j){
                if(body.charAt(i + j) != buf[j]){
                    contain = false;
                    break;
                }
            }
            if(contain){
                return true;
            }
        }
        return false;
    }
}
