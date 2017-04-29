package de.htwg.se.yourgame.controller

import com.google.inject.{Guice, Inject}
import de.htwg.se.yourgame.model.{Card, Field, Player}

import scala.collection.mutable.ListBuffer

/**
  * Created by margogo on 15.04.17.
  */
class gameController @Inject() (playerController : playerController, cardController : cardController, fieldController : fieldController) {

  var fieldList = new ListBuffer[Field]

  def initGame() = {
    fieldController.initFields
    cardController.initCards
    playerController.initPlayer
    applyFigToField
  }

  def showGameStatus() = {
    fieldController.printFields()
    cardController.printCardDecks()
    playerController.printCurrentPlayer
  }

  def playCard(player : Player, card: Card) = {
    fieldController.movePosition(player, card.value)
  }

  def applyFigToField() = {
        for (figure <-  playerController.figureListBuffer) {
          print("Size" + playerController.figureListBuffer.size + "\n")
          print(figure.position)
          val index = fieldController.fieldList.indexWhere (_.id == figure.position)
          val bufferField = fieldController.fieldList.apply(index).copy(isUsed = true)
          fieldController.fieldList.update(index, bufferField)
        }
  }

}
