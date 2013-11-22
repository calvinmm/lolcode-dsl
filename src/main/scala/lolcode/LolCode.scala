package lolcode

import scala.collection.mutable.HashMap
import scala.collection.mutable.Stack

class LolCode {
  abstract sealed class LolLine
  case class PrintString(num: Int, s: String) extends LolLine
  case class PrintVariable(num: Int, s: Symbol) extends LolLine
  case class PrintNumber(num: Int, s: Int) extends LolLine
  case class Assign(num: Int, fn: Function0[Unit]) extends LolLine
  case class End(num: Int) extends LolLine

  // keep track of which line we are on
  var current: Int = 1

  var lines = new HashMap[Int, LolLine]
  val binds = new Bindings[String, Int]

  def KTHXBYE() = {
    lines(current) = End(current)
    gotoLine(lines.keys.toList.sorted.head)
  }

  def HAI() = {
    lines = new HashMap[Int, LolLine]
  }

  /**
   * assign those things
   */
  case class Assignment(sym: Symbol) {
    def ITZ(v: String): Unit = {
      lines(current) = Assign(current, (() => binds.set(sym, v)))
      current += 1
    }
    def ITZ(v: Int): Unit = {
      lines(current) = Assign(current, (() => binds.set(sym, v)))
      current += 1
    }

    def R(v: String): Unit = {
      lines(current) = Assign(current, (() => binds.set(sym, v)))
      current += 1
    }
    def R(v: Int): Unit = {
      lines(current) = Assign(current, (() => binds.set(sym, v)))
      current += 1
    }
  }

  /**
   * build our lines
   */
  case class LineBuilder(num: Int) {
    def END() = lines(num) = End(num)

    object PRINT {
      def apply(str: String) = lines(num) = PrintString(num, str)
      def apply(s: Symbol) = lines(num) = PrintVariable(num, s)
    }
    object I_HAZ_A {
      def apply(fn: Function0[Unit]) = lines(num) = Assign(num, fn)
    }
    object LOL {
      def apply(fn: Function0[Unit]) = lines(num) = Assign(num, fn)
    }
  }

  /**
   * runtime evaluator of LOLCODE
   */
  private def gotoLine(line: Int) {
    lines(line) match {
      case PrintString(_, s: String) => {
        println(s)
        gotoLine(line + 1)
      }
      case PrintVariable(_, s: Symbol) => {
        println(binds.any(s))
        gotoLine(line + 1)
      }
      case PrintNumber(_, s: Int) => {
        println(s)
        gotoLine(line + 1)
      }
      case Assign(_, fn: Function0[Unit]) =>
        {
          fn()
          gotoLine(line + 1)
        }
      case End(_) =>
      case _ =>
    }
  }

  // implicit magic
  implicit def newline(i: Int) = LineBuilder(i)
  implicit def symbol2Assignment(sym: Symbol) = Assignment(sym)

  // prefix operators
  def UP(i: Int, j: Int): Int = { i + j }
  def NERF(i: Int, j: Int): Int = { i - j }
  def TIEMZ(i: Int, j: Int): Int = { i * j }
  def OVAR(i: Int, j: Int): Int = { i / j }
  def MOD(i: Int, j: Int): Int = { i % j }

  // infix operators
  implicit def operator_int(i: Int) = new {
    def UP(j: Int): Int = { i + j }
    def NERF(j: Int): Int = { i - j }
    def TIEMZ(j: Int): Int = { i * j }
    def OVAR(j: Int): Int = { i / j }
    def MOD(j: Int): Int = { i % j }
  }

  object VISIBLE {
    def apply(s: String) = {
      lines(current) = PrintString(current, s)
      current += 1
    }
    def apply(s: Symbol) = {
      lines(current) = PrintVariable(current, s)
      current += 1
    }
    def apply(s: Int) = {
      lines(current) = PrintNumber(current, s)
      current += 1
    }
  }

  object I_HAZ_A {
    def apply(s: Symbol) = Assignment(s)
  }

  object LOL {
    def apply(s: Symbol) = Assignment(s)
  }

  object BTW {
    def apply(s: Any) = {}
  }

  class Bindings[T, U] {
    val atoms = HashMap[Symbol, T]()
    val numerics = HashMap[Symbol, U]()

    def set[X >: T with U](k: Symbol, v: X) = v match {
      case u: U => numerics(k) = u
      case t: T => atoms(k) = t
    }
    def atom(k: Symbol): T = atoms(k)
    def num(k: Symbol): U = numerics(k)

    def any(k: Symbol): Any = {
      (atoms.get(k), numerics.get(k)) match {
        case (Some(x), None) => x
        case (None, Some(y)) => y
        case (None, None) => None
        case (Some(x), Some(y)) => Some(x, y)
      }
    }
  }
}
