package de.htwg.se.dog2.model

import scala.collection.mutable.ListBuffer

/**
  * Created by margogo on 21.06.17.
  */
case class Game (figureList : ListBuffer[Figure], playerList : ListBuffer[Player], fieldList : ListBuffer[Field]
                 , cardList : ListBuffer[Card], cardDeck: ListBuffer[CardDeck], playedCards: ListBuffer[Card], currentPlayer : Player
                 , currentFigure: Figure , currentFigNr : Int , deckSize : Int){

}
