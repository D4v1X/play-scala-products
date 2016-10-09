name := """play-scala-products"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)

libraryDependencies += "net.sf.barcode4j" % "barcode4j" % "2.1"

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8", // yes, this is 2 args
  "-explaintypes",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:postfixOps",
  "-unchecked",
  "-Xcheckinit",
  "-Xfuture",
  "-Xfatal-warnings",
  "-Xlint",
  "-Xverify",
  "-Yno-adapted-args",
  "-Ywarn-dead-code", // N.B. doesn't work well with the ??? hole
  "-Ywarn-numeric-widen",
  //"-Ywarn-value-discard",
  "-Ywarn-unused",
  //"-Ywarn-unused-import", // 2.11 only
  //"-Yno-predef" // no automatic import of Predef (removes irritating implicits)
  //"-Yno-imports" // no automatic imports at all; all symbols must be imported explicitly
  "-Ywarn-adapted-args",
  "-Ywarn-inaccessible",
  "-Ywarn-infer-any",
  "-Ywarn-nullary-override",
  "-Ywarn-nullary-unit"
)

resolvers ++= Seq(
  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/maven-releases/"
)