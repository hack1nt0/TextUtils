package com.xiaomi.nlp.classification.spamsms;

import com.xiaomi.nlp.tokenizer.MyTokenizer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.mllib.classification.NaiveBayes;
import org.apache.spark.mllib.classification.NaiveBayesModel;
import org.apache.spark.mllib.evaluation.MulticlassMetrics;
import org.apache.spark.mllib.feature.HashingTF;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Tuple2;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * Created by dy on 15-8-13.
 */
public class SpamClassifier {
    private static Logger logger = LoggerFactory.getLogger(SpamClassifier.class);
    public static void main(String[] args) {
        final int NUM_THREAD = 4;
        String TASK_NAME = "spam-classifier";
        final SparkConf conf = new SparkConf().setAppName("NewtechSmsPatternLearning-" + TASK_NAME);
        conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
        conf.set("spark.kryo.registrator", "com.xiaomi.nlp.classification.spamsms.CDSKryoRegister");
        conf.registerKryoClasses(new Class[]{});
        conf.setIfMissing("spark.master", "local[" + NUM_THREAD + "]");
        final JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<LabeledPoint> sms = (sc
                .textFile("data/train/spamsms.txt")
                .repartition(NUM_THREAD)
                .map(new Function<String, LabeledPoint>() {
                    @Override
                    public LabeledPoint call(String v1) throws Exception {
                        JSONObject jsonObject = new JSONObject(v1);
                        String body = jsonObject.getString("body");
                        boolean isSpam = jsonObject.getBoolean("spam");
                        MyTokenizer myTokenizer = MyTokenizer.getInstance();
                        String[] tokens = myTokenizer.getTokens(body);

                        HashingTF hashingTF = new HashingTF();
                        Vector tf = hashingTF.transform(Arrays.asList(tokens));
                        LabeledPoint labeledPoint = new LabeledPoint(isSpam ? 1.0 : 0.0, tf);
                        return labeledPoint;
                    }
                }));

        JavaRDD<LabeledPoint>[] splits = sms.randomSplit(new double[]{0.8, 0.2}, 11L);
        JavaRDD<LabeledPoint> trainData = splits[0];
        JavaRDD<LabeledPoint> testData = splits[1];
        final NaiveBayesModel model = NaiveBayes.train(trainData.rdd(), 1.0);

        JavaPairRDD<Object, Object> predAndLabel = testData.mapToPair(new PairFunction<LabeledPoint, Object, Object>() {
            @Override
            public Tuple2<Object, Object> call(LabeledPoint labeledPoint) throws Exception {
                return new Tuple2<Object, Object>(model.predict(labeledPoint.features()), labeledPoint.label());
            }
        });

        MulticlassMetrics multiclassMetrics = new MulticlassMetrics(predAndLabel.rdd());

        printTabular(multiclassMetrics);

        /*
        Map<Double, int[]> tabular = (predAndLabel
                .combineByKey(new Function<Double, int[]>() {
                    @Override
                    public int[] call(Double v1) throws Exception {
                        int[] res = new int[NUM_CLASS];
                        res[v1.intValue()] = 1;
                        return res;
                    }
                }, new Function2<int[], Double, int[]>() {
                    @Override
                    public int[] call(int[] v1, Double v2) throws Exception {
                        v1[v2.intValue()] += 1;
                        return v1;
                    }
                }, new Function2<int[], int[], int[]>() {
                    @Override
                    public int[] call(int[] v1, int[] v2) throws Exception {
                        for (int i = 0; i < v1.length; ++i) v1[i] += v2[i];
                        return v1;
                    }
                })
                .collectAsMap());

        int[][] cnts = new int[tabular.size()][];
        for (int i = 0; i < cnts.length; ++i) cnts[i] = tabular.get(i);
        System.out.print("\t\t");
        for (int i = 0; i < cnts.length; ++i) System.out.print(i + "\t");
        System.out.println("Recall\t" + "Precision");
        int matrixTot = 0;
        int corTot = 0;
        for (int i = 0; i < cnts.length; ++i) {
            System.out.print(i + "\t");
            int rowTot = 0;
            for (int j = 0; j < cnts[i].length; ++j) {
                rowTot += cnts[i][j];
                System.out.print(cnts[i][j] + "\t");
            }
            matrixTot += rowTot;
            corTot += cnts[i][i];
            System.out.println(100.0 * cnts[i][i] / rowTot + "%" + "\t" + 100.0 * corTot / matrixTot + "%");
        }
        */

    }

    private static void printTabular(MulticlassMetrics multiclassMetrics) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);
        out.println("===================================");
        int[] labels = new int[multiclassMetrics.labels().length];
        for (int i = 0; i < labels.length; ++i) labels[i] = (int)multiclassMetrics.labels()[i];
        System.out.println(multiclassMetrics.confusionMatrix());
        out.print("\t");
        for (int i = 0; i < labels.length; ++i) out.print(labels[i] + "\t");
        out.println("Recall\tPrecision\tfMeasure");
        for (int i = 0; i < labels.length; ++i) {
            out.print(labels[i] + "\t");
            for (int j = 0; j < labels.length; ++j) {
                out.print((int)multiclassMetrics.confusionMatrix().apply(i, j) + "\t");
            }
            out.println(100.0 * multiclassMetrics.recall(i) + "%\t" + 100.0 * multiclassMetrics.precision(i) + "%\t" + 100.0 * multiclassMetrics.fMeasure(i) + "%" );
        }
        out.println("Recall:" + 100.0 * multiclassMetrics.recall() + "%\tPrecision:" + 100.0 * multiclassMetrics.precision() + "%\tfMeasure:" + 100.0 * multiclassMetrics.fMeasure() + "%\tWRecall:" + 100.0 * multiclassMetrics.weightedRecall() + "%\tWPrecision:" + 100.0 * multiclassMetrics.weightedPrecision() + "%\tWFMeasure:" + 100.0 * multiclassMetrics.weightedFMeasure() + "%");
        out.println("===================================");
        System.out.println(stringWriter.toString());
    }
}
