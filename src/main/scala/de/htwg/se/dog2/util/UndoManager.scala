package de.htwg.se.dog2.util

import org.apache.logging.log4j.LogManager

/**
 * Created by margogo on 15.06.17.
 */
class UndoManager {

  var logger = LogManager.getLogger(UndoManager.this)

  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  def execute(command: Command): Unit = {
    logger.debug("Executing Command...")
    undoStack = command :: undoStack
    redoStack = Nil
    command.execute()
    logger.debug("Finished executing command.")

  }

  def undo(): Boolean = {
    logger.debug("Executing Undo")

    if (undoStack.isEmpty) return false

    val head :: stack = undoStack
    head.undo()
    undoStack = stack
    redoStack = head :: redoStack
    logger.debug("Finished command undo.")
    true
  }

  def redo(): Boolean = {
    logger.debug("Executing redo")
    if (redoStack.isEmpty) return false

    val head :: stack = redoStack
    head.redo()
    redoStack = stack
    undoStack = head :: undoStack
    logger.debug("Finished executing Command")
    true
  }

}
