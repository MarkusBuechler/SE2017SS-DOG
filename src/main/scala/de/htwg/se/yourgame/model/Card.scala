package de.htwg.se.yourgame.model

/**
  * Created by margogo on 11.04.17.
  */


case class Card(id: Integer, color: String, description: String, value: Integer, property: String, isPlayed: Boolean) {

  def this (id: Int) = this(id, "", "", 0, "", false)

  //def play card
  //def

}

