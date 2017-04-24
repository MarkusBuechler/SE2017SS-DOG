/**
  * Created by margogo on 02.04.17.
  * Main Class for the scala application DOG
  */

import de.htwg.se.yourgame.controller.gameController
import de.htwg.se.yourgame.view.tui.DogTui

import scala.io.StdIn._

object DogMain {

  val gameController = new gameController
  val tui = new DogTui(gameController)

  def main(args: Array[String]): Unit = {
    while (tui.processInputLine(readLine())) {}
  }

}
