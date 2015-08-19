package com.xiaomi.nlp.classification.spamsms

import com.xiaomi.nlp.tokenizer.MyTokenizer
import org.apache.spark.ml.feature.Tokenizer

/**
 * Created by dy on 15-8-19.
 */
class ChiTokenizer extends Tokenizer{

  override protected def createTransformFunc: String => Seq[String] = {
    text => MyTokenizer.getInstance().getTokens(text)
  }
}
