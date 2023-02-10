package domain.model

case class Area(limit_x: Int, limit_y: Int) {
  override def toString: String = "limit_x = "+ limit_x.toString + "   limit_y = " + limit_y.toString
}
