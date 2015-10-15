package com.xiaomi.nlp.pattern;

import com.xiaomi.nlp.tokenizer.MyTokenizer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.mllib.clustering.DistributedLDAModel;
import org.apache.spark.mllib.clustering.LDA;
import org.apache.spark.mllib.clustering.LDAModel;
import org.apache.spark.mllib.feature.HashingTF;
import org.apache.spark.mllib.feature.Word2Vec;
import org.apache.spark.mllib.feature.Word2VecModel;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.sql.SQLContext;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Tuple2;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;


/**
 * Created by dy on 15-8-13.
 */
public class SynonymDict {
    private static Logger logger = LoggerFactory.getLogger(SynonymDict.class);
    public static void main(String[] args) throws FileNotFoundException {
        final int NUM_THREAD = 4;
        String TASK_NAME = "SynonymDict";
        final SparkConf conf = new SparkConf().setAppName("NewtechSmsPatternLearning-" + TASK_NAME);
        conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
        conf.set("spark.kryo.registrator", "com.xiaomi.nlp.classification.spamsms.CDSKryoRegister");
        conf.registerKryoClasses(new Class[]{MyTokenizer.class});
        conf.setIfMissing("spark.master", "local[" + NUM_THREAD + "]");
        final JavaSparkContext sc = new JavaSparkContext(conf);
        final SQLContext sqlContext = new SQLContext(sc);
        PrintWriter out = new PrintWriter(new FileOutputStream("data/synonym-dict.txt"));
        //PrintWriter out = new PrintWriter(System.out);

        JavaRDD<List<String>> trainData = sc
                .textFile("data/train/spamsms.txt", NUM_THREAD)
                .map(new Function<String, List<String>>() {
                    @Override
                    public List<String> call(String s) throws Exception {
                        JSONObject jsonObject = new JSONObject(s);
                        int id = -1; //jsonObject.getInt("id");
                        String body = jsonObject.getString("body");
                        boolean isSpam = jsonObject.getBoolean("spam");
                        List<MyTokenizer.WordWithDebugInfo> cands = MyTokenizer.getInstance().getTokensWithDebugInfo(body);
                        List<String> res = new ArrayList<String>();
                        for (MyTokenizer.WordWithDebugInfo cand : cands) {
                            if (cand.source.equals("ASCII")) continue;
                            res.add(cand.word);
                        }
                        return res;
                    }
                });

//        Word2Vec word2Vec = new Word2Vec();
//        Word2VecModel word2VecModel = word2Vec.fit(trainData);
//
//        Tuple2<String, Object>[] synonyms = word2VecModel.findSynonyms("话费", 40);
//        for (int i = 0; i < synonyms.length; ++i)
//            System.out.println(synonyms[i]._1() + "\t" + synonyms[i]._2());



        JavaPairRDD<Long, Vector> trainDataWithIndex = trainData
                .zipWithIndex()
                .mapToPair(new PairFunction<Tuple2<List<String>, Long>, Long, Vector>() {
                    @Override
                    public Tuple2<Long, Vector> call(Tuple2<List<String>, Long> v) throws Exception {
                        return new Tuple2<Long, Vector>(v._2(), new HashingTF().transform(v._1()));
                    }
                })
                .cache()
                ;

        Map<Integer, String> id2word = trainData
                .map(new Function<List<String>, HashMap<Integer, String>>() {
                    @Override
                    public HashMap<Integer, String> call(List<String> v1) throws Exception {
                        HashMap<Integer, String> res = new HashMap<Integer, String>();
                        HashingTF hashingTF = new HashingTF();
                        for (String word: v1) res.put(hashingTF.indexOf(word), word);
                        return res;
                    }
                })
                .reduce(new Function2<HashMap<Integer, String>, HashMap<Integer, String>, HashMap<Integer, String>>() {
                    @Override
                    public HashMap<Integer, String> call(HashMap<Integer, String> v1, HashMap<Integer, String> v2) throws Exception {
                        v1.putAll(v2);
                        return v1;
                    }
                });

        int K = 200;

        LDAModel ldaModel = new LDA().setK(K).run(trainDataWithIndex);
        Matrix topics = ldaModel.topicsMatrix();
        System.out.println(ldaModel.vocabSize());
        for (int topic = 0; topic < K; topic++) {
            out.print("Topic " + topic + ": ");
            PriorityQueue<Tuple2<String, Double>> priorityQueue = new PriorityQueue<Tuple2<String, Double>>(10, new Comparator<Tuple2<String, Double>>() {
                @Override
                public int compare(Tuple2<String, Double> o1, Tuple2<String, Double> o2) {
                    if (o2._2() == o1._2()) return 0;
                    if (o2._2() > o1._2()) return 1;
                    return -1;
                }
            });
            for (int wordId = 0; wordId < ldaModel.vocabSize(); wordId++) {
                double prob = topics.apply(wordId, topic);
                if (prob == 0) continue;
                priorityQueue.add(new Tuple2<String, Double>(id2word.get(wordId), prob));
            }
            System.out.println(priorityQueue.size());
            for (int i = 0; i < 10; ++i) {
                Tuple2<String, Double> tuple2 = priorityQueue.poll();
                out.print(tuple2._1() + "\t" + tuple2._2() + ", ");
            }
            out.println();
        }
        out.close();
    }

}
