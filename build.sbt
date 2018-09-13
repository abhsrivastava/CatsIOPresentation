lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.6",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "CatsIOEx1",
    resolvers += Resolver.sonatypeRepo("snapshots"),
    scalacOptions ++= Seq("-Ypartial-unification"),
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % "1.0.0",
      // doobie
      "org.tpolecat" %% "doobie-core" % "0.5.3",
      "org.tpolecat" %% "doobie-h2" % "0.5.3",
      // http4s
      "org.http4s" %% "http4s-dsl" % "0.19.0-SNAPSHOT",
      "org.http4s" %% "http4s-blaze-server" % "0.19.0-SNAPSHOT",
      "org.http4s" %% "http4s-blaze-client" % "0.19.0-SNAPSHOT" 
    )
  )
