/**
  * Created by margogo on 02.04.17.
  * Main Class for the scala application DOG
  */

import de.htwg.se.yourgame.DependencyModule
import de.htwg.se.yourgame.controller.gameController
import de.htwg.se.yourgame.view.{DogGui, DogTui}

import scala.io.StdIn._
import scalafx.scene.paint.Color.LightGreen
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle
import com.google.inject.{Guice, Injector}
import net.codingwell.scalaguice.InjectorExtensions._

import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.layout.BorderPane
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.HBox
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{Stops, LinearGradient}
import scalafx.scene.text.Text





object Dog extends JFXApp {

  val injector: Injector = Guice.createInjector(new DependencyModule)
//
  var tui = injector.instance[DogTui]
  var dogGui = injector.instance[DogGui]

//  while (tui.processInputLine(readLine())) {}


}





