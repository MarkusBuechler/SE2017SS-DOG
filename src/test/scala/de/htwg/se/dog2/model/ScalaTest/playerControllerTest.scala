package de.htwg.se.dog2.model.ScalaTest

import com.google.inject.Guice
import de.htwg.se.dog2.DependencyModule
import de.htwg.se.dog2.controller.gameController
import org.junit.Test
import org.scalatest.{ FlatSpec, Matchers }

/**
 * Created by margogo on 10.05.17.
 */
@Test
class playerControllerTest extends FlatSpec with Matchers {

  val injector = Guice.createInjector(new DependencyModule)

  var playerController = injector.getInstance(classOf[gameController])

  playerController.initPlayer()

  val playerSize = playerController.playerList.size
  playerSize should be > 0

  assert(playerSize == 4)

  val playerA = playerController.currentPlayer
  playerController.changeCurrentPlayer()
  val playerB = playerController.currentPlayer

  assert(playerA != playerB)

}
