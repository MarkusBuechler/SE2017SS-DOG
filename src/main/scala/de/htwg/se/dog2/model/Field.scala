package de.htwg.se.dog2.model

/**
 * Created by margogo on 14.04.17.
 */
case class Field(id: Int, property: String, color: String, figure: Figure, predecessorIds: Array[Int], successorIds: Array[Int], x: Int, y: Int) {
}
