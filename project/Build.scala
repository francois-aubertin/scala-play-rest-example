import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "scala-play-rest-example"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
        // Add your project dependencies here,
        jdbc,
        anorm,
    
        "org.mockito" % "mockito-core" % "1.9.5" % "test"
    )
  
    val main = play.Project(appName, appVersion, appDependencies).settings(
        // Add your own project settings here      
    )

}
