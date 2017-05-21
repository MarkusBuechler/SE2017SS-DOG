package de.htwg.se.yourgame.controller

import com.google.inject.Inject
import de.htwg.se.yourgame.DependencyModule

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
class guiController (val vboxMain : VBox,
                    val hBoxPlayer2 : HBox,
                    val pic2_1 : ImageView,
                    val pic2_2 : ImageView,
                    val pic2_3 : ImageView,
                    val pic2_4 : ImageView,
                    val pic2_5 : ImageView,
                    val pic2_6 : ImageView,
                    val pic2_7 : ImageView,
                    val label2 : Label,
                     testDep: DependencyModule
                   ) extends gameController {

  val picCard1 = new Image("/de/htwg/se/yourgame/view/1.png")
  val picCard2 = new Image("/de/htwg/se/yourgame/view/2.png")
  val picCard3 = new Image("/de/htwg/se/yourgame/view/3.png")
  val picCard4 = new Image("/de/htwg/se/yourgame/view/4.png")
  val picCard5 = new Image("/de/htwg/se/yourgame/view/5.png")
  val picCard6 = new Image("/de/htwg/se/yourgame/view/6.png")
  val picCard7 = new Image("/de/htwg/se/yourgame/view/7.png")
  val picCard8 = new Image("/de/htwg/se/yourgame/view/8.png")
  val picCard9 = new Image("/de/htwg/se/yourgame/view/9.png")
  val picCard10 = new Image("/de/htwg/se/yourgame/view/10.png")
  val picCard12 = new Image("/de/htwg/se/yourgame/view/12.png")
  val picCard13 = new Image("/de/htwg/se/yourgame/view/13.png")
  val picCardidk = new Image("/de/htwg/se/yourgame/view/idk.png")

  val picList = List(picCard1, picCard2, picCard3, picCard4, picCard5, picCard6, picCard7, picCard8, picCard9, picCard10, picCard12, picCard12, picCardidk)


  // event handlers are simple public methods:
  def onCreate(event: ActionEvent) {
    pic2_1.setImage(picList.apply(cardDecks.apply(1).cards.apply(0).value))
    pic2_2.setImage(picList.apply(cardDecks.apply(1).cards.apply(1).value))
    pic2_3.setImage(picList.apply(cardDecks.apply(1).cards.apply(2).value))
    pic2_4.setImage(picList.apply(cardDecks.apply(1).cards.apply(3).value))
    pic2_5.setImage(picList.apply(cardDecks.apply(1).cards.apply(4).value))
    pic2_6.setImage(picList.apply(cardDecks.apply(1).cards.apply(5).value))
    pic2_7.setImage(picList.apply(cardDecks.apply(1).cards.apply(6).value))


//    for (i <- 0 to cardDecks.apply(1).cards.size - 1) {
    //      hBoxPlayer2.children.listIterator().
    //      hBoxPlayer2.children.add(new ImageView(new Image("de/htwg/se/yourgame/view/" + cardDecks.apply(1).cards.apply(i).value + ".png")));
    //              }

  }

  @javafx.fxml.FXML override def test {


    pic2_1.setImage(picCard1)

    print(cardDecks.size)
//    pic2_1.setImage(picList.apply(cardDecks.apply(1).cards.apply(1).value))
//    pic2_2.setImage(picList.apply(cardDecks.apply(1).cards.apply(1).value))
//    pic2_3.setImage(picList.apply(cardDecks.apply(1).cards.apply(2).value))
//    pic2_4.setImage(picList.apply(cardDecks.apply(1).cards.apply(3).value))
//    pic2_5.setImage(picList.apply(cardDecks.apply(1).cards.apply(4).value))
//    pic2_6.setImage(picList.apply(cardDecks.apply(1).cards.apply(5).value))
//    pic2_7.setImage(picList.apply(cardDecks.apply(1).cards.apply(6).value))
//    print("Test erfolgreich\n")
////    label2.setText("Test??")
//
//    refresh()
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