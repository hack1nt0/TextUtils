package com.xiaomi.nlp;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by dy on 16-1-20.
 */

public class PhoneNumClf {
    class Mark {
        String imei;
        int catId;

        public String getImei() {
            return imei;
        }

        public int getCatId() {
            return catId;
        }
    }

    class Feedback {
        String imei;
        String text;
        int originalCatId;

        public String getImei() {
            return imei;
        }

        public String getText() {
            return text;
        }

        public int getOriginalCatId() {
            return originalCatId;
        }
    }

    class Record {
        int provider;
        int catId;
        int count;

    }

    String[] cat2name = {"未标记",};

    int w1 = -1;
    int w2 = -1;

    public int getW1() {
        return w1;
    }

    public void setW1(int w1) {
        this.w1 = w1;
    }

    public int getW2() {
        return w2;
    }

    public void setW2(int w2) {
        this.w2 = w2;
    }

    static class Vec {
        static float[] fromString(String s) {
            if (s.length() < 2) throw new IllegalArgumentException("Vec string length less than 2.");
            s = s.substring(1, s.length() - 1);
            String[] tmp = s.split(" ");
            float[] res = new float[tmp.length];
            for (int i = 0; i < res.length; ++i) res[i] = Float.parseFloat(tmp[i]);
            return res;
        }

        static float[] add(float[] A, float[] B) {
            if (A.length != B.length) throw new IllegalArgumentException("Given Vecs are not the same Dimension.");
            float[] res = new float[A.length];
            for (int i = 0; i < A.length; ++i) res[i] = A[i] + B[i];
            return res;
        }

        static float dot(float[] A, float[] B) {
            if (A.length != B.length) throw new IllegalArgumentException("Given Vecs are not the same Dimension.");
            float res = 0;
            for (int i = 0; i < A.length; ++i) res += A[i] * B[i];
            return res;
        }

        static float[] mul(float a, float[] B) {
            float[] res = new float[B.length];
            for (int i = 0; i < B.length; ++i) res[i] = a * B[i];
            return res;
        }

        static float[] mul(float[] A, float[] B) {
            float[] res = new float[A.length];
            for (int i = 0; i < B.length; ++i) res[i] = A[i] * B[i];
            return res;
        }

        static int argmax(float[] A) {
            if (A.length == 0) return -1;
            int res = 0;
            for (int i = 0; i < A.length; ++i)
                if (A[i] > A[res]) res = i;
            return res;
        }
    }

    //imei -> credit
    private HashMap<String, Float> userCredit = new HashMap<String, Float>();
    //phone regex -> prior[]
    private HashMap<String, float[]> prior = new HashMap<String, float[]>();
    //phone regex -> phone description
    private HashMap<String, String> priorDesc = new HashMap<String, String>();
    //feedback cat regex
    private List<String> feedbackDic = new ArrayList<String>();
    private List<Pattern> feedbackDicPats = null;
    private int feedbackLowerBound;

    public PhoneNumClf(String userCreditFile, String priorFile, String feedbackDicFile) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(userCreditFile)));
            while (true) {
                String line = in.readLine();
                if (line == null) break;
                String[] tmp = line.split("\\t");
                userCredit.put(trimQuote(tmp[0]), Float.parseFloat(tmp[1]));
            }

            in = new BufferedReader(new InputStreamReader(new FileInputStream(priorFile)));
            while (true) {
                String line = in.readLine();
                if (line == null) break;
                String[] tmp = line.split("\\t");
                prior.put(trimQuote(tmp[1]), Vec.fromString(trimQuote(tmp[2])));
                priorDesc.put(trimQuote(tmp[1]), trimQuote(tmp[0]));
            }

            in = new BufferedReader(new InputStreamReader(new FileInputStream(feedbackDicFile)));
            while (true) {
                String line = in.readLine();
                if (line == null) break;
                feedbackDic.add(trimQuote(line));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String trimQuote(String s) {
        if (s.length() < 2) throw new IllegalArgumentException("s is not quoted.");
        return s.substring(1, s.length() - 1);
    }

    private float getCredit(String imei) {
        if (!userCredit.containsKey(imei)) imei = "Unknown";
        return userCredit.get(imei);
    }

    private float[] getFeedbackVotes(List<Feedback> feedbacks) { // TODO: 16/1/28
        if (feedbackDicPats == null) {
            int n = feedbackDic.size();
            feedbackLowerBound = Integer.parseInt(feedbackDic.get(n - 1));
            String notPrefix = feedbackDic.get(n - 3);
            String notOriginal = feedbackDic.get(n - 2);
            for (int i = 0; i < n - 3; ++i) feedbackDicPats.add(Pattern.compile(notPrefix + feedbackDic.get(i)));
            for (int i = 0; i < n - 3; ++i) feedbackDicPats.add(Pattern.compile(feedbackDic.get(i)));
            feedbackDicPats.add(Pattern.compile(notOriginal));
        }

        int numCat = cat2name.length;
        float[] res = new float[numCat];

        if (feedbacks.size() <= feedbackLowerBound) {
            float avgCredit = 0.0f;
            for (Feedback feedback: feedbacks) avgCredit += getCredit(feedback.getImei());
            avgCredit /= feedbacks.size();
            if (avgCredit <= getCredit("Unknown")) {
                return res;
            }
        }

        for (Feedback feedback: feedbacks) {
            String text = feedback.getText();
            float[] votes = new float[numCat];
            int originalCatId = feedback.getOriginalCatId();
            for (int i = 1; i < numCat; ++i)
                if (feedbackDicPats.get(i - 1).matcher(text).find()) votes[i] = -1;
            for (int i = 1; i < numCat; ++i) {
                if (votes[i] != 0) continue;
                if (feedbackDicPats.get(i - 2 + numCat).matcher(text).find()) votes[i] = +1;
            }
            if (originalCatId != 0 && feedbackDicPats.get((numCat - 1) * 2).matcher(text).find())
                votes[originalCatId] = -1;

            boolean existPos = false;
            for (float vote: votes) if (vote > 0) existPos = true;
            if (!existPos) votes[0] = +1;

            float credit = getCredit(feedback.getImei());
            votes = Vec.mul(credit, votes);
            res = Vec.add(res, votes);
        }
        return res;
    }

    private float[] getPriorVotes(String no) {
        for (String catRegex : prior.keySet()) {
            if (no.matches(catRegex)) return prior.get(catRegex);
        }
        float[] res = new float[cat2name.length];
        Arrays.fill(res, 1.0f / res.length);
        return res;
    }

    public int classify(String no, List<Mark> marks, List<Feedback> feedbacks, List<Record> records) {
        int numCat = cat2name.length;

        float[] priorVotes = getPriorVotes(no);

        float[] userCreditVotes = new float[numCat];
        for (Mark mark : marks) {
            int catId = mark.getCatId();
            float credit = getCredit(mark.imei);
            userCreditVotes[catId] += credit;
        }

        float[] feedbackVotes = new float[numCat];
        float[] votes = getFeedbackVotes(feedbacks);
        feedbackVotes = Vec.add(feedbackVotes, votes);

        if (w1 == -1) w1 = feedbacks.size();
        if (w2 == -1) w2 = marks.size();

        float[] finalVotes = Vec.mul(Vec.add(Vec.mul(w2, userCreditVotes), Vec.mul(w1, feedbackVotes)), priorVotes);

        int finalCat = Vec.argmax(finalVotes);
        return finalCat;
    }

    static String javaTestDataFile = "/Users/DY/Desktop/I2NLP/Assignment0/data/java_test_data.csv";
    static String userCreditFile = "/Users/DY/Desktop/I2NLP/Assignment0/data/anti_mark_user_credit_model.csv";
    static String priorFile = "/Users/DY/Desktop/I2NLP/Assignment0/data/prior_model.csv";
    static String feedbackDicFile = "/Users/DY/Desktop/I2NLP/Assignment0/data/anti_feedback_model.csv";


    public static void main(String[] args) throws IOException {
        long t1 = System.currentTimeMillis();
        PhoneNumClf clf = new PhoneNumClf(userCreditFile, priorFile, feedbackDicFile);

        long t2 = System.currentTimeMillis();
        System.out.println("Initial step costed " +  ((t2 - t1) / 1000.0) + " s");

        //test
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("javaTestDataFile")));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("data/java_test_data_output.csv")));
        while (true) {
            String line = in.readLine();
            if (line == null) break;
            String[] tmp = line.split("\\t");
            String no = tmp[0];
            List<Record> records = new ArrayList<Record>();
            records.add(Record.fromString(tmp[1]));

            List<Feedback> feedbacks = Feedback.fromString(tmp[2]);

            List<Mark> marks = Mark.fromString(tmp[3] + '\t' + tmp[4]);

            int finalCatId = clf.classify(no, marks, feedbacks, records);

            out.println(line + '\t' + finalCatId);
        }
        out.close();
    }
}

