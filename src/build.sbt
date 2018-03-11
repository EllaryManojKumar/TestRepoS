name := """kalyan-spark-streaming-phoenix-project"""

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "org.apache.spark"  %% "spark-core"  % "1.6.0" % "provided" excludeAll(ExclusionRule(organization = "org.eclipse.jetty"),ExclusionRule(organization = "ch.qos.logback")),
  "org.apache.spark"  %% "spark-streaming-twitter" % "1.6.0",
  "org.apache.spark"  %% "spark-streaming-kafka" % "1.6.0",
  "org.apache.spark"  %% "spark-streaming-flume" % "1.6.0",
  "org.apache.spark"  %% "spark-sql" % "1.6.0",
  "org.apache.spark"  %% "spark-mllib" % "1.6.0",
  "com.typesafe.akka" %% "akka-actor"  % "2.3.13", 
  "com.typesafe.akka" %% "akka-slf4j"  % "2.3.13"
)

dependencyOverrides ++= Set( 
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.4.4" 
)

// libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.4.4"
// libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.4.4"

libraryDependencies += "org.apache.hbase" % "hbase-common" % "1.1.2"
libraryDependencies += "org.apache.hbase" % "hbase-client" % "1.1.2"
libraryDependencies += "org.apache.hbase" % "hbase-server" % "1.1.2"

libraryDependencies += "org.apache.hadoop" % "hadoop-common" % "2.6.0"
libraryDependencies += "org.apache.hadoop" % "hadoop-client" % "2.6.0"

// phoenix integration
libraryDependencies += "org.apache.phoenix" % "phoenix-spark" % "4.7.0-HBase-1.1"
libraryDependencies += "org.apache.phoenix" % "phoenix-core" % "4.7.0-HBase-1.1"
libraryDependencies += "org.apache.phoenix" % "phoenix-server" % "4.7.0-HBase-1.1"


libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.18"
libraryDependencies += "com.typesafe.play" %% "anorm" % "2.4.0"
libraryDependencies += "org.webjars" % "bootstrap" % "3.3.4"
libraryDependencies += "com.google.code.gson" % "gson" % "2.6.2"
libraryDependencies += "net.liftweb" % "lift-json_2.11" % "2.6"
libraryDependencies += "com.google.guava" % "guava" % "16.0.1"

// cassandra integration
libraryDependencies += "com.datastax.cassandra" % "cassandra-driver-core" % "3.0.2"
libraryDependencies += "com.datastax.spark" % "spark-cassandra-connector_2.11" % "1.6.0-M2"
libraryDependencies += "com.twitter" % "jsr166e" % "1.1.0"
libraryDependencies += "org.apache.cassandra" % "cassandra-all" % "3.5"
libraryDependencies += "org.mortbay.jetty" % "servlet-api" % "3.0.20100224"


resolvers ++= Seq(
  "JBoss Repository" at "http://repository.jboss.org/nexus/content/repositories/releases/",
  "Spray Repository" at "http://repo.spray.cc/",
  "Cloudera Repository" at "https://repository.cloudera.com/artifactory/cloudera-repos/",
  "Akka Repository" at "http://repo.akka.io/releases/",
  "Twitter4J Repository" at "http://twitter4j.org/maven2/",
  "Apache HBase" at "https://repository.apache.org/content/repositories/releases",
  "Twitter Maven Repo" at "http://maven.twttr.com/",
  "scala-tools" at "https://oss.sonatype.org/content/groups/scala-tools",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Second Typesafe repo" at "http://repo.typesafe.com/typesafe/maven-releases/",
  "Mesosphere Public Repository" at "http://downloads.mesosphere.io/maven",
  "Sonatype snapshots repository" at "https://oss.sonatype.org/content/repositories/snapshots/",
  Resolver.sonatypeRepo("public")
)


// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

EclipseKeys.preTasks := Seq(compile in Compile)


