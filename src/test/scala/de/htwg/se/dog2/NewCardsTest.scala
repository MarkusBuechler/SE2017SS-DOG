package de.htwg.se.dog2

import com.google.inject.Guice
import de.htwg.se.dog2.controller.gameController
import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.{ FlatSpec, Matchers }
import org.scalatest.junit.JUnitRunner

/**
 * Created by margogo on 22.06.17.
 */
@Test
@RunWith(classOf[JUnitRunner])
class NewCardsTest extends FlatSpec with Matchers {

  val injector = Guice.createInjector(new DependencyModule)

  var gameController = injector.getInstance(classOf[gameController])

  gameController.initGame()

  // Check init card sizes
  assert(gameController.cardDecks.head.cards.size == 7)
  assert(gameController.cardDecks.apply(1).cards.size == 7)
  assert(gameController.cardDecks.apply(2).cards.size == 7)
  assert(gameController.cardDecks.apply(3).cards.size == 7)

  // DeckSize is now 7
  // Play 4x7 Cards
  for (i <- 1 to 28) {
    gameController.playerAction(1)
  }
  // Checking game Status -> Reset tp 6 Cards
  assert(gameController.decksize == 6)

  // DeckSize is now 6
  // Play 4x6 Cards
  for (i <- 1 to 24) {
    gameController.playerAction(1)
  }
  // Checking game Status -> Reset tp 5 Cards
  assert(gameController.decksize == 5)

  // DeckSize is now 5
  // Play 4x6 Cards
  for (i <- 1 to 20) {
    gameController.playerAction(1)
  }
  // Checking game Status -> Reset tp 4 Cards
  assert(gameController.decksize == 4)

  // DeckSize is now 7
  // Play 4x6 Cards
  for (i <- 1 to 28) {
    gameController.playerAction(1)
  }
  // Checking game Status -> Reset tp 7 Cards
  assert(gameController.decksize == 7)

}