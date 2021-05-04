package utils;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class FileHandler {
    public static String readFile(final String path) throws IOException {
        final File file = new File(path);
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

    public static String getResourceAsString(final Class<?> cls, final String path) {
        try (final var is = cls.getResourceAsStream(path)) {
            return new BufferedReader(new InputStreamReader(is))
                    .lines().collect(Collectors.joining("\n"));
        } catch (final IOException ex) {
            throw new RuntimeException(ex); // FIXME (mitts): Use custom exception
        }
    }
}
