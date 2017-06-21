package de.htwg.se.dog2.util.FileIOJsonImpl

import de.htwg.se.dog2.model._
import de.htwg.se.dog2.util.TFileIO
import play.api.libs.functional.syntax.unlift
import play.api.libs.json.{Format, __}
import play.api.libs.functional.syntax._

import scala.collection.mutable.ListBuffer

/**
  * Created by margogo on 21.06.17.
  */
class FileIO extends TFileIO {

  //  //noinspection ScalaStyle
  // import or export later game state
  def testFormat = {
    //////////// JSON FORMATTER ////////////////////
    implicit val playerFormatter: Format[Player] = (
      (__ \ "name").format[String] and
        (__ \ "playerId").format[Int] and
        (__ \ "isActive").format[Boolean]
      )(Player.apply, unlift(Player.unapply))

    implicit val cardFormatter: Format[Card] = (
      (__ \ "id").format[Int] and
        (__ \ "color").format[String] and
        (__ \ "description").format[String] and
        (__ \ "value").format[Int] and
        (__ \ "property").format[String] and
        (__ \ "isPlayed").format[Boolean]
      )(Card.apply, unlift(Card.unapply))

    implicit val cardDeckFormatter: Format[CardDeck] = (
      (__ \ "playerId").format[Int] and
        (__ \ "numberOfCards").format[Int] and
        (__ \ "cards").format[ListBuffer[Card]]
      )(CardDeck.apply, unlift(CardDeck.unapply))

    implicit val figureFormatter: Format[Figure] = (
      (__ \ "player").format[Player] and
        (__ \ "playerFigNumber").format[Int] and
        (__ \ "role").format[String] and
        (__ \ "property").format[String] and
        (__ \ "position").format[Int] and
        (__ \ "x").format[Int] and
        (__ \ "y").format[Int] and
        (__ \ "Color").format[String]
      )(Figure.apply, unlift(Figure.unapply))

    implicit val fieldFormatter: Format[Field] = (
      (__ \ "id").format[Int] and
        (__ \ "property").format[String] and
        (__ \ "color").format[String] and
        (__ \ "figure").format[Figure] and
        (__ \ "predecessorIds").format[Array[Int]] and
        (__ \ "successorIds").format[Array[Int]] and
        (__ \ "x").format[Int] and
        (__ \ "y").format[Int]
      )(Field.apply, unlift(Field.unapply))
  }

  def save(game: Game): Unit = {

  }

  override def load(): Unit = {

  }
}
