# What chapter in 'Kotlin in Action'?
- Chapter 3.3.5 Extension properties (2/e version)

# What did I look?
- You can declare not only extension functions but also **_extension properties._**
- Extension functions do NOT have backing field!

# An important point about *extension properties*
Note that:
```text
Extension properties do NOT have any state, any backing field!
```
- Even though they are *properties*, **they do not have a backing field** where such state could be kept.
  - You have to define custom accessors!
- On the JVM, Kotlin cannot add extra fields to instances of an existing Java class.
- You can NOT use an initializer. The space to hold a value specified by the initializer does not exist.
- No default getter and setter.


# Codes
## Extension property (read-only & mutable)
```kotlin
/* Kotlin */
// read-only extension property
val String.lastChar: Char
    get() = this.get(length - 1)

// mutable extension property
// * the contents of `StringBuilder` can be modified
var StringBuilder.lastChar: Char
    get() = this.get(length - 1)
    set(value) {
        this.setCharAt(length - 1, value)
    }

fun main() {
    val sb = StringBuilder("Kotlin?")
    println(sb.lastChar)
    // ?
    sb.lastChar = '!'
    println(sb)
    // Kotlin!
}
```
- You can see that the syntax of extension properties is similar to that of regular properties.
  - A receiver type is just added.
- You must define custom accessors of extension properties.

## Do you want to access extension properties from Java?
Simply speaking, you have to directly call its getter and setter.  

If the above example Kotlin code is in `stringUtil.kt`, you can access extension properties in the following manner:
```java
/* Java */
// the type of the first argument is `String`
StringUtilKt.getLastChar("Java");
// the type of the first argument is `StringBuilder`
StringUtilKt.setLastChar(sb, '!');
```

If confused, just think of Java equivalent code!  
**Assume the name of the file facade is `StringUtilKt`.**
```java
/* Java */
// file facade
public final class StringUtilKt {
    // private constructor
    // meaning: this is a class not to make instances but to collect static utility methods.
    private StringUtilKt() {
    }

    // ** The two `getLastChar`s are the overloaded static methods! **

    // val String.lastChar: Char
    //     get() = this.get(length - 1)
    public static final char getLastChar(String receiver) {
        return receiver.charAt(receiver.length() - 1);
    }

    // var StringBuilder.lastChar: Char
    //     get() = this.get(length - 1)
    public static final char getLastChar(StringBuilder receiver) {
        return receiver.charAt(receiver.length() - 1);
    }

    // var StringBuilder.lastChar: Char
    //     set(value) { this.setCharAt(length - 1, value) }
    public static final void setLastChar(StringBuilder receiver, char value) {
        receiver.setCharAt(receiver.length() - 1, value);
    }

    public static final void main() {
        StringBuilder sb = new StringBuilder("Kotlin?");

        System.out.println(StringUtilKt.getLastChar(sb));
        // ?

        StringUtilKt.setLastChar(sb, '!');
        System.out.println(sb);
        // Kotlin!
    }

    // Java entry point style
    public static void main(String[] args) {
        main();
    }
}
```
