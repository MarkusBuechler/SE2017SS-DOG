package de.htwg.se.yourgame

import com.google.inject.AbstractModule
import de.htwg.se.yourgame.controller.{TGameController, gameController}
import de.htwg.se.yourgame.view.{DogGui, DogTui}
import net.codingwell.scalaguice.ScalaModule

/**
  * Created by margogo on 24.04.17.
  */
class DependencyModule extends AbstractModule with ScalaModule {

  override def configure(): Unit = {
    bind[TGameController].to[gameController]
    bind[DogGui]
    bind[DogTui]
//    bind[TGameController]
//    bind[guiController]
  }
}
