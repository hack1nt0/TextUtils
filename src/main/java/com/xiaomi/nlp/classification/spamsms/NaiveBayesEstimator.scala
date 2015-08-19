package com.xiaomi.nlp.classification.spamsms

import org.apache.spark.annotation.DeveloperApi
import org.apache.spark.ml.{Transformer, Estimator, PipelineStage}
import org.apache.spark.ml.param.ParamMap
import org.apache.spark.mllib.classification.NaiveBayesModel
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.types.StructType

/**
 * Created by dy on 15-8-19.
 */
class NaiveBayesEstimator extends Estimator[NaiveBayesTransformer] {
  override def fit(dataset: DataFrame): NaiveBayesTransformer = ???

  override def copy(extra: ParamMap): Estimator[NaiveBayesTransformer] = ???

  @DeveloperApi
  override def transformSchema(schema: StructType): StructType = ???

  override val uid: String = _
}


class NaiveBayesTransformer(model: NaiveBayesModel) extends Transformer {
  override def transform(dataset: DataFrame): DataFrame = ???

  override def copy(extra: ParamMap): Transformer = ???

  @DeveloperApi
  override def transformSchema(schema: StructType): StructType = ???

  override val uid: String = _
}
