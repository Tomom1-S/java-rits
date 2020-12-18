package ch06.ex10;

import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ParserTest {
    @Test
    public void getLinksの正常系() throws IOException {
        final String contents = new String(
                Files.readAllBytes(Paths.get("test/ch06/ex10/sample.html")),
                StandardCharsets.UTF_8
        );
        final List<URL> actual = Parser.getLinks(contents);

        final List<URL> expected = Arrays.asList(
                new URL("https://www.foo.com/"),
                new URL("http://www.bar.com/"),
                new URL("https://www.baz.com/")
        );
        assertThat(actual.size(), is(expected.size()));
        for (int i = 0; i < actual.size(); i++) {
            assertThat(actual.get(i), is(expected.get(i)));
        }
    }

}