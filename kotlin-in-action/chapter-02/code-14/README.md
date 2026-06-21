# What chapter in 'Kotlin in Action'?
- Chapter 2.3.8 Blocks as branches of `if` and `when` (2/e version)  

# What did I look?
- When a branch of `if`/`when` has a block containing multiple expressions, the last expression is the result on the premise that the block has no `return` expression.

# Example code
```kotlin
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun evalWithLogging(e: Expr): Int =
    when (e) {
        is Num -> {
            println("num: ${e.value}")
            e.value // The last expression -> is returned
        }
        is Sum -> {
            val left = evalWithLogging(e.left)
            val right = evalWithLogging(e.right)
            println("sum: $left + $right")
            left + right    // The last expression -> is returned
        }
        else -> throw IllegalArgumentException("Unknown expression")
    }

fun main() {
    println(evalWithLogging(Sum(Sum(Num(1), Num(2)), Num(4))))
    // num: 1
    // num: 2
    // sum: 1 + 2
    // num: 4
    // sum: 3 + 4
    // 7
}
```

- There are three branches:
  - `is Num`
  - `is Sum`
  - `else`
- The first two branches have block bodies.
  - Each block contains multiple lines, including expressions.
  - The value of the last expression in each branch block is returned as the result of that branch.

---

## How about an explicit `return` expression in the block body of `if`/`when`?
If an explicit `return` in the block body in `if`/`when` is executed, the function terminates immediately at that point.  
  - Any code after the explicit `return` is not executed!
  - In other words, any code that follows the `return` expression is **unreachable**!

There are a few examples.
```kotlin
fun test(x: Int): Int {
    if (x > 0) {
        return x
        println("never printed")    // Not executed!
    }

    return 0
}
```

```kotlin
fun describeNumber(x: Int): String {
    when (x) {
        0 -> {
            println("zero branch")
            return "zero"
            println("this line is never executed")  // Not executed!
        }

        1 -> {
            println("one branch")
            return "one"
        }

        else -> {
            println("else branch")
            return "other"
        }
    }
}
```

## More concise version with a single `return`
Instead of returning from each branch separately, you can return the `when` expression itself.
  - Returning the result of the `when` ONLY ONCE
  - This way may help you to write the code in a more concise form!

```kotlin
fun describeNumber2(x: Int): String {
    return when (x) {
        0 -> {
            println("zero branch")
            "zero"
        }

        1 -> {
            println("one branch")
            "one"
        }

        else -> {
            println("else branch")
            "other"
        }
    }
}
```