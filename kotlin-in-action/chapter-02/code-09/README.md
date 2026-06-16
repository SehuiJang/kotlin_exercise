# What chapter in 'Kotlin in Action'?
- Chapter 2.3.3 Capturing the subject of a when expression in a variable (2/e version)  

# What did I look?
- What do we call the subject of `when`?
- When the subject of `when` has the form of a variable, you can restrict the scope of it.

# Example code
## Execution script
In your terminal, adjust the current path to this directory, utilizing `cd`:
```bash
cd C:/path/to/your/chapter-02/code-09
```
And then, compile and execute the code:
```bash
C:/path/to/your/kotlinc ./example/colors.kt ./example/example.kt -include-runtime -d example.jar && java -jar example.jar
```

## What is the subject of `when`?
```
The subject of a when expression is the value that when uses to decide which branch to execute.
```
That is, in a `when` expression, the subject is the value being checked against the branch conditions.

## Notes on the code
```kotlin
import ch02.colors.Color.*

fun measureColor() = ORANGE

fun getWarmthFromSensor() =
    when (val color = measureColor()) {
        RED, ORANGE, YELLOW -> "warm (red = ${color.r})"
        GREEN -> "neutral (green = ${color.g})"
        BLUE, INDIGO, VIOLET -> "cold (blue = ${color.b})"
    }

fun main() {
    println(getWarmthFromSensor())
    // warm (red = 255)
}
```

- In the above code, the subject of the `when` expression is the return value of `measureColor()`.
- A variable can be declared inside the parenthesis after `when` to capture the return value of `measureColor()`.
  - The return value of `measureColor()` serves as the subject of the `when` expression.
  - The declared variable, `color`, is scoped only to the `when` expression.
- This method is useful:
  - You can evaluate the subject **ONCE**.
  - And then, reuse it across the branches (without reevaluation).
  - At the same time, you can keep the captured variable, `color`, from interacting unnecessarily with variables outside the `when` expression.
