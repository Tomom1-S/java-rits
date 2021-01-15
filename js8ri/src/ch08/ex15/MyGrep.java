package ch08.ex15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class MyGrep {
    public static void main(final String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("How to use: MyGrep <regular expression> <path>");
        }

        final Pattern pattern = Pattern.compile(args[0]);
        final Path path = Paths.get(args[1]);

        try (final Stream<String> stream = Files.lines(path)) {
            stream.filter(pattern.asPredicate())
                    .forEach(System.out::println);
        } catch (final IOException e) {
            e.printStackTrace();
        }

    }
}
