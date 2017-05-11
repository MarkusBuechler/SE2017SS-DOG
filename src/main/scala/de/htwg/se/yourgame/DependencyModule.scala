package de.htwg.se.yourgame
import com.google.inject.{Binder, Module}
import de.htwg.se.yourgame.controller.{cardController, fieldController, gameController, playerController}

/**
  * Created by margogo on 24.04.17.
  */
class DependencyModule extends Module{

  def configure(binder:Binder): Unit = {
    binder.bind(classOf[gameController])
  }
}
