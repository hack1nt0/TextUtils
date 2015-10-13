package com.xiaomi.nlp.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by dy on 15-10-13.
 */
public class SynonymDict {
    HashMap<Integer, Integer> ps = new HashMap<Integer, Integer>();
    HashMap<String, Integer> ids = new HashMap<String, Integer>();

    public SynonymDict() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("synonym-dict.txt")));
            while (true) {
                String line = in.readLine();
                if (line == null) break;
                String[] tokens = line.split("[ ]+");
                for (int i = 0; i < tokens.length; ++i) {
                    ids.put(tokens[i], ids.size());
                    ps.put(ids.get(tokens[i]), i == 0 ? ids.get(tokens[i]) : ids.get(tokens[0]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean iSimilar(String A, String B) {
        if (!(ids.containsKey(A) && ids.containsKey(B))) return false;
        return ps.get(ids.get(A)) == ps.get(ids.get(B));
    }
}
