package example
import domain.model.{Area, Mower, Position}
import domain.service.MowerService
import org.scalatest.funsuite.AnyFunSuite

class MowerTest extends AnyFunSuite {

  /**
   * Running the following test case:
   * 5 5
   * 1 2 N
   * GAGAGAGAA
   * 3 3 E
   * AADAADADDA
   */
  test("mower test") {
    val mowerService1 = new MowerService
    val area = Area(5, 5)

    val mower = Mower(5, 5, Position(1, 2, "N"))
    val commandsList: Seq[String] = Seq("G", "A", "G", "A", "G", "A", "G", "A", "A")
    val movedMower = commandsList.foldLeft(mower)((accumulator, command) => mowerService1.action(accumulator, area, command))
    print(movedMower)
    assert(movedMower.position.x === 1)
    assert(movedMower.position.y === 3)
    assert(movedMower.position.head === "N")
  }

  test("mower test 2") {
    val mowerService2 = new MowerService
    val area2 = Area(5, 5)
    val mower2 = Mower(5, 5, Position(3, 3, "E"))
    val commandsList2: Seq[String] = Seq("A", "A", "D", "A", "A", "D", "A", "D", "D", "A")
    val movedMower2 = commandsList2.foldLeft(mower2)((accumulator, command) => mowerService2.action(accumulator, area2, command))
    print(movedMower2)
    assert(movedMower2.position.x === 1)
    assert(movedMower2.position.y === 5)
    assert(movedMower2.position.head === 'E')
  }
}

