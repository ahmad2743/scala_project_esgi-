package domain.service
import domain.model.{MowerFinished, Output, Point, PointAndDirection}
import play.api.libs.json._
import java.io.PrintWriter


trait Serializer[A, B]{
  def apply(output: A, str: String): B = ???

  def serialize(input: A, path: String): B
}

case class MySerializer(serializer: Serializer[Output, Unit]){
  def serialize(in: Output, path: String): Unit ={
    serializer.serialize(in, path)
  }
  ()
}

object MyJsonSerializer extends Serializer[Output, Unit] {
  implicit val positionImplWriter = Json.writes[Point]
  implicit val pointAndDirectionWriter: Writes[PointAndDirection] = Json.writes[PointAndDirection]
  implicit val FinishedMower = Json.writes[MowerFinished]
  implicit val outputImplWriter = Json.writes[Output]

  override def serialize(in: Output, path: String): Unit = {
    new PrintWriter(path) {
      write(Json.toJson(in).toString());
      close()
    }
    ()
  }

  override def apply(output: Output, str: String): Unit = {
    new PrintWriter(str) {
      write(Json.toJson(output).toString());
      close()
    }
    ()
  }

  object CSVSerializer extends Serializer[Output, Unit] {
    override def serialize(input: Output, path: String): Unit = {
      new PrintWriter(path) {
        write("numéro;début_x;début_y;début_direction;fin_x;fin_y;fin_direction;instructions\n")
        for ((line, index) <- input.tondeuses.zipWithIndex) {
          val res = s"${index.toString};${line.debut.point.x.toString};${line.debut.point.y.toString};${line.debut.direction};${line.fin.point.x.toString};${line.fin.point.y.toString};${line.fin.direction};${line.instructions.mkString}\n"
          write(res);
        }
        close()
      }
      ()
    }

    override def apply(output: Output, str: String): Unit = {
      new PrintWriter(str) {
        write("numéro;début_x;début_y;début_direction;fin_x;fin_y;fin_direction;instructions\n")
        for ((line, index) <- output.tondeuses.zipWithIndex) {
          val res = s"${index.toString};${line.debut.point.x.toString};${line.debut.point.y.toString};${line.debut.direction};${line.fin.point.x.toString};${line.fin.point.y.toString};${line.fin.direction};${line.instructions.mkString}\n"
          write(res);
        }
        close()
      }
      ()
    }
  }
}