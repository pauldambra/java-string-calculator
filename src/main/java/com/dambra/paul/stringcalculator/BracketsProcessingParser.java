package com.dambra.paul.stringcalculator;

import java.util.ArrayList;

class BracketsProcessingParser {
    static ArrayList<String> parse(ArrayList<String> parts) {
        if (!parts.contains("(")) {
            return parts;
        }

        var start = parts.indexOf("(");
        var end = parts.indexOf(")");
        var insideBrackets = String.join("", parts.subList(start + 1, end));

        var result = new StringCalculator().calculate(insideBrackets);

        var x = replaceBracketsDelimitedOperationsWithResult(parts, start, end, result);
        return BracketsProcessingParser.parse(x);
    }

    private static ArrayList<String> replaceBracketsDelimitedOperationsWithResult(ArrayList<String> parts, int start, int end, String result) {
        var x = new ArrayList<String>();
        for (var i = 0; i < parts.size(); i++) {
            if (i < start || end < i) {
                x.add(parts.get(i));
            }
            if (i == end) {
                x.add(result);
            }
        }
        return x;
    }
}
