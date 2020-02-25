package ch20.ex02;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TranslateByteFilterTest {

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
    public void translateByteの正常系() {
        try {
            TranslateByteFilter.main(new String[]{"abracadabra!", "b", "B"});
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "aBracadaBra!" + ls;

        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void 変換する必要がない() {
        try {
            TranslateByteFilter.main(new String[]{"abracadabra!", "e", "E"});
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "abracadabra!" + ls;

        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void 引数が足りないと例外() {
        try {
            TranslateByteFilter.main(new String[]{});
        } catch (IllegalArgumentException expected) {
            assertThat(expected.getMessage(), is("2 arguments are needed!"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}