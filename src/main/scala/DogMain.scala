/**
  * Created by margogo on 02.04.17.
  * Main Class for the scala application DOG
  */
//
//import de.htwg.se.yourgame.DependencyModule
//import de.htwg.se.yourgame.view.{DogGui, DogTui}
//import com.google.inject.{Guice, Injector}
//import net.codingwell.scalaguice.InjectorExtensions._
//
//import scalafx.application.JFXApp
//import scalafx.Includes._
//import scalafx.scene.Scene
//import scalafxml.core.{DependenciesByType, FXMLView}
//
//object Dog extends JFXApp {
//
//  val injector: Injector = Guice.createInjector(new DependencyModule)
////
//  var tui = injector.instance[DogTui]
//  var dogGui = injector.instance[DogGui]
//
//  stage = new JFXApp.PrimaryStage() {
//    title = "Test window"
//    scene = new Scene(
//    FXMLView(getClass.getResource("de/htwg/se/yourgame/fxml/basic.fxml"),
//      new DependenciesByType(Map(
//        typeOf[AnAdditionalDependency] -> new AnAdditionalDependency("dependency by type"))))
//
//}

  package scalafxml.demo

  import scalafx.application.JFXApp
  import scalafx.Includes._
  import scalafx.scene.control.TableView
  import scalafx.scene.control.TableColumn
  import scalafxml.core.{FXMLView, DependenciesByType}
  import scalafxml.core.macros.sfxml
  import scalafx.scene.Scene
  import scalafx.beans.property.StringProperty

  class Recording(initialTitle: String, initialSubtitle: String) {
    val title = StringProperty(initialTitle)
    val subtitle = StringProperty(initialSubtitle)
  }

  @sfxml
  class TestController(private val recordingsTable : TableView[Recording],
                       private val titleColumn : TableColumn[Recording, String],
                       private val subtitleColumn : TableColumn[Recording, String]) {

    titleColumn.cellValueFactory = {_.value.title}
    subtitleColumn.cellValueFactory = {_.value.subtitle}

    recordingsTable.items.getValue.add(new Recording("title", "subtitle"))
  }

  object Test extends JFXApp {
    val root = FXMLView(getClass.getResource("/de/htwg/se/yourgame/fxml/basic.fxml"),
      new DependenciesByType(Map()))

    stage = new JFXApp.PrimaryStage() {
      title = "Test"
      scene = new Scene(root)
    }


//  while (tui.processInputLine(readLine())) {}


}





