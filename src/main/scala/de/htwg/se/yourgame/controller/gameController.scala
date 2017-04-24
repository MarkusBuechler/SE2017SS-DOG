package de.htwg.se.yourgame.controller

import de.htwg.se.yourgame.model.{Card, Player}
import com.softwaremill.macwire._

/**
  * Created by margogo on 15.04.17.
  */
class gameController  {
  lazy val playerController = wire[playerController]
  lazy val cardController = wire[cardController]
  lazy val fieldController = wire[fieldController]


  def initGame() = {
    playerController.initPlayer
    cardController.initCards
    fieldController.initFields
  }

  def showGameStatus() = {
    fieldController.printFields()
    cardController.printCardDecks()
    playerController.printCurrentPlayer
  }

  def playCard(player : Player, card: Card) = {
    fieldController.movePosition(player, card.value)
  }

}
