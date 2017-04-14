package de.htwg.se.yourgame.model

/**
  * Created by margogo on 14.04.17.
  */
case class Field (id: Integer, property: String, color: String, isUsed: Boolean , predecessor : Field, successor : Field) {

}
