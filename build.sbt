val tapirVersion = "1.9.7"

lazy val rootProject = (project in file(".")).settings(
  Seq(
    name := "loan",
    version := "0.1.0-SNAPSHOT",
    organization := "loan",
    scalaVersion := "3.3.1",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % tapirVersion,
      "org.http4s" %% "http4s-ember-server" % "0.23.25",
      "com.softwaremill.sttp.tapir" %% "tapir-prometheus-metrics" % tapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-swagger-ui-bundle" % tapirVersion,
      "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % tapirVersion,
      "ch.qos.logback" % "logback-classic" % "1.4.14",
      "com.softwaremill.sttp.tapir" %% "tapir-sttp-stub-server" % tapirVersion % Test,
      "org.scalatest" %% "scalatest" % "3.2.17" % Test,
      "com.softwaremill.sttp.client3" %% "circe" % "3.9.2" % Test
    )
  )
)
