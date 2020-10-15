package ch02.ex12;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SampleTest {

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
    public void goodCountの正常系() {
        Stream<String> stream = Stream.of(
                "", "", "", "",
                "a", "b", "c", "d", "e", "f",
                "ab", "bc",
                "abc", "bcd", "cde",
                "abcde",
                "abcdef", "bcdefg",
                "abcdefghijk",
                "abcdefghijkl"
        );
        Sample.goodCount(stream);

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "[4, 6, 2, 3, 0, 1, 2, 0, 0, 0, 0, 1]" + ls;
        assertThat(outContent.toString(), is(expected));
    }

}