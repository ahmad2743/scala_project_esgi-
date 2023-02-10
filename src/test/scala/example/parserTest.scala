package example
import org.scalatest.funsuite.AnyFunSuite
import domain.service.ParserService


class parserTest extends AnyFunSuite {

  test("parser test output"){
    val parserService = ParserService("input.txt")
    println(parserService.area)
    println(parserService.mowerList)
    println(parserService.commandeList)
    assert(1 === 1)
  }

  test("string to list test") {
    val mylist = "ABCDERF".toCharArray().map(_.toString)
    for (e <- mylist){
      println(e)
    }
    assert(1 === 1)
  }
}
