fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    
    result.append(postfix)
    return result.toString()
}

// Use a more specific type (Collection<String>) as a receiver type
// Note that Collection<T> is not a class but an interface
fun Collection<String>.join(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
) = joinToString(separator, prefix, postfix)

fun main() {
    val list = listOf(1, 2, 3)
    println(
        list.joinToString(
            separator = "; ",
            prefix = "(",
            postfix = ")"
        )
    )
    // (1; 2; 3)
    
    val list2 = listOf(1, 2, 3)
    println(list2.joinToString(" "))
    // 1 2 3

    println(listOf("one", "two", "eight").join(" "))
	// one two eight
    
//     listOf(1, 2, 8).join()	// error! None of the following candidates is applicable because of a receiver type mismatch
}
