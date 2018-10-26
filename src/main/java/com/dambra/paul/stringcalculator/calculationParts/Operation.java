package com.dambra.paul.stringcalculator.calculationParts;

public class Operation extends CalculationPart {
    private final String operator;
    private final double nextValue;

    public Operation(String operator, String nextValue) {
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
