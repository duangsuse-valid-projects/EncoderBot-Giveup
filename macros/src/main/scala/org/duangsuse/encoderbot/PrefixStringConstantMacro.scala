package org.duangsuse.encoderbot

import scala.language.experimental.macros
import scala.reflect.macros.whitebox

object PrefixStringConstantMacro {
  type S = Predef.String

  def prefixImpl(p: S)(c:whitebox.Context)
                (x:c.universe.Literal):c.universe.Literal = {
    import c.universe._

    Literal(Constant(p ++x.value.value.asInstanceOf[String]))
  }

  def prefixNounImpl(c:whitebox.Context)(s:c.Tree):c.universe.Literal = prefixImpl("noun_")(c)(s.ensuring(_.isTerm).asInstanceOf[c.universe.Literal])
  def prefixVerbImpl(c:whitebox.Context)(s:c.Tree):c.universe.Literal = prefixImpl("verb_")(c)(s.ensuring(_.isTerm).asInstanceOf[c.universe.Literal])

  def n(s: S):S = macro prefixNounImpl
  def v(s: S):S = macro prefixVerbImpl
}
