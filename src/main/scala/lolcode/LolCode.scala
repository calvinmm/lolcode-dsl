package lolcode

import scala.collection.mutable.{ HashMap, Stack }
import scala.util.Random
import scala.math.{ min, max }

class LolCode {
  abstract sealed class LolLine
  case class PrintString(num: Int, s: String) extends LolLine
  case class PrintVariable(num: Int, s: Symbol) extends LolLine
  case class PrintNumber(num: Int, s: Int) extends LolLine
  case class PrintFunction(num: Int, s: Function0[Any]) extends LolLine
  case class PrintMany(num: Int, s: Vector[Any]) extends LolLine
  case class ReadString(num: Int, s: Symbol) extends LolLine
  case class ErrorPrintString(num: Int, s: String) extends LolLine
  case class ErrorPrintVariable(num: Int, s: Symbol) extends LolLine
  case class ErrorPrintNumber(num: Int, s: Int) extends LolLine
  case class ErrorPrintFunction(num: Int, s: Function0[Any]) extends LolLine
  case class ErrorPrintMany(num: Int, s: Vector[Any]) extends LolLine
  case class If(num: Int, fun: Function0[Boolean]) extends LolLine
  case class StartFalse(num: Int) extends LolLine
  case class EndIf(num: Int) extends LolLine
  case class Assign(num: Int, fn: Function0[Unit]) extends LolLine
  case class LoopBeg() extends LolLine
  case class Break() extends LolLine
  case class LoopEnd(loopBegLine: Int) extends LolLine
  case class FuncBeg(name: Symbol) extends LolLine
  case class FuncEnd() extends LolLine
  case class FuncReturn(value: Any) extends LolLine
  case class FuncCall(funcName: Symbol) extends LolLine
  case class FuncCallReturn(funcName: Symbol, variable: Symbol) extends LolLine
  case class End(num: Int) extends LolLine

  // keep track of which line we are on
  var current: Int = 1

  var lines = new HashMap[Int, LolLine]
  val binds = new Bindings
  val funcBegLines = new HashMap[Symbol, Int]
  val random = new Random
  val loopBegLines = new Stack[Int]
  val pcStack = new Stack[Int]
  val returnStack = new Stack[Any]

  def KTHXBYE() = {
    lines(current) = End(current)
    gotoLine(lines.keys.toList.sorted.head)
  }

  def HAI() = {
    lines = new HashMap[Int, LolLine]
    binds.newScope()
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
    def ITZ(v: AnyVal): Unit = {
      lines(current) = Assign(current, (() => binds.set(sym, v)))
      current += 1
    }
    def ITZ(v: Function0[Any]): Unit = {
      lines(current) = Assign(current, (() => binds.set(sym, v())))
      current += 1
    }

    def R(v: String): Unit = {
      lines(current) = Assign(current, (() => binds.set(sym, v)))
      current += 1
    }
    def R(v: AnyVal): Unit = {
      lines(current) = Assign(current, (() => binds.set(sym, v)))
      current += 1
    }
    def R(v: Function0[Any]): Unit = {
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
      case PrintMany(_, s: Vector[Any]) => {

        println(s.map(e => e match {
          case v: Symbol => binds.any(v)
          case v: Function0[Any] => v()
          case _ => e
        }).mkString(" "))

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
      case ErrorPrintMany(_, s: Vector[Any]) => {
        
        Console.err.println(Console.RED + s.map(e => e match {
          case v: Symbol => binds.any(v)
          case v: Function0[Any] => v()
          case _ => e
        }).mkString(" ") + Console.RESET)

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
          var curLine = line + 1
          var count = 0
          while (!((lines(curLine).isInstanceOf[StartFalse] || lines(curLine).isInstanceOf[EndIf])
            && count == 0)) {
            if (lines(curLine).isInstanceOf[If]) {
              count = count + 1
            } else if (lines(curLine).isInstanceOf[EndIf]) {
              count = count - 1
            }
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
      case LoopBeg() => {
        gotoLine(line + 1)
      }
      case Break() => {
        var lineVar = line
        var loopBegCount = 0
        while (!lines(lineVar).isInstanceOf[LoopEnd] ||
          loopBegCount > 0) {
          if (lines(lineVar).isInstanceOf[LoopBeg])
            loopBegCount += 1
          if (lines(lineVar).isInstanceOf[LoopEnd])
            loopBegCount -= 1
          lineVar += 1
        }
        gotoLine(lineVar + 1)
      }
      case LoopEnd(loopBegLine: Int) => {
        gotoLine(loopBegLine + 1)
      }
      case FuncBeg(name: Symbol) => {
        var lineVar = line
        while (!lines(lineVar).isInstanceOf[FuncEnd]) {
          lineVar += 1
        }
        gotoLine(lineVar + 1)
      }
      case FuncEnd() => {

        // always pop from returnStack
        val temp: Any = returnStack.pop()

        binds.leaveScope()
        
        // TODO add more options
        temp match {
          case t: Function0[Any] => {
            if (returnStack.length > 0) {
              returnStack.pop() match {
                case v: Symbol => {
                  // set our return variable
                  binds.set(v, t())
                }
                case v => {
                  v match {
                    case None => // throw both away
                    case _ => {
                      // oops both were good values
                      returnStack.push(v)
                      returnStack.push(t)
                    }
                  }
                }
              }
            }
          }
          case t: Int => {
            if (returnStack.length > 0) {
              returnStack.pop() match {
                case v: Symbol => {
                  // set our return variable
                  binds.set(v, t)
                }
                case v => {
                  v match {
                    case None => // throw both away
                    case _ => {
                      // oops both were good values
                      returnStack.push(v)
                      returnStack.push(t)
                    }
                  }
                }
              }
            }
          }
          case t: Symbol => {
            // oops we popped a symbol
            returnStack.push(t)
          }
          case None =>
          case _ => throw new RuntimeException(f"Something bad has happened! $temp")
        }

        gotoLine(pcStack.pop())
      }
      case FuncReturn(value: Any) => {
        
        // check and evaluate the types
        value match {
          case v: Function0[Any] => returnStack.push(v())
          case v: Symbol => returnStack.push(binds.any(v))
          case v => returnStack.push(v)
        }

        // actually need to go to end of function
        var lineVar = line
        while (!lines(lineVar).isInstanceOf[FuncEnd]) {
          lineVar += 1
        }
        gotoLine(lineVar)
      }
      case FuncCall(funcName: Symbol) => {
        // push trash onto the return stack
        returnStack.push(None)
        pcStack.push(line + 1)
        binds.newScope()
        gotoLine(funcBegLines.get(funcName) match {
          case Some(s) => s + 1 //go beyond the start of the function
          case None => -1
        })
      }
      case FuncCallReturn(funcName: Symbol, variable: Symbol) => {
        // push the return variable onto the return stack
        returnStack.push(variable)
        pcStack.push(line + 1)
        binds.newScope()
        gotoLine(funcBegLines.get(funcName) match {
          case Some(s) => s + 1 // go beyond the start of the function
          case None => -1
        })
      }
      case End(_) =>
      case _ =>
    }
  }

  // prefix operators / functions
  def RAND(i: Int, j: Int): Int = { random.nextInt(j + 1 - i) + i }

  //  // max and min functions
  //  // int int
  //  def BIGR_OF(i: Int, j: Int): Function0[Int] = { () => max(i, j) }
  //  def SMALLR_OF(i: Int, j: Int): Function0[Int] = { () => min(i, j) }
  //  // int symbol
  //  def BIGR_OF(i: Int, j: Symbol): Function0[Int] = { () => max(i, binds.num(j)) }
  //  def SMALLR_OF(i: Int, j: Symbol): Function0[Int] = { () => min(i, binds.num(j)) }
  //  // int function
  //  def BIGR_OF(i: Int, j: Function0[Int]): Function0[Int] = { () => max(i, j()) }
  //  def SMALLR_OF(i: Int, j: Function0[Int]): Function0[Int] = { () => min(i, j()) }
  //
  //  // symbol symbol
  //  def BIGR_OF(i: Symbol, j: Symbol): Function0[Int] = { () => max(binds.num(i), binds.num(j)) }
  //  def SMALLR_OF(i: Symbol, j: Symbol): Function0[Int] = { () => min(binds.num(i), binds.num(j)) }
  //  // symbol int
  //  def BIGR_OF(i: Symbol, j: Int): Function0[Int] = { () => max(binds.num(i), j) }
  //  def SMALLR_OF(i: Symbol, j: Int): Function0[Int] = { () => min(binds.num(i), j) }
  //  // symbol function
  //  def BIGR_OF(i: Symbol, j: Function0[Int]): Function0[Int] = { () => max(binds.num(i), j()) }
  //  def SMALLR_OF(i: Symbol, j: Function0[Int]): Function0[Int] = { () => min(binds.num(i), j()) }
  //
  //  // function function
  //  def BIGR_OF(i: Function0[Int], j: Function0[Int]): Function0[Int] = { () => max(i(), j()) }
  //  def SMALLR_OF(i: Function0[Int], j: Function0[Int]): Function0[Int] = { () => min(i(), j()) }
  //  // function int
  //  def BIGR_OF(i: Function0[Int], j: Int): Function0[Int] = { () => max(i(), j) }
  //  def SMALLR_OF(i: Function0[Int], j: Int): Function0[Int] = { () => min(i(), j) }
  //  // function symbol
  //  def BIGR_OF(i: Function0[Int], j: Symbol): Function0[Int] = { () => max(i(), binds.num(j)) }
  //  def SMALLR_OF(i: Function0[Int], j: Symbol): Function0[Int] = { () => min(i(), binds.num(j)) }

  def BIGR_OF(i: Any, j: Any): Function0[Any] = {
    () =>
      {
        val base_i = i match {
          case _i: Symbol => binds.anyval(_i)
          case _i: Function0[Any] => _i()
          case _ => i
        }

        val base_j = j match {
          case _j: Symbol => binds.anyval(_j)
          case _j: Function0[Any] => _j()
          case _ => j
        }

        base_i match {
          case _i: Int => {
            base_j match {
              case _j: Int => max(_i, _j)
              case _j: Double => max(_i, _j)
            }
          }
          case _i: Double => {
            base_j match {
              case _j: Int => max(_i, _j)
              case _j: Double => max(_i, _j)
            }
          }
        }
      }
  }

  def SMALLR_OF(i: Any, j: Any): Function0[Any] = {
    () =>
      {
        val base_i = i match {
          case _i: Symbol => binds.anyval(_i)
          case _i: Function0[Any] => _i()
          case _ => i
        }

        val base_j = j match {
          case _j: Symbol => binds.anyval(_j)
          case _j: Function0[Any] => _j()
          case _ => j
        }

        base_i match {
          case _i: Int => {
            base_j match {
              case _j: Int => min(_i, _j)
              case _j: Double => min(_i, _j)
            }
          }
          case _i: Double => {
            base_j match {
              case _j: Int => min(_i, _j)
              case _j: Double => min(_i, _j)
            }
          }
        }
      }
  }
  
  implicit def operator_any(i: Any) = new {
    def UP(j: Any): Function0[Any] = {
      () =>
        {
          val base_i = i match {
            case _i: Symbol => binds.anyval(_i)
            case _i: Function0[Any] => _i()
            case _ => i
          }

          val base_j = j match {
            case _j: Symbol => binds.anyval(_j)
            case _j: Function0[Any] => _j()
            case _ => j
          }

          base_i match {
            case _i: Int => {
              base_j match {
                case _j: Int => _i + _j
                case _j: Double => _i + _j
              }
            }
            case _i: Double => {
              base_j match {
                case _j: Int => _i + _j
                case _j: Double => _i + _j
              }
            }
          }
        }
    }

    def NERF(j: Any): Function0[Any] = {
      () =>
        {
          val base_i = i match {
            case _i: Symbol => binds.anyval(_i)
            case _i: Function0[Any] => _i()
            case _ => i
          }

          val base_j = j match {
            case _j: Symbol => binds.anyval(_j)
            case _j: Function0[Any] => _j()
            case _ => j
          }

          base_i match {
            case _i: Int => {
              base_j match {
                case _j: Int => _i - _j
                case _j: Double => _i - _j
              }
            }
            case _i: Double => {
              base_j match {
                case _j: Int => _i - _j
                case _j: Double => _i - _j
              }
            }
          }
        }
    }

    def TIEMZ(j: Any): Function0[Any] = {
      () =>
        {
          val base_i = i match {
            case _i: Symbol => binds.anyval(_i)
            case _i: Function0[Any] => _i()
            case _ => i
          }

          val base_j = j match {
            case _j: Symbol => binds.anyval(_j)
            case _j: Function0[Any] => _j()
            case _ => j
          }

          base_i match {
            case _i: Int => {
              base_j match {
                case _j: Int => _i * _j
                case _j: Double => _i * _j
              }
            }
            case _i: Double => {
              base_j match {
                case _j: Int => _i * _j
                case _j: Double => _i * _j
              }
            }
          }
        }
    }

    def OVAR(j: Any): Function0[Any] = {
      () =>
        {
          val base_i = i match {
            case _i: Symbol => binds.anyval(_i)
            case _i: Function0[Any] => _i()
            case _ => i
          }

          val base_j = j match {
            case _j: Symbol => binds.anyval(_j)
            case _j: Function0[Any] => _j()
            case _ => j
          }

          base_i match {
            case _i: Int => {
              base_j match {
                case _j: Int => _i / _j
                case _j: Double => _i / _j
              }
            }
            case _i: Double => {
              base_j match {
                case _j: Int => _i / _j
                case _j: Double => _i / _j
              }
            }
          }
        }
    }

    def MOD(j: Any): Function0[Any] = {
      () =>
        {
          val base_i = i match {
            case _i: Symbol => binds.anyval(_i)
            case _i: Function0[Any] => _i()
            case _ => i
          }

          val base_j = j match {
            case _j: Symbol => binds.anyval(_j)
            case _j: Function0[Any] => _j()
            case _ => j
          }

          base_i match {
            case _i: Int => {
              base_j match {
                case _j: Int => _i % _j
                case _j: Double => _i % _j
              }
            }
            case _i: Double => {
              base_j match {
                case _j: Int => _i % _j
                case _j: Double => _i % _j
              }
            }
          }
        }
    }

    def BIGR_THAN(j: Any): Function0[Boolean] = {
      () =>
        {
          val base_i = i match {
            case _i: Symbol => binds.anyval(_i)
            case _i: Function0[Any] => _i()
            case _ => i
          }

          val base_j = j match {
            case _j: Symbol => binds.anyval(_j)
            case _j: Function0[Any] => _j()
            case _ => j
          }

          base_i match {
            case _i: Int => {
              base_j match {
                case _j: Int => _i > _j
                case _j: Double => _i > _j
              }
            }
            case _i: Double => {
              base_j match {
                case _j: Int => _i > _j
                case _j: Double => _i > _j
              }
            }
          }
        }
    }

    def SMALLR_THAN(j: Any): Function0[Boolean] = {
      () =>
        {
          val base_i = i match {
            case _i: Symbol => binds.anyval(_i)
            case _i: Function0[Any] => _i()
            case _ => i
          }

          val base_j = j match {
            case _j: Symbol => binds.anyval(_j)
            case _j: Function0[Any] => _j()
            case _ => j
          }

          base_i match {
            case _i: Int => {
              base_j match {
                case _j: Int => _i < _j
                case _j: Double => _i < _j
              }
            }
            case _i: Double => {
              base_j match {
                case _j: Int => _i < _j
                case _j: Double => _i < _j
              }
            }
          }
        }
    }

    def LIEK(j: Any): Function0[Boolean] = {
      () =>
        {
          val base_i = i match {
            case _i: Symbol => binds.anyval(_i)
            case _i: Function0[Any] => _i()
            case _ => i
          }

          val base_j = j match {
            case _j: Symbol => binds.anyval(_j)
            case _j: Function0[Any] => _j()
            case _ => j
          }

          base_i match {
            case _i: Int => {
              base_j match {
                case _j: Int => _i == _j
                case _j: Double => _i == _j
              }
            }
            case _i: Double => {
              base_j match {
                case _j: Int => _i == _j
                case _j: Double => _i == _j
              }
            }
          }
        }
    }

  }
  
  // infix operators
//  implicit def operator_int(i: Int) = new {
//    // int int
//    def UP(j: Int): Function0[Int] = { () => (i + j) }
//    def NERF(j: Int): Function0[Int] = { () => (i - j) }
//    def TIEMZ(j: Int): Function0[Int] = { () => (i * j) }
//    def OVAR(j: Int): Function0[Int] = { () => (i / j) }
//    def MOD(j: Int): Function0[Int] = { () => (i % j) }
//    def BIGR_THAN(j: Int): Function0[Boolean] = { () => (i > j) }
//    def SMALLR_THAN(j: Int): Function0[Boolean] = { () => (i < j) }
//    def LIEK(j: Int): Function0[Boolean] = { () => (i == j) }
//
//    // int symbol
//    def UP(j: Symbol): Function0[Int] = { () => (i + binds.num(j)) }
//    def NERF(j: Symbol): Function0[Int] = { () => (i - binds.num(j)) }
//    def TIEMZ(j: Symbol): Function0[Int] = { () => (i * binds.num(j)) }
//    def OVAR(j: Symbol): Function0[Int] = { () => (i / binds.num(j)) }
//    def MOD(j: Symbol): Function0[Int] = { () => (i % binds.num(j)) }
//    def BIGR_THAN(j: Symbol): Function0[Boolean] = { () => (i > binds.num(j)) }
//    def SMALLR_THAN(j: Symbol): Function0[Boolean] = { () => (i < binds.num(j)) }
//    def LIEK(j: Symbol): Function0[Boolean] = { () => (i == binds.num(j)) }
//
//    // int function
//    def UP(j: Function0[Int]): Function0[Int] = { () => (i + j()) }
//    def NERF(j: Function0[Int]): Function0[Int] = { () => (i - j()) }
//    def TIEMZ(j: Function0[Int]): Function0[Int] = { () => (i * j()) }
//    def OVAR(j: Function0[Int]): Function0[Int] = { () => (i / j()) }
//    def MOD(j: Function0[Int]): Function0[Int] = { () => (i % j()) }
//    def BIGR_THAN(j: Function0[Int]): Function0[Boolean] = { () => (i > j()) }
//    def SMALLR_THAN(j: Function0[Int]): Function0[Boolean] = { () => (i < j()) }
//    def LIEK(j: Function0[Int]): Function0[Boolean] = { () => (i == j()) }
//  }
//
//  implicit def operator_symbol(i: Symbol) = new {
//    // symbol int
//    def UP(j: Int): Function0[Int] = { () => binds.num(i) + j }
//    def NERF(j: Int): Function0[Int] = { () => binds.num(i) - j }
//    def TIEMZ(j: Int): Function0[Int] = { () => binds.num(i) * j }
//    def OVAR(j: Int): Function0[Int] = { () => binds.num(i) / j }
//    def MOD(j: Int): Function0[Int] = { () => binds.num(i) % j }
//    def BIGR_THAN(j: Int): Function0[Boolean] = { () => (binds.num(i) > j) }
//    def SMALLR_THAN(j: Int): Function0[Boolean] = { () => (binds.num(i) < j) }
//    def LIEK(j: Int): Function0[Boolean] = { () => (binds.num(i) == j) }
//
//    // symbol symbol
//    def UP(j: Symbol): Function0[Int] = { () => binds.num(i) + binds.num(j) }
//    def NERF(j: Symbol): Function0[Int] = { () => binds.num(i) - binds.num(j) }
//    def TIEMZ(j: Symbol): Function0[Int] = { () => binds.num(i) * binds.num(j) }
//    def OVAR(j: Symbol): Function0[Int] = { () => binds.num(i) / binds.num(j) }
//    def MOD(j: Symbol): Function0[Int] = { () => binds.num(i) % binds.num(j) }
//    def BIGR_THAN(j: Symbol): Function0[Boolean] = { () => (binds.num(i) > binds.num(j)) }
//    def SMALLR_THAN(j: Symbol): Function0[Boolean] = { () => (binds.num(i) < binds.num(j)) }
//    def LIEK(j: Symbol): Function0[Boolean] = { () => (binds.num(i) == binds.num(j)) }
//
//    // symbol function
//    def UP(j: Function0[Int]): Function0[Int] = { () => binds.num(i) + j() }
//    def NERF(j: Function0[Int]): Function0[Int] = { () => binds.num(i) - j() }
//    def TIEMZ(j: Function0[Int]): Function0[Int] = { () => binds.num(i) * j() }
//    def OVAR(j: Function0[Int]): Function0[Int] = { () => binds.num(i) / j() }
//    def MOD(j: Function0[Int]): Function0[Int] = { () => binds.num(i) % j() }
//    def BIGR_THAN(j: Function0[Int]): Function0[Boolean] = { () => (binds.num(i) > j()) }
//    def SMALLR_THAN(j: Function0[Int]): Function0[Boolean] = { () => (binds.num(i) < j()) }
//    def LIEK(j: Function0[Int]): Function0[Boolean] = { () => (binds.num(i) == j()) }
//  }
//
//  implicit def operator_function(i: Function0[Int]) = new {
//    // function int
//    def UP(j: Int): Function0[Int] = { () => (i() + j) }
//    def NERF(j: Int): Function0[Int] = { () => (i() - j) }
//    def TIEMZ(j: Int): Function0[Int] = { () => (i() * j) }
//    def OVAR(j: Int): Function0[Int] = { () => (i() / j) }
//    def MOD(j: Int): Function0[Int] = { () => (i() % j) }
//    def BIGR_THAN(j: Int): Function0[Boolean] = { () => (i() > j) }
//    def SMALLR_THAN(j: Int): Function0[Boolean] = { () => (i() < j) }
//    def LIEK(j: Int): Function0[Boolean] = { () => (i() == j) }
//
//    // function symbol
//    def UP(j: Symbol): Function0[Int] = { () => (i() + binds.num(j)) }
//    def NERF(j: Symbol): Function0[Int] = { () => (i() - binds.num(j)) }
//    def TIEMZ(j: Symbol): Function0[Int] = { () => (i() * binds.num(j)) }
//    def OVAR(j: Symbol): Function0[Int] = { () => (i() / binds.num(j)) }
//    def MOD(j: Symbol): Function0[Int] = { () => (i() % binds.num(j)) }
//    def BIGR_THAN(j: Symbol): Function0[Boolean] = { () => (i() > binds.num(j)) }
//    def SMALLR_THAN(j: Symbol): Function0[Boolean] = { () => (i() < binds.num(j)) }
//    def LIEK(j: Symbol): Function0[Boolean] = { () => (i() == binds.num(j)) }
//
//    // function function
//    def UP(j: Function0[Int]): Function0[Int] = { () => (i() + j()) }
//    def NERF(j: Function0[Int]): Function0[Int] = { () => (i() - j()) }
//    def TIEMZ(j: Function0[Int]): Function0[Int] = { () => (i() * j()) }
//    def OVAR(j: Function0[Int]): Function0[Int] = { () => (i() / j()) }
//    def MOD(j: Function0[Int]): Function0[Int] = { () => (i() % j()) }
//    def BIGR_THAN(j: Function0[Int]): Function0[Boolean] = { () => (i() > j()) }
//    def SMALLR_THAN(j: Function0[Int]): Function0[Boolean] = { () => (i() < j()) }
//    def LIEK(j: Function0[Int]): Function0[Boolean] = { () => (i() == j()) }
//  }

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
    def apply(s: Any*) = {
      lines(current) = PrintMany(current, s.toVector)
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
    def apply(s: Any*) = {
      lines(current) = ErrorPrintMany(current, s.toVector)
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
    def apply(s: Function0[Any]) = {
      lines(current) = ErrorPrintFunction(current, s)
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

  def IM_IN_YR_LOOP {
    lines(current) = LoopBeg()
    loopBegLines.push(current)
    current += 1
  }

  def IM_OUTTA_YR_LOOP {
    lines(current) = LoopEnd(loopBegLines.pop())
    current += 1
  }

  def GTFO {
    lines(current) = Break()
    current += 1
  }

  object HOW_DUZ_I {
    def apply(funcName: Symbol) = {
      funcBegLines += (funcName -> current)
      lines(current) = FuncBeg(funcName)
      current += 1
    }
  }

  object FOUND_YR {
    def apply(value: Any) = {
      lines(current) = FuncReturn(value)
      current += 1
    }
  }

  def IF_U_SAY_SO {
    lines(current) = FuncEnd()
    current += 1
  }

  object PLZ {
    def apply(funcName: Symbol) = {
      lines(current) = FuncCall(funcName)
      current += 1
    }
    def apply(funcName: Symbol, variable: Symbol) = {
      lines(current) = FuncCallReturn(funcName, variable)
      current += 1
    }
  }

  class Bindings {
    val bindingsStack = Stack[HashMap[Symbol, Any]]()
    val bindings = HashMap[Symbol, Any]()

    /*
     * Create a new scope.
     * Call whenever doing a function call.
     */
    def newScope() {
      bindingsStack.push(new HashMap[Symbol, Any])
    }

    /*
     * Destroy topmost scope.
     * Call whenever leaving a function.
     */
    def leaveScope() {
      bindingsStack.pop()
    }

    /**
     * get correct HashMap for your scope
     */
    def getMap(sym: Symbol): HashMap[Symbol, Any] = {
      val bindingsStackCopy = Stack[HashMap[Symbol, Any]]()
      val bindingsStackTop = bindingsStack.top
      while (!bindingsStack.isEmpty && !bindingsStack.top.contains(sym)) {
        bindingsStackCopy.push(bindingsStack.pop())
      }
      //bindingsStackCopy.push(bindingsStack.pop())
      var map = bindingsStackTop
      if (!bindingsStack.isEmpty) {
        map = bindingsStack.top
      }
      while (!bindingsStackCopy.isEmpty) {
        bindingsStack.push(bindingsStackCopy.pop())
      }
      map
    }

    /**
     * set a value in our map
     */
    def set(k: Symbol, v: Any): Unit = {
      val map = getMap(k)
      map(k) = v;
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
    /* DID NOT TOUCH THIS WHILE IMPLEMENTING SCOPE */
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
      val map = getMap(k)
      map.get(k) match {
        case Some(x) => x
        case None => None
      }
    }

    override def toString: String = {
      bindingsStack.top.toString
    }
  }
}
