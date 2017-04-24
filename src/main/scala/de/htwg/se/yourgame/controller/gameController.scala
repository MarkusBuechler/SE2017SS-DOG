package de.htwg.se.yourgame.controller

import com.google.inject.{Guice, Inject}
import de.htwg.se.yourgame.DependencyModule
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

  //compile time error , try cake pattern
  def applyFigToField = {
        for (figure <- playerController.figureListBuffer) {
          val bufferField = fieldController.fieldList.apply(figure.position).copy(isUsed = true)
          fieldController.fieldList.update(figure.position, bufferField)
        }
  }

}
