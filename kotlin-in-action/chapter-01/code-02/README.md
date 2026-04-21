# What chapter in 'Kotlin in Action'?
Chapter 1.4.3 Safe

# What did I look?
## 1. Null safety
You can prevent some errors such as ```NullPointerException``` by marking a type as nullable.  
What you need is just a question mark '?'.  

    ```
    val s: String? = null   // May be null
    val s2: String = ""     // May not be null
    ```

## 2. Safety from ```ClassCastException```
You can check the type of an object. I think this is similar to ```isinstance``` in Python.  
Checking type can help you to avoid ```ClassCastException```.

    ```
    val value: String? = "AbCde"
    if (value is String)    // Checks the type
        // println(value.toUpperCase())     // deprecated
        println(value.uppercase())  // Uses the method of the type
    ```