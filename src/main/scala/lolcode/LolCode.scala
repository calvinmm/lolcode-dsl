package lolcode

import scala.collection.mutable.HashMap
import scala.collection.mutable.Stack

class LolCode {
  abstract sealed class LolLine
  case class PrintString(num: Int, s: String) extends LolLine
  case class PrintVariable(num: Int, s: Symbol) extends LolLine
  case class PrintNumber(num: Int, s: Int) extends LolLine
  case class PrintFunction(num: Int, s: Function0[Int]) extends LolLine
  case class ErrorPrintString(num: Int, s: String) extends LolLine
  case class ErrorPrintVariable(num: Int, s: Symbol) extends LolLine
  case class ErrorPrintNumber(num: Int, s: Int) extends LolLine
  case class ErrorPrintFunction(num: Int, s: Function0[Int]) extends LolLine
  case class If(num: Int, fun: Function0[Boolean]) extends LolLine
  case class StartFalse(num: Int) extends LolLine
  case class EndIf(num: Int) extends LolLine
  case class Assign(num: Int, fn: Function0[Unit]) extends LolLine
  case class End(num: Int) extends LolLine

  // keep track of which line we are on
  var current: Int = 1

  var lines = new HashMap[Int, LolLine]
  val binds = new Bindings[AnyVal]
  
  def KTHXBYE() = {
    lines(current) = End(current)
    gotoLine(lines.keys.toList.sorted.head)
  }

  def HAI() = {
    lines = new HashMap[Int, LolLine]
  }

  def NOWAI() = {
    lines(current) = StartFalse(current)
    current += 1
  }

  def KTHX() = {
    lines(current) = EndIf(current)
    current += 1
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
    def ITZ(v: Function0[Int]): Unit = {
      lines(current) = Assign(current, (() => binds.set(sym, v())))
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
    def R(v: Function0[Int]): Unit = {
      lines(current) = Assign(current, (() => binds.set(sym, v())))
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
      case PrintFunction(_, s: Function0[Int]) => {
        println(s())
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
      case ErrorPrintFunction(_, s: Function0[Int]) => {
        Console.err.println(Console.RED + s() + Console.RESET)
        gotoLine(line + 1)
      }

      case If(_, fun: Function0[Boolean]) => {
        if(fun()) {
          gotoLine(line + 1)
        } else {
          var curLine = line
	  while(!(lines(curLine).isInstanceOf[StartFalse] || lines(curLine).isInstanceOf[EndIf])) {
	    curLine += 1
	  }
	  gotoLine(curLine+1)
        }
      }

      case StartFalse(_) => {
	// Only reach this if true was executed
	var lineVar = line
	while(!lines(lineVar).isInstanceOf[EndIf]) {
	    lineVar = lineVar + 1;
	}
	gotoLine(lineVar+1);
      }

      case EndIf(_) => {
        gotoLine(line+1);
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

  // infix operators
  implicit def operator_int(i: Int) = new {
    def UP(j: Int): Function0[Int] = { () => (i + j) }
    def NERF(j: Int): Function0[Int] = { () => (i - j) }
    def TIEMZ(j: Int): Function0[Int] = { () => (i * j) }
    def OVAR(j: Int): Function0[Int] = { () => (i / j) }
    def MOD(j: Int): Function0[Int] = { () => (i % j) }
    def BIGR_THAN(j: Int): Function0[Boolean] = { () => (i > j) }
    def SMALLR_THAN(j: Int): Function0[Boolean] = { () => (i < j) }
    def LIEK(j: Int): Function0[Boolean] = { () => (i == j) }
    def UP(j: Function0[Int]): Function0[Int] = { () => (i + j()) }
    def NERF(j: Function0[Int]): Function0[Int] = { () => (i - j()) }
    def TIEMZ(j: Function0[Int]): Function0[Int] = { () => (i * j()) }
    def OVAR(j: Function0[Int]): Function0[Int] = { () => (i / j()) }
    def MOD(j: Function0[Int]): Function0[Int] = { () => (i % j()) }
  }

  implicit def operator_symbol(i: Symbol) = new {
    def UP(j: Int): Function0[Int] = { () => binds.num(i) + j }
    def TIEMZ(j: Int): Function0[Int] = { () => binds.num(i) * j }
    def BIGR_THAN(j: Int): Function0[Boolean] = { () => (binds.num(i) > j) }
    def SMALLR_THAN(j: Int): Function0[Boolean] = { () => (binds.num(i) < j) }
    def LIEK(j: Int): Function0[Boolean] = { () => (binds.num(i) == j) }
  }

  implicit def operator_function(i: Function0[Int]) = new {
    def UP(j: Int): Function0[Int] = { () => (i() + j) }
    def NERF(j: Int): Function0[Int] = { () => (i() - j) }
    def TIEMZ(j: Int): Function0[Int] = { () => (i() * j) }
    def OVAR(j: Int): Function0[Int] = { () => (i() / j) }
    def MOD(j: Int): Function0[Int] = { () => (i() % j) }
    def UP(j: Function0[Int]): Function0[Int] = { () => (i() + j()) }
    def NERF(j: Function0[Int]): Function0[Int] = { () => (i() - j()) }
    def TIEMZ(j: Function0[Int]): Function0[Int] = { () => (i() * j()) }
    def OVAR(j: Function0[Int]): Function0[Int] = { () => (i() / j()) }
    def MOD(j: Function0[Int]): Function0[Int] = { () => (i() % j()) }
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
    def apply(s: Function0[Int]) = {
      lines(current) = PrintFunction(current, s)
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

  object IZ {    
    def apply(s: Function0[Boolean]) = {
      lines(current) = If(current, s)
      current += 1
    }
  }

  class Bindings[U] {
    val numerics = HashMap[Symbol, U]()

    def set[X >: U](k: Symbol, v: X) = v match {
      case u: U => numerics(k) = u
    }
    def num(k: Symbol): Int = {
      numerics(k) match {
        case j: Int => j
      }
    }

    def any(k: Symbol): Any = {
      numerics.get(k) match {
        case Some(x) => x
        case None => None
      }
    }
  }
}
