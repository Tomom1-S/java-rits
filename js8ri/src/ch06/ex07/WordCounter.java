package ch06.ex07;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordCounter {
    ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
    final List<String> words;

    public WordCounter(final String path) {
        String contents = "";
        try {
            contents = new String(
                    Files.readAllBytes(Paths.get(path)),
                    StandardCharsets.UTF_8
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        words = Arrays.asList(contents.split("[\\P{L}]+"));
    }

    public ConcurrentHashMap<String, Long> countWords() {
        map = (ConcurrentHashMap<String, Long>) words.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toConcurrentMap(Function.identity(), v -> 1L, Long::sum));
        return map;
    }

    public Map.Entry<String, Long> getMostFrequently() {
        return map.reduceEntries(1,
                (e1, e2) -> e1.getValue() > e2.getValue() ? e1 : e2
        );
    }
}
