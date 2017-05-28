package de.htwg.se.yourgame.view

import java.io.File
import javax.imageio.ImageIO

import javax.swing.ImageIcon
import scala.swing._

import com.google.inject.Inject
import de.htwg.se.yourgame.controller.{UpdatePlayerLabels, gameController}


/**
 * Created by margogo on 15.05.17.
 */
class DogGui @Inject() (gameController: gameController) extends MainFrame with Reactor {

  val SizeHeight = 700
  val SizeWidth = 900

  title = "DOG - THE GAME"
  preferredSize = new Dimension(SizeWidth, SizeHeight)
  resizable = false

  // declare Components here, except menubar ...
  var label = new Label {
    text = "TEst"
  }

  val mapPicture = new Label {
    icon = new ImageIcon("resources/pictures/map600p.png")
  }

  val card1 = new Label {
    icon = new ImageIcon("resources/pictures/1.png")
  }
  val card2 = new Label {
    icon = new ImageIcon("resources/pictures/2.png")
  }
  val card3 = new Label {
    icon = new ImageIcon("resources/pictures/3.png")
  }
  val card4 = new Label {
    icon = new ImageIcon("resources/pictures/4.png")
  }
  val card5 = new Label {
    icon = new ImageIcon("resources/pictures/5.png")
  }
  val card6 = new Label {
    icon = new ImageIcon("resources/pictures/6.png")
  }
  val card7 = new Label {
    icon = new ImageIcon("resources/pictures/7.png")
  }
  val card8 = new Label {
    icon = new ImageIcon("resources/pictures/8.png")
  }
  val card9 = new Label {
    icon = new ImageIcon("resources/pictures/9.png")
  }
  val card10 = new Label {
    icon = new ImageIcon("resources/pictures/10.png")
  }
  val card12 = new Label {
    icon = new ImageIcon("resources/pictures/12.png")
  }
  val card13 = new Label {
    icon = new ImageIcon("resources/pictures/13.png")
  }



  val player1 = new BoxPanel(Orientation.Vertical) {
    contents += card1
    contents += card2
    contents += card6
    contents += card1
    contents += card1
    contents += card3
    contents += card7
  }


  //  var mapPanel = new DogMap(gameController)

  contents = new BorderPanel {
    add(new Button("North"), BorderPanel.Position.North)
    add(mapPicture, BorderPanel.Position.Center)
    add(new Button("East"), BorderPanel.Position.East)
    add(player1, BorderPanel.Position.West)
    add(Button("South: Close") {
      sys.exit(0)
    }, BorderPanel.Position.South)
  }

  visible = true




//  contents = new BoxPanel(Orientation.Horizontal) {
//    contents += menuBar
//    contents += label
//    Dialog.showMessage(contents.head, "Welcome to Scotland Yard \nMisterX starts the game!")
//  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      contents += new MenuItem(Action("Save Game") { notYetImplemted() })
      contents += new MenuItem(Action("Quit") { closeApplication() })
    }
    contents += new Menu("Game") {
      contents += new MenuItem(Action("Undo") { notYetImplemted()})
      contents += new MenuItem(Action("New Game") { notYetImplemted() })
    }
  }

  def closeApplication() {
    val res = Dialog.showConfirmation(contents.head,
      "Do you really want to quit?",
      optionType=Dialog.Options.YesNo,
      title=title)
    if (res == Dialog.Result.Ok) {
      sys.exit(0)
    }
  }

  def notYetImplemted() {
    val res = Dialog.showConfirmation(contents.head,
      "Function is not yet implemented",
      optionType=Dialog.Options.Default,
      title=title)
  }

  var map = ImageIO.read(new File("resources/pictures/map600p.png"))


//  contents = new BoxPanel(Orientation.Horizontal) {
//    contents += menuBar
//    contents += label
//  }

  // specify which Components produce events of interest
  listenTo(gameController)

  // react to events
  reactions += {
    case e: UpdatePlayerLabels => {
      label.text = gameController.currentPlayer.name
      print("Event fired")
    }

  }


  visible = true

}