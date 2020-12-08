package ch06.ex10;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LinkListViewerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String ls = System.lineSeparator();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void mainの正常系() {
        try {
            LinkListViewer.main(new String[]{"http://example.com"});
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        System.out.flush();
        final String expected = "https://www.iana.org/domains/example" + ls;
        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void blockingReadPageの正常系() {
        String actual = "";
        try {
            actual = LinkListViewer.blockingReadPage(new URL("http://example.com"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            final String expected = new String(
                    Files.readAllBytes(Paths.get("test/ch06/ex10/example.html")),
                    StandardCharsets.UTF_8
            );
            // スペースは無視して比較
            assertThat(actual.replaceAll("\\s+", ""),
                    is(expected.replaceAll("\\s+", "")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}