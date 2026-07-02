# What chapter in 'Kotlin in Action'?
- Chapter 3.4.1 Extending the Java collections API (2/e version)

# What did I look?
- Some examples of extended APIs in Kotlin standard library which are implemented by extension functions

# The standard library reference
You can see all the methods (members & extensions) belonging to all each library class
- Link: https://kotlinlang.org/api/latest/jvm/stdlib/

# Code
```kotlin
fun main() {
    val strings: List<String> = listOf("first", "second", "fourteenth")
    println(strings.last())
    // fourteenth
    
    val numbers: Collection<Int> = setOf(1, 14, 2)
    println(numbers.sum())
    // 17
}
```
- `last` and `sum` are declared as extension functions.
  - They are imported in your `.kt` files by default.
  - The simple edition of declarations looks like:
    ```text
    fun <T> List<T>.last(): T { /* returns the last element */ }
    fun Collection<Int>.sum(): Int { /* sum up all elements */ }
    ```
  - In fact, `sum` works for any number types.

# Misc.
There are a great deal of extension functions in Kotlin standard library, yet you don't need to memorize them.  
Your IDE will help you by proposing all the possible functions which include regular methods and extension functions.  
