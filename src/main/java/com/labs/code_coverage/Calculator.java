package com.labs.code_coverage;

public class Calculator {

    private Calculator() {
    }

    public static void printSum(int a, int b) {
        int result = a + b; // (1)

        if (result > 0) { // (2)
            System.out.println("result is positive."); // (3)
        } else if (result < 0){ // (4)
            System.out.println("result is negative."); // (5)
        }

        // do nothing
    }



}
