package ch20.ex04;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

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
    public void キーと同じ長さの文字列を変換() {
        try {
            LineReader.main(new String[]{"foo\nbar\nbaz"});
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "foo" + ls + "bar" + ls + "baz";

        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void 引数が足りないと例外() {
        try {
            LineReader.main(new String[]{});
        } catch (IllegalArgumentException expected) {
            assertThat(expected.getMessage(), is("1 argument is needed!"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}