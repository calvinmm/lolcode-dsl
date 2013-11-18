# LOLCODE DSL
### Assignment 4
#### CS 345H Fall 2013

> Andrew Sharp
> 
> Calvin MacKenzie
> 
> Corbyn Salisbury
> 
> Irvin Shen

## Scala Setup
I will be setting up the project following this [guide](http://utcompling.github.io/nlpclass-fall2013/scala/setup.html).

We will be using the the [Scala Build Tool (sbt)](http://www.scala-sbt.org/) to build and run our project.

### Scala Review

If you need to review your Scala, you can find a helpful guide [here](http://utcompling.github.io/nlpclass-fall2013/scala/basics.html).

## Contributing

You should clone the repo via ssh (preferably) and this will require you to authenticate with your Github profile. Following this, you can set up the project with Eclipse (if you prefer), any editor will work fine.

```
mkdir lolcode-dsl
cd lolcode-dsl
git clone git@github.com:calvinmm/lolcode-dsl.git
sbt "eclipse with-source=true"
```

**Important** - If you wish to use Eclipse, you must follow the Scala sbt setup for Eclipse, in the [Scala Setup section](#scala-setup)

### Style

For style, we will mostly follow [Twitter's Formatting Guide](http://twitter.github.io/effectivescala/#Formatting) as well as the official [Scala Style Guide](http://docs.scala-lang.org/style/).

### Specific Rules

Specifically, please abide by the following style rules:

1. Use two spaces for indentation, Eclipse with automatically do this for you if you allow it to auto format your code.
1. Try to always use `immutable` data structures
1. Try to initialize all variables as `val` instead of `var`
1. If a solution exists functionally, opt to use it instead of one that is imperative 
1. **Never** use `null`, always use `Option`

## Issues
Find and report issues [here](https://github.com/calvinmm/lolcode-dsl/issues)

## LOLCODE Keywords
List of current LOLCODE keywords [here](keywords.md)





	