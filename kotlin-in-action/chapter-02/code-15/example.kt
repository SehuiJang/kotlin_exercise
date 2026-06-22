fun main() {
    // Example for `while` + `break`
    run {
        var i = 0

        while (i < 5) {
            println("i = $i")

            if (i == 2) {
                println("Execute break")
                break
            }

            i++
        }

        println("loop (while + break) finished")
    }
    // i = 0
    // i = 1
    // i = 2
    // Execute break
    // loop (while + break) finished

    println("====================")
    // Example for `do-while` + `continue`
    run {
        var i = 0

        do {
            i++

            if (i == 2) {
                println("Skip i = 2")
                continue
            }

            println("i = $i")
        } while (i < 4)

        println("loop (do-while + continue) finished")
    }
    // i = 1
    // Skip i = 2
    // i = 3
    // i = 4
    // loop (do-while + continue) finished

    println("====================")
    // Example for `break` without label, in nested loop
    run {
        var outer = 1

        while (outer <= 3) {
            var inner = 1

            while (inner <= 3) {
                println("outer = $outer, inner = $inner")

                if (inner == 2) {
                    println("inner loop break")
                    break
                }

                inner++
            }

            outer++
        }

        println("finished (break without label in nested while loop)")
    }
    // outer = 1, inner = 1
    // outer = 1, inner = 2
    // inner loop break
    // outer = 2, inner = 1
    // outer = 2, inner = 2
    // inner loop break
    // outer = 3, inner = 1
    // outer = 3, inner = 2
    // inner loop break
    // finished (break without label in nested while loop)

    println("====================")
    // Example for `break@outer` in nested loop
    run {
        var outer = 1

        outerLoop@ while (outer <= 3) {
            var inner = 1

            while (inner <= 3) {
                println("outer = $outer, inner = $inner")

                if (outer == 2 && inner == 2) {
                    println("Execute break@outerLoop")
                    break@outerLoop
                }

                inner++
            }

            outer++
        }

        println("finished (break@outer in nested while loop)")
    }
    // outer = 1, inner = 1
    // outer = 1, inner = 2
    // outer = 1, inner = 3
    // outer = 2, inner = 1
    // outer = 2, inner = 2
    // Execute break@outerLoop
    // finished (break@outer in nested while loop)

    println("====================")
    // Example for `continue@outer` in nested loop
    run {
        var outer = 1

        outerLoop@ while (outer <= 3) {
            var inner = 1

            while (inner <= 3) {
                if (inner == 2) {
                    println("outer = $outer, inner = $inner -> continue@outerLoop")

                    outer++
                    continue@outerLoop
                }

                println("outer = $outer, inner = $inner")
                inner++
            }

            outer++
        }

        println("finished (continue@outer in nested while loop)")
    }
    // outer = 1, inner = 1
    // outer = 1, inner = 2 -> continue@outerLoop
    // outer = 2, inner = 1
    // outer = 2, inner = 2 -> continue@outerLoop
    // outer = 3, inner = 1
    // outer = 3, inner = 2 -> continue@outerLoop
    // finished (continue@outer in nested while loop)

}