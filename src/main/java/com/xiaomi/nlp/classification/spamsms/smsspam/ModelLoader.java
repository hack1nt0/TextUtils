package com.xiaomi.nlp.classification.spamsms.smsspam;

import com.xiaomi.nlp.classification.spamsms.android.content.Context;
import com.xiaomi.nlp.classification.spamsms.android.util.Log;
import com.xiaomi.nlp.classification.spamsms.smsspam.train.PCVals;

import java.io.*;

public class ModelLoader {
    private static final String TAG = ModelLoader.class.getName();

    public static final String MODEL_MBYS = "model";
    public static final String MODEL_PATTERN = "pattern";
    public static final String MODEL_PHISH = "phish";

    public static InputStream open(Context context, String name) {
        try {
            InputStream ins = new FileInputStream(PCVals.ModeDir + name);
            return ins;
        } catch (IOException e) {
            Log.e(TAG, "load Model file " + name + " failed.");
        }
        return null;
    }

    public static String getPattern(Context context){
        try{
            InputStream ins = ModelLoader.class.getClassLoader().getResourceAsStream(PCVals.PatterFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = br.readLine()) != null){
                sb.append(line);
            }
            br.close();
            ins.close();
            return sb.toString();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
