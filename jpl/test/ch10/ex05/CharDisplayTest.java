package ch10.ex05;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CharDisplayTest {

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
    public void showCharsが指定した2つの文字列の間の文字を表示する() {
        CharDisplay cd = new CharDisplay();
        cd.showChars('a', 'X');
        // 標準出力の内容を取得
        System.out.flush();

        // 期待値を設定
        final String expected = "XYZ[\\]^_`a" + System.lineSeparator();

        assertThat(outContent.toString(), is(expected));
    }
}