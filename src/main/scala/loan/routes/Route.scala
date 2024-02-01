package loan.routes

import cats.effect.IO
import sttp.tapir.server.ServerEndpoint

trait Route {

  def routes: List[ServerEndpoint[Any, IO]]
}
