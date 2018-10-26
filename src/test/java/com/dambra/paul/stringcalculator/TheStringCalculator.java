package com.dambra.paul.stringcalculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class TheStringCalculator {

    private final StringCalculator stringCalculator = new StringCalculator();

    private static Stream<Arguments> additionCasesProvider() {
        return Stream.of(
                Arguments.of("1+1", "2"),
                Arguments.of("1+2", "3"),
                Arguments.of("2+1", "3"),
                Arguments.of("10+1", "11"),
                Arguments.of("1+11", "12"),
                Arguments.of("2+3+4", "9")
        );
    }

    @ParameterizedTest(name = "{index} => {0}={1}")
    @MethodSource("additionCasesProvider")
    public void CanAddOneToOneAndGetTwo(String sum, String result) {
        assertThat(stringCalculator.calculate(sum)).isEqualTo(result);
    }
}

