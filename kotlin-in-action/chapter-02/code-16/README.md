# What chapter in 'Kotlin in Action'?
- Chapter 2.4.2 Iterating over numbers: Ranges and progressions (2/e version)  

# What did I look?
- `for` loops
  - To tell you in advance, `for` loops in Kotlin is almost the same as in Python.
  - Not C-style `for` loops
- `..` operator for declaring a *range*
  - Unlike Python, *ranges* in Kotlin are *closed* (i.e., *inclusive*).
  - For example, `1..4` means "1, 2, 3, and 4", not "1, 2, and 3".

# Codes
## Declaring a *range*
*range* means:
```text
an interval itself between two values,
such as '1 to 10', 'a to z'.
```

To declare a *range*, you can use `..` operator.

```kotlin
val oneToTen = 1..10
// It means "1, 2, 3, 4, 5, 6, 7, 8, 9, 10"
```
- In Kotlin, **the range is _closed_ (i.e., _inclusive_)**.
  - `oneToTen` include from 1 to 10.


## Declaring a *progression*
*progression* can be felt similar to *range*, yet there are a few difference.  
*progression* means:
```text
a series (iterable sequence) of values with a start value, an end value, a direction, and a step.
```

You can declare a *progression* in the following way:
```kotlin
val aaa = 100 downTo 1 step 2
```
- It means:
  - `100, 98, 96, ..., 2`

## Iterating over a range with `for` loops
The syntax of `for` loops is close to Python!  
Thus, you from Python would be familiar to `for` in Kotlin.

```kotlin
fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz "
    i % 3 == 0 -> "Fizz "
    i % 5 == 0 -> "Buzz "
    else -> "$i "
}

fun main() {
    for (i in 100 downTo 1 step 2) {
        print(fizzBuzz(i))  // Differ from `println`.
    }
}
```
- As the `for` loop is executed, `i` get an integer each iteration.
  - 1st iteration -> `i == 100`
  - 2nd iteration -> `i == 98`
  - ...
  - The last iteration -> `i == 2`

# Misc.
## *range* vs. *progression*
A *range* can be thought of as a kind of *progression*.

The difference between them is:
```text
Range -> focuses on representing a span of values and checking whether a particular value is contained in that span.
Progression -> focuses more on the order (i.e., direction) of iteration and the step size between consecutive values.
```

## `..` vs. `until` vs. `..<`
- `..`: Declaring a *closed* range
- `until` & `..<`: Declaring a left-closed and right-open interval
  - Example: `1 until 5` -> 1, 2, 3, 4
  - Example: `1..<5` == `1..4` -> 1, 2, 3, 4
  - This is like `range` in Python!

| Code                   | Meaning             | Included values          |
| -------------------- | -------------- | --------------- |
| `1..5`               | From 1 to 5        | `1, 2, 3, 4, 5` |
| `1 until 5`          | From 1 to just before 5      | `1, 2, 3, 4`    |
| `1 ..< 5`          | From 1 to just before 5      | `1, 2, 3, 4`    |
| `5 downTo 1`         | From 5 to 1 (reverse)    | `5, 4, 3, 2, 1` |
| `1..10 step 2`       | From 1 to 10, step size 2 | `1, 3, 5, 7, 9` |
| `10 downTo 1 step 3` | From 10 to 1, step size 3 | `10, 7, 4, 1`   |

## `println` vs `print`
| Function             | Behavior            |
| -------------- | ------------- |
| `print(...)`   | Printing without line breaking (`\n`) |
| `println(...)` | Printing followed by line breaking   |
