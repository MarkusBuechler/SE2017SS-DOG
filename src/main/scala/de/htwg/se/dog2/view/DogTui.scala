package de.htwg.se.dog2.view

import com.google.inject.Inject
import de.htwg.se.dog2.controller.gameController
import org.apache.logging.log4j.LogManager

import scala.swing.Reactor

/**
 * Created by margogo on 02.04.17.
 */
// $COVERAGE-OFF$Disabling highlighting by default until scala swing integration test franework is found.
class DogTui @Inject() (var gameController: gameController) extends Reactor {

  var logger = LogManager.getLogger(DogTui.this)

  val info = "Enter command: q-Quit; m - TestCard ; g - init cards/field ; s - setup player ; n-New Game ; i-Information\n"
  var continue = true
  def update(): Unit = printTui()

  def printTui(): Unit = {
    logger.info(info)
    gameController.showGameStatus()

  }

  printTui()

  def processInputLine(input: String): Boolean = {
    continue = true
    input match {
      case "q" =>
        print("q wurde gedrückt !\nSpiel wird bald verlassen\n");
        gameController.quitGame
      /*continue = false*/
      case "n" =>
        print("n wurde gedrückt !\nSpiel wird gestartet\n");
        print("\nWelche Karte möchtest du spielen?\n")
        gameController.showGameStatus()
        continue = true
      case "i" => print("i wurde gedrückt !\nInformationen werden bald angezeigt\n"); continue = true;
      case "s" =>
        print("Bitte Spieler auswählen [1-4] und einen Namen eingeben\n")
        val input = scala.io.StdIn.readLine()
        val tokens = input.split(" ")
        if (tokens.length != 2) {
          print("Falsche Eingabe!\n")
          print(info)
        } else {
          gameController.setPlayerName(tokens(0).toInt, tokens(1))
        }
        continue = true;
        printTui()
      case "y" =>
        print("Welche Karte soll mit welcher Figur gespielt werden?\n")
        // todo: prüfungen einbauen
        val input = scala.io.StdIn.readLine()
        val tokens = input.split(" ")
        if (tokens.length != 1) {
          print("Falsche Eingabe!\n")
          print(info)
        } else {
          gameController.playerActionPrep(tokens(0).toInt)
          printTui()
        }
      case "g" =>
        gameController.changeCurrentFigureNr()
        printTui()
      case _ => print("False Eingabe\n"); print(info)
    }
    continue
  }

  def gameLogic() = {
    if (false) {
      logger.info("Exit game")
      sys.exit()
    }
  }

}
// $COVERAGE-ON$