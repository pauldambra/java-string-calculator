package com.dambra.paul.stringcalculator;

class RemoveTrailingPointZero {
    static String from(double result) {
        return String.valueOf(result).replaceFirst("\\.0$", "");
    }
}
