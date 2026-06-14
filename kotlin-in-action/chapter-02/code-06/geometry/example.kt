package geometry.example    // Package declaration

import geometry.shapes.Rectangle    // Imports a class by name
import geometry.shapes.createUnitSquare
// You can also write "wildcard import (star import)":
// import geometry.shapes.*     // star import (everything in package geometry.shapes)

fun main() {
    println(Rectangle(3, 4).isSquare)  // Output: false
    println(createUnitSquare().isSquare)    // Output: true
}