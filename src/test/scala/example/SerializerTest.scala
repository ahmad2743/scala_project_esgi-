package example
import domain.model.{MowerFinished, Output, Point, PointAndDirection}
import domain.service.{CSVSerializer, MyJsonSerializer}
import org.scalatest.funsuite.AnyFunSuite



class SerializerTest extends AnyFunSuite {

  test("serializer test 1") {
    val posa = Point(1, 2)
    val p1 = PointAndDirection(posa, "F")
    val posa2 = Point(2, 2)
    val p2 = PointAndDirection(posa2, "G")
    val fn = MowerFinished(p1, Array("A", "F", "T"), p2)
    val posa3 = Point(1, 2)
    val p3 = PointAndDirection(posa3, "F")
    val posa4 = Point(2, 10)
    val p4 = PointAndDirection(posa4, "G")
    val fn2 = MowerFinished(p3, Array("A", "F", "T", "Z"), p4)
    val output = Output(limit = Point(5, 5), List(fn, fn2))
    val s = MyJsonSerializer.serialize(output, "test.json")
    print(s)
    assert(1 === 1)
  }

  test("serializer test 2") {
    val posa = Point(1, 2)
    val p1 = PointAndDirection(posa, "F")
    val posa2 = Point(2, 2)
    val p2 = PointAndDirection(posa2, "G")
    val fn = MowerFinished(p1, Array("A", "F", "T"), p2)
    val posa3 = Point(1, 2)
    val p3 = PointAndDirection(posa3, "F")
    val posa4 = Point(2, 10)
    val p4 = PointAndDirection(posa4, "G")
    val fn2 = MowerFinished(p3, Array("A", "F", "T", "Z"), p4)
    val output = Output(limit = Point(5, 5), List(fn, fn2))
    val _ = CSVSerializer.serialize(output, "test.csv")
    assert(1 === 1)
  }
}
