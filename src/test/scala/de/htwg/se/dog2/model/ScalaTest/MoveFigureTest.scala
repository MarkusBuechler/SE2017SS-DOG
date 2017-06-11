package de.htwg.se.dog2.model.ScalaTest

import com.google.inject.Guice
import de.htwg.se.dog2.DependencyModule
import de.htwg.se.dog2.controller.gameController
import org.junit.Test
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by margogo on 11.06.17.
  */
@Test
class MoveFigureTest extends FlatSpec with Matchers {

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
