package lolcode

import org.junit.Assert._
import org.junit.Test

import scala.collection.mutable.Buffer
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
      .filterNot(_.startsWith("make["))
  }

  def run_test_error(command: String): Vector[String] = {
    val out = Buffer[String]()

    // use a special logger to capture stderr
    val logger = ProcessLogger(
      (o: String) => out += o,
      (e: String) => out += e)

    command ! logger

    // remove lines from sbt and empty lines
    out.toVector
      .map(x => x.trim())
      .map(x => x.replace("[31m", ""))
      .filterNot(_.startsWith("["))
      .filterNot(_.equals(""))
      .filterNot(_.startsWith("make["))
  }

  @Test
  def test_visible {
    val command: String = "make specific-test O=LolTest_Visible"
    val output = run_test(command)

    val expected: Vector[String] = Vector("hello world", 3, 4.5).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_complain {
    val command: String = "make specific-test O=LolTest_Complain"
    val output = run_test_error(command)

    val expected: Vector[String] =
      Vector("hello world", "goodbye world" + Console.RESET, 10.2 + Console.RESET).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_int_assign {
    val command: String = "make specific-test O=LolTest_IntAssign"
    val output = run_test(command)

    val expected: Vector[String] = Vector(1, 1337).map(_.toString)
    assertEquals(expected, output)
  }
  
  @Test
  def test_int_int_assign {
    val command: String = "make specific-test O=LolTest_Int_IntAssign"
    val output = run_test(command)

    val expected: Vector[String] = Vector(1).map(_.toString) ++ Vector(2.0, 8.0).map(_.toString)
    assertEquals(expected, output)
  }
  
  @Test
  def test_int_double_assign {
    val command: String = "make specific-test O=LolTest_Int_DoubleAssign"
    val output = run_test(command)

    val expected: Vector[String] = Vector((1+1.2) * 4).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_string_assign {
    val command: String = "make specific-test O=LolTest_StringAssign"
    val output = run_test(command)

    val expected: Vector[String] = Vector("cat", "doge")
    assertEquals(expected, output)
  }

  @Test
  def test_string_assign_2 {
    val command: String = "make specific-test O=LolTest_LOLStringAssign"
    val output = run_test(command)

    val expected: Vector[String] = Vector("kitteh", "cat")
    assertEquals(expected, output)
  }

  @Test
  def test_string_double {
    val command: String = "make specific-test O=LolTest_DoubleAssign"
    val output = run_test(command)

    val expected: Vector[String] = Vector(3.5, 10.2).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_math_up {
    val command: String = "make specific-test O=LolTest_Math_Up"
    val output = run_test(command)

    val expected: Vector[String] = Vector(2 + 3, 1 + 100).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_math_double_up {
    val command: String = "make specific-test O=LolTest_Math_Double_Up"
    val output = run_test(command)

    val expected: Vector[String] = Vector(2.5 + 3, 1.2 + 100).map(_.toString)
    assertEquals(expected, output)
  }

//This test fails until we have order of operations done correctly
/*
  @Test
  def test_math_up_tiemz {
    val command: String = "make specific-test O=LolTest_Math_Up_Tiemz"
    val output = run_test(command)

    val expected: Vector[String] = Vector(13 + 100 * 12).map(_.toString)
    assertEquals(expected, output)
  }*/

  @Test
  def test_math_nerf {
    val command: String = "make specific-test O=LolTest_Math_Nerf"
    val output = run_test(command)

    val expected: Vector[String] = Vector(13 - 2, 1337 - 1000 - 12).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_math_double_nerf {
    val command: String = "make specific-test O=LolTest_Math_Double_Nerf"
    val output = run_test(command)

    val expected: Vector[String] = Vector(13.2 - 2.1, 1337.3 - 1000.2 - 32.5).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_math_tiemz {
    val command: String = "make specific-test O=LolTest_Math_Tiemz"
    val output = run_test(command)

    val expected: Vector[String] = Vector(3 * 3, 3 * 20).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_math_double_tiemz {
    val command: String = "make specific-test O=LolTest_Math_Double_Tiemz"
    val output = run_test(command)

    val expected: Vector[String] = Vector(3.3 * 3.3, 3 * 20.2).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_math_ovar {
    val command: String = "make specific-test O=LolTest_Math_Ovar"
    val output = run_test(command)

    val expected: Vector[String] = Vector(9 / 3, 100 / 10).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_math_double_ovar {
    val command: String = "make specific-test O=LolTest_Math_Double_Ovar"
    val output = run_test(command)

    val expected: Vector[String] = Vector(9.3 / 3, 100.1 / 3.4).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_math_mod {
    val command: String = "make specific-test O=LolTest_Math_Mod"
    val output = run_test(command)

    val expected: Vector[String] = Vector(9 % 3, 25 % 10).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_math_double_mod {
    val command: String = "make specific-test O=LolTest_Math_Double_Mod"
    val output = run_test(command)

    val expected: Vector[String] = Vector(9.2 % 3.9, 12.3 % 2.3).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_btw {
    val command: String = "make specific-test O=LolTest_BTW"
    val output = run_test(command)

    val expected: Vector[String] = Vector("show", "show")
    assertEquals(expected, output)
  }
}
