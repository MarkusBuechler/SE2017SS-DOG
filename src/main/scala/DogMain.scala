/**
  * Created by margogo on 02.04.17.
  * Main Class for the scala application DOG
  */

import de.htwg.se.yourgame.DependencyModule
import de.htwg.se.yourgame.view.{DogGui, DogTui}

import com.google.inject.{Guice, Injector}
import net.codingwell.scalaguice.InjectorExtensions._

import scalafx.application.JFXApp






object Dog extends JFXApp {

  val injector: Injector = Guice.createInjector(new DependencyModule)
//
  var tui = injector.instance[DogTui]
  var dogGui = injector.instance[DogGui]

//  while (tui.processInputLine(readLine())) {}


}





