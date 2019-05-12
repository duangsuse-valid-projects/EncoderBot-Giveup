package org.duangsuse.encoderbot

import PrefixStringConstantMacro.{n, v}

/**
  * A place to save key references to messages translation resource bundle
  */
object TranslationConstants {
  val HELLO = "hello"
  val BTN_TEXT_HELLO = "hello_btn"
  val POPUP_HELLO = "hello_reaction"
  val NEW_HELLO = "hello_revision"

  val HELP = "help"

  val TEMPLATE_USAGE_MARKUP = "template_codeHowTo_marks"

  type S = String

  object Nouns {
    val BASE64:S = n("b6")
    val URL:S = n("url")
    val QR:S = n("qr")
  }

  object Verbs {
    val ENCODE:S = v("encode")
    val PERFORM:S = v("perform")
  }
}
