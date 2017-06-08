package de.htwg.se.dog2.model.SpecTest

import com.google.inject.Guice
import de.htwg.se.dog2.DependencyModule
import de.htwg.se.dog2.controller.gameController
import org.scalatest.WordSpec

/**
 * Created by margogo on 10.05.17.
 */
class cardControllerSpecTest extends WordSpec {

  val injector = Guice.createInjector(new DependencyModule)

  var cardController = injector.getInstance(classOf[gameController])

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

