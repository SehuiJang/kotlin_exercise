fun <T> joinToString(
    collection: Collection<T>,
    separator: String,
    prefix: String,
    postfix: String
): String {

    val result = StringBuilder(prefix)

    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

fun main() {
    val list = listOf(1, 2, 3)

    // 1. Basic manner to call the function
    println(joinToString(list, "; ", "(", ")"))
    // (1; 2; 3)

    println("====================")

    // 2. Specifying the names of some of the arguments
    println(joinToString(list, separator = " ", prefix = " ", postfix = "."))
    //  1 2 3.

    println("====================")

    // 3. If the names of all argumments are specified, you can change the order of them.
    println(
        joinToString(
            postfix = ".",
            separator = " ",
            collection = list,
            prefix = " "
        )
    )
    //  1 2 3.
}