package lolcode

import scala.collection.mutable.HashMap
import scala.collection.mutable.Stack
import scala.util.Random

class LolCode {
  abstract sealed class LolLine
  case class PrintString(num: Int, s: String) extends LolLine
  case class PrintVariable(num: Int, s: Symbol) extends LolLine
  case class PrintNumber(num: Int, s: Int) extends LolLine
  case class PrintFunction(num: Int, s: Function0[Any]) extends LolLine
  case class ReadString(num: Int, s: Symbol) extends LolLine
  case class ErrorPrintString(num: Int, s: String) extends LolLine
  case class ErrorPrintVariable(num: Int, s: Symbol) extends LolLine
  case class ErrorPrintNumber(num: Int, s: Int) extends LolLine
  case class ErrorPrintFunction(num: Int, s: Function0[Any]) extends LolLine
  case class If(num: Int, fun: Function0[Boolean]) extends LolLine
  case class StartFalse(num: Int) extends LolLine
  case class EndIf(num: Int) extends LolLine
  case class Assign(num: Int, fn: Function0[Unit]) extends LolLine
  case class End(num: Int) extends LolLine

  // keep track of which line we are on
  var current: Int = 1

  var lines = new HashMap[Int, LolLine]
  val binds = new Bindings
  val random = new Random

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
      case PrintFunction(_, s: Function0[Any]) => {
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
      case ErrorPrintFunction(_, s: Function0[Any]) => {
        Console.err.println(Console.RED + s() + Console.RESET)
        gotoLine(line + 1)
      }
      case ReadString(_, s: Symbol) => {
        val value: Any = tryInt(readLine())
        binds.set(s, value)
        gotoLine(line + 1)
      }

      case If(_, fun: Function0[Boolean]) => {
        if (fun()) {
          gotoLine(line + 1)
        } else {
          var curLine = line
          while (!(lines(curLine).isInstanceOf[StartFalse] || lines(curLine).isInstanceOf[EndIf])) {
            curLine += 1
          }
          gotoLine(curLine + 1)
        }
      }

      case StartFalse(_) => {
        // Only reach this if true was executed
        var lineVar = line
        while (!lines(lineVar).isInstanceOf[EndIf]) {
          lineVar = lineVar + 1;
        }
        gotoLine(lineVar + 1);
      }

      case EndIf(_) => {
        gotoLine(line + 1);
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
  def RAND(i: Int, j: Int): Int = { random.nextInt(j + 1 - i) + i }
  
  // infix operators
  implicit def operator_int(i: Int) = new {
    // int int
    def UP(j: Int): Function0[Int] = { () => (i + j) }
    def NERF(j: Int): Function0[Int] = { () => (i - j) }
    def TIEMZ(j: Int): Function0[Int] = { () => (i * j) }
    def OVAR(j: Int): Function0[Int] = { () => (i / j) }
    def MOD(j: Int): Function0[Int] = { () => (i % j) }    
    def BIGR_THAN(j: Int): Function0[Boolean] = { () => (i > j) }
    def SMALLR_THAN(j: Int): Function0[Boolean] = { () => (i < j) }
    def LIEK(j: Int): Function0[Boolean] = { () => (i == j) }
    
    // int symbol
    def UP(j: Symbol): Function0[Int] = { () => (i + binds.num(j)) }
    def NERF(j: Symbol): Function0[Int] = { () => (i - binds.num(j)) }
    def TIEMZ(j: Symbol): Function0[Int] = { () => (i * binds.num(j)) }
    def OVAR(j: Symbol): Function0[Int] = { () => (i / binds.num(j)) }
    def MOD(j: Symbol): Function0[Int] = { () => (i % binds.num(j)) }
    def BIGR_THAN(j: Symbol): Function0[Boolean] = { () => (i > binds.num(j)) }
    def SMALLR_THAN(j: Symbol): Function0[Boolean] = { () => (i < binds.num(j)) }
    def LIEK(j: Symbol): Function0[Boolean] = { () => (i == binds.num(j)) }
    
    // int function
    def UP(j: Function0[Int]): Function0[Int] = { () => (i + j()) }
    def NERF(j: Function0[Int]): Function0[Int] = { () => (i - j()) }
    def TIEMZ(j: Function0[Int]): Function0[Int] = { () => (i * j()) }
    def OVAR(j: Function0[Int]): Function0[Int] = { () => (i / j()) }
    def MOD(j: Function0[Int]): Function0[Int] = { () => (i % j()) }
    def BIGR_THAN(j: Function0[Int]): Function0[Boolean] = { () => (i > j()) }
    def SMALLR_THAN(j: Function0[Int]): Function0[Boolean] = { () => (i < j()) }
    def LIEK(j: Function0[Int]): Function0[Boolean] = { () => (i == j()) }
  }

  implicit def operator_symbol(i: Symbol) = new {
    // symbol int
    def UP(j: Int): Function0[Int] = { () => binds.num(i) + j }
    def NERF(j: Int): Function0[Int] = { () => binds.num(i) - j }
    def TIEMZ(j: Int): Function0[Int] = { () => binds.num(i) * j }
    def OVAR(j: Int): Function0[Int] = { () => binds.num(i) / j }
    def MOD(j: Int): Function0[Int] = { () => binds.num(i) % j }
    def BIGR_THAN(j: Int): Function0[Boolean] = { () => (binds.num(i) > j) }
    def SMALLR_THAN(j: Int): Function0[Boolean] = { () => (binds.num(i) < j) }
    def LIEK(j: Int): Function0[Boolean] = { () => (binds.num(i) == j) }
    
    // symbol symbol
    def UP(j: Symbol): Function0[Int] = { () => binds.num(i) + binds.num(j) }
    def NERF(j: Symbol): Function0[Int] = { () => binds.num(i) - binds.num(j) }
    def TIEMZ(j: Symbol): Function0[Int] = { () => binds.num(i) * binds.num(j) }
    def OVAR(j: Symbol): Function0[Int] = { () => binds.num(i) / binds.num(j) }
    def MOD(j: Symbol): Function0[Int] = { () => binds.num(i) % binds.num(j) }
    def BIGR_THAN(j: Symbol): Function0[Boolean] = { () => (binds.num(i) > binds.num(j)) }
    def SMALLR_THAN(j: Symbol): Function0[Boolean] = { () => (binds.num(i) < binds.num(j)) }
    def LIEK(j: Symbol): Function0[Boolean] = { () => (binds.num(i) == binds.num(j)) }
    
    // symbol function
    def UP(j: Function0[Int]): Function0[Int] = { () => binds.num(i) + j() }
    def NERF(j: Function0[Int]): Function0[Int] = { () => binds.num(i) - j() }
    def TIEMZ(j: Function0[Int]): Function0[Int] = { () => binds.num(i) * j() }
    def OVAR(j: Function0[Int]): Function0[Int] = { () => binds.num(i) / j() }
    def MOD(j: Function0[Int]): Function0[Int] = { () => binds.num(i) % j() }
    def BIGR_THAN(j: Function0[Int]): Function0[Boolean] = { () => (binds.num(i) > j()) }
    def SMALLR_THAN(j: Function0[Int]): Function0[Boolean] = { () => (binds.num(i) < j()) }
    def LIEK(j: Function0[Int]): Function0[Boolean] = { () => (binds.num(i) == j()) }
  }

  implicit def operator_function(i: Function0[Int]) = new {
    // function int
    def UP(j: Int): Function0[Int] = { () => (i() + j) }
    def NERF(j: Int): Function0[Int] = { () => (i() - j) }
    def TIEMZ(j: Int): Function0[Int] = { () => (i() * j) }
    def OVAR(j: Int): Function0[Int] = { () => (i() / j) }
    def MOD(j: Int): Function0[Int] = { () => (i() % j) }
    def BIGR_THAN(j: Int): Function0[Boolean] = { () => (i() > j) }
    def SMALLR_THAN(j: Int): Function0[Boolean] = { () => (i() < j) }
    def LIEK(j: Int): Function0[Boolean] = { () => (i() == j) }
    
    // function symbol
    def UP(j: Symbol): Function0[Int] = { () => (i() + binds.num(j)) }
    def NERF(j: Symbol): Function0[Int] = { () => (i() - binds.num(j)) }
    def TIEMZ(j: Symbol): Function0[Int] = { () => (i() * binds.num(j)) }
    def OVAR(j: Symbol): Function0[Int] = { () => (i() / binds.num(j)) }
    def MOD(j: Symbol): Function0[Int] = { () => (i() % binds.num(j)) }
    def BIGR_THAN(j: Symbol): Function0[Boolean] = { () => (i() > binds.num(j)) }
    def SMALLR_THAN(j: Symbol): Function0[Boolean] = { () => (i() < binds.num(j)) }
    def LIEK(j: Symbol): Function0[Boolean] = { () => (i() == binds.num(j)) }
    
    // function function
    def UP(j: Function0[Int]): Function0[Int] = { () => (i() + j()) }
    def NERF(j: Function0[Int]): Function0[Int] = { () => (i() - j()) }
    def TIEMZ(j: Function0[Int]): Function0[Int] = { () => (i() * j()) }
    def OVAR(j: Function0[Int]): Function0[Int] = { () => (i() / j()) }
    def MOD(j: Function0[Int]): Function0[Int] = { () => (i() % j()) }
    def BIGR_THAN(j: Function0[Int]): Function0[Boolean] = { () => (i() > j()) }
    def SMALLR_THAN(j: Function0[Int]): Function0[Boolean] = { () => (i() < j()) }
    def LIEK(j: Function0[Int]): Function0[Boolean] = { () => (i() == j()) }
  }

  object GIMMEH {
    def apply(s: Symbol) = {
      lines(current) = ReadString(current, s)
      current += 1
    }
  }

  /**
   * attempt to convert String to an Integer
   * if not possible, return the original String
   */
  def tryInt(s: String): Any = {
    try {
      s.toInt
    } catch {
      case e: Exception => s
    }
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
    def apply(s: Function0[Any]) = {
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

  class Bindings {
    val bindings = HashMap[Symbol, Any]()

    /**
     * set a value in our map
     */
    def set(k: Symbol, v: Any): Unit = {
      bindings(k) = v;
    }

    /**
     * only returns integers
     */
    def num(k: Symbol): Int = {
      any(k) match {
        case n: Int => n
        case _ => throw new RuntimeException(f"Variable $k does not exist or is not an integer")
      }
    }

    /**
     * WARNING: don't use yet
     * returns ints and doubles
     */
    def anyval(k: Symbol): AnyVal = {
      any(k) match {
        case n: Int => n
        case n: Double => n
        case _ => throw new RuntimeException(f"Variable $k does not exist as type AnyVal")
      }
    }

    /**
     * returns anything
     */
    def any(k: Symbol): Any = {
      bindings.get(k) match {
        case Some(x) => x
        case None => None
      }
    }

    override def toString: String = {
      bindings.toString
    }
  }
}
