package ch02.colors

// enum class without constructor parameters
// OR enum class without arguments
enum class Color_simple {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}

enum class Color_override {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET;

    override fun toString(): String {
        return "Color: $name"
    }
}

// enum class having the constructor with three parameters
enum class Color(
    val r: Int,
    val g: Int,
    val b: Int
) {
    // enum constants (or fixed instances): RED - VIOLET
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIOLET(238, 130, 238);  // semicolon `;` -> From below this character, you can define any methods and other properties.
    val rgb = (r * 256 + g) * 256 + b   // This property has an initializer, so that it has its backing field.
    fun printColor() = println("$this is $rgb")
}

fun main() {
    run {
        println(Color.BLUE.rgb) // Output: 255
        Color.GREEN.printColor()    // Output: GREEN is 65280

        println(Color_simple.RED::class.simpleName) // Output: Color_simple
        println(Color_simple.RED)   // Output: RED
    }

    run {
        //example.kt:34:13: error: cannot access 'constructor(r: Int, g: Int, b: Int): Color': it is private in 'ch02.colors.Color'.
        // val c = Color(255, 0, 0)     // NOT ALLOWED!

        val c = Color.RED   // ALLOWED. 
            // c: its type is `Color`
            // Color.RED: an enum constant and a fixed instance of `Color` enum class
    }

    run {
        val c = Color_simple.RED    // it has no property such as c.r and c.rgb.
        // But, `c` has a few default properties provided from Kotlin/Java enum.
        println(c.name) // Output: RED
        println(c.ordinal) // Output: 0

        println(c)  // Output: RED. This internally invokes `c.toString()`, and `toString()` of enum returns the property `.name` of the enum constant by default.

        // Well, if you override `toString()` in enum class, a different message can be printed.
        println(Color_override.RED)     // Output: Color: RED
    }
}