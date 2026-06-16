import ch02.colors.Color.*

fun measureColor() = ORANGE

fun getWarmthFromSensor() =
    when (val color = measureColor()) {
        RED, ORANGE, YELLOW -> "warm (red = ${color.r})"
        GREEN -> "neutral (green = ${color.g})"
        BLUE, INDIGO, VIOLET -> "cold (blue = ${color.b})"
    }

fun main() {
    println(getWarmthFromSensor())
    // warm (red = 255)
}
