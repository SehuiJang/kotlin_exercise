# What chapter in 'Kotlin in Action'?
- Chapter 2.4.3 Iterating over maps (2/e version)  

# What did I look?
- `for` loops which iterates over a list, a map, and a list with an index.
- The index starts from 0 just as with Python!

# Codes
## Iteration over a map (list) with a `for` loop
```kotlin
val collection = listOf("red", "green", "blue")

for (color in collection) {
    print("$color ")
}
```
- `color` gets a `String` in each iteration.
  - Order: "red", "green", and "blue"

## Iteration over a mutable map with a `for` loop

```kotlin
val binaryReps = mutableMapOf<Char, String>()
for (char in 'A'..'F') {
    val binary = char.code.toString(radix = 2)  // ASCII code -> binary number
    binaryReps[char] = binary   // ex) 'A': 1000001
}

for ((letter, binary) in binaryReps) {
    println("$letter = $binary")
}
```
- `mutableMapOf` is similar to `dict` in Python.
- `char.code.toString` does:
  - (1) Get an ASCII code of a `Char`.
  - (2) Transform ASCII code to `String` at the same time as converting to a binary number (`radix = 2`).
- If you are from Python, you would be familiar with the form of the 2nd `for`!
  - Unpacking syntax: The 2nd `for` unpacks an element of a mutable map.
  - In each iteration, `letter` and `binary` get a key (`Char`) and a string (`String`) representing the binary number of the key, respectively.
  - In other words, `letter` and `binary` receives the key and the value, respectively.
- Well, you can update the value in the map!
  - `binaryReps[key] = updated_value`

## Iteration over a collection with an index

```kotlin
val list = listOf("10", "11", "1001")

for ((index, element) in list.withIndex()) {
    println("$index: $element")
}
```
- `list.withIndex()`: You can scan the elements in the list with their respective indices.
  - It's similar to `enumerate(...)` in Python
- Note that the index starts from 0!
