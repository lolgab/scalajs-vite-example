package chart

import scala.scalajs.js
import scala.scalajs.js.annotation._

import com.raquo.laminar.api.L._
import org.scalajs.dom
import typings.chartJs.mod._

object Main {
  val app = div(
    canvasTag(onMountCallback { nodeCtx =>
      // Example taken from
      // https://www.chartjs.org/docs/latest/getting-started/usage.html#creating-a-chart
      val ctx = nodeCtx.thisNode.ref
        .asInstanceOf[dom.html.Canvas]
        .getContext("2d")
        .asInstanceOf[dom.CanvasRenderingContext2D]
      val chartConfiguration = ChartConfiguration()
        .setType(ChartType.bar)
        .setData(
          ChartData()
            .setLabels(
              js.Array("Red", "Blue", "Yellow", "Green", "Purple", "Orange")
            )
            .setDatasets(
              js.Array(
                ChartDataSets()
                  .setLabel("# of Votes")
                  .setData(js.Array(10, 19, 3, 5, 2, 3))
                  .setBackgroundColor(
                    js.Array(
                      "rgba(255, 99, 132, 0.2)",
                      "rgba(54, 162, 235, 0.2)",
                      "rgba(255, 206, 86, 0.2)",
                      "rgba(75, 192, 192, 0.2)",
                      "rgba(153, 102, 255, 0.2)",
                      "rgba(255, 159, 64, 0.2)"
                    )
                  )
                  .setBorderColor(
                    js.Array(
                      "rgba(255, 99, 132, 1)",
                      "rgba(54, 162, 235, 1)",
                      "rgba(255, 206, 86, 1)",
                      "rgba(75, 192, 192, 1)",
                      "rgba(153, 102, 255, 1)",
                      "rgba(255, 159, 64, 1)"
                    )
                  )
                  .setBorderWidth(1)
              )
            )
        )
        .setOptions(
          ChartOptions().setScales(
            ChartScales().setYAxes(
              js.Array(CommonAxe().setTicks(TickOptions().setBeginAtZero(true)))
            )
          )
        )

      Chart.apply.newInstance2(ctx, chartConfiguration)
    })
  )

  @JSExportTopLevel("main")
  def main(): Unit = {
    render(dom.document.getElementById("app"), app)
  }
}
