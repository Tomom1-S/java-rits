package ch05.ex04;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CalTest {

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
    public void Dec2020のカレンダー() {
        Cal.main(new String[]{"12", "2020"});

        // 標準出力の内容を取得
        System.out.flush();
        final String expected =
                "    1  2  3  4  5  6" + ls
                        + " 7  8  9 10 11 12 13" + ls
                        + "14 15 16 17 18 19 20" + ls
                        + "21 22 23 24 25 26 27" + ls
                        + "28 29 30 31 " + ls;
        assertThat(outContent.toString(), is(expected));
    }

}