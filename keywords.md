## Keywords

[LOLCODE Keywords](http://esolangs.org/wiki/LOLCODE)

`HAI`

* In all LOLCODE programs, `HAI` introduces the program

`BTW Comment`

* Introduces a comment

`CAN HAZ STDIO?`

* Includes the library `STDIO`

`VISIBLE [STRING]`

* Prints `STRING` to the standard output stream

`COMPLAIN [STRING]`

* Prints `STRING` to the standard error stream

`I HAZ A [VAR] ITZ [VALUE]`

* Declares a variable `VAR` and assigns a value `VALUE` to it

`LOL [VAR] R [VALUE]`

* Another way of assigning a variable to the value

```
IZ [VAR] [OPERATOR] [VALUE]?
	YARLY
			...SOMECODE
			KTHX
	NOWAI
			...SOMECODE
			KTHX
```

* An if statement (spec 1.0)
* `OPERATOR` can be `BIGR THAN` (>), `SMALLR THAN` (<), or `LIEK` (==)

```
IM IN YR LOOP
	...SOMECODE
	GTFO
IM OUTTA YR LOOP
```

* Runs the same code in the `IM IN YR LOOP` code block until it goes to a `GTFO` instruction in the code block
* An `IM IN YR LOOP` code block which never goes to a `GTFO` instruction causes an infinite loop

```
HOW DUZ I <NAYM> [YR <arg1>, <arg2>]
	...SOMECODE
	BTW THIS IS HOW YOU RETURN
	FOUND YR <VALUE>
IF U SAY SO
```

* Defines a function with name `NAYM` and returns `VALUE`
* Optional to take arguments, `arg1`, `arg2`

`KTHXBYE`

* `KTHXBYE` terminates the program