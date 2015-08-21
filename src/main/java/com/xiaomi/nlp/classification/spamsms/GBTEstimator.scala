package com.xiaomi.nlp.classification.spamsms

import java.util

import org.apache.spark.annotation.DeveloperApi
import org.apache.spark.ml.param.ParamMap
import org.apache.spark.ml.{Model, Transformer, Estimator, PipelineStage}
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.tree.GradientBoostedTrees
import org.apache.spark.mllib.tree.configuration.BoostingStrategy
import org.apache.spark.mllib.tree.model.GradientBoostedTreesModel
import org.apache.spark.sql.{Row, DataFrame}
import org.apache.spark.sql.types.StructType
import org.apache.spark.mllib.linalg.{Vector, VectorUDT}
import org.apache.spark.sql.functions._
import scala.collection.JavaConverters._
import scala.collection.immutable.HashMap

/**
 * Created by dy on 15-8-21.
 */
class GBTEstimator(override val uid: String) extends Estimator[GBTransformer] {
  def this() = this("NaiveBayesEstimator_" + System.currentTimeMillis())
  var inputCols = Array("")
  var outputCol = ""
  var numIterations = 3
  var defaultParams = "Classification"
  var numClasses = 2
  var maxDepth = 5
  var maxBins = 32
  var minInstancesPerNode = 1
  var minInfoGain = 0.0
  //  Empty categoricalFeaturesInfo indicates all features are continuous.
  var categoricalFeaturesInfo = new java.util.HashMap[java.lang.Integer, java.lang.Integer]()
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

  def setNumIterations(value: Int): this.type = {
    numIterations = value
    this
  }

  def setDefaultParams(value: String): this.type = {
    defaultParams = value
    this
  }

  def setNumClasses(value: Int) : this.type = {
    numClasses = value
    this
  }

  def setMaxDepth(value: Int): this.type = {
    maxDepth = value
    this
  }

  def setMaxBins(value: Int): this.type = {
    maxBins = value
    this
  }

  def setMinInstancesPerNode(value: Int): this.type = {
    minInstancesPerNode = value
    this
  }

  def setMinInfoGain(value: Double): this.type = {
    minInfoGain = value
    this
  }

  def setCategoricalFeaturesInfo(value: java.util.HashMap[java.lang.Integer, java.lang.Integer]): this.type = {
    categoricalFeaturesInfo = value
    this
  }


  override def fit(dataset: DataFrame): GBTransformer = {
    val boostingStrategy = BoostingStrategy.defaultParams("Classification")
    boostingStrategy.setNumIterations(numIterations) // Note: Use more iterations in practice.
    boostingStrategy.getTreeStrategy.setNumClasses(numClasses)
    boostingStrategy.getTreeStrategy.setMaxDepth(maxDepth)
    boostingStrategy.getTreeStrategy.setMaxBins(maxBins)
    boostingStrategy.getTreeStrategy.setMinInstancesPerNode(minInstancesPerNode)
    boostingStrategy.getTreeStrategy.setMinInfoGain(minInfoGain)
    boostingStrategy.getTreeStrategy.setCategoricalFeaturesInfo(categoricalFeaturesInfo)

    val trainData = dataset.select(inputCols(0), inputCols(1))
      .map {case Row(label: Double, feat: Vector) => new LabeledPoint(label, feat)}
    val gradientBoostedTreesModel = GradientBoostedTrees.train(trainData, boostingStrategy)
    new GBTransformer(uid, gradientBoostedTreesModel).setParent(this)
  }

  override def copy(extra: ParamMap): Estimator[GBTransformer] = null

  @DeveloperApi
  override def transformSchema(schema: StructType): StructType = schema
}

class GBTransformer(override val uid: String, model: GradientBoostedTreesModel) extends Model[GBTransformer] {
  override def copy(extra: ParamMap): GBTransformer = null

  override def transform(dataset: DataFrame): DataFrame = {
    val parent = this.parent.asInstanceOf[GBTEstimator]
    val sqlfunc = udf((feat: Vector) => model.predict(feat))
    dataset.withColumn(parent.outputCol, sqlfunc(dataset.col(parent.inputCols(1))))
  }

  @DeveloperApi
  override def transformSchema(schema: StructType): StructType = schema
}
