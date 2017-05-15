package de.htwg.se.yourgame.view

import com.google.inject.Inject
import de.htwg.se.yourgame.controller.gameController

import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.geometry.Insets
import scalafx.scene.control._
import scalafx.scene.image.Image
import scalafx.scene.image.ImageView
import scalafx.scene.layout.{BorderPane, Priority, VBox}

/**
  * Created by margogo on 15.05.17.
  */
class DogGui @Inject()(gameController: gameController)  {

  val stage = new JFXApp.PrimaryStage {

    title = "Color Button Example"
    scene = new Scene {
      root = new VBox {
        vgrow = Priority.Always
        hgrow = Priority.Always
        spacing = 10
        padding = Insets(20)
        children = List(
          new Button {
            maxWidth = 200
            maxHeight = 150
            text = "Button 1"
            style = "-fx-base: red"
          },
          new Button {
            maxWidth = 200
            maxHeight = 150
            text = "Button 2"
            style = "-fx-base: green "
          },
          new Button {
            maxWidth = 200
            maxHeight = 150
            text = "Button 3"
            style = "-fx-base: Yellow"
          },
          new Button {
            maxWidth = 200
            maxHeight = 150
            text = "Button 4"
            style = "-fx-base: Orange"
          })
      }
    }
  }

}
