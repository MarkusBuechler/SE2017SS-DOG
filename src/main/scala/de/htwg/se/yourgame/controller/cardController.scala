package de.htwg.se.yourgame.controller

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
  */
class cardController {

  val card1 = Card(1, "Gelb", 1, "Eigeschaft1", false);
  val card2 = Card(2, "Rot", 1, "Eigeschaft2", true);
  val card3 = Card(3, "Blau", 1, "Eigeschaft3", true);

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

}