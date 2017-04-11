package de.htwg.se.yourgame.controller

import java.util

import de.htwg.se.yourgame.model.Card

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
  * todo: read from resource
  */
class cardController {

  val card1 = Card(1, "Gelb", 1, "Eigeschaft1", false)
  val card2 = Card(2, "Rot", 1, "Eigeschaft2", true)
  val card3 = Card(3, "Blau", 1, "Eigeschaft3", true)
  val card4 = Card(4, "Lila", 1, "Eigeschafts", false)
  val card5 = Card(5, "Rot", 2, "Eigescasdashaft2", true)
  val card6 = Card(6, "Blau", 3, "Eigeasdasschaft3", true)
  val card7 = Card(7, "Gelb", 4, "Eigesasdchaft1", false)
  val card8 = Card(8, "Rot", 32, "3213", true)
  val card9 = Card(9, "Blau", 23, "Eigescwerwehaft3", true)
  val card10 = Card(10, "Schwarz", 111, "ewqee", false)
  val card11 = Card(11, "Schwarz", 1112, "eqwe", true)
  val card12 = Card(12, "Blau", 1213, "qwe", true)

  var cardList = card1 :: card2 :: card3 :: card4 :: card5 :: card6 :: card7 :: card8 :: card9 :: card10 :: card11 :: card12 :: Nil


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