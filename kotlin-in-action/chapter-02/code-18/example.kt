fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'  // c in 'a'..'z' is equivalent to 'a' <= c && c <= 'z'
fun isNotDigit(c: Char) = c !in '0'..'9'	// c < '0' || c > '9'

// `in` check + `when` expression
fun recognize(c: Char) = when (c) {
    in '0'..'9' -> "It's a digit!"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter!"	// `,` means `||` (OR gate).
    else -> "I don't know..."
}

fun main() {
	run {
        println(isLetter('q'))	// true
        println(isNotDigit('x'))	// true
    }
    
    run {
        println(recognize('8'))		// It's a digit!
    }
    
    // allphabetical comparison between `String` instances
    run {
        println("Kotlin" in "Java".."Scala")	// true. It is equivalent to "Java" <= "Kotlin" && "Kotlin" <= "Scala".
        println("Kotlin" in setOf("Java", "Scala"))	// false. The set does not have the string "Kotlin".
    }
    
}
