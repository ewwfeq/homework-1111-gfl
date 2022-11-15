package org.example;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class MainTest {

    double[] arguments;
    double[] values;

    @BeforeEach
    void setUp() {
        arguments = Main.createArrayOfArguments(0.7, 2, 0.005);
        values = Main.createArrayOfValues(arguments);
    }

    @ParameterizedTest
    @CsvSource({
        "2, 2.73",
        "5, 3.14",
        "10, 3.52",
        "1.3, 11.61",
        "1, -3.86",
    })
    void testCalculateFunction(double x, double expected) {
        assertThat(Main.calculateFunction(x)).isCloseTo(expected, Percentage.withPercentage(0.1));
    }

    @Test
    void testCalculateFunctionInfinite() {
        assertThat(Main.calculateFunction(0)).isInfinite();
    }


    @Test
    void testCountNumberOfSteps() {
        assertThat(Main.countNumberOfSteps(0.7, 2, 0.005)).isEqualTo(261);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0.7",
            "120, 1.3",
            "260, 2"
    })
    void testCreateArrayOfArguments(int index, double expectedValue) {
        assertThat(arguments[index]).isCloseTo(expectedValue, Percentage.withPercentage(0.1));
    }

    @ParameterizedTest
    @CsvSource({
            "0, -12.746",
            "120, 11.61",
            "260, 2.733"
    })
    void testCreateArrayOfValues(int index, double expectedValue) {
        assertThat(values[index]).isCloseTo(expectedValue, Percentage.withPercentage(0.1));
    }

    @Test
    void testFindIndexOfMaxValue() {
        assertThat(Main.findIndexOfMaxValue(values)).isEqualTo(120);
    }

    @Test
    void testFindIndexOfMinValue() {
        assertThat(Main.findIndexOfMinValue(values)).isEqualTo(0);
    }

    @Test
    void testCalculateSumOfValues() {
        assertThat(Main.calculateSumOfValues(values)).isCloseTo(-157.1, Percentage.withPercentage(0.1));
    }

    @Test
    void testCalculateAverageOfValues() {
        assertThat(Main.calculateAverageOfValues(values)).isCloseTo(-0.602, Percentage.withPercentage(0.1));
    }
}