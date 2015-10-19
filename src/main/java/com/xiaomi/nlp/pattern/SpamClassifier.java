package com.xiaomi.nlp.pattern;

import com.xiaomi.nlp.classification.spamsms.*;
import com.xiaomi.nlp.tokenizer.MyTokenizer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.feature.*;
import org.apache.spark.mllib.evaluation.MulticlassMetrics;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Tuple2;

import java.io.*;

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
        conf.registerKryoClasses(new Class[]{MyTokenizer.class});
        conf.setIfMissing("spark.master", "local[" + NUM_THREAD + "]");
        final JavaSparkContext sc = new JavaSparkContext(conf);
        final SQLContext sqlContext = new SQLContext(sc);
        JavaRDD<Document>[] splits = sc
                .textFile("data/train/spamsms.txt", NUM_THREAD)
                .map(new Function<String, Document>() {
                    @Override
                    public Document call(String s) throws Exception {
                            JSONObject jsonObject = new JSONObject(s);
                            int id = -1; //jsonObject.getInt("id");
                            String body = jsonObject.getString("body");
                            boolean isSpam = jsonObject.getBoolean("spam");
                            return new LabeledDocument(id, body, isSpam ? 1.0 : 0.0);
                        }
                    })
                .zipWithIndex()
                .map(new Function<Tuple2<Document,Long>, Document>() {
                    @Override
                    public Document call(Tuple2<Document, Long> v1) throws Exception {
                        Document res = v1._1();
                        res.setId(v1._2());
                        return res;
                    }
                })
                .randomSplit(new double[]{0.8, 0.2}, 17);


        DataFrame trainData = sqlContext.createDataFrame(splits[0], LabeledDocument.class);
        //ML pipeline
        UrlTransformer urlTransformer = new UrlTransformer().setInputCol("text").setOutputCol("text");
        CommonCharacterTransformer ccratio = new CommonCharacterTransformer().setDict().setInputCol("text").setOutputCol("comm_char_ratio");
        Tokenizer tokenizer = new ChiTokenizer(MyTokenizer.getInstance()).setInputCol("text").setOutputCol("tokens");
        NGram twoGramTransformer = new NGram().setN(2).setInputCol(tokenizer.getOutputCol()).setOutputCol("2gram");
        NGram threeGramTransformer = new NGram().setN(3).setInputCol(tokenizer.getOutputCol()).setOutputCol("3gram");

        HashingTF hashingTF1Gram= new HashingTF().setInputCol(tokenizer.getOutputCol()).setOutputCol("1-tf");
        HashingTF hashingTF2Gram= new HashingTF().setInputCol(twoGramTransformer.getOutputCol()).setOutputCol("2-tf");
        HashingTF hashingTF3Gram= new HashingTF().setInputCol(threeGramTransformer.getOutputCol()).setOutputCol("3-tf");

        VectorAssembler assembler = new VectorAssembler().setInputCols(new String[]{hashingTF1Gram.getOutputCol(), hashingTF2Gram.getOutputCol(), hashingTF3Gram.getOutputCol()})
                .setOutputCol("tf");

        IDF idf = new IDF().setInputCol(assembler.getOutputCol()).setOutputCol("tf_idf");
        NaiveBayesEstimator naiveBayesEstimator = new NaiveBayesEstimator()
                .setInputCols(new String[]{"label", assembler.getOutputCol()})
                .setOutputCol("nb-pred");
        Pipeline pipeline = new Pipeline().setStages(new PipelineStage[]{urlTransformer, ccratio, tokenizer, twoGramTransformer, threeGramTransformer, hashingTF1Gram, hashingTF2Gram, hashingTF3Gram, assembler, idf, naiveBayesEstimator});
        PipelineModel nbModel = pipeline.fit(trainData);
        trainData = nbModel.transform(trainData);
        trainData.show();

        DataFrame testData = sqlContext.createDataFrame(splits[1], LabeledDocument.class);
        DataFrame nbPred = nbModel.transform(testData);
        /*
        for (Row r: predictions0.select("id", "text", "label", finalPred).collect()) {
            System.out.println("(" + r.get(0) + ", " + r.get(1) + ") --> label=" + r.get(2)
                    + ", prediction=" + r.get(3));
        }
        */

        JavaPairRDD<Object, Object> predAndLabel0 = (nbPred
                .select(naiveBayesEstimator.getOutputCol(), "label")
                .javaRDD()
                .mapToPair(new PairFunction<Row, Object, Object>() {
                    @Override
                    public Tuple2<Object, Object> call(Row row) throws Exception {
                        return new Tuple2<Object, Object>(row.get(0), row.get(1));
                    }
                }));

        printTabular(new MulticlassMetrics(predAndLabel0.rdd()));

        SvmEstimator svmEstimator = new SvmEstimator().setInputCols(new String[]{"label", idf.getOutputCol()})
                .setOutputCol("svm-pred");
        pipeline = pipeline.setStages(new PipelineStage[]{svmEstimator});
        PipelineModel svmModel = pipeline.fit(trainData);
        DataFrame svmPred = nbModel.transform(testData);
        /*
        for (Row r: predictions0.select("id", "text", "label", finalPred).collect()) {
            System.out.println("(" + r.get(0) + ", " + r.get(1) + ") --> label=" + r.get(2)
                    + ", prediction=" + r.get(3));
        }
        */


        printTabular(new MulticlassMetrics(svmPred
                .select(naiveBayesEstimator.getOutputCol(), "label")
                .javaRDD()
                .mapToPair(new PairFunction<Row, Object, Object>() {
                    @Override
                    public Tuple2<Object, Object> call(Row row) throws Exception {
                        return new Tuple2<Object, Object>(row.get(0), row.get(1));
                    }
                })
                .rdd()
        ));

//        VectorAssembler gbtFeatAssembler = new VectorAssembler()
//                .setInputCols(new String[]{ccratio.getOutputCol(), naiveBayesEstimator.getOutputCol()})
//                .setOutputCol("gbt_feat");
//
//        GBTEstimator gbtEstimator = new GBTEstimator()
//                .setInputCols(new String[]{"label", gbtFeatAssembler.getOutputCol()})
//                .setOutputCol("gbt_pred")
//                .setNumIterations(5)
//                ;
//        HashMap<Integer, Integer> categoricalFeaturesInfo = new HashMap<Integer, Integer>();
//        categoricalFeaturesInfo.put(0, 2);
//        gbtEstimator.setCategoricalFeaturesInfo(categoricalFeaturesInfo);
//        pipeline = new Pipeline().setStages(new PipelineStage[]{gbtFeatAssembler, gbtEstimator});
//        PipelineModel gbtModel = pipeline.fit(trainData);
//        trainData = gbtModel.transform(trainData);
//        trainData.show();
//        trainData.select("text", "tokens").repartition(1).write().format("com.databricks.spark.csv").option("header", "true").save("data/ret/gbt-data.csv");
//
//        System.out.println(gbtModel.toString());
//
//        String finalPred = gbtEstimator.getOutputCol();
//
//        DataFrame predictions = gbtModel.transform(nbModel.transform(testData));
//
//        JavaPairRDD<Object, Object> predAndLabel = (predictions
//                .select(finalPred, "label")
//                .javaRDD()
//                .mapToPair(new PairFunction<Row, Object, Object>() {
//                    @Override
//                    public Tuple2<Object, Object> call(Row row) throws Exception {
//                        return new Tuple2<Object, Object>(row.get(0), row.get(1));
//                    }
//                }));
//
//        printTabular(new MulticlassMetrics(predAndLabel.rdd()));

    }

    private static void printTabular(MulticlassMetrics multiclassMetrics) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);
        out.println("===================================");
        int[] labels = new int[multiclassMetrics.labels().length];
        for (int i = 0; i < labels.length; ++i) labels[i] = (int)multiclassMetrics.labels()[i];
        //System.out.println(multiclassMetrics.confusionMatrix());
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
        try {
            PrintWriter res = new PrintWriter(new OutputStreamWriter(new FileOutputStream("data/ret/spam-classifer.txt", true)));
            res.println(stringWriter.toString());
            res.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(stringWriter.toString());
    }

}
