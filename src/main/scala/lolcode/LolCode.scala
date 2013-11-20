package lolcode
import scala.collection.mutable._

class LolCode 
{
    abstract sealed class LolLine
    case class PrintString(num: Int, s: String) extends LolLine 
    case class PrintVariable(num: Int, s: Symbol) extends LolLine
    case class Assign(num:Int, fn:Function0[Unit]) extends LolLine
    case class End(num: Int) extends LolLine

    /**
     * Bindings holds the two types of values provided, atoms and numerics.
     * It takes a type parameter on initialization corresponding to the
     * actual type.
     */
    class Bindings[T,U] {
      val atoms = HashMap[Symbol, T]()
      val numerics = HashMap[Symbol, U]()

      /**
       * set uses a little hack to allow the storage of either one type or
       * another, but none other.
       */
      def set[X >: T with U](k:Symbol, v:X) = v match {
        case u:U => numerics(k) = u
        case t:T => atoms(k) = t
      }
      def atom(k:Symbol):T = atoms(k)
      def num(k:Symbol):U = numerics(k)

      /**
       * Technically, you can have two variables with the same name with
       * different types at the same time, but for this version that does
       * not come into play.
       */
      def any(k:Symbol):Any = {
        (atoms.get(k), numerics.get(k)) match {
          case (Some(x), None) => x
          case (None, Some(y)) => y
          case (None, None) => None
          case (Some(x), Some(y)) => Some(x,y)
        }
      }
    }


    var lines = new HashMap[Int, LolLine]
    val binds = new Bindings[String, Int]
    var current = 1

    def KTHXBYE() = 
    { 
      lines(current) = End(current)
      gotoLine(lines.keys.toList.sorted.head)
    }

    def HAI() = { var lines = new HashMap[Int, LolLine] }

    /**
     * The Assignment class is used by the `symbol2Assignment` implicit to
     * stand-in for a Scala symbol in the LET form.  This class returns
     * a function of () => Unit that does the appropriate binding.
     */
    case class Assignment(sym:Symbol) {
      def ITZ(v:String):Function0[Unit] = (() => binds.set(sym, v))
      def ITZ(v:Int):Function0[Unit] = 
      {
        //println(f"s: $sym, v: $v")
        binds.set(sym, v)
        (() => binds.set(sym, v))
      }
      def ITZ(v:Function0[Int]):Function0[Unit] = (() => binds.set(sym, v()))
      def R(v:String):Function0[Unit] = (() => binds.set(sym, v))
      def R(v:Int):Function0[Unit] = (() => binds.set(sym, v))
      def R(v:Function0[Int]):Function0[Unit] = (() => binds.set(sym, v()))
    }

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
        def apply(str:String) = lines(num) = PrintString(num, str)
        def apply(s: Symbol) = lines(num) = PrintVariable(num, s)
    }
      object I_HAZ_A {
        def apply(fn:Function0[Unit]) = lines(num) = Assign(num, fn)
      }
  }


    /**
     * This is the runtime evaluator of the built Scala classes from the
     * original BASIC forms.  Currently, lines can only be incremented by
     * 10, otherwise you program might not act the way you expect.
     */
    private def gotoLine(line: Int) {
      lines(line) match {
        case PrintString(_, s:String) => {
          println(s)
          gotoLine(line + 1)
        }
        case PrintVariable(_, s:Symbol) => {
          println(binds.any(s))
          gotoLine(line + 1)
        }
        case Assign(_, fn: Function0[Unit]) =>
        {
          println(fn)
          gotoLine(line + 1)
        }
        case End(_) => {
          println()
        }
        case _ => {
          println()
        }
      }
    }

    implicit def newline(i: Int) = LineBuilder(i)
    implicit def symbol2Assignment(sym:Symbol) = Assignment(sym)
    
    object VISIBLE
    {
      def apply(s: String) = 
      {
        lines(current) = PrintString(current, s)
        current += 1
      }
      def apply(s: Symbol) =
      {
        lines(current) = PrintVariable(current, s)
        current += 1
      }
    }

    object I_HAZ_A
    {
      def apply(s: Symbol) = 
      {
        Assignment(s)
      }
    }

    object Lol
    {
      def apply(s: String) = println(s);
      def VISIBLE(s: String) = apply(s)
    }
}
