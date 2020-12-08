package ch06.ex09;

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
    public void フィボナッチ数列を10個表示() {
        Fibonacci.create(10);

        System.out.flush();
        final String expected = "1" + ls + "2" + ls + "3" + ls
                + "5" + ls + "8" + ls + "13" + ls + "21" + ls
                + "34" + ls + "55" + ls + "89" + ls;
        assertThat(outContent.toString(), is(expected));
    }
}