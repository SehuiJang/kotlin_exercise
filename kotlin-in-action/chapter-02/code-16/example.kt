// Fizz-Buzz game
fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz "
    i % 3 == 0 -> "Fizz "
    i % 5 == 0 -> "Buzz "
    else -> "$i "
}

fun main() {
    // `range`: An interval between two values
    val oneToTen = 1..10
    println(oneToTen)   // 1..10
    println(oneToTen::class)    // class kotlin.ranges.IntRange

    println("====================")
    // `progression`
    val aaa = 100 downTo 1 step 2
    println(aaa)    // 100 downTo 2 step 2
    println(aaa::class)     // class kotlin.ranges.IntProgression

    println("====================")
    // Iterating over a range (progression) '100 -> 1'
    // step size = 2
    for (i in 100 downTo 1 step 2) {
        print(fizzBuzz(i))  // Differ from `println`.
    }
    // Buzz 98 Fizz 94 92 FizzBuzz ... Buzz 8 Fizz 4 2
}