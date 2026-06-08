# What chapter in 'Kotlin in Action'?
Chapter 2.1.2 Functions

# What did I look?
The structure and form of functions!


## Entire code
```kotlin
// Kotlin

// Block body: The function max1 has a block body. It's body is in curly braces.
fun max1(a: Int, b: Int): Int
    { return if (a > b) a else b
}

// Expression body: The function max2 returns an expression directly.
fun max2(a: Int, b: Int): Int = if (a > b) a else b
fun max3(a: Int, b: Int) = if (a > b) a else b  // More simplified function, omitting its return type

fun main(){
    // Java-style String.format(), requiring format specifiers
    println("max1(1, 2): %d".format(max1(1, 2)))

    // String templates (inserting the result of an equation or function). Recommended in Kotlin
    println("max2(5, 3): ${max2(5, 3)}")    

    // String templates (inserting just a variable). Recommended in Kotlin
    val ret = max3(10, 11)
    println("max3(10, 11): $ret")
}
```

## Function declaration
It starts with the keyword `fun`, and the function name follows it.  
And then, the parameter list in parenthesis comes after the function name, followed by a colon `:` and the return type.  

```kotlin
// Kotlin

// Function declaration
fun max2(a: Int, b: Int): Int
```

## Function body
### Block-body function
You can write the function body by using curly braces.  
In this case, it's called *block body*.
```kotlin
// Kotlin
fun max1(a: Int, b: Int): Int
    { return if (a > b) a else b
}
```

### Expression-body function
Meanwhile, when the function consists of a single expression, you can remove the curly braces.  
The function is able to be written in the form that directly returns an expression.  
Additionally, you can also omit the return type.

```kotlin
// Kotlin
fun max2(a: Int, b: Int): Int = if (a > b) a else b
fun max3(a: Int, b: Int) = if (a > b) a else b
```

## Need to write the return type?
### 1. Block-body function
  - When it returns a value, **specifying the return type is required**.
    - Omitting the return type is **NOT allowed**.
  - You have to write the `return` statements.
### 2. Expression-body function
  - **You can omit the return type**.
  - _**Type inference**_: In this case, the compiler checks the expression and infers its type.
    - Its type is used as the return type.


