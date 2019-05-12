scalaVersion := "2.12.8"

enablePlugins(SbtProguard, AssemblyPlugin)

organization := "org.duangsuse.encoderbot"
name := "EncoderBot"
version := "0.1"

val mainClassName = s"$organization.Main"
////

val zxingVersion = "3.3.3"
libraryDependencies += "com.google.zxing" % "javase" % zxingVersion

val telegramBotVersion = "4.2"
libraryDependencies += "org.telegram" % "telegrambots" % telegramBotVersion

////
proguardOptions in Proguard ++= Seq("-dontnote", "-dontwarn")
proguardOptions in Proguard += ProguardOptions.keepMain(mainClassName)

proguardOptions in Proguard ++= Seq(s"-libraryjars ${System.getProperty("java.home")}/rt.jar")

////
lazy val fatJarSettings = Seq(
  assemblyJarName in assembly := "coderBot",
  version := s"$version-SNAPSHOT",
  mainClass in assembly := Some(mainClassName),
  test in assembly := {}
)

////
lazy val macros = (project in file("macros")).settings(
  libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value
)

lazy val root = (project in file(".")) dependsOn macros
