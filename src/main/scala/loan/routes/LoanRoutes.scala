package loan.routes

import sttp.tapir.*
import cats.effect.IO
import io.circe.generic.auto.*
import sttp.tapir.generic.auto.*
import sttp.tapir.json.circe.*
import sttp.tapir.{PublicEndpoint, endpoint}
import sttp.tapir.json.circe.jsonBody
import sttp.tapir.server.ServerEndpoint
import loan.controller.LoanController
import loan.model.{CategoryMetric, Loan}

class LoanRoutes(loanController: LoanController) extends Route {

  val insertLoan: PublicEndpoint[Loan, Unit, Unit, Any] = endpoint
    .name("Insert Loan")
    .description("Endpoint responsible for the insertion of loan information")
    .post
    .in("loan")
    .in(jsonBody[Loan])

  val insertLoanEndpoint: ServerEndpoint[Any, IO] = insertLoan.serverLogicSuccess(x => loanController.insertLoan(x))

  val getAggregatedMetric: PublicEndpoint[String, Unit, Option[CategoryMetric], Any] = endpoint
    .name("Get Loan Metric")
    .description("Buckets the distribution of values for a loan field")
    .get
    .in("loan" / "metric"/ path[String])
    .out(jsonBody[Option[CategoryMetric]])

  // Probably should handle 404 on Optional Values. Skipping this for time
  val getAggregatedMetricEndpoint: ServerEndpoint[Any, IO] = getAggregatedMetric.serverLogicSuccess(x => loanController.getMetric(x))



  override def routes: List[ServerEndpoint[Any, IO]] = List(insertLoanEndpoint, getAggregatedMetricEndpoint)

}
