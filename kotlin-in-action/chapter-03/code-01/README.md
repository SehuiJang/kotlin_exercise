# What chapter in 'Kotlin in Action'?
- Chapter 3.1 Creating collections in Kotlin (2/e version)  

# What did I look?
- Some kinds of basic collections in Kotlin
  - set
  - map
  - list
- Functions to create them
  - `setOf`
  - `mapOf`
  - `listOf`
- Kotlin uses the standard Java collection classes.
  - Collection interfaces in Kotlin are basically read-only.
  - Well, there are mutable counterparts, which are explored in chapter 8 in `Kotlin in Action 2/e`.

# Codes
## Creating collections in Kotlin and checking its Java class
The below code creates a few collections **from the standard Java classes**.
```kotlin
fun main() {
    val set = setOf(1, 7, 53)
    val list = listOf(1, 7, 53)
    val map = mapOf(1 to "one", 7 to "seven", 53 to "fifty-three")

    println(set.javaClass)
    // class java.util.LinkedHashSet
    
    println(list.javaClass)
    // class java.util.Arrays$ArrayList
    
    println(map.javaClass)
    // class java.util.LinkedHashMap
}
```
- The **standard Java collection classes** are utilized in Kotlin!
  - `.javaClass`: You can check the Java class of the collection.
  - That means that Kotlin is **easy to interact with Java code**, and vice versa. Converting collections is not needed!

## More things you can do in Kotlin
As mentioned above, the basic collections in Kotlin (i.e., `set`, `map`, and `list`) are the classes identical to Java collection classes.  
- Technically, Kotlin uses the standard **Java Collection Framework (JCF)** implementations **at runtime on the JVM**.
- On a side note, as for the **compile time**, Kotlin type system distincts between read-only and mutable collections.
  - The case of `List / MutableList` is an example of this.

Even so, you can do more with the collections in Kotlin.  
I mean there are new methods on the classes from Java, which can be utilized in Kotlin.  
For instance, you can do:
  - getting the last element from a list.
  - making a suffled version of a list.
  - calculating the sum of the elements in a collection.

```kotlin
fun main() {
    val strings = listOf("first", "second", "fourteenth")
    
    println(strings.last())
    // fourteenth
    
    println(strings.shuffled())
    // [fourteenth, second, first]
    
    val numbers = setOf(1, 14, 2)
    println(numbers.sum())
    // 17
}
```

# When it comes to `.javaClass`
## Is `.javaClass` only available for classes from Java?
**`.javaClass` is NOT limited to class that come from Java.**  
On **Kotlin/JVM**, it can be used with any object running on the JVM!  
This means that:
```text
You can also use `.javaClass` on instances of classes defined directly in Kotlin!
```

## What can you see through `.javaClass`?
Even classes written in Kotlin are compiled to JVM `.class` files when they target Kotlin/JVM.  
Kotlin/JVM usually applies to:
- Android app development
- Backend/server development

As a result, they become JVM classes that the JVM can understand and load at runtime.  
Therefore, when you use `.javaClass` on an object, you can inspect the object's runtime class on the JVM (i.e., **JVM runtime class**).  

However, note that
```text
this applies specifically to Kotlin/JVM, not to other Kotlin targets.
```

## How about other Kotlin targets, not Kotlin/JVM?
There are other Kotlin targets besides Kotlin/JVM.
They produce different compilation outputs rather than JVM bytecode.  
- **Kotlin/Native**: the compilation output is machine code suitable for a certain OS and CPU architecture (iOS, Linux, Windows, macOS, embedded, etc.).
- **Kotlin/JS**: the compilation output is JS code, which is executed in Node.js or Web browser.

In other Kotlin targets, the compilation outputs differ from JVM bytecode.  
The outputs are executed in a platform other than JVM.  

What is important is that **`.javaClass` is JVM-only API** which returns JVM runtime class.  
Therefore, `.javaClass` cannot used in other Kotlin targets.  

Instead, you can consider other "Kotlin reflections":
- `::class`
- `::class.simpleName`
- `::class.qualifiedName`

## Conclusion based on Kotlin/JVM
To summarize, Kotlin-defined classes are still compiled into JVM classes on Kotlin/JVM, so their instances also have a JVM runtime class.  
The JVM runtime class can be accessed through `.javaClass`.


