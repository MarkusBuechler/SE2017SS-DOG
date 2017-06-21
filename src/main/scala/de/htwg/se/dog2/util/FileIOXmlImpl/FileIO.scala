package de.htwg.se.dog2.util.FileIOXmlImpl

import java.io.{BufferedWriter, File, FileWriter}

import de.htwg.se.dog2.model.Game
import de.htwg.se.dog2.util.TFileIO
import org.apache.logging.log4j.{LogManager, Logger}

/**
  * Created by margogo on 21.06.17.
  */
class FileIO extends TFileIO {

  var logger: Logger = LogManager.getLogger(FileIO.this)

  def save(game: Game): Unit = {
    logger.debug("Saving game data in XML...")
    val save = this.toXml(game: Game)
    val file = new File("savedGame.xml")
    val bufferedWriter = new BufferedWriter(new FileWriter(file))
    bufferedWriter.write(fromXml(save))
    bufferedWriter.close()
    logger.debug("Finished saving game data.")
  }

  def toXml(game : Game): scala.xml.Node = {
    <game>
      <figureList>{ game.figureList }</figureList>
      <playerList>{ game.playerList }</playerList>
      <fieldList>{ game.fieldList }</fieldList>
      <cardList>{ game.cardList }</cardList>
      <cardDecks>{ game.cardDeck }</cardDecks>
      <playedCards>{ game.playedCards }</playedCards>
      <currentPlayer>{game.currentPlayer }</currentPlayer>
      <currentFigure>{game.currentFigure}</currentFigure>
      <currentFigNr>{game.currentFigNr}</currentFigNr>
      <deckSize>{ game.deckSize }</deckSize>
    </game>
  }

  def fromXml(node: scala.xml.Node): String = {
    node.text
  }

  override def load(): Unit = {

  }

}
