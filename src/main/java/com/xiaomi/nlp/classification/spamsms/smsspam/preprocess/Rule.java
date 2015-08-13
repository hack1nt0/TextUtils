package com.xiaomi.nlp.classification.spamsms.smsspam.preprocess;

/**
 * Previous rules could be processed before training.
 * There is no need to do previous rule at every level of training.
 *
 * Upper rules should be processed at every of training, every training
 * short messages must be bring into statistic to fill each upper rules.
 *
 * @author qinqiuping
 *
 */
public abstract class Rule {
    protected static final int DEFAULT_SUB_COUNT = 1;
    protected static final int MAX_NAME_LENGTH = 30;

    protected int subClassCount(){
        return DEFAULT_SUB_COUNT;
    }

    public String getClassName(int i){
        if(i < subClassCount() && i >= 0){
            return padding(getClassName(getClass()));
        }
        return null;
    }

    protected static String getClassName(Class<? extends Rule> c){
        String name = c.getName();
        name = name.substring(name.lastIndexOf(".") + 1);
        return name;
    }

    protected static String padding(String name){
        if(name.length() < MAX_NAME_LENGTH){
            StringBuffer sb = new StringBuffer();
            int paddingCount = MAX_NAME_LENGTH - name.length();
            sb.append(name);
            while(paddingCount-- > 0){
                sb.append(" ");
            }
            return sb.toString();
        }
        return name;
    }

    public abstract String getName();
}
