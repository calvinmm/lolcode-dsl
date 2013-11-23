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
    COMPLAIN(10.2)
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

object LolTest_DoubleAssign extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    I_HAZ_A('z) ITZ 3.5
    VISIBLE('z)
    I_HAZ_A('q) R 10.2
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

object LolTest_Math_Double_Up extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    VISIBLE(2.5 UP 3)
    LOL('a) ITZ (1.2 UP 100)
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

object LolTest_Math_Double_Nerf extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    VISIBLE(13.2 NERF 2.1)
    LOL('a) ITZ (1337.3 NERF 1000.2 NERF 32.5)
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

object LolTest_Math_Double_Tiemz extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    VISIBLE(3.3 TIEMZ 3.3)
    LOL('c) ITZ (3 TIEMZ 20.2)
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

object LolTest_Math_Double_Ovar extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    VISIBLE(9.3 OVAR 3)
    LOL('d) ITZ (100.1 OVAR 3.4)
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

object LolTest_Math_Double_Mod extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    VISIBLE(9.2 MOD 3.9)
    LOL('e) ITZ (12.3 MOD 2.3)
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