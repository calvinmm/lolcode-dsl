package lolcode
import scala.collection.mutable._

class lolcode {
  abstract sealed class LolLine
  case class PrintString(num: Int, s: String) extends LolLine
  case class End(num: Int) extends LolLine

  val lines = new HashMap[Int, LolLine]

  def RUN() = gotoLine(lines.keys.toList.sorted.head)
  /**
   * LineBuilder is the jump off point for the line number syntax of
   * BASIC expressions.  Simply put, the `int2LineBuilder` implict
   * puts an instance of this class in place when it finds an Int
   * followed by one of the provided methods.  That is, this *only* handles
   * the forms that follow a line number and no other forms.  If you need to
   * add new functions that can be put elsewhere, then follow the form
   * used by SQRT and ABS.  For functions that can go *anywhere*, they should
   * be handled here and as separate functions as SQRT.
   */
  case class LineBuilder(num: Int) {
    def END() = lines(num) = End(num)

    object PRINT {
      def apply(str: String) = lines(num) = PrintString(num, str)
      //def apply(number: BigInt) = lines(num) = PrintNumber(num, number)
      //def apply(s: Symbol) = lines(num) = PrintVariable(num, s)
      //def apply(fn:Function0[String]) = lines(num) = PrintResult(num, fn)
    }
  }

  /**
   * This is the runtime evaluator of the built Scala classes from the
   * original BASIC forms.  Currently, lines can only be incremented by
   * 10, otherwise you program might not act the way you expect.
   */
  private def gotoLine(line: Int) {
    lines(line) match {
      case PrintString(_, s: String) => {
        println(s)
        gotoLine(line + 10)
      }
      case End(_) => {
        println()
        println("BREAK IN LINE " + line)
      }
    }
  }

  implicit def newline(i: Int) = LineBuilder(i)
}