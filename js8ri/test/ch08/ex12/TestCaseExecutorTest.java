package ch08.ex12;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestCaseExecutorTest {
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
    public void testでテストが成功する() throws ClassNotFoundException {
        TestCaseExecutor.test("ch08.ex12.SuccessSample");

        System.out.flush();
        final String expected = "addOne-----" + ls
                + "Parameter: 1" + ls
                + "Expected value: 2" + ls
                + "Result: succeeded" + ls
                + ls
                + "addOne-----" + ls
                + "Parameter: 3" + ls
                + "Expected value: 4" + ls
                + "Result: succeeded" + ls
                + ls;
        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void testでテストが失敗する() throws ClassNotFoundException {
        TestCaseExecutor.test("ch08.ex12.FailureSample");

        System.out.flush();
        final String expected = "addOne-----" + ls
                + "Parameter: 1" + ls
                + "Expected value: 2" + ls
                + "Result: failed (actual: 3)" + ls
                + ls
                + "addOne-----" + ls
                + "Parameter: -3" + ls
                + "Expected value: -1" + ls
                + "Result: failed (exception: java.lang.reflect.InvocationTargetException)" + ls
                + ls;
        assertThat(outContent.toString(), is(expected));
    }

}