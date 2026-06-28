fun main() {
    run {
        val set = setOf(1, 7, 53)
        val list = listOf(1, 7, 53)
        val map = mapOf(1 to "one", 7 to "seven", 53 to "fifty-three")

        println(set.javaClass)
        // class java.util.LinkedHashSet
        
        println(list.javaClass)
        // class java.util.Arrays$ArrayList
        
        println(map.javaClass)
        // class java.util.LinkedHashMap
    }
    
    println("====================")

    run {
        val strings = listOf("first", "second", "fourteenth")
        
        println(strings.last())
        // fourteenth
        
        println(strings.shuffled())
        // [fourteenth, second, first]
        
        val numbers = setOf(1, 14, 2)
        println(numbers.sum())
        // 17
    }
}