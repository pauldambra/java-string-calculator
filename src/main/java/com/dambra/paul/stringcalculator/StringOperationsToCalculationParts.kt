package com.dambra.paul.stringcalculator

import com.dambra.paul.stringcalculator.calculationParts.BareNumber
import com.dambra.paul.stringcalculator.calculationParts.CalculationPart
import com.dambra.paul.stringcalculator.calculationParts.Operation
import com.dambra.paul.stringcalculator.calculationParts.Terminator

internal object StringOperationsToCalculationParts {
    fun from(stringOperations: MutableList<String>): CalculationPart {
        val firstOperation = BareNumber(stringOperations.first())

        val linkedOperations =
                stringOperations
                        .drop(1)
                        .chunked(2)
                        .map { Operation(it.first(), it.last()) }
                        .fold(firstOperation) { acc: CalculationPart, o: CalculationPart ->
                            acc.addNext(o)
                            o
                        }

        linkedOperations.addNext(Terminator())
        return firstOperation
    }
}
