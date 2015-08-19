package com.xiaomi.nlp.classification.spamsms

import org.apache.spark.annotation.DeveloperApi
import org.apache.spark.ml.{Transformer, Estimator, PipelineStage}
import org.apache.spark.ml.param.{Params, ParamMap}
import org.apache.spark.ml.param.shared._
import org.apache.spark.mllib.classification.{NaiveBayes, NaiveBayesModel}
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.sql.{Row, Column, DataFrame}
import org.apache.spark.sql.types.StructType
import org.apache.spark.mllib.linalg.{Vector, VectorUDT}

/**
 * Created by dy on 15-8-19.
 */
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
    new NaiveBayesTransformer(model)
  }

  override def copy(extra: ParamMap): Estimator[NaiveBayesTransformer] = ???

  @DeveloperApi
  override def transformSchema(schema: StructType): StructType = ???

}


class NaiveBayesTransformer(model: NaiveBayesModel) extends Transformer {
  override def transform(dataset: DataFrame): DataFrame = ???

  override def copy(extra: ParamMap): Transformer = ???

  @DeveloperApi
  override def transformSchema(schema: StructType): StructType = ???

  override val uid: String = _
}
