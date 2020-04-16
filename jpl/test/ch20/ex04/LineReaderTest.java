package ch20.ex04;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LineReaderTest {

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
    public void readLineメソッドで1行ずつ読み込む() throws IOException {
        StringReader src = new StringReader("foo\nbar\nbaz");
        LineReader reader = new LineReader(src);

        final String[] expected = new String[] {"foo", "bar", "baz"};
        for (String ex : expected) {
            final String actual = reader.readLine();
            assertThat(actual, is(ex));
        }
    }

    @Test
    public void 空行が含まれる文字列をreadLineメソッドで1行ずつ読み込む() throws IOException {
        StringReader src = new StringReader("foo\nbar\n\nbaz");
        LineReader reader = new LineReader(src);

        final String[] expected = new String[] {"foo", "bar", "", "baz"};
        for (String ex : expected) {
            final String actual = reader.readLine();
            assertThat(actual, is(ex));
        }
    }

}