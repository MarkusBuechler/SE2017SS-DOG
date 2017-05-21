package de.htwg.se.yourgame.controller

import com.google.inject.Inject

import scalafx.event.ActionEvent
import scalafx.scene.control.{Alert, ButtonType, Label}
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{HBox, VBox}
import scalafxml.core.macros.sfxml
/**
  * Created by margogo on 19.05.17.
  */
@sfxml
class guiController extends gameController {

  val vboxMain = new VBox()
  val hBoxPlayer2 = new HBox()
  val pic2_1 = new ImageView()
  val label2 = new Label()

  // event handlers are simple public methods:
  def onCreate(event: ActionEvent) {
//    for (i <- 0 to cardDecks.apply(0).cards.size - 1) {
//      hBoxPlayer2.children.add(new ImageView(new Image("de/htwg/se/yourgame/view/" + cardDecks.apply(0).cards.apply(i).value + ".png")));
//              }
  }

  @javafx.fxml.FXML override def test {
    val myNewPic = new Image("de/htwg/se/yourgame/view/1.png")

    pic2_1.setImage(myNewPic)
    print("Test erfolgreich")
    label2.setText("Test??")

    refresh()
  }


  @javafx.fxml.FXML def menuItemRevertOnAction(e: javafx.event.ActionEvent) {
    new Alert(AlertType.Information, "Revert actions will be implemented in the future!!!").showAndWait()
  }

  @javafx.fxml.FXML def quitMenuItemOnAction(e: javafx.event.ActionEvent) {
    val alert = new Alert(AlertType.Confirmation) {
      title = "Confirmation Dialog"
      headerText = "Quit Game"
      contentText = "Do you really want to quit the game?"
    }

    val result = alert.showAndWait()

    // React to user's selectioon
    result match {
      case Some(ButtonType.OK) => quitGame()
      case _                   =>
    }

  }

  @javafx.fxml.FXML def menuItemHelpOnAction(e: javafx.event.ActionEvent) {
    new Alert(AlertType.Information, "Help items will be implemented in the future!!!").showAndWait()
  }

  @javafx.fxml.FXML def aboutMenuItemOnAction(e: javafx.event.ActionEvent) {
    new Alert(AlertType.Information, "About items will be implemented in the future!!!").showAndWait()
  }

  @javafx.fxml.FXML def menuItemPreferencesOnAction(e: javafx.event.ActionEvent) {
    new Alert(AlertType.Information, "Preference items will be implemented in the future!!!").showAndWait()
  }

  @javafx.fxml.FXML def menuItemSaveOnAction(e: javafx.event.ActionEvent) {
    new Alert(AlertType.Information, "Save things will be implemented in the future!!!").showAndWait()
  }

}