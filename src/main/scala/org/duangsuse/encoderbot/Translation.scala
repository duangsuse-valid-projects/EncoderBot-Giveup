package org.duangsuse.encoderbot

import scala.language.implicitConversions

import java.util.ResourceBundle

/**
  * Application translations for EncoderBot
  *
  * @param res Got resource bundle with specified language
  */
final class Translation(val res: ResourceBundle) {
  type S = String
  val TC: TranslationConstants.type = TranslationConstants

  lazy val hello:S = $(TC.HELLO)
  lazy val revisedHello:S = $(TC.NEW_HELLO)
  lazy val btnHello:S = $(TC.BTN_TEXT_HELLO)
  lazy val popupHello:S = $(TC.POPUP_HELLO)

  private lazy val nBase64:S = $(TC.Nouns.BASE64)
  private lazy val nQuickRead:S = $(TC.Nouns.QR)
  private lazy val nURL:S = $(TC.Nouns.URL)

  private lazy val vEncode:S = $(TC.Verbs.ENCODE)
  private lazy val vPerform:S = $(TC.Verbs.PERFORM)

  lazy val help:S = $(TC.HELP).format(nQuickRead, vEncode, nBase64, vEncode, vPerform, nURL, vEncode)
  lazy val guideTemplate:S = $(TC.TEMPLATE_USAGE_MARKUP)

  def mkGuide(codingName: S, command: S, example: S, note: S): String = guideTemplate.format(codingName, command, example, note)
  def mkGuide(guide: Guide): S = mkGuide(guide.name, guide.cmd, guide.sample, guide.notes)

  private val $ = $_(res) _
  private def $_(r: ResourceBundle)(id: S) = implicitly(id) res r

  /**
    * Fetch resource in ResourceBundle with string
    * @param s self string
    */
  implicit class StringResFetchExtension(val s: S) {
    def res: ResourceBundle =>S = (r: ResourceBundle) => r.getString(s)
  }

  override def equals(other: Any): Boolean = other match {
    case that: Translation =>
      res == that.res
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(res)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  override def toString = s"Translation(${res.getLocale})"
}
