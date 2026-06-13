# What chapter in 'Kotlin in Action'?
- Chapter 2.2 Classes and properties  
- Chapter 2.2.1 Properties  
- Chapter 2.2.2 Custom accessors  

# What did I look?
1. The basic syntax for declaring class
2. Property of a class
3. Accessors of a class

## Syntax of class: Java vs Kotlin
Here is a simple example: `Person` class that contains only one property.  
In Java, the class is expressed as:
```Java
// Java
public class Person {
    private final String name;

    public Person(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
```

Well, the above Java code shows so much boilerplate.  
Contrarily, the class can be written in Kotlin as follows:
```kotlin
class Person(val name: String)
```

### Note
1. The modifier `public`
As you can see, the modifier `public` disappeared in Kotlin!  
`public` is the default visibility in Kotlin.  

2. *value objects*
In above, you can see the code of `Person` class which has only data but no code.  
This type of classes are often called *value objects*.

## Properties of classes
### Java
In Java, the data in a class is usually private.  
If you want to allow for clients of the class to access that data, you have to provide *accessor methods*:
- a getter: it returns the value of the data.
- (possibly) a setter: it updates the value of the data.
  - It can also contain additional logic (e.g., sending notifications about the change, validation on the passed value, etc.)

**A *property* in Java means:**
```text
The field + its accessors
```

### Kotlin
In Kotlin, **properties are a first-class language feature.**  
That is, it entirely replace `fields and accessor methods`.  

Thus, declaring a property in a class has the meaning:
```text
declaring a field and the corresponding accessors:
- a getter for a read-only property (i.e., val)
- a getter and a setter for a writable property (i.e., var)
```

Let's see an example:
```kotlin
class Person(
    val name: String,   // Read-only property
    var isMarried: Boolean, // Writable property
)
```
- Declaring read-only property == generates a field + a trivial getter
- Declaring writable property == generates a field + a trivial getter + a trivial setter

However, you are able to declare a custom accessor containing different logic, if you want it.

## Instantiating an object and using it
Wherever `Person` is declared, you can use this class from Java and Kotlin!

### Java
```Java
Person person = new Person("Bob", true);
System.out.println(person.getName());
System.out.println(person.isMarried());
```
- `.getName()`: a getter method of `name` property.
- `.isMarried()`: a getter method of `isMarried` property.
  - Note that in the case of the property starting with `is`, it requires no additional prefix for the name of the getter.

### Kotlin
```kotlin
val person = Person("Bob", true)
println(person.name)    // Bob, ivoking its getter
println(person.isMarried)   // true, ivoking its getter

person.isMarried = false    // ivoking its setter
println(person.isMarried)   // false
```
- The constructor of `Person` can be called without the `new` keyword!
- You can access the properties directly.
  - The getter is invoked.

Note that the code in Kotlin become more concise than in Java, even though the logic stays the same.  
Setters of mutable properties work in the same manner.  

Note that **the property has a corresponding field which stores the property value** in most cases.  
Specially, the value can be calculated **on the fly** without a field to store the value, by defining and utilizing a custom accessor.

## Custom accessors
If you want a property computed on the fly (e.g., computed from other properties), you can consider declaring a custom getter.
```kotlin
class Rectangle(val height: Int, val width: Int)
{   
    val isSquare: Boolean   // Property without a field to store its value. The same applies when using `var`
        get() {     // Property getter declaration
            return height == width
        }
        // get() = height == width  // You can also write this form, as well.
}

fun main() {
    val rectangle = Rectangle(41, 43)
    println(rectangle.isSquare) // Output: false
}
```

In this code, `val hegith: Int` and `val width: Int` are properties which have to store a value got from constructor.  
It means they need their **backing field**, and thus they have their field.

Well, the property `isSquare` **doesn't need and have a field** to store its value.  
```text
height = 10
width = 10
isSquare: NOT a stored value. Just computed when invocated.
```
When you invocate this property, it is calculated everytime the property is accessed.  
If it was Java, you can call the method `isSquare()`.  

### A little extra info. on backing fields of properties
The backing field is usually made when:
  - there is an initializer of the property.
  - there are accessors of the property using `field`.

```kotlin
class Example
{
    var value: Int = 0  // initializer of the property -> generate a backing field
        get() = field   // the accessor utilizing `field` (backing field)
        set(newValue) {
            field = newValue
        }
}
```

If you want to make use of `field` through accessors, the property must be initialized.

### Misc.
On a side note, there are two ways:
  - declaring a function without arguments, which acts like a property getter.
  - declaring a property with custom getter.
There is no difference in terms of implementation and performance.  
The difference is just in readability.
