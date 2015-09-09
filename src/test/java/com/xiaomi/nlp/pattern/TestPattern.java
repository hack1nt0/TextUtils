package com.xiaomi.nlp.pattern;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by dy on 15-9-9.
 */
public class TestPattern {
    @Test
    public void testMiningPatternsSingleThread() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("data/testPattern.txt")));
        MiningPatterns miningPatterns = new MiningPatterns(0.3);
        while (true) {
            String line = in.readLine();
            if (line == null);
            if (line.length() == 0) {
                miningPatterns.getPatWithPosition()
            }
        }

    }
}
