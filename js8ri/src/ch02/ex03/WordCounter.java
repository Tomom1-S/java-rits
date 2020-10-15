package ch02.ex03;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class WordCounter {
    List<String> words;

    public WordCounter(final String path) {
        String contents = null;
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

    public static void main(final String[] args) {
        WordCounter counter = new WordCounter(args[0]);
        counter.streamCount();
        counter.parallelCount();
    }

    public long streamCount() {
        long start = System.nanoTime();

        long count = words
                .stream()
                .filter(w -> w.length() > 12)
                .count();

        System.out.println("streamCount's Time: " + (System.nanoTime() - start) / (10 ^ 6) + " msec");
        return count;
    }

    public long parallelCount() {
        long start = System.nanoTime();

        long count = words
                .parallelStream()
                .filter(w -> w.length() > 12)
                .count();

        System.out.println("parallelCount's Time: " + (System.nanoTime() - start) / (10 ^ 6) + " msec");
        return count;
    }

}
