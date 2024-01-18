package com.labs.code_coverage;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @Test
    void test() {
        System.out.println(doubleToInt(0.09));
    }

    @Test
    void time() {
        LocalDateTime x = LocalDateTime.of(0 ,0, 0, 0, 0);
        System.out.println(x.toLocalTime());
    }

    /**
     * 100을 곱해서 int 형으로 리턴
     * ex) 99.126 -> 9912
     */
    static int doubleToInt(double score) {
        return BigDecimal.valueOf(score)
                .multiply(new BigDecimal(100))
                .intValue();
    }

}