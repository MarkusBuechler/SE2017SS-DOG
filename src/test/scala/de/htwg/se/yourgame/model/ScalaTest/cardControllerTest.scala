package de.htwg.se.yourgame.model.ScalaTest

import com.google.inject.Guice
import de.htwg.se.yourgame.DependencyModule
import de.htwg.se.yourgame.controller.gameController
import org.scalatest.{ FlatSpec, Matchers }

/**
 * Created by margogo on 10.05.17.
 */
class cardControllerTest extends FlatSpec with Matchers {

  val injector = Guice.createInjector(new DependencyModule)

  var cardController = injector.getInstance(classOf[gameController])

  cardController.initCards()
  val decksize = cardController.cardList.size
  decksize should be > 0

  assert(cardController.cardList.size > 1);

  val cardA = cardController.cardList.apply(0)
  cardController.shuffleCards()
  val cardB = cardController.cardList.apply(0)

  assert(cardA != cardB)

}
