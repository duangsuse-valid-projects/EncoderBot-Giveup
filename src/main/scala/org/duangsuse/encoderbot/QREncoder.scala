package org.duangsuse.encoderbot

import com.google.zxing
import java.io.{ByteArrayInputStream, ByteArrayOutputStream, InputStream}
import java.nio.charset.StandardCharsets
import java.util

import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.{BarcodeFormat, EncodeHintType}

/**
  * Text to photo (file) byte array coder (Zxing based)
  */
object QREncoder extends Codec[InputStream] {
  final val TEXT = "Text"
  final val PNG = "PNG"
  final val PNG_DIM = 2* 16*16

  lazy val QR_WRITER = new zxing.qrcode.QRCodeWriter()
  lazy val QR_HINTS: util.Map[EncodeHintType, AnyVal] =
    collection.immutable.HashMap[EncodeHintType, AnyVal](EncodeHintType.CHARACTER_SET -> StandardCharsets.UTF_8.name()).asInstanceOf[util.Map[EncodeHintType, AnyVal]]

  override def supportedInputs: Set[QREncoder.TYPE] = Set(TEXT)
  override def supportedOutputs: Set[QREncoder.TYPE] = Set(PNG)

  override def doConvert(from: QREncoder.TYPE, to: QREncoder.TYPE)(input: Array[Byte]): InputStream = to match {
    case PNG => {
      val str = new String(input, StandardCharsets.UTF_8)

      val bmatr = QR_WRITER.encode(str, BarcodeFormat.QR_CODE, PNG_DIM, PNG_DIM, QR_HINTS)

      val png = new ByteArrayOutputStream()
      MatrixToImageWriter.writeToStream(bmatr, PNG, png)

      val result = new ByteArrayInputStream(png.toByteArray)

      png.close()

      return result
    }
  }
}
