package chart

import scala.scalajs.js
import scala.scalajs.js.annotation._

import com.raquo.laminar.api.L._
import org.scalajs.dom
import typings.chartJs.distTypesIndexMod.{
  ChartComponent,
  ChartConfiguration,
  ChartData,
  ChartDataset,
  ChartOptions,
  ChartType
}
import typings.chartJs.chartJsStrings._
import typings.chartJs.mod._

object Main {
  def registerComponents(): Unit = Chart.register(
    js.Array(
      BarController.^,
      BarElement.^,
      CategoryScale.^,
      LinearScale.^
    ).map(_.asInstanceOf[ChartComponent])
  )

  val app = div(
    canvasTag(onMountCallback { nodeCtx =>
      // Example taken from
      // https://www.chartjs.org/docs/latest/getting-started/usage.html#creating-a-chart
      val ctx = nodeCtx.thisNode.ref
        .asInstanceOf[dom.html.Canvas]
        .getContext("2d")
        .asInstanceOf[dom.CanvasRenderingContext2D]
      val chartConfiguration = ChartConfiguration(
        `type` = bar,
        data = ChartData(
          datasets = js.Array(
            new js.Object {
              val label = "# of Votes"
              val data = js.Array(10, 19, 3, 5, 2, 3)
              val backgroundColor = js.Array(
                "rgba(255, 99, 132, 0.2)",
                "rgba(54, 162, 235, 0.2)",
                "rgba(255, 206, 86, 0.2)",
                "rgba(75, 192, 192, 0.2)",
                "rgba(153, 102, 255, 0.2)",
                "rgba(255, 159, 64, 0.2)"
              )
              val borderColor = js.Array(
                "rgba(255, 99, 132, 1)",
                "rgba(54, 162, 235, 1)",
                "rgba(255, 206, 86, 1)",
                "rgba(75, 192, 192, 1)",
                "rgba(153, 102, 255, 1)",
                "rgba(255, 159, 64, 1)"
              )
              val borderWidth = 1
            }.asInstanceOf[ChartDataset[bar, _]]
          )
        ).setLabels(
          js.Array("Red", "Blue", "Yellow", "Green", "Purple", "Orange")
        )
      )

      Chart(ctx, chartConfiguration)
    })
  )

  @JSExportTopLevel("main")
  def main(): Unit = {
    registerComponents()
    renderOnDomContentLoaded(dom.document.getElementById("app"), app)
  }
}
