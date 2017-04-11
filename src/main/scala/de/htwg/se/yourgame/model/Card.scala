package de.htwg.se.yourgame.model

/**
  * Created by margogo on 11.04.17.
  */


case class Card(val id: Integer, val color: String, val value: Integer, val property: String, val isPlayed: Boolean) {

  def this (id: Int) = this(id, "", 0, "", false)

}
