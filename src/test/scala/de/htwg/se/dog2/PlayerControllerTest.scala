package de.htwg.se.dog2

import com.google.inject.Guice
import de.htwg.se.dog2.controller.gameController
import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{ FlatSpec, Matchers }

/**
 * Created by margogo on 10.05.17.
 */
@Test
@RunWith(classOf[JUnitRunner])
class PlayerControllerTest extends FlatSpec with Matchers {

  val injector = Guice.createInjector(new DependencyModule)

  var gameController = injector.getInstance(classOf[gameController])

  gameController.initGame()

  // Current Player is Player1 with Figure 1
  assert(gameController.currentFigNr == 0)
  assert(gameController.currentPlayer == gameController.playerList.head)

  // Playing the second card
  gameController.playerAction(1)

  assert(gameController.playedCards.size == 1)
  assert(gameController.cardDecks.head.cards.size == 6)

  // game game status
  gameController.saveGame()

}
