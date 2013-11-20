package lolcode

object LolTest extends LolCode
{
  def main(args: Array[String]): Unit = 
  {

    Test1()
    println("TEST 1 PASSED!\n\n")

    Test2()
  }

  def Test1(): Unit =
  {
    HAI
    VISIBLE ("hello world")
    I_HAZ_A ('a) ITZ 1
    VISIBLE ('a)
    Lol VISIBLE "hello world 2"

    KTHXBYE
  }

  def Test2(): Unit =
  {
    HAI
    VISIBLE ("Test 2 IN THE FLESH")
    KTHXBYE
  }
}
