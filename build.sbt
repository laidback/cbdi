name := "fti-jerry-service"

version := "1.0"

maintainer := "Lukas Ciszewski <Lukas.Ciszewski@Gmail.com>"

packageSummary := "Jerry internal API Service"

//javaHome := Some(file("/path/to/java"))

//javaOptions += "-Xmx256"

//javacOptions ++= Seq("-source", "1.5", "-target", "1.5")

scalaVersion := "2.11.8"

//scalaHome := Some(file("/path/to/scala"))

scalacOptions += "-deprecation"

enablePlugins(JavaAppPackaging, GitBranchPrompt)

fork in run := true

libraryDependencies ++= Seq (
  "com.typesafe" % "config" % "1.3.1",
  "com.typesafe.akka" %% "akka-actor" % "2.4.18",
  "com.typesafe.akka" %% "akka-stream" % "2.4.18",
  "com.typesafe.akka" %% "akka-stream-testkit" % "2.4.18",
  "com.typesafe.akka" %% "akka-remote" % "2.4.18",
  "com.typesafe.akka" %% "akka-cluster" % "2.4.18",
  "com.typesafe.akka" %% "akka-cluster-tools" % "2.4.18",
  "com.typesafe.akka" %% "akka-testkit" % "2.4.18",
  "com.typesafe.akka" %% "akka-slf4j" % "2.4.18",
  "com.typesafe.akka" %% "akka-http" % "10.0.5",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.5",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.0.5",
  "com.lightbend.akka" %% "akka-management-cluster-http" % "0.3",
  "com.lihaoyi" %% "scalatags" % "0.6.5",
  "ch.qos.logback" % "logback-classic" % "1.2.1" % Runtime,
  "org.scalatest" %% "scalatest" % "3.0.3",
  "org.specs2" %% "specs2-core" % "3.8.9" % Test
)

mappings in Universal += {
  val conf = (resourceDirectory in Compile).value / "application.conf"
  conf -> "conf/application.conf"
}

mappings in Universal += {
  val log = (resourceDirectory in Compile).value / "logback.xml"
  log -> "conf/logback.xml"
}

// Native packager docker settings
packageName in Docker := name.value
version in Docker := version.value
maintainer in Docker := maintainer.value

// dockerBaseImage := "registry.gitlab01.fti.int/lcb/fti-akka-skeleton/scala-sbt-docker"

dockerRepository := Some("registry.gitlab01.fti.int/lcb")

dockerUpdateLatest := true
