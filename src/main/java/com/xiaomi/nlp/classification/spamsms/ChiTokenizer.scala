package com.xiaomi.nlp.classification.spamsms

import com.xiaomi.nlp.tokenizer.{ITokenizer, MyTokenizer}
import org.apache.spark.ml.feature.Tokenizer

/**
 * Created by dy on 15-8-19.
 */
class ChiTokenizer(val innerTokenizer: ITokenizer) extends Tokenizer{

  override protected def createTransformFunc: String => Seq[String] = {
    //text => innerTokenizer.getTokens(text)
    text => Seq()
  }
}
