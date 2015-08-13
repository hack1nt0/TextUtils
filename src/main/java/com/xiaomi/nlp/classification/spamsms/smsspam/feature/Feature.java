package com.xiaomi.nlp.classification.spamsms.smsspam.feature;

import com.xiaomi.nlp.classification.spamsms.android.util.Log;
import com.xiaomi.nlp.classification.spamsms.json.JSONArray;
import com.xiaomi.nlp.classification.spamsms.json.JSONException;
import com.xiaomi.nlp.classification.spamsms.json.JSONObject;
import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Feature {
    private static final String TAG = Feature.class.getName();

    public static final int TYPE_NONE = 0;

    public static final String KEY_NAME =                 "name";
    public static final String KEY_ABSTRACT =             "abstract";
    public static final String KEY_ADDRESS =              "address";
    public static final String KEY_BODY =                 "body";
    public static final String KEY_PATTERN =              "pattern";
    public static final String KEY_NEGATIVE_PATTERN =     "neg_pattern";
    public static final String KEY_OR_PATTERN =           "or_pattern";
    public static final String KEY_BODY_LENGTH =          "body_len";
    public static final String KEY_BODY_NUMBER_TYPES =    "num_types";
    public static final String KEY_BODY_STRUCT_TYPES =    "struct_types";

    public static final String KEY_SMS_TYPE =             "sms_type";
    public static final String KEY_TYPE =                 "type";
    public static final String KEY_IS_ASSISTANT =         "assist";
    public static final String KEY_IS_COMBINATION =       "combi";

    public static final String BODY_LENGTH_SPLIT = "-";

    private Address mAddress;
    private Body mBody;
    private int mType;

    private String mName;

    private Map<String, Feature> mFeatureMap;

    private List<String[]> mCombinations;
    private boolean mIsAssist = false;

    public Feature(Map<String, Feature> map){
        mFeatureMap = map;
    }

    public boolean init(JSONObject object){
        if(!object.has(KEY_SMS_TYPE) && !object.has(KEY_IS_ASSISTANT)){
            return false;
        }
        try {
            if(object.has(KEY_SMS_TYPE)){
                mType = object.getInt(KEY_SMS_TYPE);
            }

            if(object.has(KEY_IS_ASSISTANT) && object.getBoolean(KEY_IS_ASSISTANT)){
                mIsAssist = true;
            }

            if(object.has(KEY_NAME)){
                mName = object.getString(KEY_NAME);
//                Log.d(TAG, "KEY_NAME:" + mName);
            }

            if(object.has(KEY_ADDRESS)){
//                Log.d(TAG,"object.has(KEY_ADDRESS)");
                mAddress = new Address(object.getJSONObject(KEY_ADDRESS));
            }

            if(object.has(KEY_BODY)){
                //Log.d(TAG,"object.has(KEY_BODY)");
                mBody = new Body(object.getJSONObject(KEY_BODY));
            }

            if(object.has(KEY_IS_COMBINATION)){
                JSONArray arr = object.getJSONArray(Feature.KEY_IS_COMBINATION);
                int len = arr.length();
                mCombinations = new ArrayList<String[]>();
                for(int i = 0; i < len; ++i){
                    String str = arr.getString(i);
                    mCombinations.add(str.split("&"));
                }
            }

            if(mAddress == null && mBody == null && mCombinations == null){
                return false;
            }
        }catch (JSONException e) {
            Log.e(TAG, "JSONException when decode features.");
            return false;
        }
        return true;
    }

    public String getName(){
        return mName;
    }

    public boolean isAssist(){
        return mIsAssist;
    }

    public int getType(){
        return mType;
    }

    public boolean match(Corpus cps){
        if(mAddress != null){
            if(!mAddress.match(cps)){
                return false;
            }
        }
        if(mBody != null){
            if(!mBody.match(cps)){
                return false;
            }
        }
        // combinations must match one
        if(mCombinations != null){
            for(String[] fts : mCombinations){
                boolean match = true;
                for(String ft : fts){
                    Feature feature = mFeatureMap.get(ft);
                    if(!feature.match(cps)){
                        match = false;
                        break;
                    }
                }
                if(match){
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}
