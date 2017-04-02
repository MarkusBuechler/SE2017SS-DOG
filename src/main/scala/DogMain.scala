/**
  * Created by margogo on 02.04.17.
  */

import de.htwg.se.yourgame.controller.DogController
import de.htwg.se.yourgame.view.tui.DogTui

import scala.io.StdIn._

object DogMain {

  val controller = new DogController
  val tui = new DogTui(controller)

  def main(args: Array[String]): Unit = {
    while (tui.processInputLine(readLine())) {}
  }

}
