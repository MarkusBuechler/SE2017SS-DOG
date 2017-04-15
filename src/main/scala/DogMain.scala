/**
  * Created by margogo on 02.04.17.
  * Main Class for the scala application DOG
  */

import de.htwg.se.yourgame.controller.{cardController, fieldController, playerController}
import de.htwg.se.yourgame.view.tui.DogTui

import scala.io.StdIn._

object DogMain {

  // vllt sowas wie gameController der das hier übernimmt
  val cardController = new cardController
  val fieldController = new fieldController
  val playerController = new playerController
  val tui = new DogTui(cardController, fieldController, playerController)

  def main(args: Array[String]): Unit = {
    while (tui.processInputLine(readLine())) {}
  }

}
