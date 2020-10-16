package ch02.ex13;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;

public class Sample {
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

    public static void improvedGoodCount(final Stream<String> words) {
        Map<Integer, Long> lengths = words.collect(
                Collectors.groupingBy(String::length, counting())
        );
        lengths.entrySet().forEach(e -> {
            System.out.println(e.getKey() + " characters: " + e.getValue());
        });
    }
}
