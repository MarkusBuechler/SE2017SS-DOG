package de.htwg.se.dog2

import com.google.inject.AbstractModule
import de.htwg.se.dog2.controller.{ TGameController, gameController }
import de.htwg.se.dog2.view.{ DogGui, DogTui }
import net.codingwell.scalaguice.ScalaModule

/**
 * Created by margogo on 24.04.17.
 */
class DependencyModule extends AbstractModule with ScalaModule {

  override def configure(): Unit = {
    bind[TGameController].to[gameController]
    bind[DogGui]
    bind[DogTui]
  }
}