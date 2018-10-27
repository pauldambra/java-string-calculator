package com.dambra.paul.stringcalculator

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

import java.util.stream.Stream

import org.assertj.core.api.Assertions.assertThat

internal class TheStringCalculator {

    private val stringCalculator = StringCalculator()

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun calculationCasesProvider(): Stream<Arguments> = Stream.of(
                Arguments.of("1+1", "2"),
                Arguments.of("1 + 1", "2"),
                Arguments.of("1+2", "3"),
                Arguments.of("2+1", "3"),
                Arguments.of("10+1", "11"),
                Arguments.of("1+11", "12"),
                Arguments.of("2+3+4", "9"),
                Arguments.of("2+3+4+5", "14"),
                Arguments.of("2*7", "14"),
                Arguments.of("2*7+3", "17"),
                Arguments.of("2*7*2", "28"),
                Arguments.of("2-7", "-5"),
                Arguments.of("2*7-7", "7"),
                Arguments.of("7/2", "3.5"),
                Arguments.of("2*7+10-4/2", "10"),
                Arguments.of("4 * (3+2)", "20"),
                Arguments.of("4 * (3+2) / 10", "2"),
                Arguments.of("4 * (3+2) + 10", "30"),
                Arguments.of("(3+2) * (2 + 2)", "20")
        )
    }

    @ParameterizedTest(name = "{index} => {0}={1}")
    @MethodSource("calculationCasesProvider")
    fun canCompleteCalculations(sum: String, result: String) {
        assertThat(stringCalculator.calculate(sum)).isEqualTo(result)
    }
}

