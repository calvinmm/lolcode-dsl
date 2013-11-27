LolCode Documentation
================================
*LolCode [source code](https://github.com/calvinmm/lolcode-dsl)*  
LolCode [README](https://github.com/calvinmm/lolcode-dsl/blob/master/README.md)

### Features
-------------------------
- [Code Evaluation](#code-evaluation)  
- [Stdout Printing](#stdout)  
  -  [VISIBLE keyword](#stdout-visible)
- [Stderr Printing](#stderr)  
  -  [COMPLAIN keyword](#stderr-complain)  
- [LolCode Comments](#comments) 
  -  [BTW keyword](#comments-btw)  
- [LolCode Math](#math)
  - [Addition](#math-up)  
  - [Subtraction](#math-nerf) 
  - [Multiplcation](#math-tiemz)  
  - [Division](#math-ovar)  
  - [Modular Arithmetic](#math-mod)  
  - [Max/Min](#math-max)  
- [Boolean operators](#boolean)  
  - [Inequality](#boolean-biggr)   
  - [Equality](#boolean-liek)  
- [Variables](#variables)  
  - [Variable Syntax](#variable-syntax)
  - [Math with variables](#variable-math)  
- [Conditional Branching](#if-statements)  
  - [If Syntax](#if-keywords)  
- [Loops](#loops)  
  - [Loop Syntax](#loop-syntax)  
- [User Input](#input)  
  - [Input Syntax](#input-syntax)  


##Code Evaluation##


##Stdout Printing##
###VISIBLE keyword###

	VISIBLE ("Hello World!")  
	VISIBLE (3)


##Stderr Printing##
###COMPLAIN keyword###

	COMPLAIN ("Hello World!")



##LolCode Comments##
###BTW keyword###

	BTW ("Hello World!")

##LolCode Math##
Note: Math does not follow order of operations, use gratuitous parentheses if in doubt.
###Addition###

	1 UP 2		// Equals 3

###Subtraction###

	1 NERF 2	// Equals -1

###Multiplication###

	1 TIEMZ 2	// Equals 2

###Division###

	1 OVAR 2	// Equals 0
	1.0 OVAR 2	// Equals 0.5


###Modular Arithmetic###

	1 MOD 2		// 

###Max/Min###

	BIGR_OF(1, 2)		// Equals 2
	SMALLR_OF(1, 2)		// Equals 1

##Boolean operators##
###Inequality##

    1 BIGR_THAN 2		// Evaluates to false
    1 SMALLR_THAN 2		// Evaluates to true

###Equality###

	1 LIEK 2			// Evaluates to false


##Variables##
###Variable Syntax###
A variable is a Scala symbol (ex. `'a: Symbol`)  
Declaration (`I_HAZ_A` and `LOL`) and assignment (`ITZ` and `R`) are done using the syntax:

	I_HAZ_A ('a) ITZ 17
	LOL ('str) R "Hello World!"

###Math with variables###
Math using variables proceeds similarly to other programming languages. Insert the variable name where a number should appear, and evaluation will take place as normal.
	
	I_HAZ_A ('x) ITZ 17
	I_HAZ_A ('y) ITZ 9
	I_HAZ_A ('z) ITZ ('x NERF 'y)

 
##Conditional Branching##
###If Syntax###


##Loops##
###Loop Syntax###


##User Input##
###Input Syntax###

