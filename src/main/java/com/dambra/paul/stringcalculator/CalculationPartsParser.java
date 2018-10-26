package com.dambra.paul.stringcalculator;

import com.dambra.paul.stringcalculator.calculationParts.BareNumber;
import com.dambra.paul.stringcalculator.calculationParts.CalculationPart;
import com.dambra.paul.stringcalculator.calculationParts.Terminator;
import com.dambra.paul.stringcalculator.calculationParts.Operation;

import java.util.ArrayList;

class CalculationPartsParser {
    static CalculationPart parse(ArrayList<String> stringOperations) {
        var firstOperation = new BareNumber(stringOperations.get(0));
        CalculationPart mostRecentOperation = firstOperation;

        for (var index = 1; index < stringOperations.size(); index+=2) {
            var operator = stringOperations.get(index);
            var next = stringOperations.get(index + 1);

            var operation = new Operation(operator, next);

            mostRecentOperation = mostRecentOperation.addNext(operation);
        }

        mostRecentOperation.addNext(new Terminator());
        return firstOperation;
    }
}
