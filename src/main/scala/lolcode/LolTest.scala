package lolcode

object LolTest extends LolCode
{
  def main(args: Array[String]): Unit = 
  {
    HAI
    VISIBLE("Test1:")
    Test1()
    VISIBLE("\nTest 1 done!\n\nTest2:")
    Test2()
    KTHXBYE
  }

  def Test1(): Unit =
  {
    VISIBLE ("hello world")
    I_HAZ_A ('a) ITZ 1
    VISIBLE ('a)
    Lol VISIBLE "hello world 2"
  }

  def Test2(): Unit =
  {
    VISIBLE ("Test 2 IN THE FLESH")
  }
}
