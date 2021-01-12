package ch09.ex05;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReverseReader {
    public static void main(final String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Source path & target path are needed");
        }

        final Path srcPath = Paths.get(args[0]);
        try {
            final byte[] bytes = Files.readAllBytes(srcPath);
            final String content = new String(bytes, StandardCharsets.UTF_8);
            final String reversed = new StringBuilder(content).reverse().toString();

            final Path tgtPath = Paths.get(args[1]);
            Files.write(tgtPath, reversed.getBytes(StandardCharsets.UTF_8));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
