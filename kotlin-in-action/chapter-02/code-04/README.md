# What chapter in 'Kotlin in Action'?
Chapter 2.1.4 Easier string formatting: string templates (MEAP version)

# What did I look?
Kotlin-style string formatting!

## A variable reference within a string
You can utilize a character `$` as a variable reference within a string.  
Specifically, just put a `$` in front of a variable name, if you want to refer to the local variable.

```kotlin
fun main(args: Array<String>) {
    val name = if (args.size > 0) args[0] else "Kotlin"

    println("Hello, $name!")    // equivalent to a string concatenation ("Hello, " + name + "!") in Java
}
```

## A reference to the result of expressions or functions
You can also refer to the value of expressions or functions, not only a variable.  
It requires to simply put curly braces around the expression or function.

```kotlin
fun main(args: Array<String>) {
    if (args.size > 0) {
        println("Hello, ${args[0]}!")
    }
}
```

## Using `$` itself within a string
When you want to use `$` just as a character within a string, just use `\$`!

```kotlin
fun main(args: Array<String>) {
    println("\$x!")     // Output: $x!
}
```

## Nested double quotes
You can write double quotes within other double quotes.  
Importantly, the inner double quotes must be within an expression (i.e., within curly braces).

```kotlin
fun main(args: Array<String>) {
    println("Hello, ${if (args.size > 0) args[0] else "someone"}")
}
```

Well, the structure can be thought of as follows:
```text
"Hello, ${ if (args.size > 0) args[0] else "someone" }!"
          └───────────────────────────────────────┘
                    Kotlin expression
```
