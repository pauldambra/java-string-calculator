package com.dambra.paul.stringcalculator;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

class SplitToStringOperations {
    /**
     * While you don't use a parallel stream the combiner will never be called and
     * since the reduce operation here is broken if not sequential any combiner can
     * be provided
     * see https://stackoverflow.com/a/24308988/222163
     */
    private static BinaryOperator<ArrayList<String>> noOpCombiner = (a, b) -> a;

    static ArrayList<String> from(String s) {
        s = s.replaceAll("\\s+", "");
        return splitParts(s);
    }

    private static ArrayList<String> splitParts(String input) {

        return input.chars()
                .mapToObj(c -> (char) c)
                .reduce(new ArrayList<>(), calculationReducer(), noOpCombiner);
    }

    private static BiFunction<ArrayList<String>, Character, ArrayList<String>> calculationReducer() {
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
    private static void accumulateADigit(ArrayList<String> acc, Character c) {
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
    private static void foundOperator(ArrayList<String> acc, Character c) {
        acc.add(String.valueOf(c));
    }
}
