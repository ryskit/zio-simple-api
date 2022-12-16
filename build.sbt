ThisBuild / version      := "1.0.0"
ThisBuild / scalaVersion := "3.1.3"
ThisBuild / scalacOptions := Seq(
  "-Yretain-trees"
)

val Versions = new {
  val zio = "2.0.1"
}

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .enablePlugins(DockerPlugin)
  .settings(
    name := "zio-simple-api",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio"         % Versions.zio,
      "dev.zio" %% "zio-streams" % Versions.zio,
      "dev.zio" %% "zio-json"    % "0.4.2",
      "io.d11"  %% "zhttp"       % "2.0.0-RC11"
    )
  )
  .settings(
    Docker / packageName := "XXXXXXXXXXXX.dkr.ecr.ap-northeast-1.amazonaws.com/XXXXXXXX",
    dockerExposedPorts ++= Seq(9000, 9000),
    dockerBaseImage := "openjdk:11",
    dockerBuildCommand := {
      if (sys.props("os.arch") != "amd64") {
        dockerExecCommand.value ++ Seq(
          "buildx",
          "build",
          "--platform=linux/amd64",
          "--load"
        ) ++ dockerBuildOptions.value :+ "."
      } else dockerBuildCommand.value
    }
  )
