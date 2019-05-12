package org.duangsuse.encoderbot

/**
  * Bot application starter
  */
object Launcher {
  val HELP_MESSAGE =
    """
      | Unexpected (%s) arguments [%s]
      | Expecting %s <botName> <botToken>
      | Example:
      |   %s @hello abc:2333333
    """.stripMargin
  val NAME: String = Launcher.getClass.getName

  def launch(args: String*): Unit = args match {
    case name :: token :: Nil => doLaunch(name, token)
    case xs => doHelp(xs)
  }

  @inline def doLaunch(name: String, tok: String) = ???
  @inline def doHelp(xs: Seq[String]) = println(HELP_MESSAGE.format(xs.length, xs.mkString(", "), NAME, NAME))
}
