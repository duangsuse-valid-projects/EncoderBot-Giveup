package org.duangsuse.encoderbot

import org.duangsuse.encoderbot.TranslationConstants.S

class Guide(val tr: Translation, val name:S, val cmd:S, val sample:S, val notes:S = "") extends Cloneable with Serializable with Equals {
  def canEqual(other: Any): Boolean = other.isInstanceOf[Guide]

  override def equals(other: Any): Boolean = other match {
    case that: Guide =>
      (that canEqual this) &&
        tr == that.tr &&
        name == that.name &&
        cmd == that.cmd &&
        sample == that.sample &&
        notes == that.notes
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(tr, name, cmd, sample, notes)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  override def toString = s"Guide($name, $cmd)"
}
