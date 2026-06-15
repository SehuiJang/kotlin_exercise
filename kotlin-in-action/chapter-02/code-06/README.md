# What chapter in 'Kotlin in Action'?
- Chapter 2.2.3 Directories and packages (2/e version)  

---

# Execution manual
## 0. Prepare terminal
Such as cmd, git bash, ...

## 1. Go to the directory before compiling, using `cd`
```bash
cd C:/path/to/your/chapter-02/code-06
```

## 2. Compile & Execute your code
Note that **you must compile a folder** rather than a single `.kt` file in this example folder.  
Thus, take the following command:
```bash
C:/path/to/your/kotlinc ./geometry -include-runtime -d example.jar && java -jar example.jar
```
Then, you can see the message in console:
```text
false
true
```

# What did I look?
- `package` statement
- `import` statement

## `package` & `import` statement
### `package`
`package` statement can be at the beggining of `.kt` file.  
```kotlin
// shapes.kt

package geometry.shapes    // Package declaration

class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() = height == width
}

fun createUnitSquare(): Rectangle {
    return Rectangle(1, 1)
}
```

`package geometry.shapes` in the above example means:
  - **This `.kt` file is belonging to the package `geometry.shapes`**
  - **All declarations (classes, top-level functions, and properties) defined in this `.kt` file is placed in the package `geometry.shapes`**

Declarations (classes, top-level functions, and properties) in files belonging to the same package can be used directly without `import` statement.
  - `.kt` files being in the same package == files having the same `package` statement
  - For instance:
    - `code1.kt` has `package geometry.shapes` in the beginning.
    - `code2.kt` has `package geometry.shapes` in the beginning as well.
    - Then, all declarations in `code1.kt` can be directly used in `code2.kt`, and vice versa.

### `import`
Declarations defined in other files have to be *imported* if they are belonging in a different package.  

```kotlin
// example.kt

package geometry.example    // Package declaration

import geometry.shapes.Rectangle    // Imports a class by name
import geometry.shapes.createUnitSquare
// You can also write "wildcard import (star import)":
// import geometry.shapes.*     // star import (everything in package geometry.shapes)

fun main() {
    println(Rectangle(3, 4).isSquare)  // Output: false
    println(createUnitSquare().isSquare)    // Output: true
}
```

Well, `shapes.kt` is in the package `geometry.shapes` while `example.kt` is belonging to `geometry.example`.  
Therefore, if you want to utilize the class `Rectangle` and the top-level function `createUnitSquare` in `example.kt`, you MUST *import* them:
  - Method 1: Importing by name
    ```kotlin
    import geometry.shapes.Rectangle
    import geometry.shapes.createUnitSquare
    ```
  - Method 2: Wildcard import
    ```kotlin
    import geometry.shapes.*
    ```

## The directory hierarchy: Java vs Kotlin
### Java
In Java, a structure of files and directories must match the package structure.  

For example, you have to put your files in the following structure:
```text
src/
└─ geometry/
   └─ example/    <- geometry.example package
      └─ Main.java
   └─ shapes/    <- geometry.shapes package
      ├─ Rectangle.java     <- Rectangle class
      ├─ Circle.java
      └─ Triangle.java
```

Well, if you have a package `shapes` and the package has the classes (`Rectangle`, `Circle` and `Triangle`), each class have to be written in each `.java` file.  
  - Technically, each `.java` file is able to have multiple classes
  - However, there should be only one `public` top-level class.
And then, the name of each `.java` file must be the same as the name of `public` top-level class (i.e., `Rectangle`, `Circle` and `Triangle`).  


### Kotlin
In Kotlin, the regulation is relatively free.  
  - A single file can have multiple classes.
  - You can choose any name for the file.
  - Kotlin doesn't care about the structure of directories.

There is an example:
```text
src/
└─ geometry/
   └─ example.kt    <- geometry.example package
   └─ shapes.kt    <- geometry.shapes package, including classes (i.e., `Rectangle`, `Circle` and `Triangle`)
```

Even so, it is recommended to arrange `.kt` files in the package structure, following the style of Java.  
Following that Java-style structure is helpful in projects in which Kotlin is mixed with Java.  
Well, of course, it is also good to put multiple small, simple classes into a single `.kt` file.


## Additional information
### When it comes to the path to `.kt` files
In Kotlin, it is not mandatory for `.kt` files having identical `package` declaration to be in an identical directory.  
Thus, it is permitted:
```text
src/main/kotlin/foo/Example.kt
src/main/kotlin/bar/Rectangle.kt
```

But, in practice, it is recommended that a structure of files matches the package structure.
```text
src/main/kotlin/geometry/shapes/Rectangle.kt
src/main/kotlin/geometry/shapes/Example.kt
```

### `.kt` files in different packages
`.kt` files which have different `package` declaration are belonging to different packages.  
**Importantly, even if the package names are similar, the `.kt` files are belonging in completely different packages, not even a parent-child relationship.**
  - For instance, the following packages are completely different packages:
    ```text
    geometry
    geometry.shapes
    geometry.example
    ```

### How does `kotlinc` determine and classify packages of the `.kt` files?
As mentioned above, directly using declarations is possible between `.kt` files in an identical package.  
Then, how does kotlinc judge whether the `.kt` files are in the same package? Does the kotlinc explore all `.kt` files in your computer?  
  - No, it doesn't.

When you compile code files, you can specify files to compile.  
Kotlinc just search the `package` declarations of all `.kt` files which are selected for compilation.  
And then, it is determined:
  - What packages are there.
  - What `.kt` files are belonging in the same package.

For instance, both `src\a\Rectangle.kt` and `src\b\Example.kt` have `package geometry.shapes`.  
Then, you do compile:
```text
kotlinc src\a\Rectangle.kt src\b\Example.kt -include-runtime -d app.jar
```
  - Both `.kt` are compilation target files.
  - So, kotlinc can identify that the `.kt` files are in the identical package `geometry.shapes`, even though they are in a different directory.
  - If you miss one of them in compilation, kotlin fails to identify.

### The way to specify multiple files for compilation
1. Simply listing is possible:
    ```
    kotlinc src\main\my_files\Rectangle.kt src\main\my_files\Example.kt -include-runtime -d app.jar
    ```

2. Passing the directory
    ```
    kotlinc src\main\my_files\ -include-runtime -d app.jar
    ```

3. Using wildcard `*` (`.kt` in the same folder)
    ```
    kotlinc *.kt -include-runtime -d app.jar
    ```

4. Using wildcard `**` (not recommended)
  - Whether this command is possible depends on your shell environment.
    ```
    kotlinc C:\my_project\src\main\my_files\**\*.kt -include-runtime -d app.jar
    ```

5. In practice (such as app development), Gradle is generally used.

    If you are simply studying grammar, you can use the other methods above.  
    However, if you are developing a proper app, Gradle in IDE is usually used.
