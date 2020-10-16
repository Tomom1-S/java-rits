package ch01.ex01;

import java.util.Arrays;

public class Sample {
    public static void main(final String[] args) {
        System.out.println("main executed in Thread " + Thread.currentThread().getId());

        Arrays.sort(args, (first, second) -> {
                    System.out.println("Comparator executed in Thread " + Thread.currentThread().getName());
                    return Integer.compare(first.length(), second.length());
                }
        );

        System.out.println("Result: " + Arrays.toString(args));
    }
}
