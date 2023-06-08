import $file.project.base
import $file.project.scalablytyped
import $file.project.vite

import mill._
import mill.define.Task
import mill.scalalib._
import mill.scalajslib._
import mill.scalajslib.api._

object root extends RootModule with base.JS {
  def moduleKind = ModuleKind.ESModule
  def moduleSplitStyle = ModuleSplitStyle.SmallModulesFor(List("chart"))
  def moduleDeps = Seq(scalablytyped.module)
  def ivyDeps = Agg(
    ivy"com.raquo::laminar::15.0.1"
  )
  def publicDev = T {
    public(fastLinkJS)()
  }
  def publicProd = T {
    public(fullLinkJS)()
  }
  object `test-pure` extends Tests
  object `test-dom` extends Tests with vite.TestModule
}

def public(jsTask: Task[Report]): Task[Map[String, os.Path]] = T.task {
  Map("@public" -> jsTask().dest.path)
}
