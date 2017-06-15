package de.htwg.se.dog2.util

/**
 * Created by margogo on 15.06.17.
 */
trait Command {

  def execute(): Unit

  def undo(): Unit

  def redo(): Unit

}
