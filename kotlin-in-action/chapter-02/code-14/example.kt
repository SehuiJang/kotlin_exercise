interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun evalWithLogging(e: Expr): Int =
    when (e) {
        is Num -> {
            println("num: ${e.value}")
            e.value
        }
        is Sum -> {
            val left = evalWithLogging(e.left)
            val right = evalWithLogging(e.right)
            println("sum: $left + $right")
            left + right
        }
        else -> throw IllegalArgumentException("Unknown expression")
    }

// --------

// If an explicit `return` is in the block body of `if`
fun test(x: Int): Int {
    if (x > 0) {
        return x
        println("never printed")
    }

    return 0
}

// If an explicit `return` is in each block body of `when`
fun describeNumber(x: Int): String {
    when (x) {
        0 -> {
            println("zero branch")
            return "zero"
            println("this line is never executed")
        }

        1 -> {
            println("one branch")
            return "one"
        }

        else -> {
            println("else branch")
            return "other"
        }
    }
}

// More concise version with a single `return`
fun describeNumber2(x: Int): String {
    return when (x) {
        0 -> {
            println("zero branch")
            "zero"
        }

        1 -> {
            println("one branch")
            "one"
        }

        else -> {
            println("else branch")
            "other"
        }
    }
}

fun main() {
    println(evalWithLogging(Sum(Sum(Num(1), Num(2)), Num(4))))
    // num: 1
    // num: 2
    // sum: 1 + 2
    // num: 4
    // sum: 3 + 4
    // 7

    run {
        println(test(4))
        // 4

        println(describeNumber(0))
        // zero branch
        // zero

        println(describeNumber2(0))
        // zero branch
        // zero
    }
}