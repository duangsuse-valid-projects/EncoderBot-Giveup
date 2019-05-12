package org.duangsuse.encoderbot

/**
  * Abstract coding converter (from ByteArray to ???)
  */
trait Codec[+T] { // WHY??? unnecessary covariant for parameterized type...
  type TYPE = String

  // 这里泛型整协变主要是我咸得了，懒得删... 没有太大用处，毕竟这玩意也不像容器类会有子类型问题得判断，权当支持多态一类的判断吧（自动父类转型？）...

  def supportedInputs: Set[TYPE]
  def supportedOutputs: Set[TYPE]

  def doConvert(from: TYPE, to: TYPE)(input: Array[Byte]): T
}
