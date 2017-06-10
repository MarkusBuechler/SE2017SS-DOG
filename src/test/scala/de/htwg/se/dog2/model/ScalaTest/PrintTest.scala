package de.htwg.se.dog2.model.ScalaTest

import com.google.inject.Guice
import de.htwg.se.dog2.DependencyModule
import de.htwg.se.dog2.controller.gameController
import org.junit.Test
import org.scalatest.{ FlatSpec, Matchers }

/**
 * Created by margogo on 08.06.17.
 */
@Test
class PrintTest extends FlatSpec with Matchers {

  val injector = Guice.createInjector(new DependencyModule)

  var gameController = injector.getInstance(classOf[gameController])

  gameController.initGame()

  gameController.showGameStatus()
  gameController.printFields()
  gameController.printCardDecks()
  gameController.quitGame()
}