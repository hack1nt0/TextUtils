package com.xiaomi.nlp.classification.spamsms.smsspam.train;

import com.xiaomi.nlp.classification.spamsms.android.util.Log;
import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;
import com.xiaomi.nlp.classification.spamsms.smsspam.preprocess.RuleManager;
import com.xiaomi.nlp.tokenizer.MyTokenizer;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MultiBayesTrain extends MultiBayesBatch{
    private static final int MIN_COUNT = 2000;

    private static final double PRECISION_COMM = 0.95;
    private static final int MIN_COUNT_DIVIDE = (int)(1.0 / (1 - PRECISION_COMM));

    public void startTraining(List<Corpus> trainingCpses){
        List<Corpus> cpses = trainingCpses;

        MyTokenizer seg = MyTokenizer.getInstance();

        while(cpses.size() > MIN_COUNT){
            NaiveBayesClassifierTrain bys = new NaiveBayesClassifierTrain();
            RuleManager rmgr = new RuleManager();
            rmgr.setSegment(seg);
            bys.startTraining(cpses, rmgr); //贝叶斯分类器模型nbc作为bys的一个成员，在训练过程中被定义

            int spamCount = 0;
            for(int i = 0; i < cpses.size(); i++){
                Corpus cps = cpses.get(i);
                cps.value = bys.probDiff(cps);
                if(cps.isSpam()){
                    spamCount++;
                }
            }
            Section sec = divide(cpses, spamCount);

            double startVal = cpses.get(sec.start).value;
            double endVal = cpses.get(sec.end).value;
            double midVal = cpses.get(sec.mid).value;
            if(startVal < 0 || endVal > 0){
                Log.e("", "Training fail, for: startVal < 0 || endVal > 0");
            }
            if(Math.abs(startVal) < Math.abs(endVal)){
                Log.d("", "Training startVal changed from " + startVal + " to " + Math.abs(endVal));
                startVal = Math.abs(endVal);
            }

            bys.setThresh(new double[]{startVal, endVal, midVal});
            bys.setRuleManager(rmgr);

            Log.d("", "Multi-Bys level-" + mNbys.size() + ":\t" + startVal + " -> " +
                    endVal + " -> " + midVal);

            mNbys.add(bys);
            if((sec.end - sec.start + 1) > MIN_COUNT){
                cpses = cpses.subList(sec.start, sec.end);
            }else{
                break;
            }
        }
        mCounts = new int[mNbys.size() + 2][4];
        Log.d("", "Multi-Bys trainning finished, level count:" + mNbys.size());
    }

    private static Section divide(List<Corpus> cpses, int spamCount){
        Collections.sort(cpses, new Comparator<Corpus>(){
            public int compare(Corpus arg0, Corpus arg1) {
                return Double.compare(arg1.value, arg0.value);
            }
        });

        int total = cpses.size();
        final double SPM_RATIO = 1.0 * spamCount / total;

        int maxIndex = maxCorrectRatioThresh(cpses, spamCount);

        int start = -1, end = total;
        int startNext = start, endNext = end;
        int sc = 0,  nc = 0;

        // Should keep the spam/normal count balance
        while(start < end){
            if(startNext == start){
                for(startNext = start + 1; startNext < maxIndex; ++startNext){
                    if(!cpses.get(startNext).isSpam()){
                        break;
                    }
                }
            }
            if(endNext == end){
                for(endNext = end - 1; endNext >= maxIndex; --endNext){
                    if(cpses.get(endNext).isSpam()){
                        break;
                    }
                }
            }
            if(start < 0 || end >= total){
                sc = 1;
                nc = 1;
                start = startNext;
                end = endNext;
                continue;
            }

            boolean countRatio = (1.0 * (start + 1)) / (start + 1 + total - end) > SPM_RATIO;
            boolean spamStop = divideStop(start + 1, sc, total, PRECISION_COMM);
            boolean norStop = divideStop(total - end, nc, total, PRECISION_COMM);

            if((spamStop || norStop) && countBalance(start + 1, total - end, SPM_RATIO)){
                break;
            }

            if(countRatio){
                end = endNext;
                nc++;
            }else{
                start = startNext;
                sc++;
            }
        }

        System.out.println("divide result: total[" + spamCount + "/" + total + "]  spam[" + (start + 1 - sc) + "/" + (start + 1) + "],  normal[" + (total - end - nc) + "/" + (total - end) + "]");
        return new Section(start, end, maxIndex);
    }

    private static boolean divideStop(int passed, int error, int total, double thresh){
        double precision = 1.0 * (passed - error) / passed;
        if(precision > thresh){
            return false;
        }else if(passed <= MIN_COUNT_DIVIDE){
            return false;
        }else{
            if(passed < (int)(total * (1 - thresh)) && precision > (thresh * 0.8)){
                return false;
            }
        }
        return true;
    }

    private static boolean countBalance(int spam, int normal, double thresh){
        double val = 1.0 * spam / (spam + normal);
        val /= thresh;
        return val > 0.33 && val < 3.0;
    }

    private static int maxCorrectRatioThresh(List<Corpus> cpses, int spamCount){
        int maxIndex = 0;
        int lastCorrectC = cpses.size() - spamCount;  // initial all smses to be normal
        int maxCorrectC = lastCorrectC;
        for(int i = 0; i < cpses.size(); ++i){
            Corpus cps = cpses.get(i);
            if(cps.isSpam()){
                lastCorrectC++;
            }else{
                lastCorrectC--;
            }
            
            if(lastCorrectC > maxCorrectC){
                maxCorrectC = lastCorrectC;
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    final int Big_Version = 1;
    final int Small_Version = 0;
    public void saveModel(String file){
        try{
            FileOutputStream fileOut = new FileOutputStream(file);
            DataOutputStream dataOut = new DataOutputStream(fileOut);

            dataOut.writeInt(Big_Version);
            dataOut.writeInt(Small_Version);

            // level count
            dataOut.writeInt(mNbys.size());

            for(int i = 0; i < mNbys.size(); ++i){
                ((NaiveBayesClassifierTrain)mNbys.get(i)).saveModel(dataOut);

                double[] thresh = mNbys.get(i).getThresh();
                dataOut.writeDouble(thresh[0]);
                dataOut.writeDouble(thresh[1]);
                dataOut.writeDouble(thresh[2]);
            }

            fileOut.close();
            dataOut.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    static class Section{
        public int start;
        public int end;
        public int mid;
        public Section(int s, int e, int m){
            start = s;
            end = e;
            mid = m;
        }
    }
}
