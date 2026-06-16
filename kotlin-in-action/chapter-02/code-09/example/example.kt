import ch02.colors.Color.*

fun measureColor() = ORANGE

fun getWarmthFromSensor() =
    // The subject of `when` is the return value of `measureColor()`.
    // Next, the declared variable, `color`, captures the subject.
    when (val color = measureColor()) {
        RED, ORANGE, YELLOW -> "warm (red = ${color.r})" // reusing `color` in each branch
        GREEN -> "neutral (green = ${color.g})"
        BLUE, INDIGO, VIOLET -> "cold (blue = ${color.b})"
    }

fun main() {
    println(getWarmthFromSensor())
    // warm (red = 255)
}
