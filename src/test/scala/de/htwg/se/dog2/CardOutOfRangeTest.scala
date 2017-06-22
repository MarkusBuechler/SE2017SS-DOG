package de.htwg.se.dog2

import com.google.inject.Guice
import de.htwg.se.dog2.controller.gameController
import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.junit.JUnitRunner

/**
  * Created by margogo on 22.06.17.
  */
@Test
@RunWith(classOf[JUnitRunner])
class CardOutOfRangeTest extends FlatSpec with Matchers {

  val injector = Guice.createInjector(new DependencyModule)

  var gameController = injector.getInstance(classOf[gameController])

  gameController.initGame()

  // Playing an invalid card -> GUI show pop up
  gameController.playerAction(10)

  // All cards still there
  assert(gameController.cardDecks.head.cards.size == 7)
  assert(gameController.cardDecks.apply(1).cards.size == 7)
  assert(gameController.cardDecks.apply(2).cards.size == 7)
  assert(gameController.cardDecks.apply(3).cards.size == 7)

}
