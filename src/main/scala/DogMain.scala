/**
  * Created by margogo on 02.04.17.
  */

import de.htwg.se.yourgame.controller.{cardController, fieldController}
import de.htwg.se.yourgame.view.tui.DogTui

import scala.io.StdIn._

object DogMain {

  val cardController = new cardController
  val fieldController = new fieldController
  val tui = new DogTui(cardController, fieldController)

  def main(args: Array[String]): Unit = {
    while (tui.processInputLine(readLine())) {}
  }

}
