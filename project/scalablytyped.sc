import mill._, mill.scalalib._, mill.scalajslib._
import $ivy.`com.github.lolgab::mill-scalablytyped::0.1.0`
import com.github.lolgab.mill.scalablytyped._
import $file.versions

object module extends ScalaJSModule with ScalablyTyped  {
  def scalaVersion = versions.scala
  def scalaJSVersion = versions.scalajs
}
