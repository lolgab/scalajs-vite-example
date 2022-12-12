import $file.project.versions
import $file.project.scalablytyped
import mill._
import mill.define.Task
import mill.scalalib._
import mill.scalajslib._
import mill.scalajslib.api._

object chart extends ScalaJSModule {
  def scalaVersion = versions.scala
  def scalaJSVersion = versions.scalajs
  def moduleKind = ModuleKind.ESModule
  def moduleSplitStyle = ModuleSplitStyle.SmallModulesFor(List("chart"))
  def moduleDeps = Seq(scalablytyped.module)
  def ivyDeps = Agg(
    ivy"com.raquo::laminar::0.14.2"
  )
  def publicDev = T {
    public(fastLinkJS)()
  }
  def publicProd = T {
    public(fullLinkJS)()
  }
}

def public(jsTask: Task[Report]): Task[Map[String, os.Path]] = T.task {
  Map("@public" -> jsTask().dest.path)
}
