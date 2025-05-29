//| mill-version: 1.0.0-RC1
//| mvnDeps:
//|   - com.github.lolgab::mill-scalablytyped_mill1.0.0-RC1:0.2.0-M1

package build

import mill._
import mill.define.Task
import mill.scalalib._
import mill.scalajslib._
import mill.scalajslib.api._

import com.github.lolgab.mill.scalablytyped.ScalablyTyped

object `package` extends BaseJS {
  object scalablytyped extends BaseJS with ScalablyTyped

  def moduleKind = ModuleKind.ESModule
  def moduleSplitStyle = ModuleSplitStyle.SmallModulesFor(List("chart"))
  def moduleDeps = Seq(scalablytyped)
  def mvnDeps = Seq(
    mvn"com.raquo::laminar::17.2.0"
  )
  def publicDev = Task {
    public(fastLinkJS)()
  }
  def publicProd = Task {
    public(fullLinkJS)()
  }
  object `test-pure` extends BaseTests
  object `test-dom` extends BaseTests with ViteTestModule

  def public(jsTask: Task[Report]): Task[Map[String, os.Path]] = Task.Anon {
    Map("@public" -> jsTask().dest.path)
  }
}

trait BaseJS extends ScalaJSModule {
  def scalaVersion = "3.7.0"
  def scalaJSVersion = "1.19.0"

  trait BaseTests extends ScalaJSTests with TestModule.Munit {
    override def mvnDeps = super.mvnDeps() ++ Seq(
      mvn"org.scalameta::munit::1.0.0"
    )
  }
}

trait ViteTestModule extends TestScalaJSModule {
  def jsEnvConfig = JsEnvConfig.JsDom()
  override def fastLinkJSTest = Task {
    val dest = Task.dest
    val report = super.fastLinkJSTest()
    os.call(
      (
        "npm",
        "run",
        "build",
        "--",
        "--mode",
        s"test:${report.dest.path}",
        "--outDir",
        dest.toString
      )
    )
    val jsFolder = dest / "assets"
    Report(
      publicModules = os
        .list(jsFolder)
        .map(f =>
          Report.Module(
            moduleID = "main",
            jsFileName = f.last,
            sourceMapName = None,
            moduleKind = ModuleKind.NoModule
          )
        ),
      dest = PathRef(jsFolder)
    )
  }
}
