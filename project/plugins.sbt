resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"
resolvers += "Scoverate bintray" at "https://dl.bintray.com/sksamuel/sbt-plugins/"

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.2.0-M8")
addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "0.8.5")
addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.5.0")
