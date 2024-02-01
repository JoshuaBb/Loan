package loan

import sttp.tapir.*
import cats.effect.IO
import io.circe.generic.auto.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*
import sttp.tapir.server.ServerEndpoint
import sttp.tapir.server.metrics.prometheus.PrometheusMetrics
import sttp.tapir.swagger.bundle.SwaggerInterpreter
import loan.routes.Route

case class Endpoints(apiRoutes: List[Route]){


  val apiEndpoints: List[ServerEndpoint[Any, IO]] = apiRoutes.flatMap(_.routes)

  val docEndpoints: List[ServerEndpoint[Any, IO]] = SwaggerInterpreter()
    .fromServerEndpoints[IO](apiEndpoints, "loan", "1.0.0")

  val prometheusMetrics: PrometheusMetrics[IO] = PrometheusMetrics.default[IO]()
  val metricsEndpoint: ServerEndpoint[Any, IO] = prometheusMetrics.metricsEndpoint

  val all: List[ServerEndpoint[Any, IO]] = apiEndpoints ++ docEndpoints ++ List(metricsEndpoint)
}