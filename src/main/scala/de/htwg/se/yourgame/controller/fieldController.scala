package de.htwg.se.yourgame.controller

import de.htwg.se.yourgame.model.Field

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
    print("Fieldlist: " + fieldList.size + "\n");
  }

}