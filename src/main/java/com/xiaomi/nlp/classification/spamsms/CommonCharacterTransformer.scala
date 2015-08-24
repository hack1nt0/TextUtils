package com.xiaomi.nlp.classification.spamsms

import java.io.PrintWriter

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

  def this() = this("CommonCharacterTransformer" + System.currentTimeMillis())

  var dict = Set[Char]()

  def setDict() = {
    val inputStream = CommonCharacterTransformer.getClass.getClassLoader.getResourceAsStream("com/xiaomi/nlp/classification/spamsms/common-chinese-char-3500.txt")
    val in = Source.fromInputStream(inputStream)
    in.getLines().foreach(line => dict + line(0))
    this
  }

  def setDict(dictPath: String) = {
    val inputStream = CommonCharacterTransformer.getClass.getClassLoader.getResourceAsStream(dictPath)
    val in = Source.fromInputStream(inputStream)
    in.getLines().foreach(line => dict + line(0))
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
    val sqlfunc = udf((text: String) => if (text.length == 0) 0 else (text.count(p => dict.contains(p) || p < 128) / (0.0 + text.length))) //todo
    dataset.withColumn(outputCol, sqlfunc(dataset.col(inputCol)))
  }

  override def copy(extra: ParamMap): Transformer = null

  @DeveloperApi
  override def transformSchema(schema: StructType): StructType = schema

}

object CommonCharacterTransformer extends App {
  val in = Source.fromFile("data/train/common-chinese-char-8300.txt")
  val out = new PrintWriter("data/ret/common-chinese-char-3500.txt")
  in.getLines()
    .filter(p => p.trim().split(" ").length == 2)
    .map(p => p.trim().split(" "))
    .filter({case Array(id: String, ch: String) => id.toInt <= 3500})
    .foreach(p => out.println(p(1)(0)))
  "，。！？；：［］（）、｛｝／《》@＃$＊＋－～".foreach(p => out.println(p))
  out.close()
}
