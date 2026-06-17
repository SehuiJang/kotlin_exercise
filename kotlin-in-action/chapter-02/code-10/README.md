# What chapter in 'Kotlin in Action'?
- Chapter 2.3.4 Using the when expression with arbitrary objects (2/e version)  

# What did I look?
- We can use any kind of object as a subject and a branch condition.
- `when` expression has to be exhaustive.

# Example code
## Execution script
In your terminal, adjust the current path to this directory, utilizing `cd`:
```bash
cd C:/path/to/your/chapter-02/code-10
```
And then, compile and execute the code:
```bash
C:/path/to/your/kotlinc ./example/colors.kt ./example/example.kt -include-runtime -d example.jar && java -jar example.jar
```

## Notes on the code
```kotlin
package ch02.colors
import ch02.colors.Color.*

fun measureColor() = ORANGE

fun mix(c1: Color, c2: Color) =
    when (setOf(c1, c2)) {  // the subject can be any object!
        setOf(RED, YELLOW) -> ORANGE
        setOf(YELLOW, BLUE) -> GREEN
        setOf(BLUE, VIOLET) -> INDIGO
        else -> throw Exception("Dirty color")  // You have to make `else` branch as a default case. It makes the `when` exhaustive.
}

fun main() {
    println(mix(BLUE, YELLOW))
    // GREEN
}
```

- As you can see, you can adopt any kind of object as a subject and branch condition of `when` expression.
  - Here, a function `setOf` in the Kotlin standard library is employed.
  - `setOf` creates a `Set`, and the order of elements does not matter.
- Note that the `when` expression:
  - checks its subject against the branches **from top to down**
  - and selects the first branch whose condition is satisfied.
- Well, the `else` branch is evaluated, if no previous branch condition is satisfied.
  - Note that Kotlin compiler cannot infer that every possible combination of color sets (`setOf(c1, c2)`) has been handled.
  - Since the `when` produces the return value of the `mix` function, it must be *exhaustive*.
  - For this reason, **an `else` branch is required as a default case to make the `when` expression exhaustive.**
