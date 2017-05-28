package de.htwg.se.yourgame.controller

import de.htwg.se.yourgame.model._

import scala.collection.mutable.ListBuffer
import scala.swing.Publisher
import scala.swing.event.Event

/**
 * Created by margogo on 15.05.17.
 */
trait TGameController extends Publisher {

  // change current fig

  /** Val and Vars **/

  /** Lists **/
  var figureList = new ListBuffer[Figure]
  var playerList = new ListBuffer[Player]
  var fieldList = new ListBuffer[Field]
  var cardList = new ListBuffer[Card]
  var cardDecks = new ListBuffer[CardDeck]
  var playedCards = new ListBuffer[Card]

  /** Buffer Vals **/
  val highNumber = 200
  val initDeckSize = 7
  val emptyPlayer = Player("", highNumber, isActive = false)
  val emptyFig = Figure(emptyPlayer, highNumber, "", "", highNumber)

  /** Vars **/
  var currentPlayer = Player("Player 1", 0, isActive = true)
  var currentFig = Figure(currentPlayer, 0, "BufferFig", "EmptyProp", 70)
  //  var currentFigNr =
  var decksize = initDeckSize

  def quitGame(): Unit
  def initGame(): Unit

}
