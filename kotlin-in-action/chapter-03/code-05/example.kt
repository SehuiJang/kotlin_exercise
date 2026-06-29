// An extension function
// The receiver type: String
// The receiver type: this
fun String.myLastChar(): Char = this.get(this.length - 1)

fun String.myLastChar_(): Char = get(length - 1)

fun main() {
    println("Kotlin".myLastChar())
    // n

    println("Kotlin".myLastChar_())
    // n
}