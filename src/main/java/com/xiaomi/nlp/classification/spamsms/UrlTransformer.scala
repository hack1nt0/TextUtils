package com.xiaomi.nlp.classification.spamsms

import java.util.regex.Pattern

import org.apache.spark.annotation.DeveloperApi
import org.apache.spark.ml.Transformer
import org.apache.spark.ml.param.ParamMap
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.functions._
/**
 * Created by dy on 15-8-27.
 */
class UrlTransformer(override val uid: String) extends Transformer {

  def this() = this("CommonCharacterTransformer" + System.currentTimeMillis())

  var inputCol = ""
  var outputCol = ""
  var emailRegex= "([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)";

  var REGEX_URL: String = "((https?|ftp|file)://)?(?<![@|[A-Za-z0-9_]])([[A-Za-z0-9_]-_]+[.])+([a-zA-Z]+)" + "(:[1-9]\\d*)?([/][[A-Za-z0-9_]+&#%?=.~_|!]*)*"
  var REGEX_IP_URL: String = "(((http(s?)|ftp|file):)?//)?((25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3})" + "(:[1-9]\\d*)?([/][[A-Za-z0-9_]+&#%?=.~_|!]*)*"
  var urlRegex = "(" + REGEX_URL + ")|(" + REGEX_IP_URL + ")"

  def setUrlPattern(value: String) = {
    urlRegex = value
  }

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
      val sqlfunc = udf((text: String) => {
        var tmp = text.replaceAll(emailRegex, " [EMAIL] ")
        tmp.replaceAll(urlRegex, " [URL] ")

      })
      dataset.withColumn(outputCol, sqlfunc(dataset.col(inputCol)))
  }

  override def copy(extra: ParamMap): Transformer = null

  @DeveloperApi
  override def transformSchema(schema: StructType): StructType = schema
}
