package com.dambra.paul.stringcalculator;

class EmptyDecimalRemovingParser {
    static String parse(double result) {
        return String.valueOf(result).replaceFirst("\\.0$", "");
    }
}
