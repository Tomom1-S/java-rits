package ch02.ex05;

import java.util.stream.Stream;

public class Sample {
    public static void main(final String[] args) {
        final long a = 25214903917L;
        final long c = 11;
        final long m = (long) Math.pow(2, 48);
        Stream<Long> stream = Stream.iterate(0L, x -> (a * x + c) % m).skip(1);
        // x_0 を除くため skip

        stream.limit(10).forEach(System.out::println);
    }
}
