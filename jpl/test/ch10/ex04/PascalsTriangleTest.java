package ch10.ex04;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PascalsTriangleTest {

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
    public void 深さを指定しないと深さ12のパスカルの三角形が表示される() {
        PascalsTriangle.main(new String[]{});
        // 標準出力の内容を取得
        System.out.flush();

        // 期待値を設定
        final String expected = "[1]" + System.lineSeparator()
                + "[1, 1]" + System.lineSeparator()
                + "[1, 2, 1]" + System.lineSeparator()
                + "[1, 3, 3, 1]" + System.lineSeparator()
                + "[1, 4, 6, 4, 1]" + System.lineSeparator()
                + "[1, 5, 10, 10, 5, 1]" + System.lineSeparator()
                + "[1, 6, 15, 20, 15, 6, 1]" + System.lineSeparator()
                + "[1, 7, 21, 35, 35, 21, 7, 1]" + System.lineSeparator()
                + "[1, 8, 28, 56, 70, 56, 28, 8, 1]" + System.lineSeparator()
                + "[1, 9, 36, 84, 126, 126, 84, 36, 9, 1]" + System.lineSeparator()
                + "[1, 10, 45, 120, 210, 252, 210, 120, 45, 10, 1]" + System.lineSeparator()
                + "[1, 11, 55, 165, 330, 462, 462, 330, 165, 55, 11, 1]" + System.lineSeparator();

        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void 指定された深さのパスカルの三角形が表示される() {
        PascalsTriangle.main(new String[]{"3"});
        // 標準出力の内容を取得
        System.out.flush();

        // 期待値を設定
        final String expected = "[1]" + System.lineSeparator()
                + "[1, 1]" + System.lineSeparator()
                + "[1, 2, 1]" + System.lineSeparator();

        assertThat(outContent.toString(), is(expected));
    }

}