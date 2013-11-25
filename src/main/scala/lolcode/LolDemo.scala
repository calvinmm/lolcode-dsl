package lolcode

object LolDemo extends LolCode{
  def main(args: Array[String]): Unit = {
    HAI
    VISIBLE("Welcome to the guessing game!")
    VISIBLE("Each turn you can guess a number between 1 and 20.")
    VISIBLE("We will give you hints regarding your guess.")
    VISIBLE("--------------")
    I_HAZ_A('number) ITZ RAND(1, 10)
    VISIBLE('number)
    I_HAZ_A('attempts) ITZ 0
    BTW("START LOOP")
    VISIBLE("Guess a number between 1 and 20 (inclusive)")
    GIMMEH('guess)
    IZ('guess SMALLR_THAN 'number)
    	VISIBLE("Higher!")
    	NOWAI
    KTHX
    IZ('guess BIGR_THAN 'number)
    	VISIBLE("Lower!")
    	NOWAI
    KTHX
    IZ('guess LIEK 'number)
    	VISIBLE("You got it!")
    	BTW("Break Loop")
    	NOWAI
    KTHX
    LOL('attempts) ITZ ('attempts UP 1)
    BTW("END LOOP")
    KTHXBYE
  }
}