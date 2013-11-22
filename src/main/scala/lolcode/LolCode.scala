package lolcode

import scala.collection.mutable.HashMap
import scala.collection.mutable.Stack

class LolCode {
  abstract sealed class LolLine
  case class PrintString(num: Int, s: String) extends LolLine
  case class PrintVariable(num: Int, s: Symbol) extends LolLine
  case class PrintNumber(num: Int, s: Int) extends LolLine
  case class PrintDouble(num: Int, s: Double) extends LolLine
  case class ErrorPrintString(num: Int, s: String) extends LolLine
  case class ErrorPrintVariable(num: Int, s: Symbol) extends LolLine
  case class ErrorPrintNumber(num: Int, s: Int) extends LolLine
  case class ErrorPrintDouble(num: Int, s: Double) extends LolLine
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
    def ITZ(v: Double): Unit = {
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
    def R(v: Double): Unit = {
      lines(current) = Assign(current, (() => binds.set(sym, v)))
      current += 1
    }
  }

  /**
   * runtime evaluator of LOLCODE
   */
  private def gotoLine(line: Int) {
    lines(line) match {
      // print to stdout
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
      case PrintDouble(_, s: Double) => {
        println(s)
        gotoLine(line + 1)
      }
      // print to stderr
      case ErrorPrintString(_, s: String) => {
        Console.err.println(Console.RED + s + Console.RESET)
        gotoLine(line + 1)
      }
      case ErrorPrintVariable(_, s: Symbol) => {
        Console.err.println(Console.RED + binds.any(s) + Console.RESET)
        gotoLine(line + 1)
      }
      case ErrorPrintNumber(_, s: Int) => {
        Console.err.println(Console.RED + s + Console.RESET)
        gotoLine(line + 1)
      }
      case ErrorPrintDouble(_, s: Double) => {
        Console.err.println(Console.RED + s + Console.RESET)
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

  // prefix operators
  def UP(i: Int, j: Int): Int = { i + j }
  def NERF(i: Int, j: Int): Int = { i - j }
  def TIEMZ(i: Int, j: Int): Int = { i * j }
  def OVAR(i: Int, j: Int): Int = { i / j }
  def MOD(i: Int, j: Int): Int = { i % j }

  def UP(i: Int, j: Double): Double = { i + j }
  def NERF(i: Int, j: Double): Double = { i - j }
  def TIEMZ(i: Int, j: Double): Double = { i * j }
  def OVAR(i: Int, j: Double): Double = { i / j }
  def MOD(i: Int, j: Double): Double = { i % j }

  def UP(i: Double, j: Int): Double = { i + j }
  def NERF(i: Double, j: Int): Double = { i - j }
  def TIEMZ(i: Double, j: Int): Double = { i * j }
  def OVAR(i: Double, j: Int): Double = { i / j }
  def MOD(i: Double, j: Int): Double = { i % j }

  def UP(i: Double, j: Double): Double = { i + j }
  def NERF(i: Double, j: Double): Double = { i - j }
  def TIEMZ(i: Double, j: Double): Double = { i * j }
  def OVAR(i: Double, j: Double): Double = { i / j }
  def MOD(i: Double, j: Double): Double = { i % j }

  // infix operators
  implicit def operator_int(i: Int) = new {
    def UP(j: Int): Int = { i + j }
    def NERF(j: Int): Int = { i - j }
    def TIEMZ(j: Int): Int = { i * j }
    def OVAR(j: Int): Int = { i / j }
    def MOD(j: Int): Int = { i % j }
    def UP(j: Double): Double = { i + j }
    def NERF(j: Double): Double = { i - j }
    def TIEMZ(j: Double): Double = { i * j }
    def OVAR(j: Double): Double = { i / j }
    def MOD(j: Double): Double = { i % j }
    def UP(j: Symbol): Double = { i + binds.num(j) }
    def NERF(j: Symbol): Double = { i - binds.num(j) }
    def TIEMZ(j: Symbol): Double = { i * binds.num(j) }
    def OVAR(j: Symbol): Double = { i / binds.num(j) }
    def MOD(j: Symbol): Double = { i % binds.num(j) }
  }

  implicit def operator_double(i: Double) = new {
    def UP(j: Int): Double = { i + j }
    def NERF(j: Int): Double = { i - j }
    def TIEMZ(j: Int): Double = { i * j }
    def OVAR(j: Int): Double = { i / j }
    def MOD(j: Int): Double = { i % j }
    def UP(j: Double): Double = { i + j }
    def NERF(j: Double): Double = { i - j }
    def TIEMZ(j: Double): Double = { i * j }
    def OVAR(j: Double): Double = { i / j }
    def MOD(j: Double): Double = { i % j }
    def UP(j: Symbol): Double = { i + binds.num(j) }
    def NERF(j: Symbol): Double = { i - binds.num(j) }
    def TIEMZ(j: Symbol): Double = { i * binds.num(j) }
    def OVAR(j: Symbol): Double = { i / binds.num(j) }
    def MOD(j: Symbol): Double = { i % binds.num(j) }    
  }

  implicit def operator_symbol(i: Symbol) = new {
    def UP(j: Int): Double = { binds.num(i) + j }
    def NERF(j: Int): Double = { binds.num(i) - j }
    def TIEMZ(j: Int): Double = { binds.num(i) * j }
    def OVAR(j: Int): Double = { binds.num(i) / j }
    def MOD(j: Int): Double = { binds.num(i) % j }
    def UP(j: Double): Double = { binds.num(i) + j }
    def NERF(j: Double): Double = { binds.num(i) - j }
    def TIEMZ(j: Double): Double = { binds.num(i) * j }
    def OVAR(j: Double): Double = { binds.num(i) / j }
    def MOD(j: Double): Double = { binds.num(i) % j }
    def UP(j: Symbol): Double = { binds.num(i) + binds.num(j) }
    def NERF(j: Symbol): Double = { binds.num(i) - binds.num(j) }
    def TIEMZ(j: Symbol): Double = { binds.num(i) * binds.num(j) }
    def OVAR(j: Symbol): Double = { binds.num(i) / binds.num(j) }
    def MOD(j: Symbol): Double = { binds.num(i) % binds.num(j) }    
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
    def apply(s: Double) = {
      lines(current) = PrintDouble(current, s)
      current += 1
    }
  }
  
  object COMPLAIN {
    def apply(s: String) = {
      lines(current) = ErrorPrintString(current, s)
      current += 1
    }
    def apply(s: Symbol) = {
      lines(current) = ErrorPrintVariable(current, s)
      current += 1
    }
    def apply(s: Int) = {
      lines(current) = ErrorPrintNumber(current, s)
      current += 1
    }
    def apply(s: Double) = {
      lines(current) = ErrorPrintDouble(current, s)
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
