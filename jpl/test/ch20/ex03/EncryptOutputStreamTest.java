package ch20.ex03;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class EncryptOutputStreamTest {

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
            EncryptOutputStream.main(new String[]{"1010", "0101"});
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "1111" + ls;

        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void キーより長い文字列を変換() {
        try {
            EncryptOutputStream.main(new String[]{"10101010101", "0101"});
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "11111111111" + ls;

        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void キーより短い文字列を変換() {
        try {
            EncryptOutputStream.main(new String[]{"1010", "010101010"});
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "1111" + ls;

        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void 引数が足りないと例外() {
        try {
            EncryptOutputStream.main(new String[]{});
        } catch (IllegalArgumentException expected) {
            assertThat(expected.getMessage(), is("2 arguments are needed!"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}