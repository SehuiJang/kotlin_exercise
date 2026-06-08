# What chapter in 'Kotlin in Action'?
Chapter 2.1.1 Hello, World!

# What did I look?
This is just a simple program printing a message "Hello, world".  

```kotlin
// Kotlin

// It's a top-level function which is not belonging to a class.
fun main(args: Array<String>)
    { println("Hello, world!")
}
```

- The keyword `fun`: it's used to declare a function.
- The parameter name is `args` while the parameter type written after the parameter name is `Array<String>`.
- The function can be declared at the top level of a file.
  - In Java, functions including `main` function are usually in a class. That is, functions cannot exist independently outside of a class.
    ```java
    // Java
    public class Main {
        public static void main(String[] args) {
            System.out.println("Hello, world!");
        }
    }
    ```
  - In Kotlin, functions can exist outside of a class, by itself. The type of these functions is called **top-level function**.
- In Kotlin, arrays are just classes.
  - You can use a syntax of classes: `Array<String>`, `Map<String, Int>`, etc.
  - In Java, you have to use a special syntax for declaring array types.
    ```java
    // Java
    // When you want to represent an array of a data type, you can use [] after the type name.

    // Array of String
    String[] args;
    String args[];

    int[] numbers;  // int array
    String[] names; // String array
    User[] users;   // array of instances of a class 'User'
    ```
  - There are some examples:
    | Meaning        | Java                     | Kotlin                     |
    | --------- | ------------------------ | -------------------------- |
    | String array    | `String[]`               | `Array<String>`            |
    | Int array     | `int[]`                  | `IntArray` or `Array<Int>` |
    | Array of `User` instances | `User[]`                 | `Array<User>`              |
    | Creating an array     | `new String[]{"A", "B"}` | `arrayOf("A", "B")`        |
    | Indexing  | `names[0]`               | `names[0]`                 |
- You can use `println`, not `System.out.println`.
  - Kotlin standard library provides many wrappers around standard Java library functions.
  - The word "wrappers" means that original functions are in Java library, but they are wrapped by more concise syntax. So, you can use them with more concise syntax in Kotlin!
  - There are some examples besides `println`:
    | Kotlin | Java |
    |---|---|
    | `println("Hello")` | `System.out.println("Hello");` |
    | `arrayOf("A", "B", "C")` | `new String[] {"A", "B", "C"};` |
    | `listOf("A", "B", "C")` | `Arrays.asList("A", "B", "C");` |
    | `"Kotlin".substringBefore(".")` | Directly using the combination of `indexOf` and `substring` |
    | `numbers.last()` | `numbers.get(numbers.size() - 1);` |
- The semicolon `;` can be omitted from the end of a line, like many other modern languages such as Python.
