package lolcode

object LolTest_Visible extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    VISIBLE("hello world")
    VISIBLE(3)
    KTHXBYE
  }
}

object LolTest_Complain extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    VISIBLE("hello world")
    COMPLAIN("goodbye world")
    COMPLAIN(10)
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

object LolTest_Int_ReverseAssign extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    LOL('a) ITZ 50
    VISIBLE('a UP 100)
    VISIBLE(100 UP 'a)
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

object LolTest_Math_Up_Tiemz extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    LOL('a) ITZ (13 UP (100 TIEMZ 12))
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

object LolTest_Math_Tiemz extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    VISIBLE(3 TIEMZ 3)
    LOL('c) ITZ (3 TIEMZ 20)
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

object LolTest_Math_Mod extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    VISIBLE(9 MOD 3)
    LOL('e) ITZ (25 MOD 10)
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
    VISIBLE('n SMALLR_THAN 5)
    VISIBLE('n BIGR_THAN 5)
    VISIBLE('n BIGR_THAN 20)
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
    VISIBLE(BIGR_OF('n, 1337))
    VISIBLE(SMALLR_OF(-20, 'm))
    VISIBLE(BIGR_OF('n, ('n TIEMZ 'm)))
    VISIBLE(SMALLR_OF(('m OVAR 'n), ('n TIEMZ 'm)))
    KTHXBYE
  }
}