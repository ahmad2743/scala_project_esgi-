package domain.model

import play.api.libs.json.Json

case class Mower(limit_x: Int, limit_y: Int, position: Position) {
  object Mower{
    implicit val mowImplicitWrites = Json.writes[Position]
  }
  def getPosition(): Position = {
    this.position
  }

}
