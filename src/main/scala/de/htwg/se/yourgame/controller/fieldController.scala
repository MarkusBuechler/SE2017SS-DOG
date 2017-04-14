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
  */
class fieldController {

  var fieldList = new ListBuffer[Field]

  val fieldForInit = Field(1,"String", "red", isUsed = false, null, null)
  val fieldForInit2 = Field(2,"String", "red", isUsed = false, null, null)

  def initFields = {
    val bufferedSource = io.Source.fromFile("resources/Fields.csv")
    for (line <- bufferedSource.getLines()) {
      val Array(id, property, color, isUsed, predecessor, successor) = line.split(";").map(_.trim())
      val bufferField = Field(id.toInt, property, color, isUsed.toBoolean, fieldForInit, fieldForInit2)
      fieldList += bufferField
    }

    print(fieldList.size)
    print(fieldList);
    //todo: connect fields

  }


  //sample data
  val field1 = Field(1,"String", "red", false, field3, field2)
  val field2 = Field(2,"String", "red", false, field1, field3)
  val field3 = Field(2,"String", "red", false, field2, field1)



}
