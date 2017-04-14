package de.htwg.se.yourgame.controller

import java.util

import de.htwg.se.yourgame.model.Card

import scala.collection.mutable.ListBuffer

/**
  * Created by margogo on 02.04.17.
  * code will be added in the future
  *
  * Card controller:
  * todo:
  * - init/add cards
  * - shuffle cards
  * - prepare decks
  * - tests
  *
  * Add other controllers
  * Note: A normal list in scala is NOT mutable, better use a listbuffer and convert it to a list when you need
  * or a mutable list (linear seq or other)
  */
  class cardController {

  var cardList = new ListBuffer[Card]


  def initCards = {
    val bufferedSource = io.Source.fromFile("resources/CardsSmall.csv")
    for (line <- bufferedSource.getLines()) {
      val Array(id, color, description, value, property, isPlayed) = line.split(";").map(_.trim())
      val tempCard = Card(id.toInt, color, description, value.toInt, property, isPlayed.toBoolean);
      cardList += tempCard
    }

    print(cardList.size)

  }


  def readCards = {

  //  for {
    //  line <- Source.fromFile("CardsSmall.csv").getLines().drop(1).toVector
     // values = line.split(";").map(_.trim)
    //} yield cardList(values(0))

  }




  //sample data
  val card1 = Card(1, "Gelb", "Zwei", 1, "Eigeschaft1", false)
  val card2 = Card(2, "Rot", "Zwei",1, "Eigeschaft2", true)
  val card3 = Card(3, "Blau", "Zwei",1, "Eigeschaft3", true)

  //var cardList = card1 ::


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



}