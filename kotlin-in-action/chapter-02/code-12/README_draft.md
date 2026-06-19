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
            +Int value
        }
    
        class Sum {
            +Expr left
            +Expr right
        }
    
        Num ..|> Expr
        Sum ..|> Expr
        Sum --> Expr : left, right
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
  - Accordingly, you can use `e` as `Sum` object in the block **without the explicit cast**!!
  - On a side note, the smart-cast value `e` in this block will be highlighted when you use IDE such as IntelliJ IDEA and Android Studio!
    ```
    if (e is Sum) {	// Type check
        // In this block, `e` is a smart-cast value.
        return eval(e.right) + eval(e.left)
    }
    ```

### Caution
Briefly, smart cast works only when the compiler can be sure that the value remains the same after `is` check.
  - The compiler must be able to prove that the target of `is` check still refers to the same value afterward.

Technically, remember that:
```text
All properties of the class must be declared as val,
and none of them must have custom accessors.
```

---

#### `var` properties is not good when expecting smart cast
If a class has one or more `var` properties, you cannot expect smart cast.
  - It is because the properties of instances of the class can be changed even after `is` check.
  - For instance, smart cast is not available in the following code:
    ```kotlin
    interface Expr
    class Num(val value: Int) : Expr
    class Sum(val left: Expr, val right: Expr) : Expr
    
    class Holder(
        var expr: Expr
    )
    
    fun evalHolder(holder: Holder): Int {
        if (holder.expr is Num) {
            // In this line, someone can convert `holder.expr` into `Sum` instance.
            return holder.expr.value // smart cast is impossible!
        }
        return 0
    }
    ```

#### Even though all properties are `val`, none of them must have custom accessor
For properties with their custom accessors, reading the properties can produce a diffrent value each time.
- For the following example, even if reading the folowing property once returns a `Num`, reading it again may return a `Sum`.
    ```kotlin
    class Holder {
        val expr: Expr
            get() = if (Math.random() > 0.5) Num(1) else Sum(Num(1), Num(2))
    }
    
    fun evalHolder(holder: Holder): Int {
        if (holder.expr is Num) {
            return holder.expr.value // smart cast is impossible!
        }
        return 0
    }
    ```
- There is a simpler example: a property in an arbitrary class.
  ```
  val x: Expr
      get() = Num(1)
  ```
  - Well, in this simple case, the getter always `Num(1)`, and thus it seems safe to do the smart cast.
  - However, note that the compiler generally cannot prove that the logic inside a custom getter always return the same value.
  - A custom getter (accessor) itself means that:
    - it may be recomputed whenever the property is accessed;
    - the getter may return a different object each time;
    - evaluating the getter may produce side effects.
  - In conclusion, the property must not have any custom accessor if you wanna utilize smart cast!

#### How can we safely leverage smart casts?
The solution is:
```text
Make use of local variable!
```

There is an example:
```kotlin
fun evalHolder(holder: Holder): Int {
    val expr = holder.expr

    if (expr is Num) {
        return expr.value
    }

    return 0
}
```
  - Here, the target of the smart cast is not `holder.expr` but the local variable `expr`!
  - `expr` is `val` and does not vary in the function block.
  - That's why the smart casting for `expr` is possible.
