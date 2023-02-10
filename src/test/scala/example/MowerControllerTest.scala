package example
import domain.controller.MowerController
import domain.service.{MowerService, ParserService}
import org.scalatest.funsuite.AnyFunSuite


class MowerControllerTest extends AnyFunSuite {
  test("mower controller test"){
    val mowerService = new MowerService
    val parser = ParserService("input.txt")
    val controller = MowerController(mowerService, parser)
    println(controller.runAllMowers())
    assert(1 === 1)
  }
  test("output test") {
    //val mowerService = new MowerService
    //val parser = ParserService("input.txt")
    //val controller = MowerController(mowerService, parser)
    //val serializer = new MySerializer[Output]()
    //val result = serializer.write(controller.getOutput())
    //print(result)
    assert(1 === 1)
  }
}
