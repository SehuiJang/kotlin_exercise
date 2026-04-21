fun main() {
    val s: String? = null   // May be null
    val s2: String = ""     // May not be null
    

    val value: String? = "AbCde"
    if (value is String)    // Checks the type
        // println(value.toUpperCase())     // deprecated
        println(value.uppercase())  // Uses the method of the type
}
