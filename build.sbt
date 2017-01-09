name := "email-classification"

version := "0.1"

organization := "com.linagora"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
"org.apache.spark" %% "spark-core" % "2.0.0",
"org.apache.spark" %% "spark-sql" % "2.0.0"
)
