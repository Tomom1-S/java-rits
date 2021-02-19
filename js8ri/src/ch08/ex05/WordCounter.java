package ch08.ex05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class WordCounter {
    public static void main(final String[] args) {
        String contents = "";
        try {
            contents = new String(Files.readAllBytes(
                    Paths.get("sample/AliceInWonderland.txt")
            ), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final String[] wordsArray = contents.split("[¥¥P{L}]+");
        final List<String> streamWords = Arrays.asList(wordsArray);

        System.out.println("ストリームの場合-----");
        long start = System.nanoTime();
        final long streamCount = streamWords.stream()
                .filter(w -> w.length() > 12)
                .count();
        System.out.println("処理時間: " + ((System.nanoTime() - start) / (10 ^ 6)) + " ms");
        System.out.println("結果: " + streamCount + " words");

        final List<String> lambdaWords = new ArrayList<>();
        Arrays.stream(wordsArray).forEach(s -> lambdaWords.add(s));

        System.out.println("ラムダ式の場合-----");
        start = System.nanoTime();
        // 柴田さん：元のリストを改善しているので性能比較としては不適切
        final AtomicLong lambdaCount = new AtomicLong();
        lambdaWords.forEach(s -> {
            if (s.length() > 12) {  // Predicate を使うともっとすっきり書けるかも
                lambdaCount.getAndIncrement();
            }
        });
        System.out.println("処理時間: " + ((System.nanoTime() - start) / (10 ^ 6)) + " ms");
        System.out.println("結果: " + lambdaCount.get() + " words");
    }
}
