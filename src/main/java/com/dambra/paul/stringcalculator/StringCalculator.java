package com.dambra.paul.stringcalculator;


import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

class StringCalculator {
    /**
     * While you don't use a parallel stream the combiner will never be called and
     * since the reduce operation here is broken if not sequential any combiner can
     * be provided
     * see https://stackoverflow.com/a/24308988/222163
     */
    private BinaryOperator<ArrayList<String>> noOpCombiner = (a, b) -> a;

    String calculate(String input) {
        input = input.replace(" ", "");
        var parts = toCalculationParts(input);
        var result = performCalculations(parts);

        return stripPointZeroFromResult(result);
    }

    private double performCalculations(ArrayList<String> parts) {
        var firstCalculation = parts.subList(0, 3);
        var tail = parts.subList(3, parts.size());

        var total = performCalculation(firstCalculation.get(1), Double.valueOf(firstCalculation.get(0)), firstCalculation.get(2));
        for (var index = 0; index < tail.size(); index+=2) {
            var operator = tail.get(index);
            var next = tail.get(index + 1);
            total = performCalculation(operator, total, next);
        }

        return total;
    }

    private double performCalculation(String operator, double total, String next) {
        var nextValue = Double.valueOf(next);
        switch (operator) {
            case "+":
                total += nextValue;
                break;
            case "-":
                total -= nextValue;
                break;
            case "/":
                total /= nextValue;
                break;
            default:
                total *= nextValue;
                break;
        }
        return total;
    }

    private String stripPointZeroFromResult(double result) {
        return String.valueOf(result).replaceFirst("\\.0$", "");
    }

    private ArrayList<String> toCalculationParts(String input) {
        ArrayList<String> startingParts = new ArrayList<>();
        startingParts.add("");

        return input.chars()
                .mapToObj(c -> (char) c)
                .reduce(startingParts, calculationReducer(), noOpCombiner);
    }

    private BiFunction<ArrayList<String>, Character, ArrayList<String>> calculationReducer() {
        return (acc, c) -> {
            if(Character.isDigit(c)) {
                accumulateADigit(acc, c);
            } else {
                foundOperator(acc, c);
            }
            return acc;
        };
    }

    // ugh, side effects
    private void accumulateADigit(ArrayList<String> acc, Character c) {
        var current = acc.get(acc.size() - 1) + c;
        acc.set(acc.size() - 1, current);
    }

    //ugh, side effects
    private void foundOperator(ArrayList<String> acc, Character c) {
        acc.add(String.valueOf(c));
        acc.add("");
    }
}
