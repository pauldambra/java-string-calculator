package com.dambra.paul.stringcalculator

internal class StringCalculator {

    fun calculate(input: String): String {
        val stringOperations = SplitToStringOperations.from(input)
        val parts = RecursivelyProcessBracketedCalculations.from(stringOperations)
        val calculationStart = StringOperationsToCalculationParts.from(parts)
        val result = calculationStart.calculate(0.0)
        return result.toString().removeSuffix(".0")
    }

}
