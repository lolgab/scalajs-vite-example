package chart

import com.raquo.laminar.api.L._
import org.scalajs.dom

class PureSuite extends munit.FunSuite {
  test("pure tests are better run without vite to avoid bundling") {
    assertEquals(2 + 2, 4)
  }
}
