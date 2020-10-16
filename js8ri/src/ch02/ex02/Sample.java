package ch02.ex02;

import java.util.List;
import java.util.stream.Stream;

public class Sample {
    public void limitStream(final List<String> words) {
        words.stream()
                .filter(w -> {
                    System.out.println("Filtering: " + w);
                    return w.length() > 4;
                })
                .limit(5)
                .forEach(System.out::println);

    }
}
