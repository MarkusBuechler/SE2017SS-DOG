package de.htwg.se.dog2.model
import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
 * Created by margogo on 15.04.17.
 */
case class Player(name: String, playerId: Int, isActive: Boolean) {

  //object Player {
  //  implicit val reads: Reads[Player] = (
  //    (JsPath \ "name").read[String] and
  //      (JsPath \ "playerId").read[Int] and
  //      (JsPath \ "isActive").read[Boolean]
  //    )(Player.apply _)

}