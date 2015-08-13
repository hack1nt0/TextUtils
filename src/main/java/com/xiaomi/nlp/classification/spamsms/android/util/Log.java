package com.xiaomi.nlp.classification.spamsms.android.util;

import com.xiaomi.nlp.classification.spamsms.smsspam.Options;

public class Log {
    public static void d(String TAG, String content){
        if (Options.DEBUG) System.out.println("[" + TAG + "]:" + content);
    }

    public static void e(String TAG, String content){
        if (Options.DEBUG) System.err.println("[" + TAG + "]:" + content);
    }
}
