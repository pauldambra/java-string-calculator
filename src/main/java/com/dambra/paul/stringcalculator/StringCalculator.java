package com.dambra.paul.stringcalculator;

class StringCalculator {

    String calculate(String input) {
        var stringOperations = StringOperatorParser.parse(input);
        var parts = BracketsProcessingParser.parse(stringOperations);
        var calculationStart = CalculationPartsParser.parse(parts);
        var result = calculationStart.Calculate(0);
        return EmptyDecimalRemovingParser.parse(result);
    }

}
