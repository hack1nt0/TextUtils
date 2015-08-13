package com.xiaomi.nlp.classification.spamsms.smsspam.feature;

import com.xiaomi.nlp.classification.spamsms.android.util.Log;
import com.xiaomi.nlp.classification.spamsms.json.JSONArray;
import com.xiaomi.nlp.classification.spamsms.json.JSONException;
import com.xiaomi.nlp.classification.spamsms.json.JSONObject;
import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;

import java.util.ArrayList;
import java.util.List;

public class Body extends FeaturePattern{
    private static final String TAG = FeaturePattern.class.getName();

    List<int[]> mBodyLength;
    int[] mNumberTypes;
    int[] mStructTypes;

    Body(JSONObject object){
        super(object);
        try {
            if(object.has(Feature.KEY_BODY_LENGTH)){
                JSONArray arr = object.getJSONArray(Feature.KEY_BODY_LENGTH);
                for(int i = 0; i < arr.length(); ++i){
                    String range = arr.getString(i);
//                    Log.d(TAG, "Body length range:" + range);
                    String[] rangeStrs = range.split(Feature.BODY_LENGTH_SPLIT);
                    if(rangeStrs.length == 2){
                        int start = Integer.parseInt(rangeStrs[0]);
                        int end = Integer.parseInt(rangeStrs[1]);
                        if(mBodyLength == null){
                            mBodyLength = new ArrayList<int[]>();
                        }
                        mBodyLength.add(new int[]{start, end});
                    }
                }
            }

            if(object.has(Feature.KEY_BODY_NUMBER_TYPES)){
                JSONArray arr = object.getJSONArray(Feature.KEY_BODY_NUMBER_TYPES);
                mNumberTypes = new int[arr.length()];
                for(int i = 0; i < arr.length(); ++i){
                    mNumberTypes[i] = arr.getInt(i);
                }
            }

            if(object.has(Feature.KEY_BODY_STRUCT_TYPES)){
                JSONArray arr = object.getJSONArray(Feature.KEY_BODY_STRUCT_TYPES);
                mStructTypes = new int[arr.length()];
                for(int i = 0; i < arr.length(); ++i){
                    mStructTypes[i] = arr.getInt(i);
                }
            }
        }
        catch (JSONException e) {
            Log.e(TAG, "JSONException when decode KEY_TYPE features.");
        }
    }

    public boolean match(Corpus cps){
        int cpsLen = cps.getOrigBody().length();
        if(mBodyLength != null && mBodyLength.size() > 0){
            boolean hit = false;
            for(int[] ranges : mBodyLength){
                if(ranges.length == 2){
                    if(ranges[0] <= cpsLen && cpsLen <= ranges[1]){
                        hit = true;
                        break;
                    }
                }
            }
            if(!hit){
                return false;
            }
        }

        if(mNumberTypes != null && mNumberTypes.length > 0){
            if(cps.mNumbers == null || cps.mNumbers.size() == 0){
                return false;
            }

            boolean hit = false;
            for(int i = 0; i < cps.mNumbers.size() && !hit; ++i){
                Corpus.SMSNumber nb = cps.mNumbers.get(i);
                for(int t : mNumberTypes){
                    if(nb.type == t){
                        hit = true;
                        break;
                    }
                }
            }
            if(!hit){
                return false;
            }
        }

        if(mStructTypes != null && mStructTypes.length > 0){
            int structType = cps.getStructType();
            boolean hit = false;
            for(int t : mStructTypes){
                if((structType & t) != 0){
                    hit = true;
                    break;
                }
            }
            if(!hit){
                return false;
            }
        }

        return super.match(cps.getOrigBody());
    }
}