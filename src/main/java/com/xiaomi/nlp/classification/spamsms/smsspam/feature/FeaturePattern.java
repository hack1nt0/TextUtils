package com.xiaomi.nlp.classification.spamsms.smsspam.feature;

import com.xiaomi.nlp.classification.spamsms.android.util.Log;
import com.xiaomi.nlp.classification.spamsms.json.JSONArray;
import com.xiaomi.nlp.classification.spamsms.json.JSONException;
import com.xiaomi.nlp.classification.spamsms.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class FeaturePattern{
    private static final String TAG = FeaturePattern.class.getName();

    /**
     * The priority is: common patterns     &&
     *                  negative patterns   !
     *                  OR patterns         ||
     */
    protected String[] mPatternStrs;
    private Pattern[] mPatterns;

    protected String[] mNegPatternStrs;
    private Pattern[] mNegPatterns;

    protected String[] mOrPatternStrs;
    private Pattern[] mOrPatterns;

    FeaturePattern(JSONObject object){
        try {
            if(object.has(Feature.KEY_PATTERN)){
                JSONArray arr = object.getJSONArray(Feature.KEY_PATTERN);
                int patternCount = arr.length();
                mPatternStrs = new String[patternCount];
                mPatterns = new Pattern[patternCount];

                for(int i = 0; i < patternCount; ++i){
                    mPatternStrs[i] = arr.getString(i);
                    mPatterns[i] = Pattern.compile(mPatternStrs[i], Pattern.CASE_INSENSITIVE);
//                    Log.d(TAG, "Pattern:" + mPatternStrs[i]);
                }
            }

            if(object.has(Feature.KEY_NEGATIVE_PATTERN)){
                JSONArray arr = object.getJSONArray(Feature.KEY_NEGATIVE_PATTERN);
                int patternCount = arr.length();
                mNegPatternStrs = new String[patternCount];
                mNegPatterns = new Pattern[patternCount];

                for(int i = 0; i < patternCount; ++i){
                    mNegPatternStrs[i] = arr.getString(i);
                    mNegPatterns[i] = Pattern.compile(mNegPatternStrs[i], Pattern.CASE_INSENSITIVE);
//                    Log.d(TAG, "Negtive pattern:" + mNegPatternStrs[i]);
                }
            }

            if(object.has(Feature.KEY_OR_PATTERN)){
                JSONArray arr = object.getJSONArray(Feature.KEY_OR_PATTERN);
                int patternCount = arr.length();
                mOrPatternStrs = new String[patternCount];
                mOrPatterns = new Pattern[patternCount];

                for(int i = 0; i < patternCount; ++i){
                    mOrPatternStrs[i] = arr.getString(i);
                    mOrPatterns[i] = Pattern.compile(mOrPatternStrs[i], Pattern.CASE_INSENSITIVE);
//                    Log.d(TAG, "Or pattern:" + mOrPatternStrs[i]);
                }
            }
        }
        catch (JSONException e) {
            Log.e(TAG, "JSONException when decode KEY_PATTERN features.");
        }
    }

    protected boolean match(String str){
        if(mPatterns != null && mPatterns.length > 0){
            for(Pattern pt : mPatterns){
                Matcher matcher = pt.matcher(str);
                if(!matcher.find()){
                    return false;
                }
            }
        }

        if(mNegPatterns != null && mNegPatterns.length > 0){
            for(Pattern pt : mNegPatterns){
                Matcher matcher = pt.matcher(str);
                if(matcher.find()){
                    return false;
                }
            }
        }

        if(mOrPatterns != null && mOrPatterns.length > 0){
            for(Pattern pt : mOrPatterns){
                Matcher matcher = pt.matcher(str);
                if (matcher.find()) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}