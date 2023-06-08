package chart

import com.raquo.laminar.api.L._
import org.scalajs.dom

class ChartSuite extends munit.FunSuite {
  test("can register components") {
    Main.registerComponents()
  }
}
