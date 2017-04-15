package de.htwg.se.yourgame.view.tui

import de.htwg.se.yourgame.controller.{cardController, fieldController, playerController}

/**
  * Created by margogo on 02.04.17.
  */
class DogTui (var cardController: cardController, var fieldController: fieldController, var playerController: playerController) {

  val info = "Enter command: q-Quit; m - TestCard ; g - init cards ; 4 - shuffleCards ; n-New Game ; i-Information\n"
  printTui

  def update = printTui

  def printTui = {
    println(info)
  }

    def processInputLine(input: String) = {
      var continue = true
      input match {
        case "q" => print("q wurde gedrückt !\nSpiel wird bald verlassen\n"); continue = false
        case "1" => print("1 wurde gedrückt !\n" + cardController.getCardOne.toString + "\n"); continue = true;
        case "4" => print("4 wurde gedrückt !\n\n")
          cardController.shuffleCards
          continue = true;
        case "2" => print("2 wurde gedrückt !\n" + cardController.getCardTwo.toString + "\n"); continue = true;
        case "3" => print("3 wurde gedrückt !\n" + cardController.getCardThree.toString + "\n"); continue = true;
        case "n" => print("n wurde gedrückt !\nSpiel wird bald gestartet\n"); continue = true
        case "i" => print("i wurde gedrückt !\nInformationen werden bald angezeigt\n"); continue = true;
        case "g" => print("g wurde gedrückt !\nKarten werden initialisiert\n");
          cardController.initCards
          fieldController.initFields
          continue = true;
        case _ => print("False Eingabe\n"); print(info)
      }
      continue
    }
}
