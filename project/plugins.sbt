val proSbtVersion = "0.3.0"
addSbtPlugin("com.lightbend.sbt" % "sbt-proguard" % s"$proSbtVersion")

val sbtAssemVersion = "0.14.9"
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % sbtAssemVersion)
