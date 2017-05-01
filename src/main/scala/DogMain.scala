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
  val playerController: playerController = injector.getInstance(classOf[playerController])
  val cardController: cardController = injector.getInstance(classOf[cardController])
  val fieldController: fieldController = injector.getInstance(classOf[fieldController])

  val gameController = new gameController(playerController, cardController, fieldController)
  val tui = new DogTui(gameController, playerController, cardController)

  def main(args: Array[String]): Unit = {
    while (tui.processInputLine(readLine())) {}
  }

}




