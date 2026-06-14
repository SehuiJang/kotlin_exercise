# What chapter in 'Kotlin in Action'?
- Chapter 2.3.1 Declaring enum classes and enum constants (2/e version)  

# What did I look?
- enum class & enum constants
- declaring properties of enum class
- The only place requiring `;` in the Kotlin syntax

## Enum class without constructor parameters
It is an example of an enum class without its arguments:
```kotlin
package ch02.colors

enum class Color_simple {
    // Enum constants (or fixed instances): RED - VIOLET
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}

enum class Color_override {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET;

    override fun toString(): String {
        return "Color: $name"
    }
}
```

### A few printing examples
```kotlin
fun main() {
    println(Color_simple.RED::class.simpleName) // Output: Color_simple
    println(Color_simple.RED)   // Output: RED

    val c = Color_simple.RED    // it has no user-defined property.
    println(c.name) // Output: RED
    println(c.ordinal) // Output: 0
}
```
Each enum constant (e.g., `Color_simple.RED`) is the instance of the class `Color_simple`.  
This enum class has no any constructor parameters.  
Thus, each enum constant (i.e., `c`) doesn't have any user-defined property.  
Even so, it has a few default properties provided from Kotlin/Java enum such as `.name` and `.ordinal`.

### Direct printing enum constant
```kotlin
fun main() {
    val c = Color_simple.RED
    println(c)  // Output: RED. This internally invokes `c.toString()`, and `toString()` of enum returns the property `.name` of the enum constant by default.
    // Well, if you override `toString()` in enum class, a different message can be printed.
    println(Color_override.RED)     // Output: Color: RED
}
```
In this example, if you just print `c` (i.e., `println(c)`), you can see a printed message `RED` in console.  
Invoking `println(c)` internally invokes `c.toString()`.  
Importantly, `.toString()` of enum returns the default property `.name` of the enum constant by default.
  - In the case of `c`, `c.name` is `RED`.
  - Thus, `c.toString()` is `RED` by default.

That's why `println(c)` shows you `RED`.  

However, you can override `.toString()` of enum class.  
Look at the enum class `Color_override`!  
  - In this case, `Color_override.RED.toString()` returns `Color: RED`.
  - Thus, `println(Color_override.RED)` prints `Color: RED` into your console.

## Enum class with parameters
Here is an example of an enum class with three params:
```kotlin
package ch02.colors

enum class Color(
    val r: Int,
    val g: Int,
    val b: Int
) {
    // enum constants (or fixed instances): RED - VIOLET
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIOLET(238, 130, 238);
    val rgb = (r * 256 + g) * 256 + b   // This property has an initializer
    fun printColor() = println("$this is $rgb")
}
```

You can see a semicolon `;`.  
Note that:
```text
From below `;`, you can define any methods and other properties.
```

Especially, there is a single property `rgb`:
```kotlin
val rgb = (r * 256 + g) * 256 + b
```
- Note that it has an initializer!
- Therefore, it has its backing field.
    - When an enum constant is created, the value of the property `rgb` will be computed.
    - The enum constant will hold the value in its field, not calculating on the fly.

### A few examples of using an enum constant of `Color`
```kotlin
fun main() {
    println(Color.BLUE.rgb) // Output: 255
    Color.GREEN.printColor()    // Output: GREEN is 65280
}
```

## Caution
Unlike regular classes, it is not allowed to directly call the constructor of enum classes.  
Look at the following example:
```kotlin
fun main() {
    // val c = Color(255, 0, 0)     // NOT ALLOWED!

    val c = Color.RED   // ALLOWED. 
        // c: its type is `Color`
}
```
- `val c = Color(255, 0, 0)` is a direct call to the constructor of `Color`.
  - **It is NOT ALLOWED!**
  - If you try it, you will face an error:
    ```text
    example.kt:34:13: error: cannot access 'constructor(r: Int, g: Int, b: Int): Color': it is private in 'ch02.colors.Color'.
    ```
- `val c = Color.RED` is just invoking a fixed instance of enum class `Color`.
  - it is allowed!
  - `Color.RED`: an enum constant (or a fixed instance) of `Color` enum class