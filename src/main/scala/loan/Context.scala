package loan

import loan.controller.LoanController
import loan.routes.LoanRoutes
import loan.store.LoanStore
import loan.controller.LoanController

object Context {

  val loanStore = new LoanStore()
  val loanController = new LoanController(loanStore)

  val endpoints = Endpoints(List(new LoanRoutes(loanController)))

}
