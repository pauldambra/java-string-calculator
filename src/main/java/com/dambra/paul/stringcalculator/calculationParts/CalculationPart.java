package com.dambra.paul.stringcalculator.calculationParts;

public abstract class CalculationPart {
    protected CalculationPart next;

    public abstract double Calculate(double total);

    public CalculationPart addNext(CalculationPart next) {
        this.next = next;
        return this.next;
    }
}

