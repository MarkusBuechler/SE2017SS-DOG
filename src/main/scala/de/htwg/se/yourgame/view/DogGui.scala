package de.htwg.se.yourgame.view

import com.google.inject.Inject
import de.htwg.se.yourgame.controller.gameController

import scalafxml.core.{DependenciesByType, FXMLView}


import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene


/**
  * Created by margogo on 15.05.17.
  */
class DogGui @Inject()(gameController: gameController) extends JFXApp {
//

  val root = FXMLView(getClass.getResource("de/htwg/se/yourgame/fxml/main.fxml"),
    new DependenciesByType(Map()))

  stage = new JFXApp.PrimaryStage() {
    title = "DOG - THE GAME"
    scene = new Scene(root)
  }
//  val root = FXMLView(getClass.getResource("de/htwg/se/yourgame/fxml/main.fxml"),
//    new DependenciesByType(Map()))
//
//  if (root == null)
//    print("Shit")
//
//  stage = new JFXApp.PrimaryStage() {
//    title = "DOG - THE GAME"
//    scene = new Scene(root)
//  }
//
//  val resource = getClass.getResource("/de/htwg/se/yourgame/fxml/main.fxml")
//  if (resource == null) {
//    throw new IOException("Cannot load resource: main.fxml")
//  }
//
//  val root: jfxs.Parent = jfxf.FXMLLoader.load(resource)
//
//  stage = new PrimaryStage() {
//    title = "FXML GridPane Demo"
//    scene = new Scene(root)
//  }


  //  val stage = new JFXApp.PrimaryStage {
//    title = "DOG - THE GAME"
//    minHeight = 600
//    minWidth = 800
//    maxHeight = 1080
//    maxWidth = 1920
//    resizable = true
//    scene = new Scene {
//      root = {
//
//        val label = Label("Players:")
//        val flow1 = new FlowPane(10,10)
////        flow1.setPrefWrapLength(Double.MaxValue);
//        flow1.setPrefHeight(150);
//        flow1.setMinHeight(150);
//        flow1.setMaxHeight(150);
////        flow1.setPrefWidth(800);
////        flow1.setMinWidth(800);
////        flow1.setMaxWidth(800);
////        flow1.setMaxWidth(500);
////        flow1.setMaxHeight(120);
////        flow1.margin = Insets(-50);
//        for (i <- 0 to gameController.cardDecks.apply(0).cards.size - 1) {
//          flow1.children.add(new ImageView(new Image("de/htwg/se/yourgame/view/" + gameController.cardDecks.apply(0).cards.apply(i).value + ".png")));
//        }
//        val flow2 = new FlowPane(10, 10)
////        flow2.setPrefWrapLength(300);
//        for (i <- 0 to gameController.cardDecks.apply(1).cards.size - 1) {
//          flow1.children.add(new ImageView(new Image("de/htwg/se/yourgame/view/" + gameController.cardDecks.apply(1).cards.apply(i).value + ".png")));
//        }
//        val flow3 = new FlowPane(10, 10)
////        flow3.setPrefWrapLength(300);
//        for (i <- 0 to gameController.cardDecks.apply(2).cards.size - 1) {
//          flow1.children.add(new ImageView(new Image("de/htwg/se/yourgame/view/" + gameController.cardDecks.apply(2).cards.apply(i).value + ".png")));
//        }
//        val flow4 = new FlowPane(10, 10)
////        flow4.setPrefWrapLength(300);
//        for (i <- 0 to gameController.cardDecks.apply(3).cards.size - 1) {
//          flow1.children.add(new ImageView(new Image("de/htwg/se/yourgame/view/" + gameController.cardDecks.apply(3).cards.apply(i).value + ".png")));
//        }
//
//        val player1 = new HBox {
//          spacing = 10
//          children = List(Label(gameController.playerList.apply(0).toString),flow1)
//        }
//        val player2 = new HBox {
//          spacing = 10
//          children = List(Label(gameController.playerList.apply(1).toString), flow2)
//        }
//        val player3 = new HBox {
//          spacing = 10
//          children = List(Label(gameController.playerList.apply(2).toString), flow3)
//        }
//        val player4 = new HBox
//        {
//          spacing = 10
//          children = List(Label(gameController.playerList.apply(3).toString), flow4)
//        }
//
//        // Top content using a rectangle
//        val topRectangle = new MenuBar {
//          useSystemMenuBar = false
//          val fooMenuItem = new MenuItem("foo")
//          menus = List(
//            new Menu("Scala") {
//              items = List(
//                new Menu("Author Info") {
//                  graphic = new ImageView {
//                    image = new Image(this.getClass.getResourceAsStream("crumb-selected-focused.png"))
//                    margin = Insets(0, 0, 0, 5)
//                  }
//                  items = List(
//                    new MenuItem("Type Safe"),
//                    new MenuItem("Martin Odersky")
//                  )
//                },
//                new Menu("Features") {
//                  items = List(
//                    new MenuItem("Object Oriented"),
//                    new MenuItem("Functional"),
//                    fooMenuItem,
//                    new CheckMenuItem( """Show "foo" item""") {
//                      selected = true
//                      selected.onInvalidate {
//                        fooMenuItem.setVisible(selected())
//                        println( """Menu item "foo" is now """ + (if (fooMenuItem.visible()) "" else "not") + " visible")
//                      }
//                    }
//                  )
//                },
//                new MenuItem("ScalaFX")
//              )
//            }, new Menu("Menu 2") {
//              items = List (
//                new Menu("Author Info") {
//                  graphic = new ImageView {
//                    image = new Image(this.getClass.getResourceAsStream("crumb-selected-focused.png"))
//                    margin = Insets(0, 0, 0, 5)
//                  }
//                  items = List(
//                    new MenuItem("Type Safe"),
//                    new MenuItem("Martin Odersky")
//                  )
//                },
//                new Menu("Features") {
//                  items = List(
//                    new MenuItem("Object Oriented"),
//                    new MenuItem("Functional"),
//                    fooMenuItem,
//                    new CheckMenuItem( """Show "foo" item""") {
//                      selected = true
//                      selected.onInvalidate {
//                        fooMenuItem.setVisible(selected())
//                        println( """Menu item "foo" is now """ + (if (fooMenuItem.visible()) "" else "not") + " visible")
//                      }
//                    }
//                  )
//                }
//              )
//            }
//          )
//        }
//
//        // Left content using VBox
//        val leftVBox = new VBox {
//          spacing = 10
//          maxWidth = 1000
//          alignment = Pos.TopLeft
//          children = Seq(
//            label,
//            new VBox {
//              spacing = 10
//              children = List(player1, player2, player3, player4)
//            }
//          )
//        }
//
//        // Center content using Anchor Pane
//        val centerLabel = Label("Player "+ gameController.currentPlayer.toString + "ist am Zug")
//        val imageButton = new ImageView {
//          image = new Image(this.getClass.getResourceAsStream("map600p.png"))
//
//        }
//        AnchorPane.setTopAnchor(centerLabel, 10.0)
//        AnchorPane.setTopAnchor(imageButton, 40.0)
//        AnchorPane.setRightAnchor(centerLabel, 80.0)
//        AnchorPane.setLeftAnchor(imageButton, 80.0)
//        val centerAnchorPane = new AnchorPane {
//          children = List(centerLabel, imageButton)
//        }
//
//        // Right content using VBox
//        val rightVBox = new VBox {
//          spacing = 10
//          children = List(Label("Right Hand"), Label("Nothing A"), Label("Nothing B"), Label("Nothing C"))
//        }
//
//        // Right content
//        val bottomLabel = Label("I am a status message. Use me!")
//
//        new BorderPane {
//          maxWidth = 1920
//          maxHeight = 1080
//          padding = Insets(10)
//          top = topRectangle
//          left = leftVBox
//          center = centerAnchorPane
//          right = rightVBox
//          bottom = bottomLabel
//        }
//      }
//    }
//  }

}