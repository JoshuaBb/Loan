package loan.store

import scala.collection.concurrent.TrieMap
import loan.model.CategoryMetric
import loan.model.Loan
import cats.effect.IO

class LoanStore {
  // It has been awhile since I introduced a mutable collection for a class.
  // A bit of hack to not set up a database. I wouldn't be if it doesn't handle concurrency well
  val threadSafeMap = new TrieMap[String, CategoryMetric]()

  private def insertCategory(key: String, value: String): IO[Option[CategoryMetric]] = {
    IO.pure(threadSafeMap.put(key, threadSafeMap.getOrElse(key, CategoryMetric(Map.empty[String, Int])).add(value)))
  }

  def getMetric(key: String): IO[Option[CategoryMetric]] = {
    IO.pure[Option[CategoryMetric]](threadSafeMap.get(key))
  }

  def insertLoan(loan: Loan): IO[Unit] = {
    val month = if (loan.month < 10) s"0${loan.month}" else loan.month
    val yearMonth = s"$month-${loan.year}"
    for {
      _ <- insertCategory("date", yearMonth)
      _ <- insertCategory("state", loan.state)
      _ <- insertCategory("grade", loan.grade)
    } yield ()
  }
}
