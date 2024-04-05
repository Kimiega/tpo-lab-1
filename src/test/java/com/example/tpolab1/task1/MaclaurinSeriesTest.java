package com.example.tpolab1.task1;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class MaclaurinSeriesTest {

    private static final int CONST_N = 100;
    private MaclaurinSeries ms;
    @BeforeEach
    void setUp() {
        ms = new MaclaurinSeries();
    }
    @ParameterizedTest(name = "sec({0}) is NaN")
    @ValueSource(doubles = {
            Math.PI/2,
            -Math.PI/2,
            Math.PI + Math.PI/2,
            -(Math.PI + Math.PI/2),
            2 * Math.PI + Math.PI/2,
            -(2 * Math.PI + Math.PI/2)
    })
    void testIndeterminateResults(double x) {
        double a = ms.sec(x, CONST_N);
        assertEquals(Double.NaN, a);
    }

    @Test
    @DisplayName("Invalid x")
    void testInvalidX() {
        assertAll(()->{
            assertThrowsExactly(ArithmeticException.class, () -> ms.sec(Double.NaN, CONST_N));
            assertThrowsExactly(ArithmeticException.class, () -> ms.sec(Double.POSITIVE_INFINITY, CONST_N));
            assertThrowsExactly(ArithmeticException.class, () -> ms.sec(Double.NEGATIVE_INFINITY, CONST_N));
        });
    }

    @ParameterizedTest(name = "sec({0})")
    @CsvFileSource(resources = {"/test_sec_X.csv"}, delimiterString = ";")
    void testClassicX(String name, double x, double res) {
        double a = ms.sec(x, CONST_N);
        assertEquals(res, a, 0.0000000001);
    }

    @Test
    @Disabled
    void testBoundaryHalfPi(){
        assertAll(()->{
            assertEquals(1255.76598966, ms.sec(1.57, 4000), 25);
        });
    }
}
