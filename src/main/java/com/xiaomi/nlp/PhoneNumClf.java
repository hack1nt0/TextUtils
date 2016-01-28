package com.xiaomi.nlp;

import org.json.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by dy on 16-1-20.
 */

public class PhoneNumClf {
    static class Mark {
        String imei;
        int catId;

        public Mark(String imei, int catId) {
            this.imei = imei;
            this.catId = catId;
        }

        public String getImei() {
            return imei;
        }

        public int getCatId() {
            return catId;
        }

        public static List<Mark> fromString(JSONArray imeis, JSONArray votes) throws JSONException {
            List<Mark> res = new ArrayList<Mark>();
            for (int i = 0; i < imeis.length(); ++i)
                res.add(new Mark(imeis.getString(i), votes.getInt(i)));
            return res;
        }
    }

    static class Feedback {
        String imei;
        String text;
        int originalCatId;

        public Feedback(String imei, String text, int originalCatId) {
            this.imei = imei;
            this.text = text;
            this.originalCatId = originalCatId;
        }

        public String getImei() {
            return imei;
        }

        public String getText() {
            return text;
        }

        public int getOriginalCatId() {
            return originalCatId;
        }

        public static List<Feedback> fromString(JSONObject contents) {
            List<Feedback> res = new ArrayList<Feedback>();
            JSONArray imeis = contents.names();
            for (int i = 0; i < imeis.length(); ++i) {
                String imei = imeis.getString(i);
                JSONArray text_originalCatId = contents.getJSONArray(imei);
                String text = text_originalCatId.getString(0);
                int originalCatId = text_originalCatId.getInt(1);
                res.add(new Feedback(imei, text, originalCatId));
            }
            return res;
        }
    }

    static class Record {
        int catId;
        int count;
        int provider;

        public Record(int catId, int count, int provider) {
            this.catId = catId;
            this.count = count;
            this.provider = provider;
        }

        public static List<Record> fromString(JSONArray records) {
            List<Record> res = new ArrayList<Record>();
            for (int i = 0; i < records.length(); ++i) {
                int catId = records.getInt(0);
                int count = records.getInt(1);
                int provider = records.getInt(2);
                res.add(new Record(catId, count, provider));
            }
            return res;
        }

    }

    //parameters
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


    //catId -> catTitle
    Map<Integer, String> cat2name = new HashMap<Integer, String>();
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

            in = new BufferedReader(new InputStreamReader(new FileInputStream(catTitleFile)));
            while (true) {
                String line = in.readLine();
                if (line == null) break;
                JSONObject jsonLine = new JSONObject(line);
                Integer catId = jsonLine.getInt("catId");
                String catTitle = jsonLine.getString("catTitle");
                cat2name.put(catId, catTitle);
            }
            cat2name.put(0, "未标记");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String trimQuote(String s) {
        int cntH = 0;
        while (cntH < s.length() && s.charAt(cntH) == '"') ++cntH;
        int cntT = 0;
        while (cntT < s.length() && s.charAt(s.length() - cntT - 1) == '"') ++cntT;
        if (cntH != cntT) {
            System.out.println(s + " may not be quoted valided.");
        }
        return s.substring(cntH, s.length() - cntT);
    }

    private float getCredit(String imei) {
        if (!userCredit.containsKey(imei)) imei = "Unknown";
        return userCredit.get(imei);
    }

    private float[] getFeedbackVotes(List<Feedback> feedbacks) { // TODO: 16/1/28
        if (feedbackDicPats == null) {
            feedbackDicPats = new ArrayList<Pattern>();
            int n = feedbackDic.size();
            feedbackLowerBound = Integer.parseInt(feedbackDic.get(n - 1));
            String notPrefix = feedbackDic.get(n - 3);
            String notOriginal = feedbackDic.get(n - 2);
            for (int i = 0; i < n - 3; ++i) feedbackDicPats.add(Pattern.compile(notPrefix + feedbackDic.get(i)));
            for (int i = 0; i < n - 3; ++i) feedbackDicPats.add(Pattern.compile(feedbackDic.get(i)));
            feedbackDicPats.add(Pattern.compile(notOriginal));
        }

        int numCat = cat2name.size();
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
        float[] res = new float[cat2name.size()];
        Arrays.fill(res, 1.0f / res.length);
        return res;
    }

    public int classify(String no, List<Mark> marks, List<Feedback> feedbacks, List<Record> records) {
        int numCat = cat2name.size();

        float[] priorVotes = getPriorVotes(no);

        float[] userCreditVotes = new float[numCat];
        if (marks != null) {
            for (Mark mark : marks) {
                int catId = mark.getCatId();
                float credit = getCredit(mark.imei);
                userCreditVotes[catId] += credit;
            }
        }

        float[] feedbackVotes = new float[numCat];
        if (feedbacks != null) {
            float[] votes = getFeedbackVotes(feedbacks);
            feedbackVotes = Vec.add(feedbackVotes, votes);
        }

        if (w1 == -1) w1 = feedbacks == null ? 1 : feedbacks.size();
        if (w2 == -1) w2 = marks == null ? 1 : marks.size();

        float[] finalVotes = Vec.mul(Vec.add(Vec.mul(w1, userCreditVotes), Vec.mul(w2, feedbackVotes)), priorVotes);

        int finalCat = Vec.argmax(finalVotes);
        return finalCat;
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

    static String javaTestDataFile = "/Users/DY/Desktop/I2NLP/Assignment0/data/java_test_data.json";
    static String userCreditFile = "/Users/DY/Desktop/I2NLP/Assignment0/data/anti_mark_user_credit_model.csv";
    static String priorFile = "/Users/DY/Desktop/I2NLP/Assignment0/data/prior_model.csv";
    static String feedbackDicFile = "/Users/DY/Desktop/I2NLP/Assignment0/data/anti_feedback_model.csv";
    static String catTitleFile = "/Users/DY/Desktop/I2NLP/Assignment0/data/anti_catTitle.json";

    public static void main(String[] args) throws IOException, JSONException {

//        JSONObject obj = new JSONObject("{\n" +
//                "   \"pageInfo\": {\n" +
//                "         \"pageName\": \"abc\",\n" +
//                "         \"pagePic\": \"http://example.com/content.jpg\"\n" +
//                "    },\n" +
//                "    \"posts\": [\n" +
//                "         {\n" +
//                "              \"post_id\": \"123456789012_123456789012\",\n" +
//                "              \"actor_id\": \"1234567890\",\n" +
//                "              \"picOfPersonWhoPosted\": \"http://example.com/photo.jpg\",\n" +
//                "              \"nameOfPersonWhoPosted\": \"Jane Doe\",\n" +
//                "              \"message\": \"Sounds cool. Can't wait to see it!\",\n" +
//                "              \"likesCount\": \"2\",\n" +
//                "              \"comments\": [],\n" +
//                "              \"timeOfPost\": \"1234567890\"\n" +
//                "         }\n" +
//                "    ]\n" +
//                "}");
//        String pageName = obj.getJSONObject("pageInfo").getString("pageName");
//
//        JSONArray arr = obj.getJSONArray("posts");
//        for (int i = 0; i < arr.length(); i++)
//        {
//            JSONObject tmp = arr.getJSONObject(i);
//            String post_id = arr.getJSONObject(i).getString("post_id");
//            System.out.println(post_id);
//        }


        long t1 = System.currentTimeMillis();
        PhoneNumClf clf = new PhoneNumClf(userCreditFile, priorFile, feedbackDicFile);

        long t2 = System.currentTimeMillis();
        System.out.println("Initial step costed " +  ((t2 - t1) / 1000.0) + " s");

        //test
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(javaTestDataFile)));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("/Users/DY/Desktop/I2NLP/Assignment0/data/java_test_data_output.json")));
        while (true) {
            String line = in.readLine();
            if (line == null) break;
            JSONObject jsonLine = new JSONObject(line);

            String no = jsonLine.getString("markedNo");
            List<Record> records = Record.fromString(jsonLine.getJSONArray("catId_count_provider"));


            List<Feedback> feedbacks = jsonLine.has("contents") ? Feedback.fromString(jsonLine.getJSONObject("contents")) : null;

            List<Mark> marks = jsonLine.has("imeis") ? Mark.fromString(jsonLine.getJSONArray("imeis"), jsonLine.getJSONArray("votes")) : null;

            int finalCatId = clf.classify(no, marks, feedbacks, records);

            jsonLine.put("java_final_catId", finalCatId);

            out.println(jsonLine.toString());
        }
        out.close();
    }
}

