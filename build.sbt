name := "htwg-scala-seed"
organization := "de.htwg.se"
version := "0.0.1"
scalaVersion := "2.11.8"
scalacOptions := Seq("-unchecked", "-feature", "-deprecation", "-encoding", "utf8")
//sbtPlugin := true

resolvers += Resolver.jcenterRepo

libraryDependencies ++= {
  val scalaTestV = "3.0.0-M15"
  val scalaMockV = "3.2.2"
  Seq(
    "org.scalatest" %% "scalatest" % scalaTestV % "test",
    "org.scalamock" %% "scalamock-scalatest-support" % scalaMockV % "test"
  )
}

libraryDependencies += "junit" % "junit" % "4.8" % "test"

libraryDependencies += "net.codingwell" %% "scala-guice" % "4.1.0"

libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.92-R10"

libraryDependencies += "org.scalafx" %% "scalafxml-core-sfx8" % "0.3"

