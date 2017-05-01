package de.htwg.se.yourgame.controller

import com.google.inject.Inject
import de.htwg.se.yourgame.model.{Card, Player}

/**
  * Created by margogo on 15.04.17.
  */
class gameController @Inject() (playerController : playerController, cardController : cardController, fieldController : fieldController) {

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
    fieldController.movePosition(cardFromDeckNumber, figureNumber)
    cardController.cardDecks.apply(playerController.currentPlayer.playerId).cards.remove(cardFromDeckNumber + 1)
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

}
