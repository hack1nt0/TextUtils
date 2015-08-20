package com.xiaomi.nlp.classification.spamsms

import org.apache.spark.annotation.DeveloperApi
import org.apache.spark.ml.{Model, Transformer, Estimator, PipelineStage}
import org.apache.spark.ml.param.{Params, ParamMap}
import org.apache.spark.mllib.classification.{NaiveBayes, NaiveBayesModel}
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, Column, DataFrame}
import org.apache.spark.sql.types.StructType
import org.apache.spark.mllib.linalg.{Vector, VectorUDT}
import org.apache.spark.sql.functions._

/**
 * Created by dy on 15-8-19.
 */

private
final class NaiveBayesEstimator(override val uid: String) extends Estimator[NaiveBayesTransformer] with Params {

  def this() = this("NaiveBayesEstimator_" + System.currentTimeMillis())
  var inputCols = Array("")
  var outputCol = ""
  def setInputCols(values: Array[String]): this.type = {
    inputCols = values
    this
  }

  def setOutputCol(value: String): this.type = {
    outputCol = value
    this
  }

  val naiveBayes: NaiveBayes = new NaiveBayes().setLambda(1)
  override def fit(dataset: DataFrame): NaiveBayesTransformer = {
    val trainData = dataset.select(inputCols(0), inputCols(1))
      .map {case Row(label: Double, tdIdf: Vector) => new LabeledPoint(label, tdIdf)}
    val model = NaiveBayes.train(trainData)
    new NaiveBayesTransformer(uid, model).setParent(this)
  }

  override def copy(extra: ParamMap): Estimator[NaiveBayesTransformer] = null

  @DeveloperApi
  override def transformSchema(schema: StructType): StructType = schema

}


class NaiveBayesTransformer(override val uid: String, model: NaiveBayesModel) extends Model[NaiveBayesTransformer] {

  override def transform(dataset: DataFrame): DataFrame = {
    val parent = this.parent.asInstanceOf[NaiveBayesEstimator]
    val sqlfunc = udf((tf_idf: Vector) => model.predict(tf_idf)) //todo
    dataset.withColumn(parent.outputCol, sqlfunc(dataset.col(parent.inputCols(1))))
  }


  @DeveloperApi
  override def transformSchema(schema: StructType): StructType = schema

  override def copy(extra: ParamMap): NaiveBayesTransformer = null
}
