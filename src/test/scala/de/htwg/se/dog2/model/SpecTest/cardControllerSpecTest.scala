package de.htwg.se.dog2.model.SpecTest

import de.htwg.se.dog2.model.Card
import org.scalatest.WordSpec
import org.scalatest.Matchers._

/**
 * Created by margogo on 10.05.17.
 * Useless test ...
 */
class cardControllerSpecTest extends WordSpec {

  val card = Card(1, "Black", "Sample Description", 2, "Property1", false)
  val card2 = Card(1, "Black", "Sample Description", 2, "Property1", false)

  "CardTest" should {
    "equal to" in {
      card shouldBe a[Card]
    }
  }

  "id" in {
    card.id should be(1)
  }

  "color" in {
    card.color should be("Black")
  }

  "description" in {
    card.description should be("Sample Description")
  }

  "value" in {
    card.value should be(2)
  }

  "property" in {
    card.property should be("Property1")
  }

  "isPlayed" in {
    card.isPlayed should be(false)
  }

  "Equals" in {
    card should be equals card2
  }

  "To String" in {
    card.toString shouldBe ("Card(1,Black,Sample Description,2,Property1,false)")
  }

  "Hashcode" in {
    card.hashCode() shouldBe (-280135522)
  }

}

