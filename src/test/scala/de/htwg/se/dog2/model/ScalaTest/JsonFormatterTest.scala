package de.htwg.se.dog2.model.ScalaTest

import com.google.inject.Guice
import de.htwg.se.dog2.DependencyModule
import de.htwg.se.dog2.controller.gameController
import org.junit.Test
import org.scalatest.{ FlatSpec, Matchers }
import play.api.libs.json.Json

import de.htwg.se.dog2.model._
import play.api.libs.functional.syntax.unlift
import play.api.libs.json.{ Format, __ }
import play.api.libs.functional.syntax._
import scala.collection.mutable.ListBuffer

/**
 * Created by margogo on 20.06.17.
 */
@Test
class JsonFormatterTest extends FlatSpec with Matchers {

  val injector = Guice.createInjector(new DependencyModule)

  var gameController: gameController = injector.getInstance(classOf[gameController])

  gameController.initGame()

  ////////// JSON FORMATTER ////////////////////
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

  implicit val gameFormatter: Format[Game] = (
    (__ \ "figureList").format[ListBuffer[Figure]] and
      (__ \ "playerList").format[ListBuffer[Player]] and
      (__ \ "fieldList").format[ListBuffer[Field]] and
      (__ \ "cardList").format[ListBuffer[Card]] and
      (__ \ "cardDeck").format[ListBuffer[CardDeck]] and
      (__ \ "playedCards").format[ListBuffer[Card]] and
      (__ \ "currentPlayer").format[Player] and
      (__ \ "currentFIGURE").format[Figure] and
      (__ \ "currentFigNr").format[Int] and
      (__ \ "deckSize").format[Int]
    )(Game.apply, unlift(Game.unapply))

  //convert the Player instance to a JSON String
  val myPlayer: Player = gameController.playerList.apply(1)
  val testPlayer = Json.toJson(myPlayer).toString()

  //convert a JSON String to a Player instance
  val jsonPlayer: Player = Json.parse(testPlayer).as[Player]

  //convert the Card instance to a JSON String
  val myCard: Card = gameController.cardList.apply(1)
  val testCard = Json.toJson(myCard).toString()

  //convert a JSON String to a Card instance
  val jsonCard: Card = Json.parse(testCard).as[Card]

  //convert the CardDeck instance to a JSON String
  val myCardDeck: CardDeck = gameController.cardDecks.apply(1)
  val testCardDeck = Json.toJson(myCardDeck).toString()

  //convert a JSON String to a CardDeck instance
  val jsonCardDeck: CardDeck = Json.parse(testCardDeck).as[CardDeck]

  //convert the Field instance to a JSON String
  val myField: Field = gameController.fieldList.apply(1)
  val testField = Json.toJson(myField).toString()

  //convert a JSON String to a Field instance
  val jsonField: Field = Json.parse(testField).as[Field]

  //convert the Figure instance to a JSON String
  val myFigure: Figure = gameController.figureList.apply(1)
  val testFigure = Json.toJson(myFigure).toString()

  //convert a JSON String to a Figure instance
  val jsonFigure: Figure = Json.parse(testFigure).as[Figure]

  //convert the Figure instance to a JSON String
  val myGame: Game = Game(gameController.figureList, gameController.playerList, gameController.fieldList, gameController.cardList, gameController.cardDecks, gameController.playedCards,
  gameController.currentPlayer, gameController.currentFig, gameController.currentFigNr, gameController.decksize)
  val testGame = Json.toJson(myGame).toString()

  //convert a JSON String to a Figure instance
  val jsonGame: Game = Json.parse(testGame).as[Game]

  print(testGame)
  print(jsonGame)

}
