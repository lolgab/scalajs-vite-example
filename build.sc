import $file.project.versions
import $file.project.scalablytyped
import mill._
import mill.define.Task
import mill.scalalib._
import mill.scalajslib._
import mill.scalajslib.api._

object chart extends ScalaJSModule {
  def scalaVersion = "3.1.2"
  def scalaJSVersion = "1.10.0"
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

case class Alias(find: String, replacement: os.Path)
object Alias {
  import upickle.default._
  implicit val rw: ReadWriter[Alias] = macroRW
}

private def public(jsTask: Task[Report]): Task[Seq[Alias]] =
  T.task {
    val jsDir = jsTask().dest.path
    Seq(Alias("@public", jsDir))
  }
