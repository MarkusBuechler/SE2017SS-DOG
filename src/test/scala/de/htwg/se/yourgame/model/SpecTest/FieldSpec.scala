package de.htwg.se.yourgame.model.SpecTest

import de.htwg.se.yourgame.model.{ Figure, Player }
import org.junit.runner.RunWith
import org.scalamock.scalatest.MockFactory
import org.scalatest._
import org.scalatest.junit.JUnitRunner

import scala.collection.mutable.ListBuffer

/**
 * Created by margogo on 15.04.17.
 */
@RunWith(classOf[JUnitRunner])
class FieldSpec extends FlatSpec with MockFactory {

  var figureList = new ListBuffer[Figure]
  var playerList = new ListBuffer[Player]

}
