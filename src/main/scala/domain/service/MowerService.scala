package domain.service

import domain.model.{Area, Mower, Position}

class MowerService {
  def forward(position: Position, area: Area): Position = position.head match {
    case "E" =>
      if (position.x > 0) position.copy(x = position.x - 1) else position
    case "W" =>
      if (position.x < area.limit_x) position.copy(x = position.x + 1)
      else position
    case "N" =>
      if (position.y < area.limit_y) position.copy(y = position.y + 1)
      else position
    case "S" =>
      if (position.y > 0) position.copy(y = position.y - 1) else position
    case _ => position
  }

  def turn(position: Position, direction: String): Position = {
    val heads = List("N", "E", "S", "W")
    val indexPosition = heads.indexOf(position.head)
    direction match {
      case "G" => try {
        position.copy(head = heads.apply(indexPosition + 1))
      } catch {
        case _ : IndexOutOfBoundsException => position.copy(head = "N")
      }
      case "D" => try {
        position.copy(head = heads.apply(indexPosition - 1))
      } catch {
        case _ : IndexOutOfBoundsException => position.copy(head = "W")
      }
      case _   => position
    }
  }

  def action(mower: Mower, area: Area, command: String): Mower = command match {
    case "A" => mower.copy(position = this.forward(mower.position, area))
    case _   => mower.copy(position = this.turn(mower.position, command))
  }
}
