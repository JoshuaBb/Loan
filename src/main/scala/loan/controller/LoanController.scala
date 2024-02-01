package loan.controller

import loan.model.{CategoryMetric, Loan}
import cats.effect.IO
import loan.store.LoanStore

class LoanController(loanStore: LoanStore){
  def insertLoan(loan: Loan): IO[Unit] = {
    for {
      _ <- IO.println("Inserting loan level data")
      _ <- loanStore.insertLoan(loan)
    } yield ()

  }
  def getMetric(metric: String): IO[Option[CategoryMetric]] = {
    for {
      _ <- IO.println(s"Getting metric data for $metric")
      categoryMetric <- loanStore.getMetric(metric)
    } yield categoryMetric
  }
}