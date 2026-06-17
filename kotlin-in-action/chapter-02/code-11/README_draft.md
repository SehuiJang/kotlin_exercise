# What chapter in 'Kotlin in Action'?
- Chapter 2.3.5 Using the when expression without an argument (2/e version)  

# What did I look?
- You can use `when` expression without an argument and a subject.
- You can avoid creating short-lived objects which are just used only to check the branch conditions.

# Example code
## Execution script
In your terminal, adjust the current path to this directory, utilizing `cd`:
```bash
cd C:/path/to/your/chapter-02/code-11
```
And then, compile and execute the code:
```bash
C:/path/to/your/kotlinc ./example/colors.kt ./example/example.kt -include-runtime -d example.jar && java -jar example.jar
```

## Notes on the code


- In [code-10](https://github.com/SehuiJang/kotlin_exercise/tree/main/kotlin-in-action/chapter-02/code-10), there is a subject of `when` in the form of a `Set` instance of two `Color` enum constants.
  - And the subject is just used only to check whether it matches the branch conditions.
  - In addition, there are some `Set` instances in the branch conditions, just for checking whether it is equal to the subject.
  - I mean that **there are several short-lived objects in code-10**, and they **will be cleaned up by the garbage collector**.
- Unlike code-10, you can consider the `when` expression without a subject.
  - Readability is not good.
  - Even so, this can reduce the cost of:
    - creating (i.e., allocating) short-lived objects.
    - cleaning them up (i.e., reclaiming) through garbage collection.
- If a `when` expression is written without a subject (i.e., argument), its branch conditions are evaluated as Boolean expressions.
  - Such a `when` expression works like a sequence of Boolean checks!
  - That is, each branch condition can be any Boolean expression.
