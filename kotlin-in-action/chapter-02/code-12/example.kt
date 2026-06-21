interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

// block-body function
fun eval(e: Expr): Int {
    if (e is Num) {	// Type check
        // The below explicit cast is possible but not needed, considering smart cast.
        val n = e as Num
        return n.value
    }
    if (e is Sum) {	// Type check
        // In this block, `e` is a smart-cast value.
        return eval(e.right) + eval(e.left)	// In IDE such as IntelliJ IDEA and Android Studio, `e` in this line will be highlighted!
    }
    throw IllegalArgumentException("Unknown expression")
}

fun main() {
    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
    // 7
}
