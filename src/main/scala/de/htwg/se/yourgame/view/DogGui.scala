package de.htwg.se.yourgame.view

import java.io.File
import javax.imageio.ImageIO
import javax.swing.ImageIcon

import scala.swing._
import com.google.inject.Inject
import de.htwg.se.yourgame.controller.{ UpdatePlayerCards, UpdatePlayerLabels, gameController }

/**
 * Created by margogo on 15.05.17.
 */
class DogGui @Inject() (gameController: gameController) extends MainFrame with Reactor {

  val SizeHeight = 700
  val SizeWidth = 900

  var player1 = new BoxPanel(Orientation.Vertical)
  var player2 = new BoxPanel(Orientation.Horizontal)
  var player3 = new BoxPanel(Orientation.Vertical)
  var player4 = new BoxPanel(Orientation.Horizontal)

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

  //noinspection ScalaStyle
  def cardPic(int: Int): Label = {
    val card = new Label {
      int match {
        case 1 => icon = new ImageIcon("resources/pictures/1.png")
        case 2 => icon = new ImageIcon("resources/pictures/2.png")
        case 3 => icon = new ImageIcon("resources/pictures/3.png")
        case 4 => icon = new ImageIcon("resources/pictures/4.png")
        case 5 => icon = new ImageIcon("resources/pictures/5.png")
        case 6 => icon = new ImageIcon("resources/pictures/6.png")
        case 7 => icon = new ImageIcon("resources/pictures/7.png")
        case 8 => icon = new ImageIcon("resources/pictures/8.png")
        case 9 => icon = new ImageIcon("resources/pictures/9.png")
        case 10 => icon = new ImageIcon("resources/pictures/10.png")
        case 12 => icon = new ImageIcon("resources/pictures/12.png")
        case 13 => icon = new ImageIcon("resources/pictures/13.png")
        case _ => icon = new ImageIcon("resources/pictures/idk.png")
      }

    }
    card
  }

  updateCardPics

  contents = new BorderPanel {
    add(player2, BorderPanel.Position.North)
    add(mapPicture, BorderPanel.Position.Center)
    add(player3, BorderPanel.Position.East)
    add(player1, BorderPanel.Position.West)
    add(player4, BorderPanel.Position.South)
  }

  visible = true

  //  contents = new BoxPanel(Orientation.Horizontal) {
  //    contents += menuBar
  //    contents += label
  //    Dialog.showMessage(contents.head, "Welcome to DOG - The Game \nPress enter to start the game!")
  //  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      contents += new MenuItem(Action("Save Game") { notYetImplemted() })
      contents += new MenuItem(Action("Quit") { closeApplication() })
    }
    contents += new Menu("Game") {
      contents += new MenuItem(Action("Undo") { notYetImplemted() })
      contents += new MenuItem(Action("New Game") { notYetImplemted() })
    }
  }

  def closeApplication() {
    val res = Dialog.showConfirmation(
      contents.head,
      "Do you really want to quit?",
      optionType = Dialog.Options.YesNo,
      title = title
    )
    if (res == Dialog.Result.Ok) {
      sys.exit(0)
    }
  }

  def notYetImplemted() {
    val res = Dialog.showConfirmation(
      contents.head,
      "Function is not yet implemented",
      optionType = Dialog.Options.Default,
      title = title
    )
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
      print("Event Update Label fired")
    }
    case e: UpdatePlayerCards => {
      updateCardPics
      print("Event Update Cards fired")
    }

  }

  /* Helper functions */
  private def updateCardPics = {
    /* Init Player */
    player1.contents.clear()
    player2.contents.clear()
    player3.contents.clear()
    player4.contents.clear()

    for (x <- 0 to gameController.cardDecks.apply(0).cards.size - 1) {
      player1.contents += cardPic(gameController.cardDecks.apply(0).cards.apply(x).value)
    }

    for (x <- 0 to gameController.cardDecks.apply(1).cards.size - 1) {
      player2.contents += cardPic(gameController.cardDecks.apply(1).cards.apply(x).value)
    }

    for (x <- 0 to gameController.cardDecks.apply(2).cards.size - 1) {
      player3.contents += cardPic(gameController.cardDecks.apply(2).cards.apply(x).value)
    }

    for (x <- 0 to gameController.cardDecks.apply(3).cards.size - 1) {
      player4.contents += cardPic(gameController.cardDecks.apply(3).cards.apply(x).value)
    }

    refresh

  }

  /**
   * Helper function to refresh and revalidate the playerCards
   */
  private def refresh = {
    player1.repaint()
    player2.repaint()
    player3.repaint()
    player4.repaint()

    player1.revalidate()
    player2.revalidate()
    player3.revalidate()
    player4.revalidate()
  }

  visible = true

  /*TODO:
  click auf karten ermöglichen
  current player in extra label anzeigen ? VBox hervorheben
  HoverListener auf die Karten
  Gui verschönern

  Later:
  Figuren auf Map anzeigen und anklickbar machen
  Allgemeine Funktionalität erhöhen
  */

}