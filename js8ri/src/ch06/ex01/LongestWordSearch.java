package ch06.ex01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class LongestWordSearch {
    public static void main(final String[] args) {
        String contents = "";
        try {
            contents = new String(
                    Files.readAllBytes(Paths.get(args[0])),
                    StandardCharsets.UTF_8
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String[] words = contents.split("[\\P{L}]+");

        final int MAX_COUNT = 500;
        final int threadNum = (int) Math.ceil(words.length / MAX_COUNT);

        final AtomicReference<String> longest = new AtomicReference<>("");
        final ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < threadNum; i++) {
            final int offset = i * MAX_COUNT;
            pool.submit(() -> {
                for (int j = offset; j < Math.min(offset + MAX_COUNT, words.length); j++) {
                    final String word = words[j];
                    // 最初に出てきた最大長の単語を保持
                    longest.updateAndGet(s -> word.length() > s.length() ? word : s);
                }
            });
        }

        pool.shutdown();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Longest word: " + longest);
    }
}
