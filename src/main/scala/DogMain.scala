/**
  * Created by margogo on 02.04.17.
  * Main Class for the scala application DOG
  */

import de.htwg.se.yourgame.DependencyModule
import com.google.inject.{Guice, Injector}
import java.io.IOException

import de.htwg.se.yourgame.view.DogTui

import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafxml.core.{FXMLView, NoDependencyResolver}
import net.codingwell.scalaguice.InjectorExtensions._


object DogApplication extends JFXApp {

  val injector: Injector = Guice.createInjector(new DependencyModule)

  val resource = getClass.getResource("/de/htwg/se/yourgame/fxml/main.fxml")

  if (resource == null) {
    throw new IOException("Cannot load resource: main.fxml")
  }

  val root = FXMLView(resource, NoDependencyResolver)

  stage = new PrimaryStage() {
    title = "FXML GridPane Demo"
    scene = new Scene(root)
  }

  var tui = injector.instance[DogTui]
}





