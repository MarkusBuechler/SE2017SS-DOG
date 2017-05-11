/**
  * Created by margogo on 02.04.17.
  * Main Class for the scala application DOG
  */

import com.google.inject.{Guice, Injector}
import de.htwg.se.yourgame.DependencyModule
import de.htwg.se.yourgame.controller.{cardController, fieldController, gameController, playerController}
import de.htwg.se.yourgame.view.tui.DogTui

import scala.io.StdIn._

object DogMain {

  val injector: Injector = Guice.createInjector(new DependencyModule)

  val gameController = new gameController()
  val tui = new DogTui(gameController)

  def main(args: Array[String]): Unit = {
    while (tui.processInputLine(readLine())) {}
  }

}




