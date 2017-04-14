package de.htwg.se.yourgame.testingScala

/**
  * Created by margogo on 11.04.17.
  */
class TestObjects {

  def toUpper(input: String) = {
    input
      .toUpperCase()
  }
  def toLower(input: String) = {
    input
      .toLowerCase
  }
  def reverseString(input1: String, input2: String, input3: String) = {
      input3.concat(input2).concat(input1)
  }

}
