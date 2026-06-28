# What chapter in 'Kotlin in Action'?
- Chapter 3.2.2 Default parameter values (2/e version)  

# What did I look?
- You can specify default values of parameters in a function declaration.
- Specifying default values can reduce the burden and hassle of function overloading.

# Codes
## Specifying default values of parameters
Let's revisit the example function `joinToString` with something modified.  
You can see a few default parameter values in the function declaration.  

```kotlin
fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ", ",   // default parameter value
    prefix: String = "",
    postfix: String = ""
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

## Taking advantage of default parameter values
In the call site, you can write the caller in the following manner, to make use of default parameter values.
```kotlin
fun main() {
    val list = listOf(1, 2, 3)
    
    println(joinToString(list, ", ", "", ""))
    // 1, 2, 3
    println(joinToString(list))
    // 1, 2, 3
    println(joinToString(list, "; "))   // the trailing arguments are omitted.
    // 1; 2; 3
}
```
- This case uses the regular call syntax, meaning that it does not use named arguments.
- Without named arguments, arguments must be passed in the same order as the parameters are declared.
  - Only trailing arguments can be omitted.
  - You cannot skip an argument for a middle parameter while still providing arguments before and later it.

## Taking advantage of default parameter values (with named arguments)
Well, using named arguments may give you more freedom.
```kotlin
fun main() {
    val list = listOf(1, 2, 3)
    
    println(joinToString(list, postfix = ";", prefix = "# "))
    // # 1, 2, 3;
}
```
- In this case, you can specify only the arguments you need!
  - You can choose the order in which they are written.
  - It is also possible to omit an argument for a parameter in the middle of the parameter list.

# Misc.
Default values for function parameters are defined **at the function declaration site**.  
  - Parameter default values belong to **the function declaration**, NOT to individual call sites.

In each call site, when an argument is omitted, Kotlin uses the default value declared for that parameter.  
  - This behavior is supported by compiler-generated machinery rather than by simply inserting the default value directly into every call site.

## Explanation with simple example codes
The contents immediately above might be somewhat abstract, and thus you may have difficulty understanding it.  
In conclusion, note that:
```text
The contents immediately above is essential in a situation where a function declaration and the call site are in different files.  
Technically, if the file containing the function declaration and the one containing the call site can be compiled separately, this content is critical!
```

I introduce some example codes.  

Let me assume a situation!  
There are two `.kt` files: `Library.kt` and `App.kt`.  
```kotlin
// Library.kt
fun greet(name: String = "Kotlin") {
    println("Hello, $name")
}
```
```kotlin
// App.kt
fun main() {
    greet()     // caller which haven't specified a value for the parameter `name`.
}
```

As you can see, `Library.kt` has a declaration of a function `greet`, while `App.kt` has a call site for `greet`.  
These two files may be compiled separately.  

An important question is:
> When compiling `App.kt` and `Library.kt`, does Kotlin compiler internally convert `greet()` in `App.kt` into `greet("Kotlin")`?

The answer is **NO**!

---

### Imagined scenario
If Kotlin compiler directly transform the call site `greet()` into `greet("Kotlin")`, what can happen?  
In this case, even if you change the default value `name: String = "Kotlin"` to `name: String = "World` in `Library.kt` and kotlin compiler only recompiles `Library.kt`, the existing compilation output of `App.kt` does not reflect the changed default parameter value.  
The compiled output of `App.kt` still have `greet("Kotlin")`, so that it still print "Hello, Kotlin" into the console despite the recompilation of `Library.kt`.  

### Actual behavior
In reality, the call site `greet()` in `App.kt` is conceptually described:
> Call the function `greet`, yet indicate that the argument `name` has been omitted.

Meanwhile, the compilation result of `Library.kt` which contains the declaration of `greet` has a processing logic as for:
```text
What the default value has to be used when the argument `name` is omitted in a call site.
```

Therefore, if you modify `Library.kt` and change the default parameter value from:
```kotlin
fun greet(name: String = "Kotlin")
```
into
```kotlin
fun greet(name: String = "World")
```
, and if Kotlin compiler only recompile `Library.kt`, the call site `greet()` in `App.kt` use the default value `"Kotlin"`.  
Accordingly, `greet()` in `App.kt` will print "Hello, World" into the console.

#### Flow of the actual behavior
Initial state is:
```kotlin
// Library.kt
fun greet(name: String = "Kotlin") {
    println("Hello, $name")
}
```
```kotlin
// App.kt
fun main() {
    greet()
}
```
In this state, the output is:
```text
Hello, Kotlin
```

Now, just modify `Library.kt`:
```kotlin
// Library.kt
fun greet(name: String = "World") {
    println("Hello, $name")
}
```
And only recompile `Library.kt`.  

In this situation, the compilation output of `App.kt` remains:
```kotlin
// App.kt
fun main() {
    greet()
}
```

Even so, the output is:
```text
Hello, World
```

Therefore, it can be stated:
> Default parameter values are encoded in the function declaration itself, not at the call site.