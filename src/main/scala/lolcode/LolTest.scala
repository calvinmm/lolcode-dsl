package lolcode

object LolTest_Visible extends LolCode {
  def main(args: Array[String]): Unit = {
    HAI
    VISIBLE("hello world")
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
    BTW ("hide")
    VISIBLE ("show")
    BTW ("hide")
    VISIBLE ("show")
    BTW ("hide")
    KTHXBYE
  }
}