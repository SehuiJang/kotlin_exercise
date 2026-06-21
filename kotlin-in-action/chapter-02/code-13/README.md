# What chapter in 'Kotlin in Action'?
- Chapter 2.3.7 Refactoring: Replacing an `if` with a `when` expression (2/e version)  

# What did I look?
- `if` branch without the curly braces.
- `when` expression with branches that check and smart cast the subject.

# Example code
## Block-body function &rarr; Expression-body function with `if`
In [code-12](https://github.com/SehuiJang/kotlin_exercise/tree/main/kotlin-in-action/chapter-02/code-12), there is a block-body function `eval` with `if` expression.  
Considering `if` is an *expression* in Kotlin, you can turn the function `eval` with an expression-body function.

```kotlin
fun eval_if_exp(e: Expr): Int =
    if (e is Num) {	// Type check
        e.value     // This is a `return` value!
    } else if (e is Sum) {	// Type check
        eval_if_exp(e.right) + eval_if_exp(e.left)     // This is a `return` value!
    } else {
        throw IllegalArgumentException("Unknown expression")    // It's also an expression, yet it does not return a value but throw an exception.
    }
```
- Note that the keyword `return`s are removed!
  - **The last expression in the block of `if` branch** is returned as a result.
  - **The same applies to the block of `when` branch.**

## More concise expression-body function with `if`
As you can see, each branch consists of a single expression.  
In this case, you can eliminate the curly braces from each branch!

```kotlin
fun eval_if_exp_oneline(e: Expr): Int =
    if (e is Num) e.value
    else if (e is Sum) eval_if_exp_oneline(e.right) + eval_if_exp_oneline(e.left)
    else throw IllegalArgumentException("Unknown expression")
```
- All the curly braces are removed.
  - resulting in more concise code!

## Expression body function with `when`
Well, `when` expression is also a good choice!

```kotlin
fun eval_when_exp(e: Expr): Int =
    when (e) {
        is Num -> e.value   // type check & smart cast
        is Sum -> eval_when_exp(e.right) + eval_when_exp(e.left)
        else -> throw IllegalArgumentException("Unknown expr")
    }
```
- Each branch condition checks the type of the subject `e`, followed by smart cast!
- Note that:
  ```text
  `when` branches are able to check the type of the `when` argument value,
  not only checking the value for equality.
  ```

# Caution
As you've seen, the value of the last expression in `if`/`when` branch is automatically returned as the resultant value of the branch.  
However, it is not the case in the function block.
```text
When you define a block-body function,
you MUST explicitly write a `return` if you wanna return a result value.
```
