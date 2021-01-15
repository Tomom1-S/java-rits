package ch08.ex01;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UnsignedIntCalculatorTest {
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
    public void addの正常系() {
        final String actual = UnsignedIntCalculator.add(Integer.MAX_VALUE, Integer.MAX_VALUE + 1);
        assertThat(actual, is("4294967295"));
    }

    @Test
    public void addでオーバーフローとなると間違った結果となる() {
        final String actual = UnsignedIntCalculator.add(Integer.MAX_VALUE + 1, Integer.MAX_VALUE + 1);
        assertThat(actual, is("0"));
    }

    @Test
    public void subtractの正常系() {
        final String actual = UnsignedIntCalculator.subtract(Integer.MAX_VALUE + Integer.MAX_VALUE + 1, Integer.MAX_VALUE);
        assertThat(actual, is("2147483648"));
    }

    @Test
    public void subtractでオーバーフローとなると間違った結果となる() {
        final String actual = UnsignedIntCalculator.subtract(Integer.MAX_VALUE, Integer.MAX_VALUE + 1);
        assertThat(actual, is("4294967295"));
    }

    @Test
    public void divideの正常系() {
        final String[] actual = UnsignedIntCalculator.divide(Integer.MAX_VALUE + Integer.MAX_VALUE + 1, 8);
        // {商, 余り}
        assertThat(actual, is(new String[]{"536870911", "7"}));
    }

    @Test
    public void divideでオーバーフローとなると間違った結果となる() {
        final String[] actual = UnsignedIntCalculator.divide(Integer.MAX_VALUE + Integer.MAX_VALUE + 1, 1);
        // {商, 余り}
        assertThat(actual, is(new String[]{"-1", "0"}));
    }

    @Test
    public void compareで等しい2値を比較する() {
        final int actual = UnsignedIntCalculator.compare(Integer.MAX_VALUE + 1, Integer.MAX_VALUE + 1);
        assertThat(actual, is(0));
    }

    @Test
    public void compareで異なる2値を比較する() {
        final int actual1 = UnsignedIntCalculator.compare(Integer.MAX_VALUE - 1, Integer.MAX_VALUE);
        assertThat(actual1, is(-1));

        final int actual2 = UnsignedIntCalculator.compare(Integer.MAX_VALUE, Integer.MAX_VALUE - 1);
        assertThat(actual2, is(1));
    }

    @Test
    public void compareでオーバーフローとなると間違った結果となる() {
        final int actual = UnsignedIntCalculator.compare(Integer.MAX_VALUE, -1);
        assertThat(actual, is(-1));
    }
}