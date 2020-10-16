package ch02.ex12;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Sample {
    public static void badCount(final Stream<String> words) {
        int[] shortWords = new int[12];
        words.parallel().forEach(
                s -> {
                    if (s.length() < 12) {
                        shortWords[s.length()]++;
                    }
                    // 競合状態
                }
        );
        System.out.println(Arrays.toString(shortWords));
    }

    public static void goodCount(final Stream<String> words) {
        AtomicInteger[] shortWords = new AtomicInteger[12];
        Arrays.setAll(shortWords, value -> new AtomicInteger(0));
        words.parallel().forEach(
                s -> {
                    if (s.length() < 12) {
                        shortWords[s.length()].getAndIncrement();
                    }
                }
        );
        System.out.println(Arrays.toString(shortWords));
    }
}
