package de.htwg.se.yourgame.view.tui

import de.htwg.se.yourgame.controller.{gameController}

/**
  * Created by margogo on 02.04.17.
  */
class DogTui (var gameController: gameController) {
  var playerController = this.gameController.playerController

  gameController.initGame

  val info = "Enter command: q-Quit; m - TestCard ; g - init cards/field ; s - setup player ; n-New Game ; i-Information\n"
  printTui

  def update = printTui

  def printTui = {
    println(info)
  }

    def processInputLine(input: String) = {
      var continue = true
      input match {
        case "q" => print("q wurde gedrückt !\nSpiel wird bald verlassen\n"); continue = false
        case "n" => print("n wurde gedrückt !\nSpiel wird bald gestartet\n"); continue = true
        case "i" => print("i wurde gedrückt !\nInformationen werden bald angezeigt\n"); continue = true;
        case "s" => print("s wurde gedrückt !\n Spieler 1 ist nun s");
          //todo: eingabe richtig einlesen
          playerController.setPlayerName(1,input : String)
          continue = true;
        case _ => print("False Eingabe\n"); print(info)
      }
      continue
    }
}
