package de.htwg.se.dog2

import com.google.inject.Guice
import de.htwg.se.dog2.controller.gameController
import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ FlatSpec, Matchers }

/**
 * Created by margogo on 08.06.17.
 */
@Test
@RunWith(classOf[JUnitRunner])
class UpdateFigPosTest extends FlatSpec with Matchers {

  val injector = Guice.createInjector(new DependencyModule)

  var gameController = injector.getInstance(classOf[gameController])

  gameController.initGame()

  // Field 1 is not empty and has default (empty) figure on it.
  assert(gameController.fieldList.nonEmpty)
  assert(gameController.fieldList.apply(1).figure.playerFigNumber == 200)

  // Field 1 has now Figure 1 on it.
  gameController.updateFigField(1, gameController.figureList.apply(1))
  assert(gameController.fieldList.apply(1).figure.playerFigNumber == 1)

  // Figure 1 moved to another field.
  gameController.updateFigField(2, gameController.figureList.apply(1))
  assert(gameController.fieldList.apply(1).figure.playerFigNumber == 200)
  assert(gameController.fieldList.apply(2).figure.playerFigNumber == 1)

}
