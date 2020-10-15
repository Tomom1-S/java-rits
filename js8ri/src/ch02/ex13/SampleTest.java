package ch02.ex13;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
        Sample.improvedGoodCount(stream);

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "0 characters: 4" + ls
                + "1 characters: 6" + ls
                + "2 characters: 2" + ls
                + "3 characters: 3" + ls
                + "5 characters: 1" + ls
                + "6 characters: 2" + ls
                + "11 characters: 1" + ls
                + "12 characters: 1" + ls;
        assertThat(outContent.toString(), is(expected));
    }

}