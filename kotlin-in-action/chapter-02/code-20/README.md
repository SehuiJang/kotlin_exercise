# What chapter in 'Kotlin in Action'?
- Chapter 2.5.2 Using `try` as an expression (2/e version)  

# What did I look?
- You can use `try-catch` as an expression just like `if` and `when`.
- Unlike `if` and `when`, you must write curly braces for the statement body (i.e., bodies of `try`, `catch`, and `finally`).

# Codes
In Kotlin, the `try-catch` construct is an *expression*.
  - It returns a value.

```kotlin
import java.io.BufferedReader
import java.io.StringReader

fun readNumber(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        return
    } finally {
        reader.close()
    }

    println(number)
}

fun main() {
    val reader = BufferedReader(StringReader("not a number"))
    readNumber(reader)  // Nothing is printed.
}
```
- Even though `try` body, `catch` body, and `finally` body have a single line, **you have to use curly braces!**
- Just like `if` and `when`, the value of the last expression is the value of the `try` block.
  - The same applies to the `catch` block.
- If the execution of a `try` block succeeds, the last expression in the block would be assigned to the variable `number`.
- If the `try` block fails and throws `NumberFormatException`, a `return` statement without a value will be run.
  - The function terminates just after running the `finally` block.
  - In addition, the below `println(number)` is NOT executed!!
- Meanwhile, the `finally` block is just a cleanup code.
  - It has no influence on the result of the `try-catch` expression.
  - The result value of `try-catch` expressions is either the result of a `try` block or a `catch` block.

## A slight modification
```kotlin
import java.io.BufferedReader
import java.io.StringReader

fun readNumber2(reader: BufferedReader) {
    // The type of `number` is inferred as `Int?`
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        null    // This value is used.
    } finally {
        reader.close()
    }

    println(number)
}

fun main() {
    val reader2 = BufferedReader(StringReader("not a number"))
    readNumber2(reader2)
    // null
}
```
- In this modified code, the `catch` block has a value `null`.
  - If the `try` block throws `NumberFormatException`, the result of the `catch` block is `null`.
  - The variable `number` gets a value `null`, and the execution of the function `readNumber2` continues.
  - The below `println(number)` can be executed!!