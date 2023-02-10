package domain.service
import domain.model.{Area, Mower, Position}
import scala.io.Source

case class ParserService(file: String) {

  val lines = Source.fromFile(file).getLines().toList
  val area = Area(lines(0).split(" ")(0).toInt, lines(0).split(" ")(1).toInt)

  val mowerList = for {(line, num) <- lines.zipWithIndex if num != 0 && num % 2 != 0 } yield
    Mower(area.limit_x, area.limit_y, Position(line.split(" ")(0).toInt, line.split(" ")(1).toInt, line.split(" ")(2).charAt(0).toString))
  val commandeList = for {(line, num) <- lines.zipWithIndex if num != 0 && num % 2 == 0 } yield
    line.toCharArray().map(_.toString)
}
