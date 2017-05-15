package de.htwg.se.yourgame.view

import com.google.inject.Inject
import de.htwg.se.yourgame.controller.gameController

import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.effect.DropShadow
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.paint.Color._
import scalafx.scene.paint.{LinearGradient, Stops}
import scalafx.scene.text.Text

/**
  * Created by margogo on 15.05.17.
  */
class DogGui @Inject()(gameController: gameController)  {

  val stage2 = new PrimaryStage {
    title = "Dog - Log In"
    scene = new Scene {
      fill = Black
      content = new VBox {
        padding = Insets(20)
        children = Seq(
          new Text {
            text = "Hello to the Game "
            style = "-fx-font-size: 48pt"
            fill = new LinearGradient(
              endX = 0,
              stops = Stops(PaleGreen, SeaGreen))
          },
          new Text {
            text = "Press any Button to continue "
            style = "-fx-font-size: 24pt"
            fill = new LinearGradient(
              endX = 0,
              stops = Stops(PaleGreen, SeaGreen))
          }
        )
      }
    }
  }


}
