package de.htwg.se.dog2.util

import de.htwg.se.dog2.model.Game

/**
 * Created by margogo on 21.06.17.
 */
trait TFileIO {

  def save(game: Game): Unit
  def load(): Unit

}
