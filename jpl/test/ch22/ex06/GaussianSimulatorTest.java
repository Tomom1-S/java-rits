package ch22.ex06;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GaussianSimulatorTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String ls = System.lineSeparator();
    private final String fs = File.separator;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void mainの正常系() {
        new GaussianSimulator().main(new String[]{});

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "-----" + ls
                + "-5.0|" + ls
                + "-4.5|" + ls
                + "-4.0|" + ls
                + "-3.5|" + ls
                + "-3.0|" + ls
                + "-2.5|***" + ls
                + "-2.0|***********" + ls
                + "-1.5|**************************" + ls
                + "-1.0|************************************************" + ls
                + "-0.5|*********************************************************************" + ls
                + " 0.0|******************************************************************************" + ls
                + " 0.5|*********************************************************************" + ls
                + " 1.0|************************************************" + ls
                + " 1.5|**************************" + ls
                + " 2.0|***********" + ls
                + " 2.5|***" + ls
                + " 3.0|" + ls
                + " 3.5|" + ls
                + " 4.0|" + ls
                + " 4.5|" + ls
                + "------" + ls;

        assertThat(outContent.toString(), is(expected));
    }

}