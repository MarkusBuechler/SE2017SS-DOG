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

  val fieldForInit = Field(1,"String", "red", isUsed = false, null, null)
  val fieldForInit2 = Field(2,"String", "red", isUsed = false, null, null)

  def initFields = {
    fieldList.clear
    val bufferedSource = io.Source.fromFile("resources/Fields.csv")
    for (line <- bufferedSource.getLines()) {
      val Array(id, property, color, isUsed, predecessor, successor) = line.split(";").map(_.trim())
      val bufferField = Field(id.toInt, property, color, isUsed.toBoolean, fieldForInit, fieldForInit2)
      fieldList += bufferField
    }

    print("Fieldlist: " + fieldList.size + "\n");
  }

}
