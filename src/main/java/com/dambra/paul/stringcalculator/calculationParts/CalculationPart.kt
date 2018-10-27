package com.dambra.paul.stringcalculator.calculationParts

abstract class CalculationPart {
    protected lateinit var next: CalculationPart

    abstract fun calculate(total: Double): Double

    fun addNext(next: CalculationPart): CalculationPart {
        this.next = next
        return this.next
    }
}

