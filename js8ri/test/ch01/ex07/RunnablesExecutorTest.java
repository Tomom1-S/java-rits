package ch01.ex07;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RunnablesExecutorTest {

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
    public void showDirectoriesの正常系() {
        RunnablesExecutor.main(new String[]{});

        // 標準出力の内容を取得
        System.out.flush();

        final String expected = "1st runner" + ls
                + "2nd runner" + ls;
        assertThat(outContent.toString(), is(expected));
    }

}