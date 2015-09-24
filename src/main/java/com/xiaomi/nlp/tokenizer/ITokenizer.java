package com.xiaomi.nlp.tokenizer;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DY on 15/8/26.
 */
public interface ITokenizer extends Serializable {
    public static final long serialVersionUID = 11L;
    public List<String> getTokens(String text);
}
