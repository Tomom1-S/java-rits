package ch05.ex01;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ProgrammersDayTest {

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
    public void 引数なしのmain() {
        ProgrammersDay.main(new String[]{});

        // 標準出力の内容を取得
        System.out.flush();
        assertThat(outContent.toString(),
                is("Programmer's Day: 2020-09-12" + ls));
    }

    @Test
    public void 引数ありのmain() {
        ProgrammersDay.main(new String[]{"2014"});

        // 標準出力の内容を取得
        System.out.flush();
        assertThat(outContent.toString(),
                is("Programmer's Day: 2014-09-13" + ls));
    }

}