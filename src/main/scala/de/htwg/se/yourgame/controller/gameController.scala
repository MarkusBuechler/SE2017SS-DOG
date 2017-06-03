package de.htwg.se.yourgame.controller

import java.awt.Color

import com.google.inject.Singleton
import de.htwg.se.yourgame.model._

import scala.collection.mutable.ListBuffer
import scala.reflect.internal.util.Statistics.Counter
import scala.swing.Publisher
import scala.swing.event.Event

/**
 * Created by margogo on 15.04.17.
 */

class UpdatePlayerLabels() extends Event
class UpdatePlayerCards() extends Event


@Singleton
class gameController() extends TGameController with Publisher {

  def initGame(): Unit = {
    initFields()
    initCards()
    initPlayer()
    applyFigToField()
  }

  def showGameStatus(): Unit = {
    printFields()
    printCardDecks()
    printCurrentPlayer()
  }

  def findNextField2(position: Int, valueOfCard : Int) : Unit =  {

    for (i <- 1 to valueOfCard) {
      val field = fieldList.apply(fieldList.indexWhere(_.id == position))
      var NextFields = new ListBuffer[Field]
      for (x <- field.successorIds) {
        NextFields += fieldList.apply(fieldList.indexWhere(_.id == x))
      }
      NextFields
    }
  }

  def playerActionPrep(cardFromDeckNumber: Int): Unit = {

    //    fieldController.movePosition(cardFromDeckNumber, figureNumber)
    val playedCard = cardDecks.apply(currentPlayer.playerId).cards.apply(cardFromDeckNumber - 1)
    val valueOfCard = playedCard.value
    var possibleField = new ListBuffer[Integer]
//    possibleField += findNextField(figureList.apply((currentFigNr)).position).head.id
//    for (x <- 0 to valueOfCard) {
//      possibleField += findNextField(figureList.apply(currentFigNr).position).head.id
////            updateFigField(findNextField(figureList.apply(figureNumber).position).head.id, figureList.apply(figureNumber))
//    }
    val a = findNextField(figureList(currentFigNr).position, valueOfCard)

//    print("Mögliches Felder sind " + a + "Willst du da hin ?")
//    val input = scala.io.StdIn.readLine()
//    val selectedField = fieldList.apply(fieldList.indexWhere(_.id == a.apply(input.toInt))).id
    val selectedField = fieldList.apply(fieldList.indexWhere(_.id == a.head)).id
    print(selectedField)
    updateFigField(fieldList.apply(selectedField).id, figureList.apply(currentFigNr))
    removeCard(playedCard)
    changeCurrentPlayer()
    publish(new UpdatePlayerCards)
  }

  def playerActionAfterDecision() : Unit = {

  }

  def applyFigToField(): Unit = {
    for (figure <- figureList) {
      val index = fieldList.indexWhere(_.id == figure.position)
      val bufferField = fieldList.apply(index).copy(figure = figure)
      fieldList.update(index, bufferField)
    }
  }

  def removeCard(card: Card) = {
    val cardInSet = cardDecks.apply(currentPlayer.playerId).cards.indexWhere(_.id == card.id)
    cardDecks.apply(currentPlayer.playerId).cards.remove(cardInSet)
    playedCards += card
  }

//  def test = {
//    val bufferFig = figureList.apply(0)
//    val bufferFigPos = figureList.apply(0).position
//
//    print(findNextField(bufferFigPos))
//  }
//
//  def test2 = {
//    updateFigField(findNextField(figureList.apply(0).position).head.id, figureList.apply(0))
//  }

  /** PlayerStuff **/
  def initPlayer(): Unit = {
    for (x <- 0 to 3) {
      val bufferPlayer = Player("Player " + (x + 1), x, isActive = false)
      playerList += bufferPlayer
    }
    playerList.update(0, Player("Player 1", 0, isActive = true))
    initFigures()
  }

  def initFigures(): Unit = {
    figureList.clear
    val bufferedSource = io.Source.fromFile("resources/Figures.csv")
    for (line <- bufferedSource.getLines()) {
      val Array(player, playerFigNumber, role, property, position,x,y) = line.split(";").map(_.trim())
      val bufferFig = Figure(playerList.apply(player.toInt), playerFigNumber.toInt, role, property, position.toInt, x.toInt, y.toInt, Color.BLACK)
      figureList += bufferFig
    }
    figureList.update(0, Figure(playerList.apply(0), 0, "defaultRole", "default", 70, 476, 467, Color.BLACK))
    currentFigNr = 0
    colorFigures()
  }

  def colorFigures() : Unit = {
    for (figur <- figureList) {
      val playerID = figur.player.playerId
      playerID match {
        case 0 => figureList.update(figureList.indexOf(figur),figur.copy(color = Color.BLUE))
        case 1 => figureList.update(figureList.indexOf(figur),figur.copy(color = Color.GREEN))
        case 2 => figureList.update(figureList.indexOf(figur),figur.copy(color = Color.YELLOW))
        case 3 => figureList.update(figureList.indexOf(figur),figur.copy(color = Color.RED))
      }
    }
  }

  def setPlayerName(inputNumber: Int, inputString: String): Unit = {

    var playerNo = 0
    inputNumber match {
      case 1 =>
        print("Player 1 wurde ausgewählt !\n"); playerNo = 0
      case 2 =>
        print("Player 2 wurde ausgewählt !\n"); playerNo = 1
      case 3 =>
        print("Player 3 wurde ausgewählt !\n"); playerNo = 2
      case 4 =>
        print("Player 4 wurde ausgewählt !\n"); playerNo = 3
      case _ => print("False Eingabe! Player 1 wurde ausgewählt !\n"); playerNo = 0
    }
    inputString match {
      case _ =>
        val insertNumber = playerNo + 1
        val currentPlayer = Player(inputString, insertNumber, isActive = false)
        print(inputString + " wurde eingeben und Player " + insertNumber + " zugewiesen ")
        playerList.update(playerNo, currentPlayer)
    }
    print(playerList.toString())
  }

  def printCurrentPlayer(): Unit = {
    print(currentPlayer.name + " ist am Zug")
  }

//  def printCurrentFigure(): Unit = {
//    print("Figur " + currentFig.playerFigNumber + " von " + currentFig.player.name + " ist am Zug")
//  }

  def changeCurrentPlayer() = {
    val lastPlayer = playerList.apply(currentPlayer.playerId).copy(isActive = false)
    val nextPlayerNumber = if (currentPlayer.playerId == 3) 0 else currentPlayer.playerId + 1

    playerList.update(lastPlayer.playerId, lastPlayer)

    val nextPlayer = playerList.apply(nextPlayerNumber).copy(isActive = true)
    playerList.update(nextPlayerNumber, nextPlayer)

    val currentPlayerIndex = playerList.indexWhere(_.isActive == true)

    currentPlayer = playerList.apply(currentPlayerIndex).copy(isActive = true)

//    currentFig = Figure(currentPlayer, 0, "BufferFig", "EmptyProp", 70, 0, 0, Color.BLACK)


    changeCurrentFigureNr()
    publish(new UpdatePlayerLabels)


  }
  //noinspection ScalaStyle
  def changeCurrentFigureNr() : Unit = {

    currentFigNr match {
      case 0 | 1 | 2 | 3 => currentFigNr = 4
      case 4 | 5 | 6 | 7 => currentFigNr = 8
      case 8 | 9 | 10 | 11 => currentFigNr = 12
      case 12 | 13 | 14 | 15 => currentFigNr = 0
    }

  }

  def updateFigPos(figure: Figure, newId: Int) = {
    val oldPos = figure.position
    val figNr = figure.playerFigNumber
    val field = fieldList.apply(newId)
    val bufferFig = figureList.apply(figureList.indexWhere(_.position == oldPos)).copy(position = newId, x = field.x, y = field.y)
    figureList.update(figNr, bufferFig)
  }

  //  def changeCurrentFigureToNextPlayer() = {
  //    val currentFigure = currentPlayer.playerId
  //
  //  }

  /** Field stuff **/

  def initFields(): Unit = {
    fieldList.clear
    val bufferedSource = io.Source.fromFile("resources/Fields.csv")
    for (line <- bufferedSource.getLines()) {
      val Array(id, property, color, figure, predecessorIds, successorIds, x , y) = line.split(";").map(_.trim())

      val intArraypredecessorId = predecessorIds.split(",").map(_.toInt)
      val intArraySucessorId = successorIds.split(",").map(_.toInt)
      val tempFigure = Figure(emptyPlayer, highNumber, "EmptyRole", "EmptyProp", 0, 0, 0, Color.BLACK)

      val bufferField = Field(id.toInt, property, color, tempFigure, intArraypredecessorId, intArraySucessorId, x.toInt, y.toInt)
      fieldList += bufferField
    }
  }

  def printFields(): Unit = {
    var string = "CurrentField:"
    for (x <- 0 until fieldList.size - 1) {
      if (x % 16 == 0) {
        string += "\n"
      }
      string += fieldList.apply(x).id + ": "
      val figInField = if (fieldList.apply(x).figure.playerFigNumber != highNumber) ", Figur " + fieldList.apply(x).figure.playerFigNumber else ""
      val playerInField = if (fieldList.apply(x).figure.player.name != null) "[" + fieldList.apply(x).figure.player.name + figInField + "] " else "[  ]"
      string += playerInField

      string += ", "
    }
    print(string + "\n")
    publish(new UpdatePlayerLabels)
  }

//  def findNextField(fieldId : Int, valueOfCard : Int): ListBuffer[Int] = {
//    var field = fieldList.apply(fieldList.indexWhere(_.id == fieldId))
//    var NextFields = new ListBuffer[Int]
//    for (x <- field.successorIds) {
//      var nextField = fieldList.apply(fieldList.indexWhere(_.id == x))
//      var choice = -1
//      for (i <- 1 to valueOfCard) {
//        choice = fieldList.apply(fieldList.indexWhere(_.id == x)).id
//        nextField = fieldList.apply(fieldList.indexWhere(_.id == x))
//      }
//      NextFields += choice
//    }
//    NextFields
//  }

  // TODO: Mehrere Wahlmöglichkeiten ermöglichen
  def findNextField(fieldId : Int, valueOfCard : Int): ListBuffer[Int] = {
    var field = fieldList.apply(fieldList.indexWhere(_.id == fieldId))
    var NextFields = new ListBuffer[Int]
    var choice = -1
    for (i <- 1 to valueOfCard) {
      for (x <- field.successorIds) {
        choice = fieldList.apply(fieldList.indexWhere(_.id == x)).id
        field = fieldList.apply(fieldList.indexWhere(_.id == x))
      }
    }
    NextFields += choice
    NextFields
  }

  def updateFigField(fieldId: Int, figure: Figure) = {
    // check noch einbauen
    val test1 = figure.position
    val test2 = fieldList.indexWhere(_.id == figure.position)
    val oldField = fieldList.apply(test2).copy(figure = emptyFig)
    val newField = fieldList.apply(fieldId).copy(figure = figure)
    updateFigPos(figure, newField.id)
    fieldList.update(test2, oldField)
    fieldList.update(fieldId, newField)
  }

  /** Card Stuff **/

  def initCards(): Unit = {
    cardList.clear
    playedCards.clear
    val bufferedSource = io.Source.fromFile("resources/CardsSmall.csv")
    for (line <- bufferedSource.getLines()) {
      val Array(id, color, description, value, property, isPlayed) = line.split(";").map(_.trim())
      val tempCard = Card(id.toInt, color, description, value.toInt, property, isPlayed.toBoolean)
      cardList += tempCard
    }
    shuffleCards()
    fillCardDeck()
  }

  //sample data
  val card1 = Card(1, "Gelb", "Zwei", 1, "Eigeschaft1", isPlayed = false)
  val card2 = Card(2, "Rot", "Zwei", 1, "Eigeschaft2", isPlayed = true)
  val card3 = Card(3, "Blau", "Zwei", 1, "Eigeschaft3", isPlayed = true)

  def shuffleCards(): Unit = {
    cardList = scala.util.Random.shuffle(cardList)
  }

  def fillCardDeck(): Unit = {

    cardDecks.clear
    for (a <- 1 to 4) {
      var cardBuffer = new ListBuffer[Card]
      for (i <- 1 to decksize) {
        cardBuffer += cardList.head
        cardList -= cardList.head
      }
      //      val cardBufferList = cardBuffer.toList
      val filledCardDeck = CardDeck(a, decksize, cardBuffer)
      cardDecks += filledCardDeck
    }

  }

  def printCardDecks(): Unit = {
    var string = ""
    for (x <- 0 until cardDecks.size) {
      string += "Player [" + cardDecks.apply(x).cards.size + "] "
      string += cardDecks.apply(x).PlayerId + ": "
      for (y <- 0 until cardDecks.apply(x).cards.size) {
        string += cardDecks.apply(x).cards.apply(y).description.toString + " ,"
      }
      string += "\n"
    }
    print(string + "\n")
    print("Gespielte Karten :" + playedCards + "\n")
    publish(new UpdatePlayerLabels)
    print("Current Player:" + currentPlayer + "\n")
    print("Current Figur :" + figureList.apply(currentFigNr) + "\n")

  }

  def refresh(): Unit = {
  }

  def quitGame(): Unit = {
    sys.exit()
  }

}
