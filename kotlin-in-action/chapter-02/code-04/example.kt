fun main(args: Array<String>) {
    run { // Scope1
        val name = if (args.size > 0) args[0] else "Kotlin"

        // When referring to local variables in string literals, just put the character '$' in front of the variable name!
        // $: variable reference
        println("Hello, $name!")    // equivalent to a string concatenation ("Hello, " + name + "!") in Java
    }

    run { // Scope2
        // Intending to use '$' itself within a string, you can use "\$"
        println("\$x!")
    }

    run { // Scope3
        // Able to print the result of expressions or functions by putting curly braces around the expression
        if (args.size > 0) {
            println("Hello, ${args[0]}!")
        }
    }

    run { // Scope4
        // nested double quotes ("")
        // The inner double quotes have to be within an expression (i.e., within curly braces).
        println("Hello, ${if (args.size > 0) args[0] else "someone"}")
    }

}
