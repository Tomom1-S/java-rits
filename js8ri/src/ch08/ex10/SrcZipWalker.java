package ch08.ex10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class SrcZipWalker {
    public static void main(final String[] _args) throws IOException {
        final Path srcPath = Paths.get("sample/src");
        final String[] keywords = new String[]{"transient", "volatile"};
        try (final Stream<Path> entries = Files.walk(srcPath)) {
            entries.sorted()
                    .filter(path -> path.toFile().isFile())
                    .filter(path -> path.toFile().getName().endsWith(".java"))
                    .filter(path -> hasKeywords(path, keywords))
                    .forEach(path ->
                            System.out.println(path.toFile().getPath().replace("sample/src/", "")));
        }

    }

    private static boolean hasKeywords(final Path path, final String[] keywords) {
        try (final Scanner scanner = new Scanner(path)) {
            while (scanner.hasNext()) {
                final String word = scanner.next();
                for (final String keyword : keywords) {
                    if (word.contains(keyword)) {
                        return true;
                    }
                }
            }
        } catch (
                final IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
