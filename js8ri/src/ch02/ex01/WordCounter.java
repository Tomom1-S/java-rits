package ch02.ex01;

import ch01.ex11.S;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class WordCounter {
    public List<String> initList(final String path) throws IOException {
        String contents = new String(
                Files.readAllBytes(Paths.get(path)),
                StandardCharsets.UTF_8
        );
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        return words;
    }

    public int forCount(final List<String> words) {
        int count = 0;
        for (String w : words) {
            if (w.length() > 12) {
                count++;
            }
        }
        return count;
    }

    public long streamCount(final List<String> words) {
        long count = words
                .stream()
                .filter(w -> w.length() > 12)
                .count();
        return count;
    }

    public long parallelCount(final List<String> words) {
        long count = words
                .parallelStream()
                .filter(w -> w.length() > 12)
                .count();
        return count;
    }

    public int threadCount(final List<String> words) {
        AtomicInteger count = new AtomicInteger(0);
        for (String word : words) {
            new Thread(() -> {
                if (word.length() > 12) {
                    count.getAndIncrement();
                }
            }).start();
        }
        return count.get();
    }
}
