lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.6",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "CatsIOEx1",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % "1.0.0",
      "org.tpolecat" %% "doobie-core" % "0.5.3",
      "org.tpolecat" %% "doobie-h2" % "0.5.3"
    )
  )
