class Person(
    val name: String,   // Read-only property
    var isMarried: Boolean, // Writable property 
)

class Rectangle(val height: Int, val width: Int)
{   
    val isSquare: Boolean   // Property without a field to store its value. The same applies when using `var`
        get() {     // Property getter declaration
            return height == width
        }
        // get() = height == width  // You can also write this form, as well.
}

class Example
{
    var value: Int = 0  // initializer of the property -> generate a backing field
        get() = field   // the accessor using `field` -> generate a backing field
        set(newValue) {
            field = newValue
        }
}

fun main() {
    run {   
        val person = Person("Bob", true)
        println(person.name)    // Output: Bob
        println(person.isMarried)   // Output: true

        person.isMarried = false    // invoking its setter (trivial setter)
        println(person.isMarried)   // Output: false
    }

    // The invocation of a property
    run {
        val rectangle = Rectangle(41, 43)
        println(rectangle.isSquare) // Output: false
    }

    run {
        val ex = Example()
        println(ex.value)  // Output: 0
        ex.value = 100
        println(ex.value)  // Output: 100
    }
}
