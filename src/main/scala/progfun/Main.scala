package fr.esgi.al.funprog

import domain.controller.MowerController
import domain.service.MyJsonSerializer.CSVSerializer
import domain.service.{MowerService, MyJsonSerializer, MySerializer, ParserService}

object Main extends App {
  if (args.length == 0){
    println("tondeuses lancés sans path spécifique. le résultat est dans le repertoire  par défaut")
    run(path = "")
  }
  else if (args.length == 1){
    println(s"les résultats sont dans le dossier ${args(0)}")
    run(path = args(0))
  }
  else {
    println("trop d'argument, Merci de renseigner uniquement le chemin du résultat")
  }

  def run(path: String): Unit ={
    val mowerService = new MowerService
    val parser = ParserService("input.txt")
    val controller = MowerController(mowerService, parser)
    val output = controller.getOutput()
    val csvSerializer = CSVSerializer
    val serializer = MySerializer(csvSerializer)
    val jsonSerializer = MyJsonSerializer
    val serializableJson = MySerializer(jsonSerializer)
    if(path.isEmpty){
      locally {
        val _ = serializer.serialize(output, "output.csv")
      }
      locally{
        val _ = serializableJson.serialize(output, "output.json")
      }
    }
    else {
      locally {
        val _ = serializer.serialize(output, s"${path}/output.csv")
      }
      locally {
        val _ = serializableJson.serialize(output, s"${path}/output.json")
      }
    }
  }
}


