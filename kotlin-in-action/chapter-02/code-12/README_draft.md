# What chapter in 'Kotlin in Action'?
- Chapter 2.3.6 Smart casts: Combining type checks and casts (2/e version)  

# What did I look?
- Declaring an interface (a *marker interface*) by using `interface`.
- Type checking & smart cast!

# Example code
In this code, we want to encode (i.e., express) an expression (i.e., mathematical expression).
- `(1 + 2) + 4`
- You can utilize an marker interface `interface` and smart casts.

```
(required to paste a source code)
```

## Defining a *marker interface*, using `interface`
Let's focus on the following code segment:
```kotlin
interface Expr  // defining a marker interface
class Num(val value: Int) : Expr  // Declaring a class `Num`, and "`Num` can be used as `Expr`"
class Sum(val left: Expr, val right: Expr) : Expr  // Declaring a class `Sum`, and "`Sum` can be used as `Expr`"
```
- `interface Expr` means you define a marker interface `Expr`.
  - Note that `Expr` does not declare its method.
  - In this example, `Expr` can be `Sum` or `Num`.
    ```mermaid
    classDiagram
        direction BT
    
        class Expr {
            <<interface>>
        }
    
        class Num {
            +value: Int
        }
    
        class Sum {
            +left: Expr
            +right: Expr
        }
    
        Num ..|> Expr : implements
        Sum ..|> Expr : implements
        Sum --> Expr : left
        Sum --> Expr : right
    ```
- `class Num(val value: Int) : Expr` means:
  - Basically, you define a class `Num` with its immutable property `value`.
  - **Importantly, `: Expr` at the end means that `Num` implements the `Expr` interface.**
    - In other words, an instance of `Num` can also be used as a value of type `Expr`.
    - Therefore, a `Num` object is also an `Expr`, so it can be used wherever a value of type `Expr` is expected.
- `class Sum(val left: Expr, val right: Expr) : Expr` is similar, yet it has three `: Expr`s.
  - The `: Expr` at the end of the class declaration means the same thing as it does for `Num`.
    - **`Sum` implements the `Expr` interface!**
  - The two `: Expr` type annotations in the constructor declare that both `left` and `right` can hold any `Expr` value.
    - In this example, a `Sum` object can contain either `Num` or `Sum` objects as its operands, **since `Num` is an `Expr` and `Sum` is also an `Expr`.**
    - That is, these are possible: `Sum(Num(1), Num(2))`, `Sum(Sum(Num(1), Num(2)), Num(4))`, etc.
    ```text
            Sum
           /   \
       Num(1)   Num(2)
    ------
            Sum
           /   \
         Sum    Num(4)
        /   \
     Num(1) Num(2)
    ```

### Utilizing the marker interface
As a result, you can make use of the marker interface `Expr`.
```kotlin
fun eval(e: Expr): Int {
    ...
}
```
- The function `eval` has a parameter `e`.
  - An instance of `Num` can be passed as `e`.
  - Since `Sum` also implements `Expr`, a `Sum` instance can also be passed to `eval` as `e`.

### Note: `interface` does not automatically apply to every class declared after it!
Look at the example below:
```kotlin
interface Expr
class Num(val value: Int) : Expr  // Num is Expr!
class Sum(val left: Expr, val right: Expr) : Expr  // Sum is Expr!
class Person(val name: String)  // It is not related to Expr!!!
```
- A class becomes related to an interface only when the interface is explicitly specified at the end of the class declaration, as in `: Expr`.
  - `class Num(val value: Int) : Expr` &rarr; `Num` is `Expr`.
  - `class Sum(val left: Expr, val right: Expr) : Expr` &rarr; `Sum` is also `Expr`.
  - `class Person(val name: String)` &rarr; `Person` has nothing to do with `Expr`!!
- Thus, `Person` cannot be used as `Expr` type.

## Smart casts
Smart cast means:
```text
If you check that a variable has a certain type,
Kotlin can automatically treat it as that type afterward!
Therefore, you do not need an explicit cast!!
```

Well, the explicit cast (manual cast) is also possible but not required:
- The type of `e` is checked as `Num` in the 1st `if` branch.
  - In the block, explicit cast is not impossible. You can do it.
  - Even so, it is not necessary in Kotlin, owing to smart cast.
    ```
    if (e is Num) {	// Type check
        val n = e as Num
        return n.value
    }
    ```
- The type of `e` is checked as `Sum` in the 2nd `if` branch.
  - Then, the compiler takes the cast for you.
  - Accordingly, you can use `e` as `Num` object in the block **without the explicit cast**!!
  - On a side note, the smart-cast value `e` in this block will be highlighted when you use IDE such as IntelliJ IDEA and Android Studio!
    ```
    if (e is Sum) {	// Type check
        // In this block, `e` is a smart-cast value.
        return eval(e.right) + eval(e.left)
    }
    ```
