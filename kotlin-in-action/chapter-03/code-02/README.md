# What chapter in 'Kotlin in Action'?
- Chapter 3.2.1 Named arguments (2/e version)  

# What did I look?
- When invoking a function, you can specify the names of arguments.
  - You can choose some of arguments of which you specify the names.
- Specifying the names of all arguments is also possible!
  - If you do that, you can switch the order of the arguments.
- You from Python may feel familiar to this chapter.

# Codes
## Preparing an example
If you create a collection (e.g, a list) and print it with `println`, the format of the output is fixed.  
So, you can declare and implement a function to make a custom format of the output of a collection.  
Like the following code:
```kotlin
fun <T> joinToString(
    collection: Collection<T>,
    separator: String,
    prefix: String,
    postfix: String
): String {

    val result = StringBuilder(prefix)

    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}
```
- The above function is generic. You can see `<T>`.
  - It means the function can be applied to various collections, regardless of the element type.
  - The content about generics will be discussed in detail in the later chapter.

## Basic manner to invoke a function
Well, the following code is a basic way to call a function.
```kotlin
fun main() {
    val list = listOf(1, 2, 3)

    println(joinToString(list, "; ", "(", ")"))
    // (1; 2; 3)
}
```

## Specifying the names of some of the arguments
When you invoke a function, you can specify the names of some of the arguments.

```kotlin
fun main() {
    val list = listOf(1, 2, 3)

    println(joinToString(list, separator = " ", prefix = " ", postfix = "."))
    //  1 2 3.
}
```

## Specifying all the argument names + Switching the order

```kotlin
fun main() {
    val list = listOf(1, 2, 3)

    println(
        joinToString(
            postfix = ".",
            separator = " ",
            collection = list,
            prefix = " "
        )
    )
    //  1 2 3.
}
```
- When all the argument names are specified, you can freely determine the order of the arguments.
