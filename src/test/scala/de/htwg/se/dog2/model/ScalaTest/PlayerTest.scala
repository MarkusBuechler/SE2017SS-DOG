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
class PlayerTest extends FlatSpec with Matchers {

  val injector = Guice.createInjector(new DependencyModule)

  var gameController = injector.getInstance(classOf[gameController])

  gameController.initGame()

  // Current Player is Player 1 with ID = 0
  assert(gameController.currentPlayer.playerId == 0)

  // Change to Player 2
  gameController.changeCurrentPlayer
  assert(gameController.currentPlayer.playerId == 1)

  // Change to Player 3
  gameController.changeCurrentPlayer
  assert(gameController.currentPlayer.playerId == 2)

  // Change to Player 4
  gameController.changeCurrentPlayer
  assert(gameController.currentPlayer.playerId == 3)

  // Change back to Player 1
  gameController.changeCurrentPlayer
  assert(gameController.currentPlayer.playerId == 0)

}
