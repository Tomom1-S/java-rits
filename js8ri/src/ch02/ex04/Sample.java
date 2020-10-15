package ch02.ex04;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Sample {
    public static void main(final String[] args) {
        final int[] values = {1, 4, 9, 16};

        Stream<int[]> stream = Stream.of(values);
        stream.forEach(System.out::println);

        IntStream intStream = IntStream.of(values);
        intStream.forEach(System.out::println);
    }
}
