package de.htwg.se.yourgame.controller

import de.htwg.se.yourgame.model._

import scala.collection.mutable.ListBuffer

/**
  * Created by margogo on 15.04.17.
  */
class gameController () {

  /** Val and Vars **/

  /** Lists **/
  var figureList = new ListBuffer[Figure]
  var playerList = new ListBuffer[Player]
  var fieldList = new ListBuffer[Field]
  var cardList = new ListBuffer[Card]
  var cardDecks = new ListBuffer[CardDeck]
  var playedCards = new ListBuffer[Card]

  /** Vars **/
  var currentPlayer = Player("Player 1",0,isActive = true)
  var currentFig =  Figure(player = emptyPlayer, 0,"BufferFig", "EmptyProp", 0)
  var decksize = initDeckSize

  /** Buffer Vals **/
  val highNumber = 200
  val initDeckSize = 7
  val emptyPlayer = Player("", highNumber, isActive = false)
  val emptyFig = Figure(emptyPlayer, highNumber, "","",highNumber)



  def initGame(): Unit = {
    initFields()
    initCards()
    initPlayer()
    applyFigToField()
    print(playerList)
  }

  def showGameStatus(): Unit = {
    printFields()
    printCardDecks()
    printCurrentPlayer()
  }

  def playerAction(cardFromDeckNumber: Int, figureNumber: Int): Unit = {
//    fieldController.movePosition(cardFromDeckNumber, figureNumber)
    val playedCard = cardDecks.apply(currentPlayer.playerId).cards.apply(cardFromDeckNumber - 1)
    removeCard(playedCard)
    changeCurrentPlayer()
  }

  def applyFigToField(): Unit = {
        for (figure <-  figureList) {
          val index = fieldList.indexWhere (_.id == figure.position)
          val bufferField = fieldList.apply(index).copy(figure = figure)
          fieldList.update(index, bufferField)
        }
  }

  def removeCard(card: Card) = {
    val cardInSet = cardDecks.apply(currentPlayer.playerId).cards.indexWhere(_.id == card.id)
    cardDecks.apply(currentPlayer.playerId).cards.remove(cardInSet)
    playedCards += card
  }

  def test = {
    val bufferFig = figureList.apply(0)
    val bufferFigPos = figureList.apply(0).position

    print(findNextField(bufferFigPos))
  }

  def test2 = {
    updateFigField(findNextField(figureList.apply(0).position).head.id,figureList.apply(0))
  }



  /** PlayerStuff **/
  def initPlayer(): Unit = {
    for (x <- 0 to 3) {
      val bufferPlayer = Player("Player " + (x + 1) , x, isActive = false)
      playerList += bufferPlayer
    }
    playerList.update(0, Player("Player 1",0, isActive = true))
    initFigures()
  }

  def initFigures(): Unit = {
    figureList.clear
    val bufferedSource = io.Source.fromFile("resources/Figures.csv")
    for (line <- bufferedSource.getLines()) {
      val Array(player, playerFigNumber, role, property, position) = line.split(";").map(_.trim())
      val bufferFig = Figure(playerList.apply(player.toInt), playerFigNumber.toInt, role, property, position.toInt)
      figureList += bufferFig
    }
    figureList.update(0, Figure(playerList.apply(0),0,"defaultRole","default",70))
  }

  def setPlayerName(inputNumber: Int, inputString: String): Unit = {

    var playerNo = 0
    inputNumber match {
      case 1 => print("Player 1 wurde ausgewählt !\n"); playerNo = 0
      case 2 => print("Player 2 wurde ausgewählt !\n"); playerNo = 1
      case 3 => print("Player 3 wurde ausgewählt !\n"); playerNo = 2
      case 4 => print("Player 4 wurde ausgewählt !\n"); playerNo = 3
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

  def changeCurrentPlayer() = {
    val lastPlayer = playerList.apply(currentPlayer.playerId).copy(isActive = false)
    val nextPlayerNumber = if (currentPlayer.playerId == 3) 0 else currentPlayer.playerId + 1

    playerList.update(lastPlayer.playerId, lastPlayer)

    val nextPlayer = playerList.apply(nextPlayerNumber).copy(isActive = true)
    playerList.update(nextPlayerNumber, nextPlayer)

    val currentPlayerIndex = playerList.indexWhere(_.isActive == true)
    currentPlayer = playerList.apply(currentPlayerIndex).copy(isActive = true)
  }

  def updateFigPos(figure : Figure, newId : Int) = {
    print(figureList)
    val oldPos = figure.position
    val figNr = figure.playerFigNumber
    val bufferFig = figureList.apply(figureList.indexWhere(_.position == oldPos)).copy(position = newId)
    figureList.update(figNr, bufferFig)
  }

  /** Field stuff **/

  def initFields(): Unit = {
    fieldList.clear
    val bufferedSource = io.Source.fromFile("resources/Fields.csv")
    for (line <- bufferedSource.getLines()) {
      val Array(id, property, color, figure, predecessorIds, successorIds) = line.split(";").map(_.trim())

      val intArraypredecessorId = predecessorIds.split(",").map(_.toInt)
      val intArraySucessorId = successorIds.split(",").map(_.toInt)
      val tempFigure = Figure(emptyPlayer, 100, "EmptyRole", "EmptyProp", 0)

      val bufferField = Field(id.toInt, property, color, tempFigure, intArraypredecessorId, intArraySucessorId)
      fieldList += bufferField
    }
  }

  def printFields(): Unit = {
    var string = "CurrentField:"
    for (x <-  0 until fieldList.size-1) {
      if (x % 16 == 0) {
        string += "\n"
      }
      string += fieldList.apply(x).id + ": "
      val figInField = if (fieldList.apply(x).figure.playerFigNumber != 100) ", Figur " + fieldList.apply(x).figure.playerFigNumber else ""
      val playerInField = if(fieldList.apply(x).figure.player.name != null)  "[" + fieldList.apply(x).figure.player.name + figInField + "] "  else "[  ]"
      string += playerInField

      string += ", "
    }
    print(string + "\n")
  }


  def movePosition(cardFromDeckNumber: Int, figureNumber: Int): Unit = {

    // größeres problem
    // inject lädt leere listen

    //    print(cardController.cardList)
    //    val currentPlayer = playerController2.currentPlayer
    //    print(currentPlayer)
    //    val oldPosition = playerController2.figureListBuffer.apply(figureNumber)
    //    print(oldPosition)
    //    fieldList.update(position, ) update old field

    //    val oldPosition = 1
    //    val oldCardId = cardController.cardDecks.apply(playerController.currentPlayer.playerId).cards.apply(cardFromDeckNumber).id
    //    val cardValue = cardController.cardDecks.apply(player.playerId).cards.apply(cardFromDeckNumber).value
    //
    //    // eventuell fehler bei lücken der ids
    //    val newBufferField = fieldList.apply(oldPosition).copy(isUsed = true, id = cardValue + oldCardId)
    //    val oldBufferField = fieldList.apply(oldPosition).copy()
    ////
    //    fieldList.updated(oldPosition, oldBufferField)
    //    fieldList.updated(oldPosition + cardValue, newBufferField)
  }

  //  /**
  //    * check vllt später einbauen
  //    * @param cardFromDeckNumber
  //    * @return
  //    */
  //  def movePosition(cardFromDeckNumber: Int, figureNumber: Int): ListBuffer[Field] = {
  //    val currentPlayer = playerController.currentPlayer
  //    val oldPosition = playerController.figureListBuffer.apply(figureNumber).position
  //    print(oldPosition)
  //    //    fieldList.update(position, ) update old field
  //
  //    //    val oldPosition = 1
  //    //    val oldCardId = cardController.cardDecks.apply(playerController.currentPlayer.playerId).cards.apply(cardFromDeckNumber).id
  //    //    val cardValue = cardController.cardDecks.apply(player.playerId).cards.apply(cardFromDeckNumber).value
  //    //
  //    //    // eventuell fehler bei lücken der ids
  //    //    val newBufferField = fieldList.apply(oldPosition).copy(isUsed = true, id = cardValue + oldCardId)
  //    val oldBufferField = fieldList.apply(oldPosition).copy()
  //    //
  //    fieldList.updated(oldPosition, oldBufferField)
  //    //    fieldList.updated(oldPosition + cardValue, newBufferField)
  //  }

  def findNextField(fieldId: Int): ListBuffer[Field] = {
    val field = fieldList.apply(fieldList.indexWhere(_.id == fieldId))
    var NextFields = new ListBuffer[Field]
    for (x <- field.successorIds)
    {
      NextFields += fieldList.apply(fieldList.indexWhere(_.id == x))
    }
    NextFields
  }

  def updateFigField(fieldId: Int, figure: Figure) = {
    // check noch einbauen
    val test1 = figure.position // 71
    val test2 = fieldList.indexWhere(_.id == figure.position) //65
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
  val card2 = Card(2, "Rot", "Zwei",1, "Eigeschaft2", isPlayed = true)
  val card3 = Card(3, "Blau", "Zwei",1, "Eigeschaft3", isPlayed = true)


  def shuffleCards(): Unit = {
    cardList = scala.util.Random.shuffle(cardList)
  }

  def fillCardDeck(): Unit = {

    cardDecks.clear
    for (a <- 1 to 4) {
      var cardBuffer = new ListBuffer[Card]
      for (i <- 1 to decksize) {
        cardBuffer+=cardList.head
        cardList-=cardList.head
      }
      //      val cardBufferList = cardBuffer.toList
      val filledCardDeck = CardDeck(a,decksize,cardBuffer)
      cardDecks+=filledCardDeck
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
  }



}
