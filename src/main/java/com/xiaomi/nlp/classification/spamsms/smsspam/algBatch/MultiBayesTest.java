package com.xiaomi.nlp.classification.spamsms.smsspam.algBatch;

import com.xiaomi.nlp.classification.spamsms.smsspam.train.MultiBayesBatch;
import com.xiaomi.nlp.classification.spamsms.smsspam.train.PCVals;
import com.xiaomi.nlp.tokenizer.MyTokenizer;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MultiBayesTest extends MultiBayesBatch {
    private final static MultiBayesTest INSTANCE = new MultiBayesTest();

    public static MultiBayesTest getInstance() {
        return INSTANCE;
    }

    public MultiBayesTest() {
        //super();
        try {
            readModel(PCVals.ModelFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readModel(String modelPath) throws IOException,
    InstantiationException, IllegalAccessException, ClassNotFoundException{
        MyTokenizer seg = MyTokenizer.getInstance();

        InputStream fileIn = MultiBayesTest.class.getClassLoader().getResourceAsStream(modelPath);
        DataInputStream dataIn = new DataInputStream(fileIn);

        // Version numbers
        dataIn.readInt();  // Big Version
        dataIn.readInt();  // Small Version

        int levelCount = dataIn.readInt();
        for(int i = 0; i < levelCount; ++i){
            NaiveBayesClassifierTest bys = new NaiveBayesClassifierTest();
            bys.readModel(dataIn);
            bys.getRuleManager().setSegment(seg);

            double[] threshes = new double[3];
            threshes[0] = dataIn.readDouble();
            threshes[1] = dataIn.readDouble();
            threshes[2] = dataIn.readDouble();
            bys.setThresh(threshes);

            mNbys.add(bys);
        }

        dataIn.close();
        fileIn.close();
        mCounts = new int[levelCount + 2][4];
    }
}
