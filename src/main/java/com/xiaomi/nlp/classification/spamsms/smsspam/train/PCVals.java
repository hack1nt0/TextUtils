package com.xiaomi.nlp.classification.spamsms.smsspam.train;

import com.xiaomi.nlp.classification.spamsms.json.JSONException;
import com.xiaomi.nlp.classification.spamsms.json.JSONObject;
import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;
import com.xiaomi.nlp.classification.spamsms.smsspam.ModelLoader;
import com.xiaomi.nlp.classification.spamsms.smsspam.preprocess.RuleManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PCVals {

    public static final String TrainFile = "data/train/testSpamsms.txt";

//    public static final String TestFile = TrainDir + "training_data";
//    public static final String TestFile = TrainDir + "test_baidu_5000";
    public static final String TestFile = "data/test/testSpamsms.txt";
//    public static final String TestFile = TrainDir + "test_single";

    public static final String ModeDir = "com/xiaomi/nlp/classification/spamsms/";

    public static final String ModelFile = ModeDir + ModelLoader.MODEL_MBYS;

    public static final String PhishFile = ModeDir + ModelLoader.MODEL_PHISH;

    public static final String PatterFile = ModeDir + ModelLoader.MODEL_PATTERN;

    public static void saveSmses(String file, List<Corpus> cps){
        try{
            OutputStream outs = new FileOutputStream(file);
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(outs));
            for(Corpus sms : cps){
                JSONObject object = new JSONObject();
                object.put(Corpus.ADDRESS, sms.getAddress());
                object.put(Corpus.BODY, sms.getOrigBody());
                object.put(Corpus.SPAM, sms.isSpam());
                object.put(Corpus.ID, sms.getId());
        
                wr.write(object.toString());
                wr.newLine();
            }
            wr.close();
            outs.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static List<Corpus> readPreprocessSMS(String file, RuleManager rmgr){
        List<Corpus> cpses = readSMS(file);
        for(Corpus cps : cpses){
            rmgr.preprocess(cps);
        }
        return cpses;
    }

    public static List<Corpus> readSMS(String file){
        List<Corpus> allMsm = new ArrayList<Corpus>();
        try {
            InputStream ins = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String json;

            int spamNum = 0;
            int totalNum = 0;

            while((json = br.readLine()) != null){
                JSONObject object = new JSONObject(json);
                String body = object.getString(Corpus.BODY);
                boolean spam = object.getBoolean(Corpus.SPAM);
                String address = object.getString(Corpus.ADDRESS);
                int id = 1;
                if(object.has(Corpus.ID)){
                    id = object.getInt(Corpus.ID);
                }

                totalNum++;
                if(spam){
                    spamNum++;
                }

                Corpus cps = new Corpus(address, body);
                cps.setSpam(spam);
                cps.setId(id);
                allMsm.add(cps);
            }

            System.out.println("totalNum:" + totalNum + "\tspamNum:" + spamNum);
            ins.close();
            br.close();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return allMsm;
    }
}
