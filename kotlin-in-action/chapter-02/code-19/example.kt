fun main() {
    /*run*/ {
        val percentage: Int = 120
        if (percentage !in 0..100) {
            throw IllegalArgumentException(
                "A percentage value must be between 0 and 100: $percentage"
            )
        }
    }

    /*run*/ {
        val number:Int = 123

        val percentage =
            if (number in 0..100)
                number
            else
                throw IllegalArgumentException(
                    "A percentage value must be between 0 and 100: $number"
                )   // `throw` is an expression
    }

}