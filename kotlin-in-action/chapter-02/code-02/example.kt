// Block body: The function max1 has a block body. It's body is in curly braces.
fun max1(a: Int, b: Int): Int
    { return if (a > b) a else b
}

// Expression bodies: The function max2 returns an expression directly.
fun max2(a: Int, b: Int): Int = if (a > b) a else b
fun max3(a: Int, b: Int) = if (a > b) a else b  // More simplified function, omitting its return type

fun main(){
    // Java-style String.format(), requiring format specifiers
    println("max1(1, 2): %d".format(max1(1, 2)))

    // String templates (inserting the result of an equation or function). Recommended in Kotlin
    println("max2(5, 3): ${max2(5, 3)}")    

    // String templates (inserting just a variable). Recommended in Kotlin
    val ret = max3(10, 11)
    println("max3(10, 11): $ret")
}