package com.dambra.paul.stringcalculator;


import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

abstract class CalculationPart {
    protected CalculationPart next;

    public abstract double Calculate(double total);

    CalculationPart addNext(CalculationPart next) {
        this.next = next;
        return this.next;
    }
}

class Number extends CalculationPart {
    private final double value;

    Number(String value) {
        this.value = Double.valueOf(value);
    }

    @Override
    public double Calculate(double total) {
        return next.Calculate(total + value);
    }
}

class operation extends CalculationPart {
    private final String operator;
    private final double nextValue;

    operation(String operator, String nextValue) {
        this.operator = operator;
        this.nextValue = Double.valueOf(nextValue);
    }

    @Override
    public double Calculate(double total) {
        return next.Calculate(performCalculation(total));
    }

    private double performCalculation(double total) {
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
}

class Terminator extends CalculationPart {

    @Override
    public double Calculate(double total) {
        return total;
    }
}

class StringCalculator {
    /**
     * While you don't use a parallel stream the combiner will never be called and
     * since the reduce operation here is broken if not sequential any combiner can
     * be provided
     * see https://stackoverflow.com/a/24308988/222163
     */
    private BinaryOperator<ArrayList<String>> noOpCombiner = (a, b) -> a;

    String calculate(String input) {
        input = input.replaceAll("\\s+", "");
        var parts = splitParts(input);
        parts = collapseBrackets(parts);
        var operations = toOperations(parts);
        var result = operations.Calculate(0);

        return stripPointZeroFromResult(result);
    }

    private ArrayList<String> collapseBrackets(ArrayList<String> parts) {
        if (parts.contains("(")) {
            var start = parts.indexOf("(");
            var end = parts.indexOf(")");
            var insideBrackets = String.join("", parts.subList(start + 1, end));
            var result = new StringCalculator().calculate(insideBrackets);
            var x = new ArrayList<String>();
            for (var i = 0; i < parts.size(); i++) {
                if (i < start || end < i) {
                    x.add(parts.get(i));
                }
                if (i == end) {
                    x.add(result);
                }
            }
            return collapseBrackets(x);
        }
        return parts;
    }

    private CalculationPart toOperations(ArrayList<String> parts) {

        var firstOperation = new Number(parts.get(0));
        CalculationPart mostRecentOperation = firstOperation;
        for (var index = 1; index < parts.size(); index+=2) {
            var operator = parts.get(index);
            var next = parts.get(index + 1);
            var operation = new operation(operator, next);
            mostRecentOperation = mostRecentOperation.addNext(operation);
        }

        mostRecentOperation.addNext(new Terminator());
        return firstOperation;
    }

    private String stripPointZeroFromResult(double result) {
        return String.valueOf(result).replaceFirst("\\.0$", "");
    }

    private ArrayList<String> splitParts(String input) {

        return input.chars()
                .mapToObj(c -> (char) c)
                .reduce(new ArrayList<>(), calculationReducer(), noOpCombiner);
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
        if (acc.isEmpty()) {
            acc.add(String.valueOf(c));
        } else {
            var last = acc.get(acc.size() - 1);
            if (last.matches("[^0-9]")) {
                acc.add("");
                last = acc.get(acc.size() - 1);
            }
            var current = last + c;
            acc.set(acc.size() - 1, current);
        }
    }

    //ugh, side effects
    private void foundOperator(ArrayList<String> acc, Character c) {
        acc.add(String.valueOf(c));
    }
}
