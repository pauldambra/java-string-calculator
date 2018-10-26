package com.dambra.paul.stringcalculator;

class StringCalculator {
    String calculate(String input) {

        var split = input.split("\\+");
        var a = Double.parseDouble(split[0]);
        var b = Double.parseDouble(split[1]);

        var result = a + b;
        return String.valueOf(result).replaceFirst("\\.0$", "");
    }
}
