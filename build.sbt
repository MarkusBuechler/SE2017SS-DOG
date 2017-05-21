lazy val commonSettings =
  Seq( name := "htwg-scala-seed",
    organization := "de.htwg.se",
    version := "0.0.1",
    scalaVersion := "2.11.8",
    scalacOptions := Seq("-unchecked", "-feature", "-deprecation", "-encoding", "utf8")
  )
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

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

libraryDependencies += "org.scalafx" %% "scalafxml-core-sfx8" % "0.3"

libraryDependencies += "org.scalafx" % "scalafxml-core_2.10" % "0.2.1"

lazy val core = Project("scalafxml-core-sfx8", file("core"),
  settings = commonSettings ++ Seq(
    description := "ScalaFXML core module"
  ))
  .dependsOn(coreMacros)

lazy val guiceSettings = commonSettings ++ Seq(
  description := "Guice based dependency resolver for ScalaFXML",
  libraryDependencies += "com.google.inject" % "guice" % "4.1.0"
)

lazy val guice = Project("scalafxml-guice-sfx8", file("guice"),
  settings = guiceSettings)
  .aggregate(core)
  .dependsOn(core)

lazy val coreMacros = Project("dog2-build", file("core-macros"),
  settings = commonSettings ++ Seq(
    description := "ScalaFXML macros",
    libraryDependencies += scalaVersion("org.scala-lang" % "scala-reflect" % _).value
  ))


//jfxSettings

//JFX.mainClass := Some("DogMain.scala")