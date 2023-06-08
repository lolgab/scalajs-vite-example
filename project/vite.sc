import mill._
import mill.util.Jvm
import mill.scalajslib._
import mill.scalajslib.api._

trait TestModule extends TestScalaJSModule {
  def jsEnvConfig = JsEnvConfig.JsDom()
  override def fastLinkJSTest = T {
    val dest = T.dest
    val report = super.fastLinkJSTest()
    Jvm.runSubprocess(
      commandArgs = Seq(
        "npm",
        "run",
        "build",
        "--",
        "--mode",
        s"test:${report.dest.path}",
        "--outDir",
        dest.toString
      ),
      envArgs = Map(),
      workingDir = T.workspace
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
