import java.io.BufferedReader
import java.io.StringReader

fun readNumber(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        return  // If this block is executed, the below `println(number)` cannot be executed.
    } finally {
        reader.close()
    }

    println(number)
}

fun readNumber2(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        null    // This value is used.
    } finally {
        reader.close()
    }

    println(number)
}

fun main() {
    val reader = BufferedReader(StringReader("not a number"))
    readNumber(reader)  // Nothing is printed.

    val reader2 = BufferedReader(StringReader("not a number"))
    readNumber2(reader2)
    // null

}