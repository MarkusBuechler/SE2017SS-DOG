/**
 * Created by margogo on 02.04.17.
 * Main Class for the scala application DOG
 */

import com.google.inject.Guice
import de.htwg.se.yourgame.DependencyModule
import de.htwg.se.yourgame.controller.gameController
import de.htwg.se.yourgame.view.{ DogGui, DogTui }
import net.codingwell.scalaguice.InjectorExtensions._

import scalafx.application.Platform

object DogApplication extends App {

  val injector = Guice.createInjector(new DependencyModule)

  var gameController = injector.instance[gameController]
  gameController.initGame()

  var gui = injector.instance[DogGui]
  var tui = injector.instance[DogTui]

  while (tui.processInputLine(scala.io.StdIn.readLine())) {}

}

