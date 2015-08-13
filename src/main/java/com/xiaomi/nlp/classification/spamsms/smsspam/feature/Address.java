package com.xiaomi.nlp.classification.spamsms.smsspam.feature;


import com.xiaomi.nlp.classification.spamsms.android.util.Log;
import com.xiaomi.nlp.classification.spamsms.json.JSONArray;
import com.xiaomi.nlp.classification.spamsms.json.JSONException;
import com.xiaomi.nlp.classification.spamsms.json.JSONObject;
import com.xiaomi.nlp.classification.spamsms.smsspam.Corpus;

public class Address extends FeaturePattern{
    private static final String TAG = Address.class.getName();

    private int[] mTypes;
    Address(JSONObject object){
        super(object);
        try {
            if(object.has(Feature.KEY_TYPE)){
                JSONArray arr = object.getJSONArray(Feature.KEY_TYPE);
                mTypes = new int[arr.length()];
                for(int i = 0; i < arr.length(); ++i){
                    mTypes[i] = arr.getInt(i);
                }
            }
        }
        catch (JSONException e) {
            Log.e(TAG, "JSONException when decode KEY_TYPE features.");
        }
    }

    public boolean match(Corpus cps){
        int type = cps.getAddressType();
        if(mTypes != null && mTypes.length > 0){
            boolean has = false;
            for(int t : mTypes){
                if(t == type){
                    has = true;
                    break;
                }
            }
            if(!has){
                return false;
            }
        }
        return super.match(cps.getAddress());
    }
}
