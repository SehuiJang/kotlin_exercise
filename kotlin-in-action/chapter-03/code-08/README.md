# What chapter in 'Kotlin in Action'?
- Chapter 3.3.4 No overriding for extension functions (2/e version)

# What did I look?
- Extension functions CANNOT be overriden!
  - Owing to their **static nature**, not dynamic nature
  - From the perspective of Java equivalent code, it is actually "overloaded static methods".

# The static nature of extension functions
Recall that:
> Considering Java equivalent code, an extension function is a static method of a class under the hood, and the type of its first argument is the receiver type.

In this point, please focus on the word **"static"**.  
This feature is connected to the fact:
```text
It is impossible to override extension functions.
```

# Codes
## An example of overriding a member function (regular method)
```kotlin
// open class: it allows the creation of subclasses (i.e., permits inheritance)
// open fun: it allows that the member function is overriden in subclasses
open class View {
    open fun click() = println("View clicked")
}

class Button: View() {
    override fun click() = println("Button clicked")
}

fun main() {
    val view: View = Button()
    view.click()
    // Button clicked
}
```
- `open class` &rarr; permits subclass
- `open fun` &rarr; permits being overriden
- Note that `view` is an instance of `View` but is actually holding a value of type `Button`.
  - It is possible because `Button` is a subtype (or subclass) of `View`
- `view.click()`:
  - `view` itself has its type `View`.
  - Even so, the actual value that `view` belongs to the subclass `Button`.
  - Thus, the method `click` overriden in `Button` is selected!
- As you can see, what `click` is employed is determined depending on the type (or class) of the actual value.
  - It is a dynamic dispatch (dynamic behavior).
  - I mean what `click` would be adopted is not decided by the compiler alone but is up to the runtime type of the object.
  - In other words, method selection is resolved at runtime rather than being fixed entirely at compile time.

## Overriding an extension function is impossible
Well, overriding does not work for extension functions.  
In first, technically, extension functions are NOT a part of the class.  

```kotlin
open class View {
    open fun click() = println("View clicked")
}

class Button: View() {
    override fun click() = println("Button clicked")
}

fun View.showOff() = println("I'm a view!")
fun Button.showOff() = println("I'm a button!")

fun main() {
    val view2: View = Button()
    view2.showOff()
    // I'm a view!
}
```
- On the surface, the two extension functions have an identical name (`showOff`) and parameter list.
- However, Which of two `showOff` is called is determined by the static type of the variable calling it.
- `view2.showOff()`:
  - `view2` is a variable of the type `View`.
  - Even though the runtime type of the actual value (`Button()`) is `Button`, `showOff` which is declared on `View` class is selected.
  - It is determined at **a compile time.** and does not resolves the runtime type of the actual value.

### Thinking from the point of view of Java equivalent code
Let's see a Java equivalent code of the above example!  
Given an assumption that the above Kotlin code is written in `Extensions.kt`, its Java equivalent code may be:
```java
/* Java */
public class View {
    public void click() {
        System.out.println("View clicked");
    }
}

public final class Button extends View {
    @Override
    public void click() {
        System.out.println("Button clicked");
    }
}
```
and  
```java
/* Java */
// Extension functions
public final class ExtensionsKt {
    public static final void showOff(View receiver) {
        System.out.println("I'm a view!");
    }

    public static final void showOff(Button receiver) {
        System.out.println("I'm a button!");
    }
}
```
You can see that the two `showOff` have the same name but **the different parameter type!**
- From the point of view of Java equivalent code, these `showOff` methods are in the relation of **static method overloading.**
- To put it bluntly, **static methods do not participate in override-based dispatch** based on the runtime type of an instance.

So, if you call a `showOff` in Java, the following code can be considered:
```java
View view = new Button();

ExtensionsKt.showOff(view);
```
- Note that the compile-time type of `view` is `View`.
- In addition, the compiler does not treat the relation between two `showOff` as overriding.
  - This means that the method selection is not deferred to runtime. The call is not resolved through dynamic dispatch at runtime.
- Therefore, the compiler just binds `public static final void showOff(View receiver)`.
  - The compiler just checks the type of `view`!

### Thinking from the viewpoint of C++ is also not bad
Consider the simple case of non-member overload functions:
```cpp
void showOff(View& v);
void showOff(Button& b);
```
And you execute the following code:
```cpp
View& view = button;
showOff(view);
```

In this case, too, the two `showOff` is not related by overriding but by overloading.  
Accordingly, when running the line `showOff(view);`, the first `showOff` is used.
- The type of the variable `view` is `View&` in compile time.

The almost same applies to the extension functions in Kotlin!

# Note
Let's assume that a class has a member function, and you declared an extension function to the class.  
If the signature of the member function and the extension function are identical, **the member function would be selected first.**  
It is important when extending the API of classes.