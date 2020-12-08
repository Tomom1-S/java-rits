package ch06.ex06;

import ch06.ex05.MyFileReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.function.Function;

public class WordMapper {
    public static ConcurrentHashMap<String, Set<File>> create(final List<String> paths) {
        final List<File> files = new ArrayList<>();
        paths.forEach(path -> {
            final File file = new File(path);
            files.add(file);
        });

        final ConcurrentHashMap<String, Set<File>> map = new ConcurrentHashMap<>();
        // ファイルの個数分スレッドを立ち上げて終了を待つ
        final CountDownLatch latch = new CountDownLatch(files.size());

        final ExecutorService pool = Executors.newCachedThreadPool();
        for (final File file : files) {
            pool.submit(() -> {
                try (final MyFileReader reader = new MyFileReader(file)) {
                    String word;
                    while ((word = reader.getWord()) != null) {
                        map.computeIfAbsent(word, _f -> ConcurrentHashMap.newKeySet())
                                .add(file);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            latch.countDown();
        }

        pool.shutdown();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return map;
    }
}
