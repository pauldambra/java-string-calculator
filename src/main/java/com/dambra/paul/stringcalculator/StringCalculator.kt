package com.dambra.paul.stringcalculator

import java.util.ArrayList

internal class StringCalculator {

    fun calculate(input: String): String {
        val stringOperations = SplitToStringOperations.from(input)
        val parts = RecursivelyProcessBracketedCalculations.from(stringOperations as ArrayList<String>?)
        val calculationStart = StringOperationsToCalculationParts.from(parts)
        val result = calculationStart.Calculate(0.0)
        return RemoveTrailingPointZero.from(result)
    }

}
