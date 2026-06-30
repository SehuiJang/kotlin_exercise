# What chapter in 'Kotlin in Action'?
- Chapter 3.3.1 Imports and extension functions (2/e version)
- Chapter 3.3.2 Calling extension functions from Java (2/e version)

# What did I look?
- You can import extension functions from other package.
  - By using `as`, you can change the class name or function name which you are importing. (As in Python)
- The method to call extension functions from Java.


# Codes
## Execution script
In your terminal, adjust the current path to this directory, utilizing `cd`:
```bash
cd C:/path/to/your/chapter-03/code-06
```
And then, compile and execute the code:
```bash
C:/path/to/your/kotlinc ./example/string.kt ./example/example.kt -include-runtime -d example.jar && java -jar example.jar
```

## Define an extension function
```kotlin
/* string.kt */
package strings

fun String.lastChar(): Char = get(length - 1)
```

## Import an extension function
```kotlin
/* example.kt */
import strings.lastChar     // {package name}.{name of ext func}
// or
// import strings.*
import strings.lastChar as last     // Changing the class name or function name you are importing

fun main() {
    val c = "Kotlin".lastChar()
    println(c)
    // n

    val c2 = "Hello".last()
    println(c2)
    // o
}
```
- As you can see, just specify **the package name and the name of the extension function** when you import the extension function.
  - You don't need to write the receiver type.
- You can also utilize `as` if you want to change the class name or function name imported.
  - It is useful when the names of extension functions conflict.

- Note that the imported extension function is required to be called in its **short name**.
  - If `lastChar` was a regular class or regular top-level function, you can call that with its fully qualified name `strings.lastChar`.
  - On the other hand, if you import an **extension function** by using `import strings.lastChar`, you can use only its short name `lastChar`.
  - Thus, `as` is very useful for extension functions to avoid the name conflict!

## How to call extension functions from Java?
Importantly, an extension function is actually a static method of which the first argument is the receiver object.  
Invoking the extension function do not entail creating adapter objects and runtime overhead.  

Thus, in Java, you simply need to call the static method and pass the receiver object as the first argument.

If you defined the extension function `lastChar` in `StringUtil.kt`, you can call it from Java in the following way:
```java
/* Java */
char c = StringUtilKt.lastChar("Java");
```
- Note that the Java class which contains the extension method is `StringUtilKt`.
- `StringUtil.kt` has a declaration of `String.lastChar()`
  - That means:
    ```text
    "When you call it in Java,"
    the type of the first argument is `String`,
    and it is equal to the receiver type
    ```
