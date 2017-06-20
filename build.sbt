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
    "org.scalamock" %% "scalamock-scalatest-support" % scalaMockV % "test",
    "org.scala-lang" % "scala-swing" % "2.11.0-M7",
    "org.apache.logging.log4j" %  "log4j-api" % "2.8.2",
    "org.apache.logging.log4j" %  "log4j-core" % "2.8.2"
  )
}

libraryDependencies += "junit" % "junit" % "4.8" % "test"

libraryDependencies += "net.codingwell" %% "scala-guice" % "4.1.0"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.0-RC2"

// Fork a new JVM for 'run' and 'test:run', to avoid JavaFX double initialization problems
fork := true
coverageEnabled := true

