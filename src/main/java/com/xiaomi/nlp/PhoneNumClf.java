package com.xiaomi.nlp;

import java.io.*;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dy on 16-1-20.
 */

public class PhoneNumClf {
    class Mark {
        String imei;
        int cat;
    }

    class Feedback {
        String imei;
        String text;
    }

    class Record {
        String app;
        int cat;
        int numOfVoters;
    }

    String[] cat2name = {"位标记",};

    int w1 = -1;
    int w2 = -1;


    static class UserCreditRow {
        float credit;
        int numVotes;

        UserCreditRow(String[] rowString){

        }
    }

    static class Vec {
        static float[] fromString(String s) {
            if (s.length() < 2) throw new IllegalArgumentException("Vec string length less than 2.");
            s = s.substring(1, s.length() - 1);
            String[] tmp = s.split(",");
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

    private HashMap<String, UserCreditRow> userCredit = new HashMap<String, UserCreditRow>();
    private HashMap<String, float[]> prior = new HashMap<String, float[]>();
    private HashMap<Integer, String> feedbackDic = new HashMap<Integer, String>();

    public PhoneNumClf(String userCreditFile, String priorFile, String feedbackDicFile) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(userCreditFile)));
            while (true) {
                String line = in.readLine();
                if (line == null) break;
                String[] tmp = line.split("\\t");
                String imei = tmp[0];
                userCredit.put(imei, new UserCreditRow(tmp));
            }

            in = new BufferedReader(new InputStreamReader(new FileInputStream(priorFile)));
            while (true) {
                String line = in.readLine();
                if (line == null) break;
                String[] tmp = line.split("\\t");
                prior.put(tmp[0], Vec.fromString(tmp[1]));
            }

            in = new BufferedReader(new InputStreamReader(new FileInputStream(feedbackDicFile)));
            while (true) {
                String line = in.readLine();
                if (line == null) break;
                String[] tmp = line.split("\\t");
                feedbackDic.put(Integer.parseInt(tmp[0]), tmp[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private float getCredit(String imei) {
        return 0;
    }

    private float[] getFeedbackVotes(String imei, String text) {
        return new float[0];
    }

    private float[] getPriorVotes(String no) {
        for (String catRegex : prior.keySet()) {
            if (no.matches(catRegex)) return prior.get(catRegex);
        }
        float[] res = new float[cat2name.length];
k   }
    public int classify(String no, List<Mark> marks, List<Feedback> feedbacks, List<Record> records) {
        int numCat = cat2name.length;

        float[] priorVotes = getPriorVotes(no);

        float[] userCreditVotes = new float[numCat];
        for (Mark mark : marks) {
            int cat = mark.cat;
            float credit = getCredit(mark.imei);
            userCreditVotes[cat] += credit;
        }

        float[] feedbackVotes = new float[numCat];
        for (Feedback feedback: feedbacks) {
            float[] votes = getFeedbackVotes(feedback.imei, feedback.text);
            feedbackVotes = Vec.add(feedbackVotes, votes);
        }

        if (w1 == -1) w1 = feedbacks.size();
        if (w2 == -1) w2 = marks.size();

        float[] finalVotes = Vec.mul(Vec.add(Vec.mul(w2, userCreditVotes), Vec.mul(w1, feedbackVotes)), priorVotes);

        int finalCat = Vec.argmax(finalVotes);
        return finalCat;
    }



    public static void main(String[] args) {

    }
}

