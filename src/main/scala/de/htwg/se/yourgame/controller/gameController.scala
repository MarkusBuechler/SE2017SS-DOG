package de.htwg.se.yourgame.controller

/**
  * Created by margogo on 15.04.17.
  */
class gameController  {
  lazy val playerController = new playerController
  lazy val cardController = new cardController
  lazy val fieldController = new fieldController

  def initGame() = {
    playerController.initPlayer
    cardController.initCards
    fieldController.initFields
  }

  def showGameStatus() = {
    fieldController.printFields()
    cardController.printCardDecks()
  }

}
