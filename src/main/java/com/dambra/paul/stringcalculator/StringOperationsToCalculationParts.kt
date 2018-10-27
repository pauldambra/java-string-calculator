package com.dambra.paul.stringcalculator

import com.dambra.paul.stringcalculator.calculationParts.BareNumber
import com.dambra.paul.stringcalculator.calculationParts.CalculationPart
import com.dambra.paul.stringcalculator.calculationParts.Operation
import com.dambra.paul.stringcalculator.calculationParts.Terminator

fun List<String>.stringOperationsToCalculationParts(): CalculationPart {
    val firstOperation = BareNumber(this.first())

    val linkedOperations =
            this
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