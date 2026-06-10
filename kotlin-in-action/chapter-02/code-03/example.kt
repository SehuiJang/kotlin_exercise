val answer5: Int = 100  // Top-level declaration requires an initializer.
// val answer5: Int    // NOT ALLOWED!

var answer6: Int = -12  // Top-level declaration requires an initializer.
// var answer6: Int    // NOT ALLOWED!

fun main() {
    // Immutable reference

    run {   // Scope 1
        val question = "The Ultimate Question of Life, the Universe, and Everything"

        val answer1 = 42
        val answer2: Int = 45

        val yearsToCompute = 7.5e6
    }

    run {   // Scope 2
        val answer3: Int    // When a variable has no initializer, you have to specify its type.
        answer3 = 42
    }

    run {   // Scope 3
        val message: String // Initialization depending on some condition
        val success = true
        if (success)
            { message = "Success"
            // ... perform the operation
        }
        else {
            message = "Failed"
        }
        println(message)
    }
    
    run {   // Scope 4
        val languages = arrayListOf("Java")
        languages.add("Kotlin")
    }

    // ===========================

    // Mutable reference

    run {   // Scope 5
        var ans = 17
        // ans = "no answer"   // Error: type mismatch
        ans = 20
        println(ans)
    }
    
}
