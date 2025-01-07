package build

import $packages._

import build.project.scalablytyped

import $file.project.base
import $file.project.vite

import mill._
import mill.define.Task
import mill.scalalib._
import mill.scalajslib._
import mill.scalajslib.api._

object `package` extends RootModule with base.JS {
  def moduleKind = ModuleKind.ESModule
  def moduleSplitStyle = ModuleSplitStyle.SmallModulesFor(List("chart"))
  def moduleDeps = Seq(scalablytyped)
  def ivyDeps = Agg(
    ivy"com.raquo::laminar::17.2.0"
  )
  def publicDev = T {
    public(fastLinkJS)()
  }
  def publicProd = T {
    public(fullLinkJS)()
  }
  object `test-pure` extends BaseTests
  object `test-dom` extends BaseTests with vite.TestModule

  def public(jsTask: Task[Report]): Task[Map[String, os.Path]] = Task.Anon {
    Map("@public" -> jsTask().dest.path)
  }
}
