fun main() {
    run {
        val collection = listOf("red", "green", "blue")
        
        for (color in collection) {
            print("$color ")
        }
    }
    // red green blue
    print("\n")
    println("====================")
    run {
        val binaryReps = mutableMapOf<Char, String>()
        for (char in 'A'..'F') {
            val binary = char.code.toString(radix = 2)  // ASCII code -> binary number
            binaryReps[char] = binary   // ex) 'A': 1000001
        }

        for ((letter, binary) in binaryReps) {
            println("$letter = $binary")
        }
        println(binaryReps) // {A=1000001, B=1000010, C=1000011, D=1000100, E=1000101, F=1000110}
    }
    // A = 1000001
    // B = 1000010
    // ...
    // F = 1000110

    println("====================")
    run {
        val list = listOf("10", "11", "1001")
        
        // iterates over a collection with an index
        // The index starts from 0.
        for ((index, element) in list.withIndex()) {
            println("$index: $element")
        }
    }
    // 0: 10
    // 1: 11
    // 2: 1001

}