package de.htwg.se.yourgame
import com.google.inject.{Binder, Guice, Inject, Module}
import com.google.inject.name.Names
import de.htwg.se.yourgame.controller
import de.htwg.se.yourgame.controller.{cardController, fieldController, gameController, playerController}
import de.htwg.se.yourgame.view.tui.DogTui

/**
  * Created by margogo on 24.04.17.
  */
class DependencyModule extends Module{

  def configure(binder:Binder) = {
    binder.bind(classOf[gameController])
    binder.bind(classOf[fieldController])
    binder.bind(classOf[playerController])
    binder.bind(classOf[cardController])
  }
}
