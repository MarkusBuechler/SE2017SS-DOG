package de.htwg.se.yourgame.controller

import de.htwg.se.yourgame.model.{Figure, Player}
import scala.collection.mutable.ListBuffer

/**
  * Created by margogo on 15.04.17.
  */
class playerController {

  val emptyPlayer = Player("", 100, isActive = false)
  var figureListBuffer = new ListBuffer[Figure]
  var playerList = new ListBuffer[Player]
  val numberOfPlayer = 3 // 3+1 regulär
  var currentPlayer = Player("Player 1",0,isActive = true)


  def initPlayer(): Unit = {
    for (x <- 0 to numberOfPlayer) {
      val bufferPlayer = Player("Player " + (x + 1) , x, isActive = false)
      playerList += bufferPlayer
    }
    playerList.update(0, Player("Player 1",0, isActive = true))
    initFigures()
  }

  def initFigures(): Unit = {
    figureListBuffer.clear
    val bufferedSource = io.Source.fromFile("resources/Figures.csv")
    for (line <- bufferedSource.getLines()) {
      val Array(player, playerFigNumber, role, property, position) = line.split(";").map(_.trim())
      val bufferFig = Figure(playerList.apply(player.toInt), playerFigNumber.toInt, role, property, position.toInt)
      figureListBuffer += bufferFig
    }
  }

  def setPlayerName(inputNumber: Int, inputString: String): Unit = {

    var playerNo = 0
    inputNumber match {
      case 1 => print("Player 1 wurde ausgewählt !\n"); playerNo = 0
      case 2 => print("Player 2 wurde ausgewählt !\n"); playerNo = 1
      case 3 => print("Player 3 wurde ausgewählt !\n"); playerNo = 2
      case 4 => print("Player 4 wurde ausgewählt !\n"); playerNo = 3
      case _ => print("False Eingabe! Player 1 wurde ausgewählt !\n"); playerNo = 0
    }
    inputString match {
      case _ =>
        val insertNumber = playerNo + 1
        val currentPlayer = Player(inputString, insertNumber, isActive = false)
        print(inputString + " wurde eingeben und Player " + insertNumber + " zugewiesen ")
        playerList.update(playerNo, currentPlayer)
    }
    print(playerList.toString())
  }

  //Todo: ternary operator verwenden
  def printCurrentPlayer(): Unit = {
//    for ( player  <- playerList) {
//      if (player.isActive)
//        {
//          currentPlayer += player.name
//        }
//    }
    if (currentPlayer != "") {
      print(currentPlayer.name + " ist am Zug")
    } else {
      print("Niemand ist am Zug")
    }
  }

  def changeCurrentPlayer() = {
    val lastPlayer = playerList.apply(currentPlayer.playerId).copy(isActive = false)
    val nextPlayerNumber = if (currentPlayer.playerId == 3) 0 else currentPlayer.playerId + 1

    playerList.update(lastPlayer.playerId, lastPlayer)

    val nextPlayer = playerList.apply(nextPlayerNumber).copy(isActive = true)
    playerList.update(nextPlayerNumber, nextPlayer)

    val currentPlayerIndex = playerList.indexWhere(_.isActive == true)
    currentPlayer = playerList.apply(currentPlayerIndex).copy(isActive = true)
  }


}
