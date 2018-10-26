package com.dambra.paul.stringcalculator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TheStringCalculator {
    @Test
    public void CanAddOneToOneAndGetTwo() {
        assertThat(new StringCalculator().calculate("1+1")).isEqualTo("2");
    }

    @Test
    public void CanAddOneToTwoAndGetThree() {
        assertThat(new StringCalculator().calculate("1+2")).isEqualTo("3");
    }

    @Test
    public void CanAddTwoToOneAndGetThree() {
        assertThat(new StringCalculator().calculate("2+1")).isEqualTo("3");
    }

    @Test
    public void CanAddTenToOne() {
        assertThat(new StringCalculator().calculate("10+1")).isEqualTo("11");
    }

    @Test
    public void CanAddOneToEleven() {
        assertThat(new StringCalculator().calculate("1+11")).isEqualTo("12");
    }
}

