package de.htwg.se.dog2

/**
 * Created by margogo on 02.04.17.
 * Main Class for the scala application DOG
 */

import com.google.inject.Guice
import de.htwg.se.dog2.controller.gameController
import de.htwg.se.dog2.view.{ DogGui, DogTui }
import net.codingwell.scalaguice.InjectorExtensions._

object DogApplication extends App {

  val injector = Guice.createInjector(new DependencyModule)

  var gameController = injector.instance[gameController]
  gameController.initGame()

  var gui = injector.instance[DogGui]
  var tui = injector.instance[DogTui]

  gameController.testFormat

  while (tui.processInputLine(scala.io.StdIn.readLine())) {}

}

