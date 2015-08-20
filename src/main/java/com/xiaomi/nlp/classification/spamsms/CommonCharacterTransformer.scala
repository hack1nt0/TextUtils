package com.xiaomi.nlp.classification.spamsms

import org.apache.spark.annotation.DeveloperApi
import org.apache.spark.ml.Transformer
import org.apache.spark.ml.param.ParamMap
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.functions._

import scala.io.Source

/**
 * Created by DY on 15/8/20.
 */
class CommonCharacterTransformer(override val uid: String) extends Transformer {

  var dict = Set[Char]()

  def setDict(dictPath: String) = {
    val in = Source.fromFile(dictPath)
    in.getLines().foreach(line => dict + line.charAt(0))
    this
  }

  var inputCol = ""
  var outputCol = ""
  def setInputCol(value: String) = {
    inputCol = value
    this
  }

  def setOutputCol(value: String) = {
    outputCol = value
    this
  }

  def getInputCol = {
    inputCol
  }

  def getOutputCol = {
    outputCol
  }

  override def transform(dataset: DataFrame): DataFrame = {
    val sqlfunc = udf((text: String) => text.count(p => dict.contains(p)) / (0.0 + text.length)) //todo
    dataset.withColumn(outputCol, sqlfunc(dataset.col(inputCol)))
  }

  override def copy(extra: ParamMap): Transformer = null

  @DeveloperApi
  override def transformSchema(schema: StructType): StructType = schema

}
