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

    static String userCreditFile = "";

    static class UserCreditRow {
        float credit;
        int numVotes;

        UserCreditRow(String[] rowString){

        }
    }

    static HashMap<String, UserCreditRow> userCredit = new HashMap<String, UserCreditRow>();
    static {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(userCreditFile)));
            while (true) {
                String line = in.readLine();
                if (line == null) break;
                String[] tmp = line.split("\\t");
                String imei = tmp[0];
                userCredit.put(imei, new UserCreditRow(tmp));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private float getCredit(String no) {

    }

    public float[] addVec(float[] A, float[] B) {
        if (A.length != B.length) throw new IllegalArgumentException("Given Vecs are not the same Dimension.");
        float[] res = new float[A.length];
        for (int i = 0; i < A.length; ++i) res[i] = A[i] + B[i];
        return res;
    }

    public float dot(float[] A, float[] B) {
        if (A.length != B.length) throw new IllegalArgumentException("Given Vecs are not the same Dimension.");
        float res = 0;
        for (int i = 0; i < A.length; ++i) res += A[i] * B[i];
        return res;
    }

    public float[] mulVec(float a, float[] B) {
        float[] res = new float[B.length];
        for (int i = 0; i < B.length; ++i) res[i] = a * B[i];
        return res;
    }

    public float[] mulVec(float[] A, float[] B) {
        float[] res = new float[A.length];
        for (int i = 0; i < B.length; ++i) res[i] = A[i] * B[i];
        return res;
    }

    public int argmax(float[] A) {
        if (A.length == 0) return -1;
        int res = 0;
        for (int i = 0; i < A.length; ++i)
            if (A[i] > A[res]) res = i;
        return res;
    }

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
            feedbackVotes = addVec(feedbackVotes, votes);
        }

        if (w1 == -1) w1 = feedbacks.size();
        if (w2 == -1) w2 = marks.size();

        float[] finalVotes = mulVec(addVec(mulVec(w2, userCreditVotes), mulVec(w1, feedbackVotes)), priorVotes);

        int finalCat = argmax(finalVotes);
        return finalCat;
    }

    public static void main(String[] args) {
    }
}

