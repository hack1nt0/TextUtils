package com.xiaomi.nlp.classification.spamsms.smsspam.preprocess;

import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class RuleUpper extends Rule {

    public boolean fit(Corpus cps, int[] vals, int start){
        if(null == cps || null == vals || start >= vals.length){
            return false;
        }
        return doFitting(cps,  vals, start);
    }

    protected abstract boolean doFitting(Corpus cps, int[] vals, int start);

    // do some things after all corpus handled by RulePrevious, if need
    public void firstStepDone(){}

    // compute every corpus after RulePrevious, if need
    // It's only should be called on training step.
    protected void doFirstStep(Corpus cps){}

    public abstract void readDef(DataInputStream dataIn) throws IOException;
    public abstract void writeDef(DataOutputStream dataOut) throws IOException;
}
