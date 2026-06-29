import strings.lastChar  // or strings.*
import strings.lastChar as last     // Changing the class name or function name you are importing

fun main() {
    val c = "Kotlin".lastChar()
    println(c)
    // n

    val c2 = "Hello".last()
    println(c2)
    // o
}