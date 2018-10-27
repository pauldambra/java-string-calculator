package com.dambra.paul.stringcalculator;

class StringCalculator {

    String calculate(String input) {
        var stringOperations = SplitToStringOperations.from(input);
        var parts = RecursivelyProcessBracketedCalculations.from(stringOperations);
        var calculationStart = StringOperationsToCalculationParts.from(parts);
        var result = calculationStart.Calculate(0);
        return RemoveTrailingPointZero.from(result);
    }

}
