package de.htwg.se.yourgame.model

import com.google.inject.Guice
import de.htwg.se.yourgame.DependencyModule
import de.htwg.se.yourgame.controller.cardController
import org.scalatest.{FlatSpec, Matchers, WordSpec}

/**
  * Created by margogo on 10.05.17.
  */
class cardControllerSpec extends WordSpec {

  val injector = Guice.createInjector(new DependencyModule)


  var cardController = injector.getInstance(classOf[cardController])

  print(cardController)

  "Controller" should {
    "not be null" in {
    }
  }

//  cardController.initCards()

  /* wie soll man denn gescheit testen ? */
  "Decksize" should {
    "be greater than" in (
      1000
    )
  }

}

class cardControllerTest extends FlatSpec with Matchers {

  val injector = Guice.createInjector(new DependencyModule)


  var cardController = injector.getInstance(classOf[cardController])

  print(cardController)

  //  cardController.initCards()
  //
  //  /* wie soll man denn gescheit testen ? */
  //  "Decksize" should {
  //    "be greater than" in (
  //      1000
  //    )
  //  }

  cardController.initCards()
  val decksize = cardController.cardList.size
  decksize should be > 0

  assert(cardController.cardList.size > 1);

}
