package de.htwg.se.yourgame.model.SpecTest

import org.scalatest.{ FlatSpec, Matchers }

import scala.collection.mutable

/**
 * Created by margogo on 10.05.17.
 */
class ExampleSpec extends FlatSpec with Matchers {

  "A Stack" should "pop values in last-in-first-out order" in {
    val stack = new mutable.Stack[Int]
    stack.push(1)
    stack.push(2)
    stack.pop() should be(2)
    stack.push(3)
    stack.pop() should be(3)
    stack.pop() should be(1)
  }

  it should "throw NoSuchElementException if an empty stack is popped" in {
    val emptyStack = new mutable.Stack[Int]
    a[NoSuchElementException] should be thrownBy {
      emptyStack.pop()
    }

  }
}
