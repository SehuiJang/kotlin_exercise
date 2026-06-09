# What chapter in 'Kotlin in Action'?
Chapter 2.1.3 Variables

# What did I look?
Mutable & immutable variables!

## Mutable variable vs Immutable variable
When you declare a variable, you are bound to use either `val` or `var`.
- `val`: Immutable reference.
  - The variable declared with `var` can be initialized only once (NO REASSIGNING).
  - It corresponds to a final variable in Java.
  - It's recommended **by default**.
- `var`: Mutable reference.
  - Reassigning its value is allowed.
  - It corresponds to a regular Java variable.
  - It's recommended to be used **only if necessary**.

## Declaring Immutable variable, using `val`
### Basic declaration
You can omit a data type of the variable.  
*Type inference* is occured!  

```kotlin
fun main() {
    val question = "The Ultimate Question of Life, the Universe, and Everything"

    val answer1 = 42    // type inference is occured
    val answer2: Int = 45   // explicit type specification

    val yearsToCompute = 7.5e6  // a floating-point constant. Its type is inferred as Double.
}
```

### Later initialization: When a variable has no initializer in declaration
When a variable has no initializer, **you have to specify its type**!  
Don't forget to assign a value to the variable before you use it.

```kotlin
fun main() {
    val answer3: Int    // specifying its type.
    answer3 = 42
}
```

### Initialization of `val` with some condition
Using later initialization, you can assign a value to the declared variable.  
Importantly, you have to make sure that **only one of the initialization statements will be executed**.

```kotlin
fun main() {
    val message: String // Initialization depending on some condition
    val success = true
    if (success)
        { message = "Success"
        // ... perform the operation
    }
    else {
        message = "Failed"
    }
    println(message)
}
```

### Immutable `val` reference + mutable object
As you know, a `val` reference is itself immutable.  
**Technically, a variable declared in `val` reference must point to only one object which is initially assigned to the variable.**  
However, if the object that `val` reference points to is mutable, you can change the internal state of the object.  

Thus, the following example is valid:  

```kotlin
fun main() {
    val languages = arrayListOf("Java")
    languages.add("Kotlin")

    // languages = arrayListOf("Python") // Error!
}
```

Well, the above code can be expressed like a picture:
```text
languages ───► ["Java"]     // (initialization) val languages = arrayListOf("Java")
languages ───► ["Java", "Kotlin"]   // languages.add("Kotlin")
languages ─X─► ["Python"]   // languages = arrayListOf("Python") -> Error!
```

## Declaring mutable variable, using `var`
In the case of `var` reference, you can change the value.  
However, the type of `var` reference is fixed.

```kotlin
fun main() {
    var ans = 17    // Type inference of a compiler is occured
    // ans = "no answer"   // Error: type mismatch
    ans = 20    // OK
    println(ans)    
}
```

## Caution: Top-level declaration always needs an initializer
Unlike local variables, you must immediately set a value to the variable **in the case of top-level declaration**.

```kotlin
val answer5: Int = 100  // Top-level declaration requires an initializer.
// val answer5: Int    // NOT ALLOWED!

var answer6: Int = -12  // Top-level declaration requires an initializer.
// var answer6: Int    // NOT ALLOWED!

fun main() {

}
```