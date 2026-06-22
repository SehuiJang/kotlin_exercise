# What chapter in 'Kotlin in Action'?
- Chapter 2.4.1 Repeating code while a condition is true: The `while` loop (2/e version)  

# What did I look?
- `while` & `do-while` loops
- Label with "at" sign (`@`)
- When using `break` or `continue`, you can reference a certain loop by using `@label`.

# Basic code
## `while` loop
A `while` loop checks its condition right before starting each iteration.
```kotlin
while (condition) {
    /*...*/
    if (shouldExit) break
}
```
- You can use `break` to exit a loop.

## `do-while` loop
A `do-while` loop:
  1. executes the loop body (iteration) first
  2. and then checks the condition immediately after each iteration.
```kotlin
do {
    if (shouldSkip) continue
    /*...*/
} while (condition)
```
- You can use `continue` to immediately move on to the next iteration of a `while` or `do-while` loop.
  - Note that **the `continue` statement skips the rest of the current iteration.**

## `continue` & `break` with `@label`
Well, `@label` is so useful **when using nested loops**!

```kotlin
outerLoop@ while (outerCondition) {
    while (innerCondition) {
        if (shouldExitInner) break
        if (shouldSkipInner) continue
        if (shouldExit) break@outerLoop
        if (shouldSkip) continue@outerLoop
        // ...
    }
    // ...
}
```
- `break` without a label: exists the **nearest enclosing loop**.
- `continue` without a label: skips the rest of the current iteration of the nearest enclosing loop and proceeds (i.e., jumps) to its next iteration.
  - In a `while` loop, this means that control immediately moves to the next condition check.
- Labeled `break`: exits the loop marked with the specified label.
  - In the code above, both inner and outer `while` loop are terminated.
- Labeled `continue`: skips the rest of the current iteration of the loop marked with the specified label and immediately proceeds to its next iteration.
  - In the code above, this means the inner `while` loop is stopped immediately.
  - The code below the inner loop is also not executed.
  - Control moves directly to the next iteration (+ condition check) of the outer `while` loop.

- When it comes to `@label`, you can use any valid identifier!
  - For instance, all the following labels can be used!
    ```kotlin
    outer@ while (true) { }
    outerLoop@ while (true) { }
    rowLoop@ while (true) { }
    searchLoop@ while (true) { }
    myCustomLabel@ while (true) { }
    ```

# Example codes
Refer to the `example.kt` for the full code!

## `while` + `break`
```kotlin
var i = 0

while (i < 5) {
    println("i = $i")

    if (i == 2) {
        println("Execute break")
        break
    }

    i++
}

println("loop (while + break) finished")
```

## `do-while` + `continue`
```kotlin
var i = 0

do {
    i++

    if (i == 2) {
        println("Skip i = 2")
        continue
    }

    println("i = $i")
} while (i < 4)

println("loop (do-while + continue) finished")
```

## `break` without label, in nested loop
```kotlin
var outer = 1

while (outer <= 3) {
    var inner = 1

    while (inner <= 3) {
        println("outer = $outer, inner = $inner")

        if (inner == 2) {
            println("inner loop break")
            break
        }

        inner++
    }

    outer++
}

println("finished (break without label in nested while loop)")
```

## `break@outer` in nested loop
```kotlin
var outer = 1

outerLoop@ while (outer <= 3) {
    var inner = 1

    while (inner <= 3) {
        println("outer = $outer, inner = $inner")

        if (outer == 2 && inner == 2) {
            println("Execute break@outerLoop")
            break@outerLoop
        }

        inner++
    }

    outer++
}

println("finished (break@outer in nested while loop)")
```

## `continue@outer` in nested loop
```kotlin
var outer = 1

outerLoop@ while (outer <= 3) {
    var inner = 1

    while (inner <= 3) {
        if (inner == 2) {
            println("outer = $outer, inner = $inner -> continue@outerLoop")

            outer++     // If this line is omitted, it will be an infinite loop!
            continue@outerLoop
        }

        println("outer = $outer, inner = $inner")
        inner++
    }

    outer++
}

println("finished (continue@outer in nested while loop)")
```

# Misc.
## Can we attach a label to inner loops?
Yes, we can!  
However, there is usually no need it.  

There is an example:
```kotlin
fun main() {
    var outer = 1

    while (outer <= 2) {
        var inner = 1

        innerLoop@ while (inner <= 3) {
            println("outer = $outer, inner = $inner")

            if (inner == 2) {
                println("break@innerLoop 실행")
                break@innerLoop
            }

            inner++
        }

        outer++
    }
}
```

## Can we use a label even for a single loop, not nested loop?
Yes, we can!  
Even so, it is also not necessary.
- In non-nested loops, `break@label` and `continue@label` are actually identical to `break` and `continue`, respectively.

```kotlin
fun main() {
    var i = 0

    singleLoop@ while (i < 5) {
        println(i)

        if (i == 2) {
            break@singleLoop    // the same as `break`
        }

        i++
    }
}
```

## Can `@label` also be utilized in `for` loop?
Of course!!  
The same usage of `@label` applies to `for` loops which are covered in the next section.  
You can apply `@label` to `for`, `while`, and `do-while`!  

```kotlin
fun main() {
    rowLoop@ for (row in 1..3) {
        for (col in 1..3) {
            println("row = $row, col = $col")

            if (row == 2 && col == 2) {
                println("Execute break@rowLoop")
                break@rowLoop   // Both inner and outer `for` loops will be terminated!
            }
        }
    }

    println("finished")
}
```
