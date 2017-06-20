package de.htwg.se.dog2.controller

import de.htwg.se.dog2.model._

import scala.collection.mutable.ListBuffer
import scala.swing.Publisher

/**
 * Created by margogo on 15.05.17.
 * Interface for gameController
 */
trait TGameController extends Publisher {

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
  val emptyFig = Figure(emptyPlayer, highNumber, "", "", highNumber, 0, 0, "Black" /*Color.BLACK*/ )

  /** Vars **/
  var currentPlayer = Player("Player 1", 0, isActive = true)
  var currentFigNr = 0
  var currentFig = Figure(currentPlayer, 0, "BufferFig", "EmptyProp", 70, 0, 0, "Black" /*Color.BLACK*/ )
  //  var currentFigNr =
  var decksize: Int = initDeckSize

  /** Val and Vars **/

  /** Lists **/
  var R_figureList = new ListBuffer[Figure]
  var R_playerList = new ListBuffer[Player]
  var R_fieldList = new ListBuffer[Field]
  var R_cardList = new ListBuffer[Card]
  var R_cardDecks = new ListBuffer[CardDeck]
  var R_playedCards = new ListBuffer[Card]

  /** Vars **/
  var R_currentPlayer: Player = currentPlayer
  var R_currentFigNr = 0
  var R_currentFig: Figure = currentFig
  //  var currentFigNr =
  var R_decksize: Int = initDeckSize

  def quitGame(): Unit
  def initGame(): Unit
  def initPlayer(): Unit
  def initFigures(): Unit
  def colorFigures(): Unit
  def showGameStatus(): Unit
  def applyFigToField(): Unit
  def removeCard(card: Card): Unit
  def updateDeckSize(): Unit
  def playerAction(int: Int): Unit

}
