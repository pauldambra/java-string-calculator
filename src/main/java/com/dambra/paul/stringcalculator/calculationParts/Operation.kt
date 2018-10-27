package com.dambra.paul.stringcalculator.calculationParts

class Operation(private val operator: String, nextValue: String) : CalculationPart() {
    private val nextValue: Double = java.lang.Double.valueOf(nextValue)

    override fun calculate(total: Double) =
            next.calculate(performCalculation(total))

    private fun performCalculation(total: Double) =
            when (operator) {
                "+" -> total + nextValue
                "-" -> total - nextValue
                "/" -> total / nextValue
                else -> total * nextValue
            }
}
