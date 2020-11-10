package ch03.ex01;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DelayedLoggerTest {

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
    public void FINESTのときのlogIf() {
        final int i = 10;
        final int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        DelayedLogger.logIf(Level.FINEST, () -> {
            System.out.println("condition");
            return i == 10;
        }, () -> {
            System.out.println("supplier");
            return "a[10] = " + a[10];
        });

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "";
        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void FINESTでないかつconditionがtrueのときのlogIf() {
        final int i = 10;
        final int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        DelayedLogger.logIf(Level.INFO, () -> {
            System.out.println("condition");
            return i == 10;
        }, () -> {
            System.out.println("supplier");
            return "a[10] = " + a[10];
        });

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "condition" + ls + "supplier" + ls + "a[10] = 10" + ls;
        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void FINESTでないかつconditionがfalseのときのlogIf() {
        final int i = 10;
        final int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        DelayedLogger.logIf(Level.INFO, () -> {
            System.out.println("condition");
            return i == 1;
        }, () -> {
            System.out.println("supplier");
            return "a[10] = " + a[10];
        });

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "condition" + ls;
        assertThat(outContent.toString(), is(expected));
    }

}