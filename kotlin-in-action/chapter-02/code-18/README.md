# What chapter in 'Kotlin in Action'?
- Chapter 2.4.4 Using `in` to check collection and range membership (2/e version)  

# What did I look?
- The `in` operator can be utilized for checking whether a value is a member of an interval.
- The `!in` operator has the opposite function of `in`. That is, it can check whether a value is NOT in an interval.
- The range operator `..` can be used with any class whose instance can be compared.
  - In other words, it can be used with **any classes that implements the `kotlin.Comparable` interface**!

# Codes
## Usage of `in`: checking range membership
```kotlin
fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c: Char) = c !in '0'..'9'	// c < '0' || c > '9'

fun main() {
    println(isLetter('q')) // true
    println(isNotDigit('x'))  // true
}
```
- In `isLetter`, the following two boolean expressions are equivalent:
    ```text
    - c in 'a'..'z'
    - 'a' <= c && c <= 'z'
    ```
- In `isNotDigit`, the following two boolean expressions are equivalent:
    ```text
    - c !in '0'..'9'
    - c < '0' || c > '9'
    ```

## Making use of `in` (or `!in`) in `when` expressions
```kotlin
fun recognize(c: Char) = when (c) {
    in '0'..'9' -> "It's a digit!"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter!"	// `,` means `||` (OR gate).
    else -> "I don't know..."
}

fun main() {
    println(recognize('8'))
    // It's a digit!
}
```

- You can use `in` and `!in` as branch conditions in `when` expression!

## `..` is available for any class that implement the `kotlin.Comparable` interface!
The `..` operator is widely usable than you think!

```kotlin
println("Kotlin" in "Java".."Scala")	// true. It is equivalent to "Java" <= "Kotlin" && "Kotlin" <= "Scala".
println("Kotlin" in setOf("Java", "Scala"))	// false. The set does not have the string "Kotlin".
```
- Note that `String` is also the class which implements the `Comparable` interface.
  - `String` instances are compared alphabetically.
  - That is, just consider alphabetical sorting.
  - According to the result of sorting, the order is:
    ```text
    Java -> Kotlin -> Scala
    ```
  - Thus, the below comparisons are all true!
    ```text
    "Java" <= "Kotlin"
    "Kotlin" <= "Scala"
    ```
  - In conclusion, `"Kotlin" in "Java".."Scala"` returns `true`.
- Meanwhile, `setOf("Java", "Scala")` has two elements: `"Java"` and `"Scale"`.
  - `"Kotlin"` is not the member of the set.
  - Therefore, `"Kotlin" in setOf("Java", "Scala")` returns `false`.
