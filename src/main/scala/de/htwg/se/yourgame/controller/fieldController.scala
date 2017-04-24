package de.htwg.se.yourgame.controller

import de.htwg.se.yourgame.model.{Field, Player}
import com.softwaremill.macwire._
import scala.collection.mutable.ListBuffer

/**
  * Created by margogo on 14.04.17.
  *
  * field controller:
  * todo:
  * - prepare for player
  * - tests
  * 
  */
class fieldController {

  lazy val fieldController = wire[fieldController]
  var fieldList = new ListBuffer[Field]

  def initFields = {
    fieldList.clear
    val bufferedSource = io.Source.fromFile("resources/Fields.csv")
    for (line <- bufferedSource.getLines()) {
      val Array(id, property, color, isUsed, predecessorIds, successorIds) = line.split(";").map(_.trim())

      val intArraypredecessorId = predecessorIds.split(",").map(_.toInt)
      val intArraySucessorId = successorIds.split(",").map(_.toInt)

      val bufferField = Field(id.toInt, property, color, isUsed.toBoolean, intArraypredecessorId, intArraySucessorId)
      fieldList += bufferField
    }
//    print("Fieldlist: " + fieldList.size + "\n");
  }

  def initFigures = {

  }

  def printFields() = {
    var string = "CurrentField:"
    for (x <- 0 to fieldList.size-1) {
      if (x % 16 == 0) {
        string += "\n"
      }
      string += fieldList.apply(x).id + ": "
      val fieldIsUsed = if (fieldList.apply(x).isUsed) "[x]" else "[]"
      string += fieldIsUsed
      string += ", "
    }
    print(string + "\n")
  }

  def movePosition(player: Player, fieldId : Int) = {
    val bufferField = fieldList.apply(fieldId).copy(isUsed = true)
    fieldList.updated(fieldId, bufferField)
  }

}
