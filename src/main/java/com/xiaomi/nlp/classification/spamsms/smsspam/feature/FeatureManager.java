package com.xiaomi.nlp.classification.spamsms.smsspam.feature;

import com.xiaomi.nlp.classification.spamsms.android.util.Log;
import com.xiaomi.nlp.classification.spamsms.json.JSONArray;
import com.xiaomi.nlp.classification.spamsms.json.JSONException;
import com.xiaomi.nlp.classification.spamsms.json.JSONObject;
import com.xiaomi.nlp.classification.spamsms.json.JSONTokener;
import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeatureManager {
    private static final String TAG = FeatureManager.class.getName();

    /**
     * 0x87654321
     *   8:       Reserved
     *    765:    Spam used
     *       4321:Normal used
     *
     *       Every 4 bits is a unit, as a F.
     *       unit is: spamV - normalV
     */
    public static final int TYPE_NORMAL = 0x0000FFFF;
    public static final int TYPE_SPAM   = 0x0FFF0000;
    public static final int TYPE_HAS    = 0x0FFFFFFF;

    public static final int WEIGHT_UNIT = 4;

    // For normal weight, the value is smaller, the weight is higher
    public static final int TYPE_LET_GO           =         0x00000001;
    public static final int TYPE_LIKE_NORMAL_MASK =         0x0000FFFF;


    // For spam weight, the value is bigger, the weight is higher
    public static final int TYPE_BLOCK          =           0x08000000;
    public static final int TYPE_LIKE_SPAM_MASK =           0x0FFF0000;

    public static final String KEY_VERSION = "version";
    private static final String KEY_FEATURES = "features";

    private Map<String, Feature> mFeatureMap;
    private List<Feature> mFeatures;

    private boolean mInited = false;

    public static int getSpamWeight(int v){
        int formatV = (v & TYPE_LIKE_SPAM_MASK) >> 16;
        int spamW = 0, step = 1;
        while(formatV != 0){
            if((formatV & 1) != 0){
                spamW += step;
            }
            formatV = (formatV >> 1);
            step++;
        }
        return spamW;
    }

    public static int getNormalWeight(int v){
        int formatV = (v & TYPE_LIKE_NORMAL_MASK) >> 4;
        // 12 is the count of 1 in TYPE_LIKE_NORMAL_MASK
        int spamW = 0, step = 12;
        while(formatV != 0){
            if((formatV & 1) != 0){
                spamW += step;
            }
            formatV = (formatV >> 1);
            step--;
        }
        return spamW;
    }

    public void loadFeature(String ins) {
        try{
            JSONObject object =  new JSONObject(new JSONTokener(ins));
            String version = object.getString(KEY_VERSION);
            Log.d(TAG, "Version:" + version);

            JSONArray features = object.getJSONArray(KEY_FEATURES);
            int featureCount = features.length();
            Log.d(TAG,"feature count:" + featureCount);

            mFeatureMap = new HashMap<String, Feature>();
            mFeatures = new ArrayList<Feature>();
            for(int i = 0; i < featureCount; ++i){
                Feature ft = new Feature(mFeatureMap);
                if(ft.init(features.getJSONObject(i))){
                    mFeatureMap.put(ft.getName(), ft);
                    mFeatures.add(ft);
                }
            }
            mInited = true;
        }catch(JSONException e){
            mInited = false;
            Log.e(TAG, "JSONException when load features from pattern files");
            e.printStackTrace();
        }
    }

    public boolean inited(){
        return mInited;
    }

    public int smsType(Corpus cps){
        int type = 0;
        for(Feature feature : mFeatures){
            if(!feature.isAssist()){
                if(feature.match(cps)){
                    type |= feature.getType();
                }
            }
        }
        return type;
    }
}
