# What chapter in 'Kotlin in Action'?
- Chapter 3.3 Adding methods to other people’s classes: Extension functions and properties (2/e version)  

# What did I look?
- An *extension function*: a function which can be invoked as a member of a class, being defined outside of the class.
  - The *receiver type*: The class or interface to which you add a function (extension function).
  - The *receiver object*: The instance on which you are invoking the extension function. It is usually `this` in the implementation of the *extension function*.
- In the body of *extension functions*, you do not have access to `private` and `protected` methods & properties.

# Codes
## An example of an *extension function*

```kotlin
// An extension function of which name is `myLastChar`
// The receiver type: String
// The receiver type: this
fun String.myLastChar(): Char = this.get(this.length - 1)

fun main() {
    println("Kotlin".myLastChar())
    // n
}
```
- You can freely define your own method as if you add it to the `String` class as a member.
  - Even though you do not have the source code of `String`, you can do it!
  - Even though the code of `String` does not account for a portion of your code, you can do it!
  - Even if the original source code of `String` is defined in Java, Kotlin, or other JVM language (e.g., Groovy), you can do it!
  - Even though `String` is a `final class` in Java, you can do it!
    ```text
    It is because the extension function in Kotlin is not an inheritance (i.e., subclassing) but just adding a static method from outside of the class.
    ``
- **Nevertheless**, implementing *extension functions* is NOT equal to adding real members to a class.
  - *Extension functions* just help you to call the `public` APIs (i.e., methods and properties) conveniently.

## Omitting `this` in the body of the *extension function*
You can just look the *extension function* like a regular method of the *receiver type* in terms of `this`.  
Thus, you can omit `this` from the *extension function*.
```kotlin
fun String.myLastChar_(): Char = get(length - 1)

fun main() {
    println("Kotlin".myLastChar_())
    // n
}
```

## The range of properties and methods to which *extension functions* can access
Note that:
> You can directly use the methods and properties of the `receiver type`, in the extension function.

**However**, please note the difference between *extension functions* and methods defined in the class:
    ```text
    Private and protected members & properties of the class cannot be accessed by extension functions!
    == You CANNOT break encapsulation!
    ```

Let's see a simple example:
```kotlin
// Source code of a class `User`
class User(
    val name: String,
    private val password: String
) {
    fun sayHello() {
        println("Hello, $name")
    }
}

// extension function
// receiver type: User
fun User.printInfo() {
    println(name)
    sayHello()
}

// unpermitted extension function 
fun User.printPassword() {
    println(password) // NOT allowed!
}
```
- In the *extension function*, you cannot make use of the property: `private val password`.
  - The same applies to `protected`.
  - The same applies to members (i.e., methods).

### Note
The same goes for methods.
| The kind of Property                | Possible to access from Extension function? |
| -------------------------- | --------------------------: |
| `public val name`          |                          Possible |
| `public var age`           |                    Possible (read/write) |
| `private val password`     |                         Impossible |
| `protected val secretName` |         Impossible in general top-level extension functions |
| `internal val id`          |              Possible in the same module |

# Misc.
## The term *method*
It contains:
- members of the class
- extension functions

In the body of extension function, any *method* on the receiver can be invoked.  
- That is, you can invoke both extensions and original members in that!

## How about on the call site?
Whether it is members or extension functions, you would call a method on an instance at a call site.  
From the view point of the call site, extensions and members are indistinguishable.  

That means:
> In the call site, it is not essential whether a particular *method* falls under extension functions and members.

