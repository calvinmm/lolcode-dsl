package lolcode

import org.junit.Assert._
import org.junit.Test

import scala.collection.mutable.Buffer
import scala.sys.process._
import scala.math.{ min, max }

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

    val expected: Vector[String] = Vector("hello world", 3).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_complain {
    val command: String = "make specific-test O=LolTest_Complain"
    val output = run_test_error(command)

    val expected: Vector[String] =
      Vector("hello world", "goodbye world" + Console.RESET, 10 + Console.RESET).map(_.toString)
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
  def test_int_reverse_assign {
    val command: String = "make specific-test O=LolTest_Int_ReverseAssign"
    val output = run_test(command)

    val expected: Vector[String] = Vector(150, 150).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_int_int_assign {
    val command: String = "make specific-test O=LolTest_Int_IntAssign"
    val output = run_test(command)

    val expected: Vector[String] = Vector(1, 2, 8).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_int_assign_print {
    val command: String = "make specific-test O=LolTest_Int_Assign_Print"
    val output = run_test(command)

    val expected: Vector[String] = Vector((1 + 1) * 4).map(_.toString)
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
  def test_math_up {
    val command: String = "make specific-test O=LolTest_Math_Up"
    val output = run_test(command)

    val expected: Vector[String] = Vector(2 + 3, 1 + 100).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_math_up_tiemz {
    val command: String = "make specific-test O=LolTest_Math_Up_Tiemz"
    val output = run_test(command)

    val expected: Vector[String] = Vector(13 + 100 * 12).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_math_nerf {
    val command: String = "make specific-test O=LolTest_Math_Nerf"
    val output = run_test(command)

    val expected: Vector[String] = Vector(13 - 2, 1337 - 1000 - 12).map(_.toString)
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
  def test_math_ovar {
    val command: String = "make specific-test O=LolTest_Math_Ovar"
    val output = run_test(command)

    val expected: Vector[String] = Vector(9 / 3, 100 / 10).map(_.toString)
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
  def test_btw {
    val command: String = "make specific-test O=LolTest_BTW"
    val output = run_test(command)

    val expected: Vector[String] = Vector("show", "show")
    assertEquals(expected, output)
  }

  @Test
  def test_if_true_nums {
    val command: String = "make specific-test O=LolTest_IZ_TRUE_NUMS"
    val output = run_test(command)

    val expected: Vector[String] = Vector(5).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_if_false_nums {
    val command: String = "make specific-test O=LolTest_IZ_FALSE_NUMS"
    val output = run_test(command)

    val expected: Vector[String] = Vector(6).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_nested_ifs_false_true {
    val command: String = "make specific-test O=LolTest_NESTED_IZ_FALSE_TRUE"
    val output = run_test(command)

    val expected: Vector[String] = Vector(15).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_nested_ifs_false_false {
    val command: String = "make specific-test O=LolTest_NESTED_IZ_FALSE_FALSE"
    val output = run_test(command)

    val expected: Vector[String] = Vector(16).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_nested_ifs_true_false {
    val command: String = "make specific-test O=LolTest_NESTED_IZ_TRUE_FALSE"
    val output = run_test(command)

    val expected: Vector[String] = Vector(14).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_nested_ifs_true_true {
    val command: String = "make specific-test O=LolTest_NESTED_IZ_TRUE_TRUE"
    val output = run_test(command)

    val expected: Vector[String] = Vector(13).map(_.toString)
    assertEquals(expected, output)
  }


  @Test
  def test_if_true_var {
    val command: String = "make specific-test O=LolTest_IZ_TRUE_VAR"
    val output = run_test(command)

    val expected: Vector[String] = Vector(5).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_if_false_var {
    val command: String = "make specific-test O=LolTest_IZ_FALSE_VAR"
    val output = run_test(command)

    val expected: Vector[String] = Vector(6).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_gimmeh {
    val command: String = "make specific-test-input O=LolTest_GIMMEH INPUT=4"
    val output = run_test(command)

    val expected: Vector[String] = Vector(4, 4 * 30).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_gimmeh_string {
    val command: String = "make specific-test-input O=LolTest_GIMMEH_STRING INPUT=hello"
    val output = run_test(command)

    val expected: Vector[String] = Vector("hello").map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_boolean_ops {
    val command: String = "make specific-test O=LolTest_BOOLEAN_OPS"
    val output = run_test(command)

    val expected: Vector[String] = Vector(true, false, true, false, true).map(_.toString)
    assertEquals(expected, output)
  }

  @Test
  def test_loop {
    val command: String = "make specific-test O=LolTest_Loop"
    val output = run_test(command)

    val expected: Vector[String] = Vector(
      "loop", "loop", "loop", "loop", "loop", "loop", "loop")
    assertEquals(expected, output)
  }

  @Test
  def test_nested_loops {
    val command: String = "make specific-test O=LolTest_Nested_Loops"
    val output = run_test(command)

    val expected: Vector[String] = Vector("9", "8", "7", "11", "12",
      "13")
    assertEquals(expected, output)
  }

  @Test
  def test_nested_loops2 {
    val command: String = "make specific-test O=LolTest_Nested_Loops2"
    val output = run_test(command)

    val expected: Vector[String] = Vector("11", "9", "8", "7", "12", "13")
    assertEquals(expected, output)
  }

  @Test
  def test_print_many {
    val command: String = "make specific-test O=LolTest_Print_Many"
    val output = run_test(command)

    val expected: Vector[String] = Vector(Vector("n", 10, "n+10", 10 + 10).map(_.toString).mkString(" "))
    assertEquals(expected, output)
  }

  @Test
  def test_max_min {
    val command: String = "make specific-test O=LolTest_Max_Min"
    val output = run_test(command)

    val (n, m) = (3, 10)

    val expected: Vector[String] = Vector(
      max(n, m), min(n, m), max(n, 1337), min(-20, m),
      max(n, n * m), min(m / n, n * m)).map(_.toString)
    assertEquals(expected, output)
  }
}
