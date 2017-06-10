package de.htwg.se.dog2.controller

import java.awt.Color

import de.htwg.se.dog2.model._

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
  val emptyFig = Figure(emptyPlayer, highNumber, "", "", highNumber, 0, 0, Color.BLACK)

  /** Vars **/
  var currentPlayer = Player("Player 1", 0, isActive = true)
  var currentFigNr = 0
  var currentFig = Figure(currentPlayer, 0, "BufferFig", "EmptyProp", 70, 0, 0, Color.BLACK)
  //  var currentFigNr =
  var decksize = initDeckSize

  def quitGame(): Unit
  def initGame(): Unit
  def initPlayer(): Unit
  def initFigures(): Unit
  def colorFigures(): Unit
  def setPlayerName(inputNumber: Int, inputString: String): Unit

  def showGameStatus(): Unit
  def applyFigToField(): Unit
  def removeCard(card: Card): Unit

}