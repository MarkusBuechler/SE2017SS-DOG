package de.htwg.se.yourgame.controller

import com.google.inject.Inject
import de.htwg.se.yourgame.model.{Card, CardDeck, Player}

import scala.collection.mutable.ListBuffer

/**
  * Created by margogo on 02.04.17.
  * code will be added in the future
  *
  * Card controller:
  * todo:
  * - prepare decks
  * - tests
  *
  * Add other controllers
  * Note: A normal list in scala is NOT mutable, better use a listbuffer and convert it to a list when you need
  * or a mutable list (linear seq or other)
  */
  class cardController {
  var cardList = new ListBuffer[Card]
  var cardDecks = new ListBuffer[CardDeck]
  var a = 0
  val playerList = List (1,2,3,4)
  var numberOfPlayers = 4
  var decksize = 7
  var playedCards = new ListBuffer[Card]


  def initCards() = {
    cardList.clear
    playedCards.clear
    val bufferedSource = io.Source.fromFile("resources/CardsSmall.csv")
    for (line <- bufferedSource.getLines()) {
      val Array(id, color, description, value, property, isPlayed) = line.split(";").map(_.trim())
      val tempCard = Card(id.toInt, color, description, value.toInt, property, isPlayed.toBoolean)
      cardList += tempCard
    }
    shuffleCards
    fillCardDeck
  }

  //sample data
  val card1 = Card(1, "Gelb", "Zwei", 1, "Eigeschaft1", isPlayed = false)
  val card2 = Card(2, "Rot", "Zwei",1, "Eigeschaft2", isPlayed = true)
  val card3 = Card(3, "Blau", "Zwei",1, "Eigeschaft3", isPlayed = true)


  def shuffleCards = {
    cardList = scala.util.Random.shuffle(cardList)
  }

  def fillCardDeck = {

    cardDecks.clear
    for (a <- playerList) {
      var cardBuffer = new ListBuffer[Card]
      for (i <- 1 to decksize) {
        cardBuffer+=cardList.head
        cardList-=cardList.head
      }
      val cardBufferList = cardBuffer.toList
      val filledCardDeck = CardDeck(a,decksize,cardBufferList)
      cardDecks+=filledCardDeck
    }

  }

  def printCardDecks() = {
    var string = ""
    for (x <- 0 until cardDecks.size-1) {
      string += "Player "
      string += cardDecks.apply(x).PlayerId + ": "
      for (y <- 0 until cardDecks.apply(x).cards.size - 1) {
        string += cardDecks.apply(x).cards.apply(y).description.toString + " ,"
      }
      string += "\n"
    }
    print(string + "\n")
  }

}