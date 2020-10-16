package ch22.ex01;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FloatArrayTableTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final String ls = System.lineSeparator();
    private final String fs = File.separator;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void showTableの正常系() {
        final FloatArrayTable floatArrayTable = new FloatArrayTable();
        final float[] floats = {1.23f, -456.789f, 0.1f, 23456f, 7.8901234f, 5f, 6.7f, 890f};
        floatArrayTable.showTable(floats, 3);

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "|     1.2300 |  -456.7890 |     0.1000 |"+ls
                +"| 23456.0000 |     7.8901 |     5.0000 |"+ls
                +"|     6.7000 |   890.0000 |" + ls;

        assertThat(outContent.toString(), is(expected));
    }

}