import mill.scalalib._
import mill.scalajslib._

trait JS extends ScalaJSModule {
  def scalaVersion = "3.2.2"
  def scalaJSVersion = "1.13.0"

  trait Tests extends super.Tests with TestModule.Munit {
    override def ivyDeps = super.ivyDeps() ++ Agg(
      ivy"org.scalameta::munit::1.0.0-M7"
    )
  }
}
