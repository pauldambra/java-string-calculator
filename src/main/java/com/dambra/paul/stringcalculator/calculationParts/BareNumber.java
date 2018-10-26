package com.dambra.paul.stringcalculator.calculationParts;

public class BareNumber extends CalculationPart {
    private final double value;

    public BareNumber(String value) {
        this.value = Double.valueOf(value);
    }

    @Override
    public double Calculate(double total) {
        return next.Calculate(total + value);
    }
}
