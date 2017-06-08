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
class ChangeFigTest extends FlatSpec with Matchers {

  val injector = Guice.createInjector(new DependencyModule)

  var gameController = injector.getInstance(classOf[gameController])

  gameController.initGame()

  // Change Current Fig Nr between 0 and 3 with Player 1
  assert(gameController.currentFigNr == 0)

  gameController.changeCurrentFigureNr()
  assert(gameController.currentFigNr == 1)

  gameController.changeCurrentFigureNr()
  assert(gameController.currentFigNr == 2)

  gameController.changeCurrentFigureNr()
  assert(gameController.currentFigNr == 3)

  gameController.changeCurrentFigureNr()
  assert(gameController.currentFigNr == 0)

  // Change to Player 2
  gameController.changeCurrentFigure()
  assert(gameController.currentFigNr == 4)

  gameController.changeCurrentFigureNr()
  assert(gameController.currentFigNr == 5)

  gameController.changeCurrentFigureNr()
  assert(gameController.currentFigNr == 6)

  gameController.changeCurrentFigureNr()
  assert(gameController.currentFigNr == 7)

  gameController.changeCurrentFigureNr()
  assert(gameController.currentFigNr == 4)

  // Change to Player 3
  gameController.changeCurrentFigure()
  assert(gameController.currentFigNr == 8)

  gameController.changeCurrentFigureNr()
  assert(gameController.currentFigNr == 9)

  gameController.changeCurrentFigureNr()
  assert(gameController.currentFigNr == 10)

  gameController.changeCurrentFigureNr()
  assert(gameController.currentFigNr == 11)

  gameController.changeCurrentFigureNr()
  assert(gameController.currentFigNr == 8)

  // Change to Player 4
  gameController.changeCurrentFigure()
  assert(gameController.currentFigNr == 12)

  gameController.changeCurrentFigureNr()
  assert(gameController.currentFigNr == 13)

  gameController.changeCurrentFigureNr()
  assert(gameController.currentFigNr == 14)

  gameController.changeCurrentFigureNr()
  assert(gameController.currentFigNr == 15)

  gameController.changeCurrentFigureNr()
  assert(gameController.currentFigNr == 12)

  // Back to Player 1
  gameController.changeCurrentFigure()
  assert(gameController.currentFigNr == 0)

  gameController.changeCurrentFigureNr()
  assert(gameController.currentFigNr == 1)

}
