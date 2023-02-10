package domain.controller
import domain.model.{Area, Mower, MowerFinished, Output, Point, PointAndDirection}
import domain.service.{MowerService, ParserService}

case class MowerController(mowerService: MowerService, parserService: ParserService) {

  private def runMower(mower: Mower, area: Area, commands: Array[String]): Mower = {
    commands.foldLeft(mower)((accumulator, command) => this.mowerService.action(accumulator, area, command))
  }

  def runAllMowers(): List[Mower] = {
    val mowerAndCommandList = parserService.mowerList zip parserService.commandeList
    val mowerFinished = for {(mower, commands) <- mowerAndCommandList} yield runMower(mower, parserService.area, commands)
    mowerFinished
    }

    def getOutput(): Output ={
      val mowerFinished = runAllMowers()
      val mowerAndCommandList = mowerFinished zip parserService.commandeList
      val limit = Point(x = this.parserService.area.limit_x, y = this.parserService.area.limit_y)
      val mowersFinishedOutput = for {((mowerEnd, commands), index) <- mowerAndCommandList.zipWithIndex} yield {
        println(index)
        MowerFinished(
          debut = PointAndDirection(
            point = Point(
              x = this.parserService.mowerList(index).position.x,
              y = this.parserService.mowerList(index).position.y
            ),
            direction = parserService.mowerList(index).position.head
          ),
          instructions = commands,
          fin = PointAndDirection(
            point = Point(x = mowerEnd.position.x, y = mowerEnd.position.y),
            direction = mowerEnd.position.head

          )
        )
      }
      Output(limit = limit, tondeuses = mowersFinishedOutput)
    }

}
