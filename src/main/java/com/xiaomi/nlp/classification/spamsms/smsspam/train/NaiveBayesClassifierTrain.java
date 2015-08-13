package com.xiaomi.nlp.classification.spamsms.smsspam.train;

import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;
import com.xiaomi.nlp.classification.spamsms.smsspam.Utils;
import com.xiaomi.nlp.classification.spamsms.smsspam.preprocess.AddressType;
import com.xiaomi.nlp.classification.spamsms.smsspam.preprocess.RuleManager;

import java.io.DataOutputStream;
import java.util.*;

public class NaiveBayesClassifierTrain extends NaiveBayesClassifierBase{

    private Map<String, Integer> termIndex;
    private Map<String, Double> mTermIGs;
    private List<String> mTerms;
    private List<Integer> mMaxIGsIndex;
    private List<Double> mMaxIGsValue;

    private int                  spamNormalCount[];

    private int                  classKeyMap[][];

    private int                  mRuleCountMap[][];

    private int                  vocabulary      = 0;

    private int                  mUsedTermsCount = 0;

    public NewPhraseExplorer mNewPhrases = new NewPhraseExplorer();
    private int mNumNewPhrase = 0;
    private int                  mNewPhraseCountMap[][];
    private int                  mAddressTypeCountMap[][];
    private Map<String, Integer> mNewPhraseIndex;

    public static final int SPAM = Utils.SPAM;
    public static final int NORMAL = Utils.NORMAL;
    public static final int CLASS_COUNT = Utils.CLASS_COUNT;

    private double mClassRuleProbability[][][];
    private double mClassConditionlProbability[][][];
    private double mNewPhraseProbability[][][];
    private double mAddressTypeProbability[][];

    public static final int WORD_COUNT_BY_IG = 8000;

    public Set<String> getNewPhrases(){
        return mNewPhraseIndex.keySet();
    }

    private void buildIndex(List<Corpus> orignCorpus) {
        Integer idTerm = new Integer(-1);
        for (int i = 0; i < orignCorpus.size(); ++i) {
            Corpus corpus = orignCorpus.get(i);
            Set<String> terms = corpus.getSegments();

            for (String term : terms) {
                if (!termIndex.containsKey(term)) {
                    idTerm++;
                    termIndex.put(term, idTerm);
                    mTerms.add(term);
                }
            }
        }
        vocabulary = termIndex.size();
    }

    private void trainByTerms(List<Corpus> trianCorpus){
        vocabulary      = 0;
        mTerms = new ArrayList<String>();
        mMaxIGsIndex = new ArrayList<Integer>();
        mMaxIGsValue = new ArrayList<Double>();
        termIndex = new HashMap<String, Integer>();

        buildIndex(trianCorpus);

        spamNormalCount = new int[CLASS_COUNT];
        classKeyMap = new int[CLASS_COUNT][vocabulary];
        mRuleCountMap = new int[CLASS_COUNT][mRuleManager.getRuleCount()];
        mAddressTypeCountMap = new int[CLASS_COUNT][AddressType.getTypeCount()];

        int spamCount = 0;

        for (int i = 0; i < trianCorpus.size(); ++i) {
            Corpus corpus = trianCorpus.get(i);
            boolean isSpam = corpus.isSpam();
            if(isSpam){
                spamCount++;
            }
            Set<String> terms = corpus.getSegments();
            int spamIndex = isSpam ? SPAM : NORMAL;

            Iterator<String> it = terms.iterator();
            while(it.hasNext()){
                String term = it.next();
                Integer wordIndex = termIndex.get(term);
                classKeyMap[spamIndex][wordIndex]++;
            }

            spamNormalCount[spamIndex]++;
            int[] ruleCounts = corpus.getRules();
            for(int j = 0; j < ruleCounts.length; ++j){
                if(ruleCounts[j] > 0){
                    mRuleCountMap[spamIndex][j]++;
                }
            }
            int addressType = corpus.getAddressType();
            if(addressType >= 0){
                mAddressTypeCountMap[spamIndex][addressType]++;
            }
        }

        int total = trianCorpus.size();

        double entropySpam = Utils.getEntropy(total, spamCount);

        List<Pair> infoGains = new ArrayList<Pair>();
        double[] igs = new double[vocabulary];

/*        if(!Options.TEST_ALG){
            for(int i = 0; i < mRuleManager.getRuleCount(); ++i){
                int hasCount = 0;
                for(int j = 0; j < CLASS_COUNT; ++j){
                    hasCount += mRuleCountMap[j][i];
                }
                int noCount = total - hasCount;
                double entropy = 0.0;
                if(hasCount > 0 && noCount > 0){
                    entropy = entropySpam - (1.0 * hasCount / total) * Utils.getEntropy(hasCount, mRuleCountMap[0][i])
                            - (1.0 * noCount / total) * Utils.getEntropy(noCount, spamCount - mRuleCountMap[0][i]);
                }
                System.out.println(mRuleManager.getRuleName(i) + ":\t" + entropy  + ",\t" + mRuleCountMap[0][i] + ",\t" + mRuleCountMap[1][i]);
            }
        }*/
/*        if(!Options.TEST_ALG){
            double condEntropy = 0;
            for(int i = 0; i < mRuleManager.getAddressTypeCount(); ++i){
                double pi = 1.0 * (mAddressTypeCountMap[0][i] + mAddressTypeCountMap[1][i]) / total;
                pi *= Utils.getEntropy(mAddressTypeCountMap[0][i] + mAddressTypeCountMap[1][i], mAddressTypeCountMap[0][i]);
                condEntropy += pi;
            }
            double entropy = entropySpam - condEntropy;
            System.out.println(mRuleManager.mRulesAddress.getName() + ":\t" + entropy);
            for(int i = 0; i < mRuleManager.getAddressTypeCount(); ++i){
                System.out.println(mRuleManager.mRulesAddress.getName(i) + ":\t" + mAddressTypeCountMap[0][i] + ",\t" + mAddressTypeCountMap[1][i]);
            }
        }*/


        mTermIGs = new HashMap<String, Double>();
        for(int i = 0; i < vocabulary; ++i){
            int hasCount = 0;
            for(int j = 0; j < CLASS_COUNT; ++j){
                hasCount += classKeyMap[j][i];
            }
            
            int noCount = total - hasCount;
            double entropy = 0.0;
            if(hasCount > 0 && noCount > 0){
                entropy = entropySpam - (1.0 * hasCount / total) * Utils.getEntropy(hasCount, classKeyMap[0][i])
                        - (1.0 * noCount / total) * Utils.getEntropy(noCount, spamCount - classKeyMap[0][i]);
            }
            infoGains.add(new Pair(i, entropy));
            igs[i] = entropy;

            mTermIGs.put(mTerms.get(i), entropy);
        }

        Collections.sort(infoGains, new Comparator<Pair>(){
            public int compare(Pair arg0, Pair arg1) {
                return Double.compare(arg1.p, arg0.p);
            }
        });

        mMaxIGsIndex.clear();
        mMaxIGsValue.clear();
        int index = 0;
        mUsedTermsCount = WORD_COUNT_BY_IG;
        if(trianCorpus.size() < WORD_COUNT_BY_IG){
            mUsedTermsCount = trianCorpus.size();
        }
        while(mMaxIGsIndex.size() < mUsedTermsCount && index < infoGains.size()){
            mMaxIGsIndex.add(Integer.valueOf(infoGains.get(index).i));
            mMaxIGsValue.add(Double.valueOf(infoGains.get(index).p));
            index++;
        }

        /*if(!Options.TEST_ALG){
            System.out.println("----------------------Max " + WORD_COUNT_BY_IG + " information gains start------------------------");
            int count = 0;
            for(int i : mMaxIGsIndex){
                System.out.println(mTerms.get(i) + "," + igs[i] + "," + classKeyMap[0][i] + "," + classKeyMap[1][i]);
                if(count++ > 1300)break;
            }
            System.out.println("----------------------Max " + WORD_COUNT_BY_IG + " information gains end------------------------");
        }*/
        
    }

    public void startTraining(List<Corpus> orignCorpus, RuleManager rmgr)  {
        mRuleManager = rmgr;

        mRuleManager.processForTraining(orignCorpus);
        trainByTerms(orignCorpus);

        mNewPhrases.doExploring(orignCorpus, mRuleManager);
        mNewPhrases.checkSubWord(mMaxIGsIndex, mTerms);
        mNumNewPhrase = mNewPhrases.getPhraseCount();
        mNewPhraseCountMap = new int[CLASS_COUNT][mNumNewPhrase];

        mNewPhraseIndex = mNewPhrases.getPhraseMap();

        for (int i = 0; i < orignCorpus.size(); ++i) {
            Corpus corpus = orignCorpus.get(i);
            int spamIndex = corpus.isSpam() ? SPAM : NORMAL;
            Iterator<Map.Entry<String, Integer>> it = mNewPhraseIndex.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String, Integer> entry = it.next();
                if(corpus.getOrigBody().indexOf(entry.getKey()) != -1){
                    mNewPhraseCountMap[spamIndex][entry.getValue().intValue()]++;
                }
            }
        }

        mClassRuleProbability = new double[CLASS_COUNT][mRuleManager.getRuleCount()][2];
        mClassConditionlProbability = new double[CLASS_COUNT][vocabulary][2];
        mNewPhraseProbability = new double[CLASS_COUNT][mNumNewPhrase][2];
        mAddressTypeProbability = new double[CLASS_COUNT][AddressType.getTypeCount()];

        for(int i = 0; i < CLASS_COUNT; ++i){
            for(int j = 0; j < mRuleManager.getRuleCount(); ++j){
                double prob = getClassRuleProbability(i, j, true);
                mClassRuleProbability[i][j][0] = Math.log(prob);
                mClassRuleProbability[i][j][1] = Math.log(1 - prob);
            }

            for(int j = 0; j < vocabulary; ++j){
                double prob = getClassConditionlProbability(i, j, true);
                mClassConditionlProbability[i][j][0] = Math.log(prob);
                mClassConditionlProbability[i][j][1] = Math.log(1 - prob);
            }

            for(int j = 0; j < mNumNewPhrase; ++j){
                double prob = getNewPhraseProbability(i, j, true);
                mNewPhraseProbability[i][j][0] = Math.log(prob);
                mNewPhraseProbability[i][j][1] = Math.log(1 - prob);
            }

            for(int j = 0; j < AddressType.getTypeCount(); ++j){
                double prob = getAddressProbability(i, j);
                mAddressTypeProbability[i][j] = Math.log(prob);
            }
        }
    }

    public double probDiff(Corpus cps) {
        Set<String> terms = cps.getSegments();
        int[] rules = cps.getRules();
        double[] probs = new double[CLASS_COUNT];
        String body = cps.getOrigBody();

        for (int cIndex = 0; cIndex < CLASS_COUNT; ++cIndex) {
            probs[cIndex] = 0;
            for(int termIndex : mMaxIGsIndex){
                String term = mTerms.get(termIndex);
                probs[cIndex] += mClassConditionlProbability[cIndex][termIndex][terms.contains(term) ? 0 : 1];
            }
            for(int j = 0; j < rules.length; ++j){
                probs[cIndex] += mClassRuleProbability[cIndex][j][rules[j] > 0 ? 0 : 1];
            }

            Iterator<Map.Entry<String, Integer>> it = mNewPhraseIndex.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String, Integer> entry = it.next();
                boolean contain = (body.indexOf(entry.getKey()) != -1);
                probs[cIndex] += mNewPhraseProbability[cIndex][entry.getValue().intValue()][contain ? 0 : 1];
            }

            probs[cIndex] += mAddressTypeProbability[cIndex][cps.getAddressType()];
        }
        return (probs[SPAM] - probs[NORMAL]);
    }

    private List<Integer> sortHightIGTerms(List<Integer> terms){
        List<Integer> sortedTerms = new LinkedList<Integer>();
        sortedTerms.addAll(terms);
        Collections.sort(sortedTerms, new Comparator<Integer>(){
            public int compare(Integer arg0, Integer arg1) {
                return mTerms.get(arg0).compareTo(mTerms.get(arg1));
            }});
        return sortedTerms;
    }

    public void saveModel(DataOutputStream dataOut){
        try{
            // ruler
            int ruleCount = mRuleManager.getRuleCount();
            dataOut.writeInt(ruleCount);
            for(int i = 0; i < mRuleManager.mRulesUpper.length; i++){
                mRuleManager.mRulesUpper[i].writeDef(dataOut);
            }
            for(int i = 0; i < ruleCount; i++){
                dataOut.writeDouble(mClassRuleProbability[0][i][0]);
                dataOut.writeDouble(mClassRuleProbability[1][i][0]);
            }

            // 高IG词
            int wordCount = mMaxIGsIndex.size();
            dataOut.writeInt(wordCount);
            List<Integer> sortedIndex = sortHightIGTerms(mMaxIGsIndex);
            for(int i = 0; i < wordCount; ++i){
                int index = sortedIndex.get(i);
                dataOut.writeChars((mTerms.get(index) + "\n"));
                dataOut.writeDouble(mClassConditionlProbability[0][index][0]);
                dataOut.writeDouble(mClassConditionlProbability[1][index][0]);
            }

            // 高IG phrase
            int phraseCount = mNumNewPhrase;
            dataOut.writeInt(phraseCount);

            Map<String, PairCount>[] filteredPhrases = mNewPhrases.getFilteredPhrases();
            int index = 0;
            for(Map<String, PairCount> map : filteredPhrases){
                Iterator<Map.Entry<String, PairCount>> it = map.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry<String, PairCount> entry = it.next();
                    dataOut.writeChars((entry.getKey() + "\n"));

                    dataOut.writeDouble(mNewPhraseProbability[0][index][0]);
                    dataOut.writeDouble(mNewPhraseProbability[1][index][0]);
                    index++;
                }
            }

            for(int i = 0; i < AddressType.getTypeCount(); ++i){
                dataOut.writeDouble(mAddressTypeProbability[0][i]);
                dataOut.writeDouble(mAddressTypeProbability[1][i]);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private double getClassRuleProbability(int classIndex, int ruleIndex, boolean contain){
        return getProbability(classIndex, ruleIndex, mRuleCountMap, contain);
    }

    private double getClassConditionlProbability(int classIndex, int wordIndex, boolean contain){
        return getProbability(classIndex, wordIndex, classKeyMap, contain);
    }

    private double getNewPhraseProbability(int classIndex, int newPhraseIndex, boolean contain){
        return getProbability(classIndex, newPhraseIndex, mNewPhraseCountMap, contain);
    }

    private double getAddressProbability(int classIndex, int addressIndex){
        int N = spamNormalCount[classIndex];
        int V = AddressType.getTypeCount();
        int NCX = mAddressTypeCountMap[classIndex][addressIndex];

        double ret = (1.0 + NCX) / (N + V);
        return ret;
    }

    private double getProbability(int classIndex, int termIndex, int[][] map, boolean contain){
        int N = spamNormalCount[classIndex];
        int NCX = map[classIndex][termIndex];
        if(!contain){
            NCX = N - map[classIndex][termIndex];
        }
        double ret = (1.0 + NCX) / (N + 2);
        return ret;
    }
}

class Pair{
    int i;
    double p;
    Pair(int ii, double pp){
        i = ii;
        p = pp;
    }
}

class PairCount{
    String phs;
    int[] vals;
    double ig;
    //
    boolean dup = false;
    PairCount(String p, int[] v){
        phs = p;
        vals = v;
    }
}
