package de.htwg.se.yourgame.view

import com.google.inject.Inject
import de.htwg.se.yourgame.controller.gameController
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.geometry.Insets
import scalafx.scene.control._
import scalafx.scene.image.Image
import scalafx.scene.image.ImageView
import scalafx.scene.layout.{AnchorPane, BorderPane, VBox}



/**
  * Created by margogo on 15.05.17.
  */
class DogGui @Inject()(gameController: gameController)  {

  val stage = new JFXApp.PrimaryStage {
    title = "DOG - THE GAME"
    minHeight = 600
    minWidth = 800
    maxHeight = 1080
    maxWidth = 1920
    resizable = true
    scene = new Scene {
      root = {
        // Top content using a rectangle
        val topRectangle = new MenuBar {
          useSystemMenuBar = false
          val fooMenuItem = new MenuItem("foo")
          menus = List(
            new Menu("Scala") {
              items = List(
                new Menu("Author Info") {
                  graphic = new ImageView {
                    image = new Image(this.getClass.getResourceAsStream("crumb-selected-focused.png"))
                    margin = Insets(0, 0, 0, 5)
                  }
                  items = List(
                    new MenuItem("Type Safe"),
                    new MenuItem("Martin Odersky")
                  )
                },
                new Menu("Features") {
                  items = List(
                    new MenuItem("Object Oriented"),
                    new MenuItem("Functional"),
                    fooMenuItem,
                    new CheckMenuItem( """Show "foo" item""") {
                      selected = true
                      selected.onInvalidate {
                        fooMenuItem.setVisible(selected())
                        println( """Menu item "foo" is now """ + (if (fooMenuItem.visible()) "" else "not") + " visible")
                      }
                    }
                  )
                },
                new MenuItem("ScalaFX")
              )
            }, new Menu("Menu 2") {
              items = List (
                new Menu("Author Info") {
                  graphic = new ImageView {
                    image = new Image(this.getClass.getResourceAsStream("crumb-selected-focused.png"))
                    margin = Insets(0, 0, 0, 5)
                  }
                  items = List(
                    new MenuItem("Type Safe"),
                    new MenuItem("Martin Odersky")
                  )
                },
                new Menu("Features") {
                  items = List(
                    new MenuItem("Object Oriented"),
                    new MenuItem("Functional"),
                    fooMenuItem,
                    new CheckMenuItem( """Show "foo" item""") {
                      selected = true
                      selected.onInvalidate {
                        fooMenuItem.setVisible(selected())
                        println( """Menu item "foo" is now """ + (if (fooMenuItem.visible()) "" else "not") + " visible")
                      }
                    }
                  )
                }
              )
            }
          )
        }

        // Left content using VBox
        val leftVBox = new VBox {
          spacing = 10
          children = List(Label("Left Hand"), Label("Choice One"), Label("Choice Two"), Label("Choice Three"))
        }

        // Center content using Anchor Pane
        val centerLabel = Label("We're in the center area.")
        val imageButton = new ImageView {
          image = new Image(this.getClass.getResourceAsStream("map600p.png"))

        }
        AnchorPane.setTopAnchor(centerLabel, 10.0)
        AnchorPane.setTopAnchor(imageButton, 40.0)
        AnchorPane.setLeftAnchor(centerLabel, 80.0)
        AnchorPane.setLeftAnchor(imageButton, 80.0)
        val centerAnchorPane = new AnchorPane {
          children = List(centerLabel, imageButton)
        }

        // Right content using VBox
        val rightVBox = new VBox {
          spacing = 10
          children = List(Label("Right Hand"), Label("Thing A"), Label("Thing B"), Label("Thing C"))
        }

        // Right content
        val bottomLabel = Label("I am a status message. I am at the bottom")

        new BorderPane {
          maxWidth = 1920
          maxHeight = 1080
          padding = Insets(10)
          top = topRectangle
          left = leftVBox
          center = centerAnchorPane
          right = rightVBox
          bottom = bottomLabel
        }
      }
    }
  }

}