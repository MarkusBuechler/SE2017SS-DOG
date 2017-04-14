/**
  * Created by margogo on 02.04.17.
  */

import de.htwg.se.yourgame.controller.cardController
import de.htwg.se.yourgame.view.tui.DogTui

import scala.io.StdIn._

object DogMain {

  val controller = new cardController
  val tui = new DogTui(controller)

  def main(args: Array[String]): Unit = {
    while (tui.processInputLine(readLine())) {}
  }

}
