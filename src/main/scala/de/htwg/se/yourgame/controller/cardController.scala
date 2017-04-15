package de.htwg.se.yourgame.controller

import de.htwg.se.yourgame.model.{Card, CardDeck}

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
  val playerList = List (1,2,3,4);
  var numberOfPlayers = 4
  var decksize = 7


  def initCards = {
    cardList.clear
    val bufferedSource = io.Source.fromFile("resources/CardsSmall.csv")
    for (line <- bufferedSource.getLines()) {
      val Array(id, color, description, value, property, isPlayed) = line.split(";").map(_.trim())
      val tempCard = Card(id.toInt, color, description, value.toInt, property, isPlayed.toBoolean);
      cardList += tempCard
    }
    shuffleCards
    fillCardDeck
  }

  //sample data
  val card1 = Card(1, "Gelb", "Zwei", 1, "Eigeschaft1", false)
  val card2 = Card(2, "Rot", "Zwei",1, "Eigeschaft2", true)
  val card3 = Card(3, "Blau", "Zwei",1, "Eigeschaft3", true)


  // Card after method: is the return type ...
  def getCardOne: Card = {
    card1
  }
  def getCardTwo: Card = {
    card2
  }
  def getCardThree: Card = {
    card3
  }

  def shuffleCards = {
    cardList = scala.util.Random.shuffle(cardList)
  }

  def fillCardDeck = {

    print("CardList: " + cardList.size + "\n");
    cardDecks.clear
    for (a <- playerList) {
      var cardBuffer = new ListBuffer[Card]
      for (i <- 1 to decksize) {
        cardBuffer+=cardList.head
        cardList-=cardList.head
      }
      val cardBufferList = cardBuffer.toList
      val filledCardDeck = new CardDeck(a,decksize,cardBufferList)
      cardDecks+=filledCardDeck
    }

    print("CardList: " + cardList.size + "\n");
    print("CardDeck: " + cardDecks.size + "\n");

    print("Deck 1: " + cardDecks.apply(0).cards.size + "\n");
    print("Deck 2: " + cardDecks.apply(1).cards.size + "\n");
    print("Deck 3: " + cardDecks.apply(2).cards.size + "\n");
    print("Deck 4: " + cardDecks.apply(3).cards.size + "\n");

  }



}