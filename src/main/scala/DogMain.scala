/**
 * Created by margogo on 02.04.17.
 * Main Class for the scala application DOG
 */

import de.htwg.se.yourgame.DependencyModule
import com.google.inject.{ Guice, Injector }
import de.htwg.se.yourgame.controller.TGameController
import de.htwg.se.yourgame.view.{ DogGui, DogTui }
import net.codingwell.scalaguice.InjectorExtensions._

import scalafx.application.JFXApp

object DogApplication extends JFXApp {

  val injector: Injector = Guice.createInjector(new DependencyModule)
  val gameController = injector.instance[TGameController]
  gameController.initGame()

  var gui = injector.instance[DogGui]
  var tui = injector.instance[DogTui]

}

