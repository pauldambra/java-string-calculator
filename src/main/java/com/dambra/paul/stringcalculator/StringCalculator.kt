package com.dambra.paul.stringcalculator

internal class StringCalculator {

    fun calculate(input: String): String {
        return input
                .splitToStringOperations()
                .recursivelyProcessBracketedCalculations()
                .stringOperationsToCalculationParts()
                .calculate(0.0)
                .toString()
                .removeSuffix(".0")
    }
}
