/**
  * Created by margogo on 02.04.17.
  * Main Class for the scala application DOG
  */

import de.htwg.se.yourgame.DependencyModule
import de.htwg.se.yourgame.view.{DogGui, DogTui}
import com.google.inject.{Guice, Injector}
import net.codingwell.scalaguice.InjectorExtensions._

import scalafxml.core.{DependenciesByType, FXMLView}


import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene



  object DogApplication extends JFXApp {



    val injector: Injector = Guice.createInjector(new DependencyModule)

    val root = FXMLView(getClass.getResource("/de/htwg/se/yourgame/fxml/main.fxml"),
      new DependenciesByType(Map()))

    stage = new JFXApp.PrimaryStage() {
      title = "DOG - THE GAME"
      scene = new Scene(root)
    }

    var tui = injector.instance[DogTui]
}





