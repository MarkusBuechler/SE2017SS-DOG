/**
 * Created by margogo on 02.04.17.
 * Main Class for the scala application DOG
 */

import de.htwg.se.yourgame.DependencyModule
import com.google.inject.{ Guice, Injector }
import de.htwg.se.yourgame.controller.gameController
import de.htwg.se.yourgame.view.{ DogGui, DogTui }
import net.codingwell.scalaguice.InjectorExtensions._

object DogApplication {

  val injector: Injector = Guice.createInjector(new DependencyModule)
  val gameController: gameController = injector.instance[gameController]
  gameController.initGame()

  var gui = new DogGui(gameController)
  var tui = new DogTui(gameController)

  def main(args: Array[String]): Unit = {
    startJFXGui(gui)
    while (tui.processInputLine(scala.io.StdIn.readLine())) {}
    System.exit(0)
  }

  def startJFXGui(gui: DogGui): Unit = {
    new Thread(new Runnable {
      def run() {
        gui.main(Array())
      }
    }).start()
  }
}

