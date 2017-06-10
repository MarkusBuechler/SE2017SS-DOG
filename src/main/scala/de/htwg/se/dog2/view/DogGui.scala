package de.htwg.se.dog2.view

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing.ImageIcon

import scala.swing._
import com.google.inject.Inject
import de.htwg.se.dog2.controller._
import de.htwg.se.dog2.model.Figure

import scala.collection.mutable.ListBuffer
import scala.swing.event.ButtonClicked

/**
 * Created by margogo on 15.05.17.
 * Dog Gui
 */
// $COVERAGE-OFF$Disabling highlighting by default until scala swing integration test franework is found.
class DogGui @Inject() (gameController: gameController) extends MainFrame with Reactor {

  val SizeHeight = 700
  val SizeWidth = 1100

  val SizeWidthFlowPane = 450
  val SizeHeigthFlowPane = 60
  val myDimension = new Dimension(SizeWidth, SizeHeight)

  val myDimensionFlowPane = new Dimension(SizeWidthFlowPane, SizeHeigthFlowPane)

  val darkRed = new java.awt.Color(0, 0, 0)
  val brightRed = new java.awt.Color(196, 102, 113)

  val normalBorder = Swing.BeveledBorder(Swing.Lowered)
  val highlightedBorder = Swing.BeveledBorder(Swing.Lowered, brightRed, brightRed, brightRed, brightRed)

  var player1FlowPanel = new FlowPanel() {
    preferredSize = myDimensionFlowPane
  }
  var player2FlowPanel = new FlowPanel() {
    preferredSize = myDimensionFlowPane
  }
  var player3FlowPanel = new FlowPanel() {
    preferredSize = myDimensionFlowPane
  }
  var player4FlowPanel = new FlowPanel() {
    preferredSize = myDimensionFlowPane
  }
  var buttonPlayer1 = new Button() {
    text = "Click to change Figure"
    listenTo(this)
    reactions += {
      case ButtonClicked(me) =>
        gameController.changeCurrentFigureNr()
        refresh()
    }
  }
  var buttonPlayer2 = new Button() {
    text = "Click to change Figure"
    listenTo(this)
    reactions += {
      case ButtonClicked(me) =>
        gameController.changeCurrentFigureNr()
        refresh()
    }
  }
  var buttonPlayer3 = new Button() {
    text = "Click to change Figure"
    listenTo(this)
    reactions += {
      case ButtonClicked(me) =>
        gameController.changeCurrentFigureNr()
        refresh()
    }
  }
  var buttonPlayer4 = new Button() {
    text = "Click to change Figure"
    listenTo(this)
    reactions += {
      case ButtonClicked(me) =>
        gameController.changeCurrentFigureNr()
        refresh()
    }
  }

  title = "DOG - THE GAME"
  preferredSize = myDimension
  minimumSize = myDimension
  maximumSize = myDimension
  resizable = false

  // declare Components here, except menubar ...
  var labelPlayer1 = new Label {
    text = "Player 1"
  }

  var labelPlayer2 = new Label {
    text = "Player 2"
  }

  var labelPlayer3 = new Label {
    text = "Player 3"
  }

  var labelPlayer4 = new Label {
    text = "Player 4"
  }

  val mapPicture = new Label {
    icon = new ImageIcon("resources/pictures/map600p.png")
    border = normalBorder
    opaque = false
  }

  val canvas = new Canvas(gameController) {
  }

  //  val map = new ImageIcon("resources/pictures/map600p.png")
  lazy val pic1: ImageIcon = new ImageIcon("resources/pictures/1.png")
  lazy val pic2: ImageIcon = new ImageIcon("resources/pictures/2.png")
  lazy val pic3: ImageIcon = new ImageIcon("resources/pictures/3.png")
  lazy val pic4: ImageIcon = new ImageIcon("resources/pictures/4.png")
  lazy val pic5: ImageIcon = new ImageIcon("resources/pictures/5.png")
  lazy val pic6: ImageIcon = new ImageIcon("resources/pictures/6.png")
  lazy val pic7: ImageIcon = new ImageIcon("resources/pictures/7.png")
  lazy val pic8: ImageIcon = new ImageIcon("resources/pictures/8.png")
  lazy val pic9: ImageIcon = new ImageIcon("resources/pictures/9.png")
  lazy val pic10: ImageIcon = new ImageIcon("resources/pictures/10.png")
  lazy val pic12: ImageIcon = new ImageIcon("resources/pictures/12.png")
  lazy val pic13: ImageIcon = new ImageIcon("resources/pictures/13.png")
  lazy val picIdk: ImageIcon = new ImageIcon("resources/pictures/idk.png")

  //noinspection ScalaStyle
  def cardPic(int: Int): Label = {
    val card = new Label {
      int match {
        case 1 => icon = pic1
        case 2 => icon = pic2
        case 3 => icon = pic3
        case 4 => icon = pic4
        case 5 => icon = pic5
        case 6 => icon = pic6
        case 7 => icon = pic7
        case 8 => icon = pic8
        case 9 => icon = pic9
        case 10 => icon = pic10
        case 12 => icon = pic12
        case 13 => icon = pic13
        case _ => icon = picIdk
      }

    }
    card
  }

  var HBoxPlayer1Labels = new BoxPanel(Orientation.Horizontal) {
    contents += labelPlayer1
    contents += buttonPlayer1
  }

  var HBoxPlayer2Labels = new BoxPanel(Orientation.Horizontal) {
    contents += labelPlayer2
    contents += buttonPlayer2
  }

  var HBoxPlayer3Labels = new BoxPanel(Orientation.Horizontal) {
    contents += labelPlayer3
    contents += buttonPlayer3
  }

  var HBoxPlayer4Labels = new BoxPanel(Orientation.Horizontal) {
    contents += labelPlayer4
    contents += buttonPlayer4
  }

  var HVoxPlayer1 = new BoxPanel(Orientation.Vertical) {
    contents += player1FlowPanel
    contents += HBoxPlayer1Labels
    border = normalBorder

  }

  var HVoxPlayer2 = new BoxPanel(Orientation.Vertical) {
    contents += player2FlowPanel
    contents += HBoxPlayer2Labels
    border = normalBorder
  }

  var HVoxPlayer3 = new BoxPanel(Orientation.Vertical) {
    contents += player3FlowPanel
    contents += HBoxPlayer3Labels
    border = normalBorder
  }

  var HVoxPlayer4 = new BoxPanel(Orientation.Vertical) {
    contents += player4FlowPanel
    contents += HBoxPlayer4Labels
    border = normalBorder
  }

  var playerVBox = new BoxPanel(Orientation.Vertical) {
    contents += HVoxPlayer1
    contents += HVoxPlayer2
    contents += HVoxPlayer3
    contents += HVoxPlayer4
    resizable = false
  }

  contents = new BoxPanel(Orientation.Horizontal) {
    contents += playerVBox
    contents += canvas
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
    Dialog.showConfirmation(
      contents.head,
      "Function is not yet implemented",
      optionType = Dialog.Options.Default,
      title = title
    )
  }

  // specify which Components produce events of interest
  listenTo(gameController)

  // react to events
  reactions += {
    case e: UpdatePlayerCards =>
      updateCardPics()
      print("Event Update Cards fired")
    case e: UpdateToRepaint =>
      repaint()
  }

  /* Helper functions */
  private def updateCardPics(): Unit = {
    /* Init Player */
    player1FlowPanel.contents.clear()
    player2FlowPanel.contents.clear()
    player3FlowPanel.contents.clear()
    player4FlowPanel.contents.clear()

    for (x <- 0 to gameController.cardDecks.apply(0).cards.size - 1) {
      player1FlowPanel.contents += cardPic(gameController.cardDecks.apply(0).cards.apply(x).value)
    }

    for (x <- 0 to gameController.cardDecks.apply(1).cards.size - 1) {
      player2FlowPanel.contents += cardPic(gameController.cardDecks.apply(1).cards.apply(x).value)
    }

    for (x <- 0 to gameController.cardDecks.apply(2).cards.size - 1) {
      player3FlowPanel.contents += cardPic(gameController.cardDecks.apply(2).cards.apply(x).value)
    }

    for (x <- 0 to gameController.cardDecks.apply(3).cards.size - 1) {
      player4FlowPanel.contents += cardPic(gameController.cardDecks.apply(3).cards.apply(x).value)
    }

    updateProperties

    refresh()

  }

  private def updateProperties = {
    buttonPlayer1.visible = if (gameController.currentPlayer.playerId.equals(0)) true else false
    buttonPlayer2.visible = if (gameController.currentPlayer.playerId.equals(1)) true else false
    buttonPlayer3.visible = if (gameController.currentPlayer.playerId.equals(2)) true else false
    buttonPlayer4.visible = if (gameController.currentPlayer.playerId.equals(3)) true else false

    HVoxPlayer1.border = if (gameController.currentPlayer.playerId.equals(0)) highlightedBorder else normalBorder
    HVoxPlayer2.border = if (gameController.currentPlayer.playerId.equals(1)) highlightedBorder else normalBorder
    HVoxPlayer3.border = if (gameController.currentPlayer.playerId.equals(2)) highlightedBorder else normalBorder
    HVoxPlayer4.border = if (gameController.currentPlayer.playerId.equals(3)) highlightedBorder else normalBorder
  }

  updateCardPics()

  /**
   * Helper function to refresh and revalidate the playerCards
   */
  private def refresh(): Unit = {

    player1FlowPanel.revalidate()
    player2FlowPanel.revalidate()
    player3FlowPanel.revalidate()
    player4FlowPanel.revalidate()

    player1FlowPanel.repaint()
    player2FlowPanel.repaint()
    player3FlowPanel.repaint()
    player4FlowPanel.repaint()
  }

  visible = true

  /*TODO:
  click auf karten ermöglichen
  HoverListener auf die Karten
  Gui verschönern

  Later:
  Figuren wechseln innerhalb player
  Figuren auf Map anzeigen und anklickbar machen
  Allgemeine Funktionalität erhöhen
  */

}

case class FigureDraw(x: Int, y: Int, color: java.awt.Color)

class Canvas @Inject() (gameController: gameController) extends Panel with Reactor {

  val map: BufferedImage = ImageIO.read(new File("resources/pictures/map600p.png"))
  val deviation = 13
  val figureRadius = 25
  val dimSize = 600
  val myDimension = new Dimension(dimSize, dimSize)

  preferredSize = myDimension

  //  resizable = false
  var figures: ListBuffer[Figure] = gameController.figureList
  //  var currentFigur = gameController.currentFig

  override def paintComponent(g: Graphics2D) {

    // Start by erasing this Canvas
    g.clearRect(0, 0, size.width, size.height)
    g.drawImage(map, 0, 0, map.getWidth, map.getHeight, 0, 0, map.getWidth, map.getHeight, null)

    // Draw things that change on top of background
    for (figure <- figures) {

      g.setColor(figure.color)

      if (figure.playerFigNumber.equals(gameController.currentFigNr)) {
        g.setColor(Color.BLACK)
      }

      g.fillOval(figure.x - deviation, figure.y - deviation, figureRadius, figureRadius)
    }

  }
  // Do this outside to avoid huge performance issues
  this.revalidate()
  this.repaint()
}
// $COVERAGE-ON$
