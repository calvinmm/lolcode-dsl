package lolcode

object LolTest_Visible extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    VISIBLE("hello world")
    VISIBLE(3)
    VISIBLE(4.5)
    KTHXBYE
  }
}

object LolTest_Complain extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    VISIBLE("hello world")
    COMPLAIN("goodbye world")
    COMPLAIN(10)
    COMPLAIN(2.2)
    KTHXBYE
  }
}

object LolTest_IntAssign extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    I_HAZ_A('a) ITZ 1
    VISIBLE('a)
    I_HAZ_A('b) R 1337
    VISIBLE('b)
    KTHXBYE
  }
}

object LolTest_DoubleAssign extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    I_HAZ_A('a) ITZ 10.1
    VISIBLE('a)
    I_HAZ_A('b) R 1337.13
    VISIBLE('b)
    KTHXBYE
  }
}

object LolTest_Int_IntAssign extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    I_HAZ_A('a) ITZ 1
    VISIBLE('a)
    I_HAZ_A('b) R ('a UP 1)
    VISIBLE('b)
    VISIBLE('b TIEMZ 4)
    KTHXBYE
  }
}

object LolTest_Double_IntAssign extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    I_HAZ_A('a) ITZ 1
    VISIBLE('a)
    I_HAZ_A('b) R ('a UP 1.5)
    VISIBLE('b)
    VISIBLE('b TIEMZ 4)
    KTHXBYE
  }
}

object LolTest_Int_ReverseAssign extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    LOL('a) ITZ 50
    VISIBLE('a UP 100)
    VISIBLE(100 UP 'a)
    KTHXBYE
  }
}

object LolTest_Double_ReverseAssign extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    LOL('a) ITZ 50
    VISIBLE('a UP 10.5)
    VISIBLE(150.5 UP 'a)
    KTHXBYE
  }
}

object LolTest_Int_Assign_Print extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    I_HAZ_A('b) R (1 UP 1)
    VISIBLE('b TIEMZ 4)
    KTHXBYE
  }
}

object LolTest_Double_Assign_Print extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    I_HAZ_A('b) R (1.5 UP 1.5)
    VISIBLE('b TIEMZ 4)
    KTHXBYE
  }
}

object LolTest_Check_Types extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    I_HAZ_A('b) R 1
    VISIBLE('b TIEMZ 4)
    VISIBLE('b TIEMZ 4.0)
    I_HAZ_A('c) R 2
    I_HAZ_A('d) R 2.0
    VISIBLE('b TIEMZ 'c)
    VISIBLE('d TIEMZ 'b)
    VISIBLE('d TIEMZ 'd)
    KTHXBYE
  }
}

object LolTest_StringAssign extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    I_HAZ_A('z) ITZ "cat"
    VISIBLE('z)
    I_HAZ_A('q) R "doge"
    VISIBLE('q)
    KTHXBYE
  }
}

object LolTest_LOLStringAssign extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    LOL('a) ITZ "kitteh"
    VISIBLE('a)
    LOL('b) R "cat"
    VISIBLE('b)
    KTHXBYE
  }
}

object LolTest_Math_Up extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    VISIBLE(2 UP 3)
    LOL('a) ITZ (1 UP 100)
    VISIBLE('a)
    KTHXBYE
  }
}

object LolTest_Double_Math_Up extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    VISIBLE(2.5 UP 3)
    LOL('a) ITZ (1.1 UP 100)
    VISIBLE('a)
    KTHXBYE
  }
}

object LolTest_Math_Up_Tiemz extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    LOL('a) ITZ (13 UP (100 TIEMZ 12))
    VISIBLE('a)
    KTHXBYE
  }
}

object LolTest_Double_Math_Up_Tiemz extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    LOL('a) ITZ (13.0 UP (100 TIEMZ 12))
    VISIBLE('a)
    KTHXBYE
  }
}

object LolTest_Math_Nerf extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    VISIBLE(13 NERF 2)
    LOL('a) ITZ (1337 NERF 1000 NERF 12)
    VISIBLE('a)
    KTHXBYE
  }
}

object LolTest_Double_Math_Nerf extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    VISIBLE(13.5 NERF 2)
    LOL('a) ITZ (1337 NERF 1000.0 NERF 12.0)
    VISIBLE('a)
    KTHXBYE
  }
}

object LolTest_Math_Tiemz extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    VISIBLE(3 TIEMZ 3)
    LOL('c) ITZ (3 TIEMZ 20)
    VISIBLE('c)
    KTHXBYE
  }
}

object LolTest_Double_Math_Tiemz extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    VISIBLE(3.3 TIEMZ 3.3)
    LOL('c) ITZ (3 TIEMZ 20.5)
    VISIBLE('c)
    KTHXBYE
  }
}

object LolTest_Math_Ovar extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    VISIBLE(9 OVAR 3)
    LOL('d) ITZ (100 OVAR 10)
    VISIBLE('d)
    KTHXBYE
  }
}

object LolTest_Double_Math_Ovar extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    VISIBLE(9.0 OVAR 3)
    LOL('d) ITZ (100 OVAR 10.0)
    VISIBLE('d)
    KTHXBYE
  }
}

object LolTest_Math_Mod extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    VISIBLE(9 MOD 3)
    LOL('e) ITZ (25 MOD 10)
    VISIBLE('e)
    KTHXBYE
  }
}

object LolTest_Double_Math_Mod extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    VISIBLE(9.0 MOD 3)
    LOL('e) ITZ (25 MOD 10.0)
    VISIBLE('e)
    KTHXBYE
  }
}

object LolTest_BTW extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    BTW("hide")
    VISIBLE("show")
    BTW("hide")
    VISIBLE("show")
    BTW("hide")
    KTHXBYE
  }
}

object LolTest_IZ_TRUE_NUMS extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    I_HAZ_A('num) ITZ 4
    IZ(4 LIEK 4)
    LOL('num) R 5
    NOWAI
    LOL('num) R 6
    KTHX
    VISIBLE('num)
    KTHXBYE
  }
}

object LolTest_IZ_FALSE_NUMS extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    I_HAZ_A('num) ITZ 4
    IZ(4 BIGR_THAN 4)
    LOL('num) R 5
    NOWAI
    LOL('num) R 6
    KTHX
    VISIBLE('num)
    KTHXBYE
  }
}

object LolTest_IZ_TRUE_VAR extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    I_HAZ_A('num) ITZ 4
    IZ('num LIEK 4)
    LOL('num) R 5
    NOWAI
    LOL('num) R 6
    KTHX
    VISIBLE('num)
    KTHXBYE
  }
}

object LolTest_IZ_FALSE_VAR extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    I_HAZ_A('num) ITZ 4
    IZ('num SMALLR_THAN 4)
      LOL('num) R 5
    NOWAI
      LOL('num) R 6
    KTHX
      VISIBLE('num)
    KTHXBYE
  }
}

object LolTest_NESTED_IZ_FALSE_TRUE extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    I_HAZ_A('num) ITZ 8
    IZ('num SMALLR_THAN 4)
      IZ('num SMALLR_THAN 3)
        LOL('num) R 13
      NOWAI
        LOL('num) R 14
      KTHX
    NOWAI
      IZ('num BIGR_THAN 5)
        LOL('num) R 15
      NOWAI
        LOL('num) R 16
      KTHX
    KTHX
    VISIBLE('num)
    KTHXBYE
  }
}

object LolTest_NESTED_IZ_FALSE_FALSE extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    I_HAZ_A('num) ITZ 4
    IZ('num SMALLR_THAN 4)
      IZ('num SMALLR_THAN 3)
        LOL('num) R 13
      NOWAI
        LOL('num) R 14
      KTHX
    NOWAI
      IZ('num BIGR_THAN 5)
        LOL('num) R 15
      NOWAI
        LOL('num) R 16
      KTHX
    KTHX
    VISIBLE('num)
    KTHXBYE
  }
}

object LolTest_NESTED_IZ_TRUE_FALSE extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    I_HAZ_A('num) ITZ 3
    IZ('num SMALLR_THAN 4)
      IZ('num SMALLR_THAN 2)
        LOL('num) R 13
      NOWAI
        LOL('num) R 14
      KTHX
    NOWAI
      IZ('num BIGR_THAN 5)
        LOL('num) R 15
      NOWAI
        LOL('num) R 16
      KTHX
    KTHX
    VISIBLE('num)
    KTHXBYE
  }
}

object LolTest_NESTED_IZ_TRUE_TRUE extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    I_HAZ_A('num) ITZ 1
    IZ('num SMALLR_THAN 4)
      IZ('num SMALLR_THAN 2)
        LOL('num) R 13
      NOWAI
        LOL('num) R 14
      KTHX
    NOWAI
      IZ('num BIGR_THAN 5)
        LOL('num) R 15
      NOWAI
        LOL('num) R 16
      KTHX
    KTHX
    VISIBLE('num)
    KTHXBYE
  }
}

object LolTest_GIMMEH extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    GIMMEH('n)
    VISIBLE('n)
    LOL('m) R ('n TIEMZ 30)
    VISIBLE('m)
    KTHXBYE
  }
}

object LolTest_GIMMEH_STRING extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    GIMMEH('n)
    VISIBLE('n)
    KTHXBYE
  }
}

object LolTest_BOOLEAN_OPS extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    LOL('n) ITZ 10
    VISIBLE('n LIEK 10)
    VISIBLE('n SMALLR_THAN 5.0)
    VISIBLE('n BIGR_THAN 5)
    VISIBLE('n BIGR_THAN 20.0)
    VISIBLE('n SMALLR_THAN 20)
    KTHXBYE
  }
}

object LolTest_Loop extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    LOL('n) ITZ 10
    IM_IN_YR_LOOP
    IZ('n SMALLR_THAN 4)
    GTFO
    NOWAI
    LOL('n) ITZ ('n NERF 1)
    VISIBLE("loop")
    KTHX
    IM_OUTTA_YR_LOOP
    KTHXBYE
  }
}

object LolTest_Nested_Loops extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    LOL('n) ITZ 10
    LOL('m) ITZ 10
    IM_IN_YR_LOOP
    IM_IN_YR_LOOP
    IZ('n SMALLR_THAN 8)
    GTFO
    NOWAI
    LOL('n) ITZ ('n NERF 1)
    VISIBLE('n)
    KTHX
    IM_OUTTA_YR_LOOP
    IZ('m BIGR_THAN 12)
    GTFO
    NOWAI
    LOL('m) ITZ ('m UP 1)
    VISIBLE('m)
    KTHX
    IM_OUTTA_YR_LOOP
    KTHXBYE
  }
}

object LolTest_Nested_Loops2 extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    LOL('n) ITZ 10
    LOL('m) ITZ 10
    IM_IN_YR_LOOP
      IZ('m BIGR_THAN 12)
        GTFO
      NOWAI
        LOL('m) ITZ ('m UP 1)
        VISIBLE('m)
      KTHX
      IM_IN_YR_LOOP
        IZ('n SMALLR_THAN 8)
          GTFO
        NOWAI
          LOL('n) ITZ ('n NERF 1)
          VISIBLE('n)
        KTHX
      IM_OUTTA_YR_LOOP
    IM_OUTTA_YR_LOOP
    KTHXBYE
  }
}

object LolTest_Print_Many extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    LOL('n) ITZ 10
    VISIBLE("n", 'n, "n+10", 'n UP 10)
    KTHXBYE
  }
}

object LolTest_Max_Min extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    LOL('n) ITZ 3
    LOL('m) ITZ 10
    VISIBLE(BIGR_OF('n, 'm))
    VISIBLE(SMALLR_OF('n, 'm))
    VISIBLE(BIGR_OF('n, 1337.9))
    VISIBLE(SMALLR_OF(-20.2, 'm))
    VISIBLE(BIGR_OF('n, ('n TIEMZ 'm)))
    VISIBLE(SMALLR_OF(('m OVAR 'n), ('n TIEMZ 'm)))
    KTHXBYE
  }
}

object LolTest_Function extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    HOW_DUZ_I('func)
      VISIBLE("func-test")
    IF_U_SAY_SO
    PLZ('func)
    KTHXBYE
  }
}

object LolTest_2Function extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    HOW_DUZ_I('func1)
      VISIBLE("func1-test")
    IF_U_SAY_SO
    PLZ('func1)
    HOW_DUZ_I('func2)
      VISIBLE("func2-test")
    IF_U_SAY_SO
    PLZ('func2)
    KTHXBYE
  }
}

object LolTest_CallInFunction extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    HOW_DUZ_I('func2)
      VISIBLE("func2-test")
    IF_U_SAY_SO
    HOW_DUZ_I('func1)
      VISIBLE("func1-test")
      PLZ('func2)
    IF_U_SAY_SO
    PLZ('func1)
    KTHXBYE
  }
}

object LolTest_AssignmentInFunction extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    LOL('n) ITZ 10
    HOW_DUZ_I('func1)
      LOL('n) ITZ 1
    IF_U_SAY_SO
    VISIBLE('n)
    PLZ('func1)
    VISIBLE('n)
    KTHXBYE
  }
}

object LolTest_Function_Return extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    HOW_DUZ_I('beep)
      VISIBLE("beep")
      PLZ('bang)
      PLZ('bing, 'temp)
      VISIBLE('temp)
      FOUND_YR('temp NERF 300)
    IF_U_SAY_SO
    HOW_DUZ_I('bang)
      PLZ('bing, 'qwerty)
      VISIBLE('qwerty)
      VISIBLE("bang")
      FOUND_YR(1000000)
    IF_U_SAY_SO
    HOW_DUZ_I('bing)
      VISIBLE("bing")
      FOUND_YR(347)
    IF_U_SAY_SO
    PLZ('beep, 'x)
    VISIBLE('x)
    KTHXBYE
  }
}
object LolTest_Scope extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    HOW_DUZ_I('func1)
      LOL('n) ITZ 1
    IF_U_SAY_SO
    VISIBLE('n)
    KTHXBYE
  }
}

object LolTest_Function_Recursion extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    LOL('x) ITZ 2
    LOL('i) ITZ 10
    HOW_DUZ_I('recurse)
      IZ('i LIEK 0)
        FOUND_YR('x)
        VISIBLE("nope")
      NOWAI
      KTHX
      LOL('i) ITZ ('i NERF 1)
      LOL('x) ITZ ('x UP 'x)
      PLZ('recurse, 'x)
      FOUND_YR('x)
    IF_U_SAY_SO
    PLZ('recurse, 'value)
    VISIBLE('value)
    KTHXBYE
  }
}

object LolTest_Scope2 extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    HOW_DUZ_I('func1)
      LOL('n) ITZ 1
    IF_U_SAY_SO
    HOW_DUZ_I('func2)
      PLZ('func1)
      VISIBLE('n)
      LOL('n) ITZ 1
      VISIBLE('n)
    IF_U_SAY_SO
    PLZ('func2)
    KTHXBYE
  }
}
