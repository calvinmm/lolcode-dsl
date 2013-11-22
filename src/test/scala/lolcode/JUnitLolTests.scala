package lolcode

import org.junit.Assert._
import org.junit.Test

import scala.sys.process._

class JUnitLolTests {

  def run_test(command: String): Vector[String] = {
    // run the tests and capture output
    val output: String = command.!!

    // remove lines from sbt and empty lines
    output.split("\n")
      .toVector
      .map(x => x.trim())
      .filterNot(_.startsWith("["))
      .filterNot(_.equals(""))
  }

  @Test
  def test_visible {
    val command: String = "make specific-test O=LolTest_Visible"
    val output = run_test(command)
    
    val actual: String = output(0)
    val expected: String = "hello world"

    assertEquals(actual, expected)
  }

  @Test
  def test_int_assign {
    val command: String = "make specific-test O=LolTest_IntAssign"
    val output = run_test(command)
    
    val expected: Vector[String] = Vector(1, 1337).map(_.toString)

    assertEquals(output, expected)
  }
  
  @Test
  def test_string_assign {
    val command: String = "make specific-test O=LolTest_StringAssign"
    val output = run_test(command)
    
    val expected: Vector[String] = Vector("cat", "doge")
    assertEquals(output, expected)
  }
  
  @Test
  def test_string_assign_2 {
    val command: String = "make specific-test O=LolTest_LOLStringAssign"
    val output = run_test(command)
    
    val expected: Vector[String] = Vector("kitteh", "cat")
    assertEquals(output, expected)
  }
  
  @Test
  def test_math_up {
    val command: String = "make specific-test O=LolTest_Math_Up"
    val output = run_test(command)
    
    val expected: Vector[String] = Vector(2+3, 1+100).map(_.toString)
    assertEquals(output, expected)
  }
  
  @Test
  def test_math_nerf {
    val command: String = "make specific-test O=LolTest_Math_Nerf"
    val output = run_test(command)
    
    val expected: Vector[String] = Vector(13-2, 1337-1000-12).map(_.toString)
    assertEquals(output, expected)
  }
  
  @Test
  def test_math_tiemz {
    val command: String = "make specific-test O=LolTest_Math_Tiemz"
    val output = run_test(command)
    
    val expected: Vector[String] = Vector(3*3, 3*20).map(_.toString)
    assertEquals(output, expected)
  }
  
  @Test
  def test_math_ovar {
    val command: String = "make specific-test O=LolTest_Math_Ovar"
    val output = run_test(command)
    
    val expected: Vector[String] = Vector(9/3, 100/10).map(_.toString)
    assertEquals(output, expected)
  }
  
  @Test
  def test_math_mod {
    val command: String = "make specific-test O=LolTest_Math_Mod"
    val output = run_test(command)
    
    val expected: Vector[String] = Vector(9%3, 25%10).map(_.toString)
    assertEquals(output, expected)
  }
  
  @Test
  def test_btw {
    val command: String = "make specific-test O=LolTest_BTW"
    val output = run_test(command)
    
    val expected: Vector[String] = Vector("show", "show")
    assertEquals(output, expected)
  }
}
