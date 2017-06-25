package de.htwg.se.dog2.controller

import com.google.inject.{Guice, Singleton}
import de.htwg.se.dog2.DependencyModule
import de.htwg.se.dog2.model._
import de.htwg.se.dog2.util.TFileIO

import scala.collection.mutable.ListBuffer
import scala.swing.Publisher
import scala.swing.event.Event
import org.apache.logging.log4j.{LogManager, Logger}
import net.codingwell.scalaguice.InjectorExtensions._
import scala.io.Source


/**
 * Created by margogo on 15.04.17.
 * GameController
 */

class UpdatePlayerLabels() extends Event
class UpdatePlayerCards() extends Event
class UpdateToRepaint() extends Event
class newPlayerCards() extends Event
class CardNotInRange() extends Event

@Singleton
class gameController() extends TGameController with Publisher {

  val injector = Guice.createInjector(new DependencyModule)
  val fileIo = injector.instance[TFileIO]

  var logger: Logger = LogManager.getLogger(gameController.this)

  def initGame(): Unit = {
    logger.debug("Initializing game...")
    initFields()
    initCards()
    initPlayer()
    applyFigToField()
    logger.debug("Finished initializing game.")
  }

  def showGameStatus(): Unit = {
    printFields()
    printCardDecks()
    printCurrentPlayer()
  }

  def playerAction(cardFromDeckNumber: Int): Unit = {
    logger.debug("Player is actioning...")

    if (cardFromDeckNumber > cardDecks.apply(currentPlayer.playerId).cards.size || cardFromDeckNumber <= 0) {
      logger.debug("Card is not in range!")
      publish(new CardNotInRange)
    } else {
      val playedCard = cardDecks.apply(currentPlayer.playerId).cards.apply(cardFromDeckNumber - 1)
      val valueOfCard = playedCard.value
      val possibleField = findNextField(figureList(currentFigNr).position, valueOfCard)
      val selectedField = fieldList.apply(fieldList.indexWhere(_.id == possibleField.head)).id

      logger.info("Selected field was :" + selectedField)
      updateFigField(fieldList.apply(selectedField).id, figureList.apply(currentFigNr))
      removeCard(playedCard)
      changeCurrentPlayer()
      publish(new UpdatePlayerCards)
      logger.debug("Finished player action.")
      checkStatus()
    }
  }

  def checkStatus(): Unit = {
    // check if someone won the game

    // check if cards needs to be shuffled
    // Beste abfrage
    if (cardDecks.head.cards.isEmpty && cardDecks.apply(1).cards.isEmpty && cardDecks.apply(2).cards.isEmpty && cardDecks.apply(3).cards.isEmpty) {
      updateDeckSize()
      initCards()
      publish(new newPlayerCards)
    }

  }

  def applyFigToField(): Unit = {
    logger.debug("Applying figures to field...")
    for (figure <- figureList) {
      val index = fieldList.indexWhere(_.id == figure.position)
      val bufferField = fieldList.apply(index).copy(figure = figure)
      fieldList.update(index, bufferField)
    }
    logger.debug("Finished applying figures to field.")
  }

  def removeCard(card: Card): Unit = {
    logger.debug("Removing card...")
    val cardInSet = cardDecks.apply(currentPlayer.playerId).cards.indexWhere(_.id == card.id)
    cardDecks.apply(currentPlayer.playerId).cards.remove(cardInSet)
    playedCards += card
    logger.debug("Finished removing card.")
  }

  /** PlayerStuff **/
  def initPlayer(): Unit = {
    logger.debug("Initializing player...")
    for (x <- 0 to 3) {
      val bufferPlayer = Player("Player " + (x + 1), x, isActive = false)
      playerList += bufferPlayer
    }
    playerList.update(0, Player("Player 1", 0, isActive = true))
    initFigures()
    logger.debug("Finished initializing player.")
  }

  def initFigures(): Unit = {
    logger.debug("Initializing figures...")
    figureList.clear
    val bufferedSource = Source.fromURL(getClass.getResource("/Figures.csv"))
    for (line <- bufferedSource.getLines()) {
      val Array(player, playerFigNumber, role, property, position, x, y) = line.split(";").map(_.trim())
      val bufferFig = Figure(playerList.apply(player.toInt), playerFigNumber.toInt, role, property, position.toInt, x.toInt, y.toInt, "Black" /*Color.BLACK*/ )
      figureList += bufferFig
    }
    figureList.update(0, Figure(playerList.head, 0, "defaultRole", "default", 70, 476, 467, "Black" /*Color.BLACK*/ ))
    currentFigNr = 0
    colorFigures()
    logger.debug("Finished unitializing figures.")
  }

  def colorFigures(): Unit = {
    logger.debug("Coloring figures...")
    for (figure <- figureList) {
      val playerID = figure.player.playerId
      playerID match {
        case 0 => figureList.update(figureList.indexOf(figure), figure.copy(color = "Blue" /*Color.BLUE)*/ ))
        case 1 => figureList.update(figureList.indexOf(figure), figure.copy(color = "Green" /*Color.GREEN)*/ ))
        case 2 => figureList.update(figureList.indexOf(figure), figure.copy(color = "Yellow" /*Color.YELLOW)*/ ))
        case 3 => figureList.update(figureList.indexOf(figure), figure.copy(color = "Red" /*Color.RED)*/ ))
      }
    }
    logger.debug("Finished coloring figures.")
  }

  def printCurrentPlayer(): Unit = {
    logger.info(currentPlayer.name + " ist am Zug")
  }

  def changeCurrentPlayer(): Unit = {
    logger.debug("Changing current Player...")
    val lastPlayer = playerList.apply(currentPlayer.playerId).copy(isActive = false)
    val nextPlayerNumber = if (currentPlayer.playerId == 3) 0 else currentPlayer.playerId + 1

    playerList.update(lastPlayer.playerId, lastPlayer)

    val nextPlayer = playerList.apply(nextPlayerNumber).copy(isActive = true)
    playerList.update(nextPlayerNumber, nextPlayer)

    val currentPlayerIndex = playerList indexWhere (_.isActive == true)

    currentPlayer = playerList.apply(currentPlayerIndex).copy(isActive = true)

    changeCurrentFigure()
    publish(new UpdatePlayerLabels)
    logger.debug("Finished changing current Player.")

  }
  //noinspection ScalaStyle
  def changeCurrentFigure(): Unit = {
    logger.debug("Changing current figure...")
    currentFigNr match {
      case 0 | 1 | 2 | 3 => currentFigNr = 4
      case 4 | 5 | 6 | 7 => currentFigNr = 8
      case 8 | 9 | 10 | 11 => currentFigNr = 12
      case 12 | 13 | 14 | 15 => currentFigNr = 0
    }
    publish(new UpdateToRepaint)
    logger.debug("Finished changing current figure.")
  }

  def changeCurrentFigureNr(): Unit = {
    logger.debug("Changing current figure nr...")
    val buffer = currentFigNr % 4
    buffer match {
      case 0 | 1 | 2 => currentFigNr += 1
      case 3 => currentFigNr -= 3
    }
    publish(new UpdateToRepaint)
    logger.debug("Finished changing current figure nr.")
  }

  def updateFigPos(figure: Figure, newId: Int): Unit = {
    logger.debug("Updating figure position.")
    val oldPos = figure.position
    val figNr = figure.playerFigNumber
    val field = fieldList.apply(newId)
    val bufferFig = figureList.apply(figureList.indexWhere(_.position == oldPos)).copy(position = newId, x = field.x, y = field.y)
    figureList.update(figNr, bufferFig)
    logger.debug("Finished updating figure position.")
  }

  /** Field stuff **/

  def initFields(): Unit = {
    logger.debug("Initializing fields...")
    fieldList.clear
    val bufferedSource = Source.fromURL(getClass.getResource("/Fields.csv"))
    for (line <- bufferedSource.getLines()) {
      val Array(id, property, color, predecessorIds, successorIds, x, y) = line.split(";").map(_.trim())

      val intArraypredecessorId = predecessorIds.split(",").map(_.toInt)
      val intArraySucessorId = successorIds.split(",").map(_.toInt)
      val tempFigure = Figure(emptyPlayer, highNumber, "EmptyRole", "EmptyProp", 0, 0, 0, "Black" /*Color.getColor(Color.BLACK)*/ )

      val bufferField = Field(id.toInt, property, color, tempFigure, intArraypredecessorId, intArraySucessorId, x.toInt, y.toInt)
      fieldList += bufferField
    }
    logger.debug("Finished initializing fields")
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
    logger.info(string + "\n")
    publish(new UpdatePlayerLabels)
  }

  // TODO: Mehrere Wahlmöglichkeiten ermöglichen
  def findNextField(fieldId: Int, valueOfCard: Int): ListBuffer[Int] = {
    logger.debug("Searching next field...")
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
    logger.debug("Finished searching and returning next field.")
    NextFields
  }

  def updateFigField(fieldId: Int, figure: Figure): Unit = {
    // check noch einbauen
    logger.debug("Updating figure on field...")
    val test2 = fieldList.indexWhere(_.id == figure.position)
    val oldField = fieldList.apply(test2).copy(figure = emptyFig)
    val newField = fieldList.apply(fieldId).copy(figure = figure)
    updateFigPos(figure, newField.id)
    fieldList.update(test2, oldField)
    fieldList.update(fieldId, newField)
    logger.debug("Finished updating figure on field.")

  }

  /** Card Stuff **/

  def initCards(): Unit = {
    logger.debug("Initializing cards...")
    cardList.clear
    playedCards.clear
    val bufferedSource = Source.fromURL(getClass.getResource("/CardsSmall.csv"))
    for (line <- bufferedSource.getLines()) {
      val Array(id, color, description, value, property, isPlayed) = line.split(";").map(_.trim())
      val tempCard = Card(id.toInt, color, description, value.toInt, property, isPlayed.toBoolean)
      cardList += tempCard
    }
    shuffleCards()
    fillCardDeck()
    logger.debug("Finished initializing cards.")
  }

  def shuffleCards(): Unit = {
    logger.debug("Shuffling cards...")
    cardList = scala.util.Random.shuffle(cardList)
    logger.debug("Finished shuffling cards.")

  }

  def fillCardDeck(): Unit = {
    logger.debug("Filling card deck...")
    cardDecks.clear
    for (a <- 1 to 4) {
      var cardBuffer = new ListBuffer[Card]
      for (i <- 1 to decksize) {
        cardBuffer += cardList.head
        cardList -= cardList.head
      }
      val filledCardDeck = CardDeck(a, decksize, cardBuffer)
      cardDecks += filledCardDeck
    }
    logger.debug("Finished filling card deck.")
  }

  def printCardDecks(): Unit = {
    var string = "CardDeck:\n"
    for (x <- cardDecks.indices) {
      string += "Player [" + cardDecks.apply(x).cards.size + "] "
      string += cardDecks.apply(x).PlayerId + ": "
      for (y <- cardDecks.apply(x).cards.indices) {
        string += cardDecks.apply(x).cards.apply(y).description.toString + " ,"
      }
      string += "\n"
    }
    logger.info(string + "\n")
    logger.info("Played Cards :" + playedCards + "\n")
    publish(new UpdatePlayerLabels)
    logger.info("Current Player:" + currentPlayer + "\n")
    logger.info("Current Figure :" + figureList.apply(currentFigNr) + "\n")

  }

  def quitGame(): Unit = {
    logger.info("Exit game...")
    sys.exit()
  }

  def saveGame(): Unit = {
    val game = new Game(figureList, playerList, fieldList, cardList, cardDecks, playedCards, currentPlayer, currentFig, currentFigNr, decksize)
    fileIo.save(game)
  }

  //noinspection ScalaStyle
  override def updateDeckSize(): Unit = {
    decksize match {
      case 7 | 6 | 5 => decksize -= 1
      case 4 => decksize = 7
      case _ =>
        decksize = 7
        logger.debug("deckSize was not between 4 and 7 ! ")
    }
  }

  def newGame(): Unit = {
    logger.info("New game...")
    decksize = initDeckSize
    figureList.clear()
    initGame()
    currentPlayer = playerList.apply(0)
    currentFig = figureList.apply(0)
    currentFigNr = 0
    publish(new UpdatePlayerCards)
    publish(new UpdateToRepaint)
  }
}
