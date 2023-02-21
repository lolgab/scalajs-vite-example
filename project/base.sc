import mill.scalalib._
import mill.scalajslib._

trait JS extends ScalaJSModule {
  def scalaVersion = "3.2.2"
  def scalaJSVersion = "1.13.0"
}
