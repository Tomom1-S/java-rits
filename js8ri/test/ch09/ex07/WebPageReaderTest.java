package ch09.ex07;

import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class WebPageReaderTest {
    final String ansPath = "test/ch09/ex07/expected.txt";

    @Test
    public void 出力先パスを指定したときの正常系() {
        final String path = "test/ch09/ex07/out.html";
        WebPageReader.main(new String[]{"http://example.com", path});

        try {
            final String actual = new String(
                    Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);

            final String expected = new String(
                    Files.readAllBytes(Paths.get(ansPath)), StandardCharsets.UTF_8);
            assertThat(actual, is(expected));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void 出力先パスを指定しないときの正常系() {
        final String path = "src/ch09/ex07/result/out.html";
        WebPageReader.main(new String[]{"http://example.com"});

        try {
            final String actual = new String(
                    Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);

            final String expected = new String(
                    Files.readAllBytes(Paths.get(ansPath)), StandardCharsets.UTF_8);
            assertThat(actual, is(expected));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}