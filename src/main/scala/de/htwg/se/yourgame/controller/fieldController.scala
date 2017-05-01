package de.htwg.se.yourgame.controller

import com.google.inject.Inject
import de.htwg.se.yourgame.model.{Card, Field, Figure, Player}

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
class fieldController @Inject() (playerController : playerController, cardController : cardController) {

  var fieldList = new ListBuffer[Field]

  def initFields(): Unit = {
    fieldList.clear
    val bufferedSource = io.Source.fromFile("resources/Fields.csv")
    for (line <- bufferedSource.getLines()) {
      val Array(id, property, color, figure, predecessorIds, successorIds) = line.split(";").map(_.trim())

      val intArraypredecessorId = predecessorIds.split(",").map(_.toInt)
      val intArraySucessorId = successorIds.split(",").map(_.toInt)
      val tempFigure = Figure(playerController.currentPlayer, 100, "EmptyRole", "EmptyProp", 0)

      val bufferField = Field(id.toInt, property, color, tempFigure, intArraypredecessorId, intArraySucessorId)
      fieldList += bufferField
    }
  }

  def printFields(): Unit = {
    var string = "CurrentField:"
    for (x <-  0 until fieldList.size-1) {
      if (x % 16 == 0) {
        string += "\n"
      }
      string += fieldList.apply(x).id + ": "
      val figInField = if (fieldList.apply(x).figure.playerFigNumber != 100) ", Figur " + fieldList.apply(x).figure.playerFigNumber else ""
      val playerInField = if(fieldList.apply(x).figure.player.name != null)  "[" + fieldList.apply(x).figure.player.name + figInField + "] "  else "[ ]"
      string += playerInField

      string += ", "
    }
    print(string + "\n")
  }

  /**
    * check vllt später einbauen
    * @param cardFromDeckNumber
    * @return
    */
  def movePosition(cardFromDeckNumber: Int, figureNumber: Int): ListBuffer[Field] = {
    val currentPlayer = playerController.currentPlayer
    val oldPosition = playerController.figureListBuffer.apply(figureNumber).position
//    fieldList.update(position, ) update old field

//    val oldPosition = 1
//    val oldCardId = cardController.cardDecks.apply(playerController.currentPlayer.playerId).cards.apply(cardFromDeckNumber).id
//    val cardValue = cardController.cardDecks.apply(player.playerId).cards.apply(cardFromDeckNumber).value
//
//    // eventuell fehler bei lücken der ids
//    val newBufferField = fieldList.apply(oldPosition).copy(isUsed = true, id = cardValue + oldCardId)
    val oldBufferField = fieldList.apply(oldPosition).copy()
//
    fieldList.updated(oldPosition, oldBufferField)
//    fieldList.updated(oldPosition + cardValue, newBufferField)
  }

}
