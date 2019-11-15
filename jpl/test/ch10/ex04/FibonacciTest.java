package ch10.ex04;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FibonacciTest {

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
    public void フィボナッチ数列の最初の10個が表示される() {
        Fibonacci.main(new String[] {});
        // 標準出力の内容を取得
        System.out.flush();

        // 期待値を設定
        final String expected = "フィボナッチ数列の最初の10個" + System.lineSeparator()
                + "[1, 1, 2, 3, 5, 8, 13, 21, 34, 55]" + System.lineSeparator();

        assertThat(outContent.toString(), is(expected));
    }

}