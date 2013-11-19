package lolcode

import org.junit.Assert._
import org.junit.Test

import scala.sys.process._

class JUnitLolTests {

  @Test
  def testPrint {

    // run the tests and capture output
    val output: String = "make temp-test".!!
    // remove lines from sbt and empty lines
    val lines: Vector[String] = output.split("\n")
      .toVector
      .map(x => x.trim())
      .filterNot(_.startsWith("["))
      .filterNot(_.equals(""))

    // pull out actual output
    val actual: String = lines(0)
    val expected: String = "Hello World!"

    // check if they are equal
    assertEquals(actual, expected)
  }

}