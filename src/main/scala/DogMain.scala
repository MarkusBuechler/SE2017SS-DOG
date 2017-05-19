/**
  * Created by margogo on 02.04.17.
  * Main Class for the scala application DOG
  */

import de.htwg.se.yourgame.DependencyModule
import de.htwg.se.yourgame.view.{DogGui, DogTui}
import com.google.inject.{Guice, Injector}
import net.codingwell.scalaguice.InjectorExtensions._

import scalafx.application.JFXApp
import scalafx.Includes._
import scalafx.scene.Scene
import scalafxml.core.{DependenciesByType, FXMLView}


  object DogApplication extends JFXApp {

    val injector: Injector = Guice.createInjector(new DependencyModule)
    var tui = injector.instance[DogTui]
//      var dogGui = injector.instance[DogGui]

    val root = FXMLView(getClass.getResource("/de/htwg/se/yourgame/fxml/basic.fxml"),
      new DependenciesByType(Map()))

    stage = new JFXApp.PrimaryStage() {
      title = "DOG - THE GAME"
      scene = new Scene(root)
    }


//  while (tui.processInputLine(readLine())) {}


}





