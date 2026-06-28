fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ", ",   // default parameter value
    prefix: String = "",
    postfix: String = ""
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
    
    println(joinToString(list, ", ", "", ""))
    // 1, 2, 3
    println(joinToString(list))
    // 1, 2, 3
    println(joinToString(list, "; "))
    // 1; 2; 3

    println(joinToString(list, postfix = ";", prefix = "# "))
    // # 1, 2, 3;
}