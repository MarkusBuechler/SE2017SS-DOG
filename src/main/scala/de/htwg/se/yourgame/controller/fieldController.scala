package de.htwg.se.yourgame.controller

import de.htwg.se.yourgame.model.Field

import scala.collection.mutable.ListBuffer

/**
  * Created by margogo on 14.04.17.
  *
  * field controller:
  * todo:
  * - init field
  * - prepare for player
  * - tests
  * do not allow double init
  * 
  */
class fieldController {

  var fieldList = new ListBuffer[Field]

  val fieldForInit = Field(1,"String", "red", isUsed = false, Array(100), Array(100))
  val fieldForInit2 = Field(2,"String", "red", isUsed = false, Array(100), Array(100))

  def initFields = {
    fieldList.clear
    val bufferedSource = io.Source.fromFile("resources/Fields.csv")
    for (line <- bufferedSource.getLines()) {
      var Array(id, property, color, isUsed, predecessorIds, successorIds) = line.split(";").map(_.trim())

      var intArraypredecessorId = predecessorIds.map(_.toInt)
      var intArraySucessorId = successorIds.map(_.toInt)

      val bufferField = Field(id.toInt, property, color, isUsed.toBoolean, intArraypredecessorId.toArray, intArraySucessorId.toArray)
      fieldList += bufferField
    }

    print("Fieldlist: " + fieldList.size + "\n");
    print(fieldList)
  }

}
