package de.htwg.se.yourgame

import de.htwg.se.yourgame.model.Student

object Hello {
  def main(args: Array[String]): Unit = {
    val student = Student("Markus")
    println("Hello, " + student.name)
  }
}
