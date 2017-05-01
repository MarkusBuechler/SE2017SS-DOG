package de.htwg.se.yourgame.controller

import com.google.inject.Inject
import de.htwg.se.yourgame.model.{Card, Field, Player}

import scala.collection.mutable.ListBuffer

/**
  * Created by margogo on 15.04.17.
  */
class gameController @Inject() (playerController : playerController, cardController : cardController, fieldController : fieldController) {

  var fieldList = new ListBuffer[Field]

  def initGame(): Unit = {
    fieldController.initFields()
    cardController.initCards()
    playerController.initPlayer()
    applyFigToField()
  }

  def showGameStatus(): Unit = {
    fieldController.printFields()
    cardController.printCardDecks()
    playerController.printCurrentPlayer()
  }

  def playerAction(cardFromDeckNumber: Int, figureNumber: Int): Unit = {
//    fieldController.movePosition(cardFromDeckNumber, figureNumber)
    val playedCard = cardController.cardDecks.apply(playerController.currentPlayer.playerId).cards.apply(cardFromDeckNumber - 1)
    removeCard(playedCard)
    // add removed card to playedCards
    playerController.changeCurrentPlayer()
  }

  def applyFigToField(): Unit = {
        for (figure <-  playerController.figureListBuffer) {
          val index = fieldController.fieldList.indexWhere (_.id == figure.position)
          val bufferField = fieldController.fieldList.apply(index).copy(figure = figure)
          fieldController.fieldList.update(index, bufferField)
        }
  }

  def removeCard(card: Card) = {
    val cardInSet = cardController.cardDecks.apply(playerController.currentPlayer.playerId).cards.indexWhere(_.id == card.id)
    cardController.cardDecks.apply(playerController.currentPlayer.playerId).cards.remove(cardInSet)
    cardController.playedCards += card
  }

}
