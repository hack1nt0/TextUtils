package com.xiaomi.nlp.classification.spamsms

import java.io.{DataInputStream, DataOutputStream}

import com.esotericsoftware.kryo.io.{Input, Output}
import com.esotericsoftware.kryo.{Serializer, Kryo}
import org.apache.hadoop.io.{Writable, LongWritable}
import org.apache.spark.serializer.KryoRegistrator
/**
 * Created by yunsima on 15-3-9.
 */
class CDSKryoRegister extends KryoRegistrator{
  override def registerClasses(kryo: Kryo) {
    kryo.register(classOf[LongWritable], new KryoWritableSerializer[LongWritable])
    //kryo.register(classOf[StructuredData], new KryoWritableSerializer[StructuredData])
  }
}

/** A Kryo serializer for Hadoop writables. copied from Shark sources */
class KryoWritableSerializer[T <: Writable] extends Serializer[T] {
  override def write(kryo: Kryo, output: Output, writable: T) {
    val ouputStream = new DataOutputStream(output)
    writable.write(ouputStream)
  }

  override def read(kryo: Kryo, input: Input, cls: java.lang.Class[T]): T = {
    val writable = cls.newInstance()
    val inputStream = new DataInputStream(input)
    writable.readFields(inputStream)
    writable
  }
}
