/**
  * Created by margogo on 02.04.17.
  * Main Class for the scala application DOG
  */

import de.htwg.se.yourgame.DependencyModule
import java.io.IOException

import com.google.inject.{AbstractModule, Guice}
import de.htwg.se.yourgame.fxml.GuiceDependencyResolver
import de.htwg.se.yourgame.view.{DogGui, DogTui, DogTuiT}

import scalafx.application.JFXApp.PrimaryStage
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafxml.core.FXMLView


object DogApplication extends JFXApp {

val module = new AbstractModule {
  def configure() {
    bind(classOf[DependencyModule]).toInstance(new DependencyModule())
  }
}
implicit val injector = Guice.createInjector(module)

  val resource = getClass.getResource("/de/htwg/se/yourgame/fxml/main.fxml")

  if (resource == null) {
    throw new IOException("Cannot load resource: main.fxml")
  }

  val root = FXMLView(resource, new GuiceDependencyResolver())

  stage = new PrimaryStage() {
    title = "DOG - THE GAME"
    scene = new Scene(root)
  }
  injector.instance[DogTuiT]
//  var tui = injector.getInstance[DogTuiT]
}

//
//import scalafx.Includes._
//import scalafx.application.JFXApp
//import scalafx.scene.Scene
//import scalafxml.core.FXMLView
//import scalafxml.guice.GuiceDependencyResolver
//import com.google.inject.{AbstractModule, Guice}
//
//object GuiceDemo extends JFXApp {
//
//  val module = new AbstractModule {
//    def configure() {
//      bind(classOf[TestDependency]).toInstance(new TestDependency("guice dependency"))
//    }
//  }
//  implicit val injector = Guice.createInjector(module)
//
//  stage = new JFXApp.PrimaryStage() {
//    title = "Hello world"
//    scene = new Scene(FXMLView(getClass.getResource("startscreen.fxml"), new GuiceDependencyResolver()))
//
//  }
//}
//

