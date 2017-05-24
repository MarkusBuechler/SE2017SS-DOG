package de.htwg.se.yourgame.view
import com.google.inject.Inject
import de.htwg.se.yourgame.controller.{TGameController, UpdatePlayerLabels, gameController}

import scala.swing.Reactor
import scala.util.Properties
import scalafx.Includes.handle
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.beans.property.ReadOnlyDoubleProperty
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.{Scene, SceneAntialiasing, SubScene}
import scalafx.scene.control.{Label, TableColumn, TableView, _}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.input.{KeyCombination, MouseEvent}
import scalafx.scene.layout._
import scalafx.scene.paint.{Color, LinearGradient, Stops}
import scalafx.scene.shape.Rectangle
import scalafx.scene.text.Text
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.scene.paint.Color._
import scalafx.scene.shape.Rectangle
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.beans.property.{ObjectProperty, StringProperty}
import scalafx.collections.ObservableBuffer
import scalafx.event.ActionEvent
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.chart.{BarChart, CategoryAxis, NumberAxis, XYChart}
import scalafx.scene.control.TableColumn._
import scalafx.scene.layout.{BorderPane, HBox}
import scalafx.stage.{Modality, Stage}

/**
 * Created by margogo on 15.05.17.
 */
class DogGui @Inject() (gameController: gameController) extends JFXApp with Reactor {

  listenTo(gameController)


  val SizeHeight = 600
  val SizeWidth = 900

  stage = new JFXApp.PrimaryStage {

    minHeight = SizeHeight
    minWidth = SizeWidth
    maxHeight = SizeHeight
    maxWidth = SizeWidth
    title = "DOG - THE GAME"
    resizable = false

    scene = new Scene(SizeWidth, SizeHeight) {

//    val label = Label("Players:")
//    val flow1 = new FlowPane(10, 10)
//    flow1.setPrefHeight(150);
//    flow1.setMinHeight(150);
//    flow1.setMaxHeight(150);
//    for (i <- 0 to gameController.cardDecks.apply(0).cards.size - 1) {
//      //              flow1.children.add(new ImageView(new Image("de/htwg/se/yourgame/view/" + gameController.cardDecks.apply(0).cards.apply(i).value + ".png")));
//    }
//    val flow2 = new FlowPane(10, 10)
//    //        flow2.setPrefWrapLength(300);
//    for (i <- 0 to gameController.cardDecks.apply(1).cards.size - 1) {
//      //              flow1.children.add(new ImageView(new Image("de/htwg/se/yourgame/view/" + gameController.cardDecks.apply(1).cards.apply(i).value + ".png")));
//    }
//    val flow3 = new FlowPane(10, 10)
//    //        flow3.setPrefWrapLength(300);
//    for (i <- 0 to gameController.cardDecks.apply(2).cards.size - 1) {
//      //              flow1.children.add(new ImageView(new Image("de/htwg/se/yourgame/view/" + gameController.cardDecks.apply(2).cards.apply(i).value + ".png")));
//    }
//    val flow4 = new FlowPane(10, 10)
//    //        flow4.setPrefWrapLength(300);
//    for (i <- 0 to gameController.cardDecks.apply(3).cards.size - 1) {
//      //              flow1.children.add(new ImageView(new Image("de/htwg/se/yourgame/view/" + gameController.cardDecks.apply(3).cards.apply(i).value + ".png")));
//    }

    var player1 = new VBox {
      spacing = 10
      children = List(Label(gameController.playerList.apply(0).name), /*flow1,*/ Label("Anzahl Karten: " + gameController.cardDecks.apply(0).cards.size.toString), Label(gameController.cardDecks.apply(0).cards.toString()))
    }
    player1.layoutX = 50
    player1.layoutY = 50

    var player2 = new VBox {
      spacing = 10
      children = List(Label(gameController.playerList.apply(1).name), /*flow2,*/ Label("Anzahl Karten: " + gameController.cardDecks.apply(1).cards.size.toString), Label(gameController.cardDecks.apply(1).cards.toString()))
    }

    player2.layoutX = 50
    player2.layoutY = 150

    var player3 = new VBox {
      spacing = 10
      children = List(Label(gameController.playerList.apply(2).name), /*flow3,*/ Label("Anzahl Karten: " + gameController.cardDecks.apply(2).cards.size.toString), Label(gameController.cardDecks.apply(2).cards.toString()))
    }

    player3.layoutX = 50
    player3.layoutY = 250

    var player4 = new VBox {
      spacing = 10
      children = List(Label(gameController.playerList.apply(3).name), /*flow4,*/ Label("Anzahl Karten: " + gameController.cardDecks.apply(3).cards.size.toString), Label(gameController.cardDecks.apply(3).cards.toString()))
    }
    player4.layoutX = 50
    player4.layoutY = 350

    content = List(player1, player2, player3, player4)

//    player1.onMouseClicked = (e: MouseEvent) => {
//      player1.children = List(Label(gameController.playerList.apply(0).name), /*flow1,*/ Label("Anzahl Karten: " + gameController.cardDecks.apply(0).cards.size.toString), Label(gameController.cardDecks.apply(0).cards.toString()))
//    }
//
//    player2.onMouseClicked = (e: MouseEvent) => {
//      player2.children = List(Label(gameController.playerList.apply(1).name), /*flow1,*/ Label("Anzahl Karten: " + gameController.cardDecks.apply(1).cards.size.toString), Label(gameController.cardDecks.apply(1).cards.toString()))
//    }
//
//    player3.onMouseClicked = (e: MouseEvent) => {
//      player3.children = List(Label(gameController.playerList.apply(2).name), /*flow1,*/ Label("Anzahl Karten: " + gameController.cardDecks.apply(2).cards.size.toString), Label(gameController.cardDecks.apply(2).cards.toString()))
//    }
//
//    player4.onMouseClicked = (e: MouseEvent) => {
//      player4.children = List(Label(gameController.playerList.apply(3).name), /*flow1,*/ Label("Anzahl Karten: " + gameController.cardDecks.apply(3).cards.size.toString), Label(gameController.cardDecks.apply(3).cards.toString()))
//    }

      reactions += {
        case e: UpdatePlayerLabels =>
          print("Reaktion")
          player1.children = List(Label(gameController.playerList.apply(0).name), /*flow1,*/ Label("Anzahl Karten: " + gameController.cardDecks.apply(0).cards.size.toString), Label(gameController.cardDecks.apply(0).cards.toString()))
          player2.children = List(Label(gameController.playerList.apply(1).name), /*flow1,*/ Label("Anzahl Karten: " + gameController.cardDecks.apply(1).cards.size.toString), Label(gameController.cardDecks.apply(1).cards.toString()))
          player3.children = List(Label(gameController.playerList.apply(2).name), /*flow1,*/ Label("Anzahl Karten: " + gameController.cardDecks.apply(2).cards.size.toString), Label(gameController.cardDecks.apply(2).cards.toString()))
          player4.children = List(Label(gameController.playerList.apply(3).name), /*flow1,*/ Label("Anzahl Karten: " + gameController.cardDecks.apply(3).cards.size.toString), Label(gameController.cardDecks.apply(3).cards.toString()))

      }

  }
}




//// Working actually
//  stage = new JFXApp.PrimaryStage {
//    title = "First GUI"
//    scene = new Scene (400,300) {
//      val button = new Button("Click Me")
//      button.layoutX = 100
//      button.layoutY = 50
//
//      val comboBox = new ComboBox(List("Scala", "Java", "C++", "Haskell"))
//      comboBox.layoutX = 200
//      comboBox.layoutY = 50
//
//      val listView = new ListView(List("AWT", "Swing", "JavaFX", "ScalaFX"))
//      listView.layoutX = 100
//      listView.layoutY = 100
//      listView.prefHeight = 150
//
//      content = List(button, comboBox, listView)
//
//      button.onAction = (e:ActionEvent) => {
//        println("Button clicked")
//        val selected = listView.selectionModel.apply.getSelectedItem
//        listView.items = listView.items.apply.diff(selected) // geht nicht
//        button.text = gameController.currentPlayer.name
//      }
//
//      comboBox.onAction = (e:ActionEvent) => {
//        listView.items.apply += comboBox.selectionModel.apply.getSelectedItem
//      }
//
//    }
//  }




//  def createBarChart(chartTitle: String, chartData: ObservableBuffer[Position]): BarChart[String, Number] =
//    new BarChart(CategoryAxis(), NumberAxis()) {
//      title = chartTitle
//      data = XYChart.Series(chartData.map(d => XYChart.Data[String, Number](d.name(), d.value())))
//      legendVisible = false
//      onMouseClicked = handle {showAsTable(title(), chartData)}
//    }
//
//  def createTextField(stringtext : String) : TextField = new TextField() {
//    text = stringtext
//  }


//  private def showAsTable(name: String, data: ObservableBuffer[Position]) {
//
//    val tableView = new TableView[Position](data) {
//      columns ++= List(
//        new TableColumn[Position, String] {
//          text = "Position"
//          cellValueFactory = {_.value.name}
//          prefWidth = 180
//        },
//        new TableColumn[Position, Int] {
//          text = "Value"
//          cellValueFactory = {_.value.value}
//          prefWidth = 180
//        }
//      )
//    }

//    // Show as modal dialog
//    new Stage {
//      title = name
//      initModality(Modality.WindowModal)
//      initOwner(stage)
//      scene = new Scene {
//        root = new BorderPane {
//          center = tableView
//        }
//      }
//    }.showAndWait()
//  }
}
