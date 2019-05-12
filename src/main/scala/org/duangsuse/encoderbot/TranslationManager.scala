package org.duangsuse.encoderbot

import java.util.{Locale, ResourceBundle}

import scala.collection.mutable

/**
  * Naive translation pool
  */
object TranslationManager {
  private val baseName = "messages"
  lazy val instances: mutable.Map[Locale, Translation] = mutable.Map()

  def getForLocale(locale: Locale): Translation = {
    if (instances contains locale) { return instances(locale) }
    else {
      val instance = new Translation(ResourceBundle.getBundle(baseName, locale))
      instances(locale) = instance
      return instance
    }
  }

  def getForName(name: String): Translation = getForLocale(new Locale(name))
  def getForNameDashAsUnderscore(name: String): Translation = getForName(name.replace('-', '_'))
  def getDefault: Translation = getForLocale(Locale.ENGLISH)

  def get(lc: String): Translation = getForNameDashAsUnderscore(lc)
}
