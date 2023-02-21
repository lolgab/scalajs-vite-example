import $file.project.base
import $file.project.scalablytyped
import mill._
import mill.define.Task
import mill.scalalib._
import mill.scalajslib._
import mill.scalajslib.api._

object chart extends base.JS {
  def moduleKind = ModuleKind.ESModule
  def moduleSplitStyle = ModuleSplitStyle.SmallModulesFor(List("chart"))
  def moduleDeps = Seq(scalablytyped.module)
  def ivyDeps = Agg(
    ivy"com.raquo::laminar::15.0.0-M6"
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
