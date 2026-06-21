interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

// expression-body function (if - else if - else)
fun eval_if_exp(e: Expr): Int =
    if (e is Num) {	// Type check
        e.value
    } else if (e is Sum) {	// Type check
        eval_if_exp(e.right) + eval_if_exp(e.left)
    } else {
        throw IllegalArgumentException("Unknown expression")
    }

// expression-body function (if - else if - else)
fun eval_if_exp_oneline(e: Expr): Int =
    if (e is Num) e.value
    else if (e is Sum) eval_if_exp_oneline(e.right) + eval_if_exp_oneline(e.left)
    else throw IllegalArgumentException("Unknown expression")

// expression-body function (when expression)
fun eval_when_exp(e: Expr): Int =
    when (e) {
        is Num -> e.value
        is Sum -> eval_when_exp(e.right) + eval_when_exp(e.left)
        else -> throw IllegalArgumentException("Unknown expr")
    }

fun main() {
    println(eval_if_exp(Sum(Num(1), Num(2))))
    // 3
    println(eval_if_exp_oneline(Sum(Num(10), Num(2))))
    // 12
    println(eval_when_exp(Sum(Num(1), Num(21))))
    // 22
}