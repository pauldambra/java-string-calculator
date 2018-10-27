package com.dambra.paul.stringcalculator.calculationParts

class BareNumber(value: String) : CalculationPart() {
    private val value: Double = java.lang.Double.valueOf(value)

    override fun calculate(total: Double) =
            next.calculate(total + value)
}
