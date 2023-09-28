import mill._
import mill.scalalib._
import mill.scalajslib._

trait JS extends ScalaJSModule {
  def scalaVersion = "3.3.1"
  def scalaJSVersion = "1.14.0"

  trait Tests extends ScalaJSTests with TestModule.Munit {
    override def ivyDeps = super.ivyDeps() ++ Agg(
      ivy"org.scalameta::munit::1.0.0-M10"
    )
  }
}
