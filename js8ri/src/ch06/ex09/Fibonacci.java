package ch06.ex09;

import java.util.Arrays;

public class Fibonacci {
    public static void create(final int n) {
        final Matrix f = new Matrix(1, 1, 1, 0);
        final Matrix[] array = new Matrix[n];

        Arrays.parallelSetAll(array, _value -> f);
        Arrays.parallelPrefix(array, Matrix::multiply);
        Arrays.stream(array).forEach(m -> System.out.println(m.get(0, 0)));
    }
}
