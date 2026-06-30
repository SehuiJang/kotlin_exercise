open class View {
    open fun click() = println("View clicked")
}

class Button: View() {
    override fun click() = println("Button clicked")
}

fun View.showOff() = println("I'm a view!")
fun Button.showOff() = println("I'm a button!")

fun main() {
    // An example of overriding
    val view: View = Button()
    view.click()
    // Button clicked

    // No overriding for extension functions
    val view2: View = Button()
    view2.showOff()
    // I'm a view!
}