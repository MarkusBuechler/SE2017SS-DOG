package de.htwg.se.dog2

import com.google.inject.Guice
import de.htwg.se.dog2.controller.gameController
import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ FlatSpec, Matchers }

/**
 * Created by margogo on 15.06.17.
 */
@Test
@RunWith(classOf[JUnitRunner])
class UpdateDeckSizeTest_A extends FlatSpec with Matchers {

  val injector = Guice.createInjector(new DependencyModule)

  var gameController = injector.getInstance(classOf[gameController])

  // normal way
  gameController.initGame()
  assert(gameController.decksize == 7)

  gameController.updateDeckSize()
  assert(gameController.decksize == 6)

  gameController.updateDeckSize()
  assert(gameController.decksize == 5)

  gameController.updateDeckSize()
  assert(gameController.decksize == 4)

  gameController.updateDeckSize()
  assert(gameController.decksize == 7)

  // for test or error
  gameController.decksize = 1
  gameController.updateDeckSize()
  assert(gameController.decksize == 7)

}

@Test
@RunWith(classOf[JUnitRunner])
class UpdateDeckSizeTest_B extends FlatSpec with Matchers {

  val injector = Guice.createInjector(new DependencyModule)

  var gameController = injector.getInstance(classOf[gameController])

  // normal way
  gameController.initGame()
  assert(gameController.decksize == 7)

  gameController.updateDeckSize()
  assert(gameController.decksize == 6)

  gameController.updateDeckSize()
  assert(gameController.decksize == 5)

  // player started a new game ! -> default : 7
  gameController.newGame()
  assert(gameController.decksize == 7)

}
