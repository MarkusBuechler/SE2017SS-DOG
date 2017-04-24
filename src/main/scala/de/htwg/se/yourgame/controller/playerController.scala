package de.htwg.se.yourgame.controller

import de.htwg.se.yourgame.model.Player
import com.softwaremill.macwire._
import scala.collection.mutable.ListBuffer

/**
  * Created by margogo on 15.04.17.
  */
class playerController {

  var playerList = new ListBuffer[Player]
  val numberOfPlayer = 4
//  var currentPlayer = playerList.apply(2)
  def initPlayer = {

    for (x <- 1 to numberOfPlayer) {
      val bufferPlayer = Player("Player " + x , x, false)
      playerList += bufferPlayer
    }
    playerList.update(1, Player("Player 1",1, true))
//    print(playerList.size)
  }

  def setPlayerName(inputNumber: Int, inputString: String) = {
    print("Bitte Spieler auswählen [1-4] und einen Namen eingeben\n")
    var playerNo = 0
    inputNumber match {
      case 1 => print("Player 1 wurde ausgewählt !\n"); playerNo = 0
//      case 2 => print("Player 2 wurde ausgewählt !\n"); playerNo = 1
//      case 3 => print("Player 3 wurde ausgewählt !\n"); playerNo = 2
//      case 4 => print("Player 4 wurde ausgewählt !\n"); playerNo = 3
//      case _ => print("Falsche Eingabe !\nPlayer 1 wurde standardmäßig ausgewählt\n"); playerNo = 0
    }
    inputString match {
      case _ => {
        val insertNumber = playerNo + 1
        val currentPlayer = Player(inputString, insertNumber, false)
        print(inputString + "wurde eingeben und Player " + insertNumber + "zugewiesen")
        playerList.update(playerNo, currentPlayer)
      }
    }
    print(playerList.toString())
  }

  //Todo: ternary operator verwenden
  def printCurrentPlayer = {
    var currentPlayer = ""
    for ( player  <- playerList) {
      if (player.isActive)
        {
          currentPlayer += player.name
        }
    }
    if (currentPlayer != "") {
      print(currentPlayer + " ist am Zug")
    } else {
      print("Niemand ist am Zug")
    }
  }

  def changeCurrentPlayer(currentPlayer : Player)= {

  }



}
