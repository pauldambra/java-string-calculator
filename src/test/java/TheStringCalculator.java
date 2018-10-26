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

    private class StringCalculator {
        public String calculate(String input) {
            var a = input.charAt(0);
            var firstValue = Character.getNumericValue(a);

            var b = input.charAt(input.length() - 1);
            var secondValue = Character.getNumericValue(b);

            var result = firstValue + secondValue;
            return String.valueOf(result);
        }
    }
}
