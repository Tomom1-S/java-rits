package ch09.ex06;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class ReverseReader {
    public static void main(final String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Source path & target path are needed");
        }

        final Path srcPath = Paths.get(args[0]);
        try {
            final List<String> lines = Files.readAllLines(srcPath);
            Collections.reverse(lines);

            final Path tgtPath = Paths.get(args[1]);
            Files.write(tgtPath, lines);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
