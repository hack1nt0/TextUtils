package com.xiaomi.nlp.classification.spamsms.smsspam.train.remark;

import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;
import com.xiaomi.nlp.classification.spamsms.smsspam.train.PCVals;

import java.util.ArrayList;
import java.util.List;

public class RemarkAndMerge {

    public static void mergeNewCorpus(String orig, String neww){
        List<Corpus> origCps = null;
        if(orig != null){
            origCps = PCVals.readSMS(orig);
        }
        List<Corpus> newCps = PCVals.readSMS(neww);

        int lastId = 1;
        if(origCps != null && origCps.size() > 0){
            lastId = origCps.get(origCps.size() - 1).getId() + 1;
        }
        List<Corpus> mergeCps = new ArrayList<Corpus>();

        if(origCps != null){
            mergeCps.addAll(origCps);
        }

        for(int i = 0; i < newCps.size(); ++i){
            Corpus cps = newCps.get(i);
            cps.setId(lastId + i);
            mergeCps.add(cps);
        }

        PCVals.saveSmses(neww + ".merged", mergeCps);
    }

    public static void mergeRemarkCorpus(String orig, String remark){
        List<Corpus> origCps = PCVals.readSMS(orig);
        List<Corpus> newCps = PCVals.readSMS(remark);

        int remarkCount = 0;
        for(int i = 0; i < newCps.size(); ++i){
            Corpus cps = newCps.get(i);
            if(cps.isSpam() != origCps.get(cps.getId() - 1).isSpam()){
                origCps.get(cps.getId() - 1).setSpam(cps.isSpam());
                remarkCount++;
            }
        }

        PCVals.saveSmses(remark + ".remark", origCps);
        System.out.println("remark count:" + remarkCount);
    }

    public static void markAndMerge(String mark, String orig){
        new Tables(mark, orig);
    }
    public static void main(String[] args) {
//        mergeNewCorpus(null, PCVals.TrainFile);

//        mergeRemarkCorpus(PCVals.TrainFile, PCVals.TrainFile + ".bysWro");

        markAndMerge(PCVals.TrainFile + ".featureWro", PCVals.TrainFile);
    }
}
