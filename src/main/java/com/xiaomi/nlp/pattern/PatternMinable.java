package com.xiaomi.nlp.pattern;

import java.util.List;

public interface PatternMinable {
    String getCorpus();

    List<String> getTokens();

    int getSupport();
}

