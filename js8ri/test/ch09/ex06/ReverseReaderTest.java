package ch09.ex06;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ReverseReaderTest {
    final String ls = System.lineSeparator();

    @Test
    public void mainの正常系() {
        final String path = "test/ch09/ex06";
        final String outPath = path + "/out.txt";
        ReverseReader.main(new String[]{path + "/in.txt", outPath});

        final byte[] bytes;
        try {
            bytes = Files.readAllBytes(Paths.get(outPath));
            final String actual = new String(bytes, StandardCharsets.UTF_8);

            final String expected = "These are files." + ls
                    + ls
                    + "This is not a folder." + ls
                    + ls
                    + "That is not a file." + ls
                    + "This is a file." + ls;
            assertThat(actual, is(expected));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}