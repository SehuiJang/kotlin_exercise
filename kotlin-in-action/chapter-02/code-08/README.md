# What chapter in 'Kotlin in Action'?
- Chapter 2.3.2 Using the when expression to deal with enum classes (2/e version)  

# What did I look?
- Some examples as for `when` expression
- Dealing with enum classes, in `when` expression.

# Example1
## Execution script
In your terminal, adjust the current path to this directory, utilizing `cd`:
```bash
cd C:/path/to/your/chapter-02/code-08
```
And then, compile and execute the code:
```bash
C:/path/to/your/kotlinc ./examples/colors.kt ./examples/example1.kt -include-runtime -d example1.jar && java -jar example1.jar
```

## Notes on the code
```kotlin
package ch02.colors

// This is an expression-body function.
fun getMnemonic(color: Color) =
    when (color) {
        Color.RED -> "Richard"  // `break` is not required!
        Color.ORANGE -> "Of"
        Color.YELLOW -> "York"
        Color.GREEN -> "Gave"
        Color.BLUE -> "Battle"
        Color.INDIGO -> "In"
        Color.VIOLET -> "Vain"
    }

fun main() {
    println(getMnemonic(Color.BLUE))
    // Battle
}
```
- The function `getMnemonic` returns a `when` directly.
  - Well, `when` is an **"expression"**, meaning that it has its value itself.
  - Thus, `getMnemonic` can be implemented in the form of an expression-body function.
- Depending on `color` value, `when` in the above example returns the corresponding string.
  - It is similar to `if`.
- **Note that `break` statements for each branch is not required!**
  - Unlike Java.
  - For instance, if `color == Color.BLUE`, `"Battle"` is returned without `"In"` and `"Vain"`.

# Example2
## Execution script
The command for compilation:
```bash
C:/path/to/your/kotlinc ./examples/colors.kt ./examples/example2.kt -include-runtime -d example2.jar && java -jar example2.jar
```

## Notes on the code
```kotlin
package ch02.colors

// In practice, more complicated logic can be here.
// In this example, we just assume that the function `measureColor` return `Color.ORANGE`.
fun measureColor() = Color.ORANGE

fun getWarmthFromSensor(): String {
    val color = measureColor()
    return when(color) {
        Color.RED, Color.ORANGE, Color.YELLOW -> "warm (red = ${color.r})"
        Color.GREEN -> "neutral (green = ${color.g})"
        Color.BLUE, Color.INDIGO, Color.VIOLET -> "cold (blue = ${color.b})"
    }
}

fun main() {
    println(getWarmthFromSensor())
    // warm (red = 255)
}
```

- You can combine multiple values in the same branch.
  - By using `,`!
  - For example, `Color.RED`, `Color.ORANGE`, and `Color.YELLOW` are combined in the first branch.

# Example3
## Execution script
The command for compilation:
```bash
C:/path/to/your/kotlinc ./examples/colors.kt ./examples/example3.kt -include-runtime -d example3.jar && java -jar example3.jar
```

## Notes on the code
```kotlin
// Importing enum constant values.
import ch02.colors.Color.*  // This is a keypoint of this code!

fun measureColor() = ORANGE     // Thanks to `import`, you don't need to specify the enum class name `Color`.

fun getWarmthFromSensor(): String {
    val color = measureColor()
    return when (color) {
        RED, ORANGE, YELLOW ->  // imported constants
            "warm (red = ${color.r})"
        GREEN ->
            "neutral (green = ${color.g})"
        BLUE, INDIGO, VIOLET ->
            "cold (blue = ${color.b})"
    }
}

fun main() {
    println(getWarmthFromSensor())
    // warm (red = 255)
}
```

- You can import the constant values of the enum class `Color`.
  - After that, you don't need to specify `Color.`!
  - You can reduce the repetition of `Color.`.
