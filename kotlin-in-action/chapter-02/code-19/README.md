# What chapter in 'Kotlin in Action'?
- Chapter 2.5 Throwing and catching exceptions in Kotlin (2/e version)  
- Chapter 2.5.1 Handling exceptions and recovering from errors: `try`, `catch`, and `finally` (2/e version)  

# What did I look?
- If an error occurs from a function, the function caller catches it and process it.
  - If it does not, it propagates up the call stack.
- An exception can be thrown using the `throw` keyword.
- The `throw` construct is also an *expression*.
  - You can use a `throw` construct in an expression-body function!
  - `throw` constructs can be used as a part of other expression (e.g., `if`)!
- You can handle exceptions by making use of `try`, `catch`, and `finally`.

# Exception and call stack
Let's consider the simple example:
```kotlin
fun parseNumber(text: String): Int {
    return text.toInt()
}

fun calculate() {
    val number = parseNumber("abc") // When this line is executed, it throws an exception (error).
    println(number) // It is not executed.
}

fun main() {
    calculate()
}
```

Then, the flow is roughly as follows:
```text
main()
    -> calculate()
        -> parseNumber()
            -> An error would be occured from "abc".toInt()
```
- In `parseNumber()`, it is recommended to handle the exception.
  - If you don't, the exception go up to the caller of `calculate()`.
  - If you don't again, the exception go further to the caller of `main()`.
  - If you finally don't, the program prints the message describing the exception and the stack trace, and then it terminates.
- If the exception is not properly handled, execution of the remaining lines of code stops.
  - I mean that normal execution is interrupted.
  - In the above example, this prevents `println(number)` from being executed.

# Codes
## `throw`: throwing an exception
```kotlin
val percentage: Int = 120
if (percentage !in 0..100) {
    throw IllegalArgumentException(
        "A percentage value must be between 0 and 100: $percentage"
    )   // Here, an exception is thrown.
}
```

## `throw` construct is also an *expression*.
```kotlin
val percentage =
    if (number in 0..100)
        number
    else
        throw IllegalArgumentException(
            "A percentage value must be between 0 and 100: $number"
        )
```
- `throw IllegalArgumentException(...)` is an expression!
  - In this example, it is used as a part of `if` expression.

## Handling exceptions: `try`, `catch`, and `finally`

```kotlin
import java.io.BufferedReader
import java.io.StringReader

// This function tries to transform a string into an `Int` instance.
fun readNumber(reader: BufferedReader): Int? {
    try {
        val line = reader.readLine()    // read "239"
        return Integer.parseInt(line)   // try to transformation from a string "239" into an integer 239
    } catch (e: NumberFormatException) {
        return null
    } finally {
        reader.close()
    }
}

fun main() {
    val reader = BufferedReader(StringReader("239"))
    println(readNumber(reader))
    // 239

    val reader2 = BufferedReader(StringReader("abc"))
    println(readNumber(reader2))
    // null
}
```
- The return type `Int?`
  - It means that the function `readNumber` can return an integer or `null`.
- `try` block
  - You can write your code here.
  - The code may cause an error.
- `catch` block
  - If an exception occurs from the `try` block, this block exert its force!
  - In this example, `catch` block will be executed if `try` block throw an `NumberFormatException`.
- `finally` block
  - Its role is a **cleanup code**.
  - This block almost always runs at the end, regardless of whether:
    - the `try` block succeeds,
    - control moves to a `catch` block,
    - or a `return` expression is executed.
  - This block is executed after the function prepares its return value but before that value is actually returned.
    - In other words, it runs last before the function actually finishes!


## No obligation to handle checked exceptions, in Kotlin
### Checked exception vs. Unchecked exception in Java
A checked exception is:
```text
An exception whose handling is checked by the compiler.
Exceptions that do not herit from `RuntimeException`.
```
- It includes:
  - `IOException`
  - `SQLException`
  - `ClassNotFoundException`
  - `FileNotFoundException`

On the other hand, an unchecked exception is:
```text
An exception for which the compiler does not enforce explicit handling.
Exceptions in the `RuntimeException` hierarchy.
```
- It includes:
  - `NullPointerException`
  - `IllegalArgumentException`
  - `IndexOutOfBoundsException`
  - `ArithmeticException`
  - `NumberFormatException`

### In Java, you must keep checked exceptions in mind!
In Java, if a function called from your function can lead to a checked exception, you must write something in your function.  
  - You have to take either Method 1 or Method 2.

#### Method 1: Handling the checked exception with `try-catch`
There is an example:
```Java
import java.io.BufferedReader;
import java.io.IOException;

public String readLine(BufferedReader reader) {
    try {
        return reader.readLine(); // It can cause IOException!
    } catch (IOException e) {
        return null;
    }
}
```

#### Method 2: Declaring `throws` in the right side of the function signature
There is an example:
```Java
import java.io.BufferedReader;
import java.io.IOException;

public String readLine(BufferedReader reader) throws IOException {
    return reader.readLine(); // It can cause IOException!
}
```

### In Kotlin, you are not enforced to handle checked exceptions!
**Fundamentally, Kotlin does not differentiate between checked and unchecked exception.**  
Thus, you can write the code of `readline` as follows:
```kotlin
import java.io.BufferedReader

fun readLine(reader: BufferedReader): String? {
    return reader.readLine()
}
```
- The code in Kotlin has less boilerplate than in Java.
- Well, `reader.readline()` is Java API, and it can throw `IOException`.
  - Even so, Kotlin does not force you to handle it!
- If you want to handle the exception, you can make use of `try-catch` construct.
  - But, **note that it is not mandatory!**
