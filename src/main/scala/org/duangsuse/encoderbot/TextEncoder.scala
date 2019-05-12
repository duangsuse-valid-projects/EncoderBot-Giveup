package org.duangsuse.encoderbot

import java.util.Base64
import java.net.URLEncoder
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets

/**
  * Base64/URL Encoder
  */
object TextEncoder extends Codec[String] {
  final val BASE64 =  "Base64"
  final val URL = "URL"

  lazy val Base64Coder = Base64.getEncoder

  override def supportedInputs: Set[TextEncoder.TYPE] = Set("Text")
  override def supportedOutputs: Set[TextEncoder.TYPE] = Set(BASE64, URL)

  private def decodeByteArrayToString(in: Array[Byte]): String = StandardCharsets.UTF_8.decode(ByteBuffer.wrap(in)).toString

  override def doConvert(from: TextEncoder.TYPE, to: TextEncoder.TYPE)(input: Array[Byte]): String = to match {
    case BASE64 => Base64Coder.encodeToString(input)
    case URL => URLEncoder.encode(decodeByteArrayToString(input), StandardCharsets.UTF_8.name())
  }
}
