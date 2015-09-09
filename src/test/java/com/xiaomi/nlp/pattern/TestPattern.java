package com.xiaomi.nlp.pattern;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy on 15-9-9.
 */
public class TestPattern {
    @Test
    public void testMiningPatternsSingleThread() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("data/test/testPattern.txt")));
        MiningPatterns miningPatterns = new MiningPatterns(0.3);
        List<MiningPatterns.Line> initialList = new ArrayList<MiningPatterns.Line>();
        while (true) {
            String line = in.readLine();
            if (line == null) break;
            if (line.length() == 0) {
                while (line != null && line.length() == 0) line = in.readLine();
                miningPatterns.inital(initialList);
                for (SmsPattern pattern: miningPatterns.getPatWithPosition()) {
                    System.out.println(pattern.getSup() + "\t" + pattern.toString());
                }
                if (line == null) break;
                initialList.clear();
                continue;
            }
            String[] splitLine = line.split("####");
            initialList.add(new MiningPatterns.Line(splitLine[5], Integer.parseInt(splitLine[2])));
        }

    }
}
