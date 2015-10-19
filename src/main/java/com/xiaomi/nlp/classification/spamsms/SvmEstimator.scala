package com.xiaomi.nlp.classification.spamsms

import org.apache.spark.annotation.DeveloperApi
import org.apache.spark.ml.{Model, Estimator}
import org.apache.spark.ml.param.{ParamMap, Params}
import org.apache.spark.mllib.classification.{SVMModel, SVMWithSGD, NaiveBayesModel, NaiveBayes}
import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{Row, DataFrame}

/**
 * Created by dy on 15-10-19.
 */
final class SvmEstimator(override val uid: String) extends Estimator[SvmTransformer] with Params {

  def this() = this("SvmEstimator_" + System.currentTimeMillis())
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

  def getOutputCol: String = {
    outputCol
  }

  val naiveBayes: SVMWithSGD = new SVMWithSGD()
  override def fit(dataset: DataFrame): SvmTransformer = {
    val trainData = dataset.select(inputCols(0), inputCols(1))
      .map {case Row(label: Double, tdIdf: Vector) => new LabeledPoint(label, tdIdf)}
    val model = SVMWithSGD.train(trainData, 100);
    new SvmTransformer(uid, model).setParent(this)
  }

  override def copy(extra: ParamMap): Estimator[SvmTransformer] = null

  @DeveloperApi
  override def transformSchema(schema: StructType): StructType = schema

}


class SvmTransformer(override val uid: String, model: SVMModel) extends Model[SvmTransformer] {

  override def transform(dataset: DataFrame): DataFrame = {
    val parent = this.parent.asInstanceOf[SvmEstimator]
    val sqlfunc = udf((tf_idf: Vector) => model.predict(tf_idf)) //todo
    dataset.withColumn(parent.outputCol, sqlfunc(dataset.col(parent.inputCols(1))))
  }


  @DeveloperApi
  override def transformSchema(schema: StructType): StructType = schema

  override def copy(extra: ParamMap): SvmTransformer = null
}
