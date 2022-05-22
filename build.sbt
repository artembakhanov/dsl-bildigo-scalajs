enablePlugins(ScalaJSPlugin)

name := "DSL Bildigo"
scalaVersion := "3.1.2" // or any other Scala version >= 2.11.12

// This is an application with a main method
scalaJSUseMainModuleInitializer := true

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.1.0"