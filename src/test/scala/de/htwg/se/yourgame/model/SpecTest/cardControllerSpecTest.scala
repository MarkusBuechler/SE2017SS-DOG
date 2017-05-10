package de.htwg.se.yourgame.model.SpecTest

import com.google.inject.Guice
import de.htwg.se.yourgame.DependencyModule
import de.htwg.se.yourgame.controller.cardController
import org.scalatest.WordSpec

/**
  * Created by margogo on 10.05.17.
  */
class cardControllerSpecTest extends WordSpec {

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


