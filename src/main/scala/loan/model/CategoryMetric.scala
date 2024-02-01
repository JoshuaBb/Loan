package loan.model


case class CategoryMetric(distribution: Map[String, Int]) {
  def add(category: String): CategoryMetric = CategoryMetric(distribution + (category -> (distribution.getOrElse(category, 0) + 1)))
}