ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "web3j-example"
  )

libraryDependencies += "org.web3j" % "core" % "4.9.1"
