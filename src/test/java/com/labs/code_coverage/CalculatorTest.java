package com.labs.code_coverage;

import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    void a_one_b_one() {
        int a = 1, b = 1;
        Calculator.printSum(a, b);
    }

    @Test
    void a_minus_one_b_minus_one() {
        int a = -1, b = -1;
        Calculator.printSum(a, b);
    }

}