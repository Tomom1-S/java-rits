package ch08.ex14;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class NonNullSampleTest {
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
    public void 全ての引数がNonNullならば何も表示されない() {
        NonNullSample.main(new String[]{"one", "two", "three"});

        System.out.flush();
        final String expected = "";
        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void 一つ目の引数がNullのとき例外が発生する() {
        try {
            NonNullSample.main(new String[]{null, "two", "three"});
        } catch (final NullPointerException exception) {
            assertNull(exception.getMessage());
        }
    }

    @Test
    public void 二つ目の引数がNullのとき例外が発生する() {
        try {
            NonNullSample.main(new String[]{"one", null, "three"});
        } catch (final NullPointerException exception) {
            assertThat(exception.getMessage(),
                    is("2nd argument is null"));
        }
    }

    @Test
    public void 三つ目の引数がNullのとき例外が発生する() throws Exception {
        try {
            NonNullSample.main(new String[]{"one", "two", null});
        } catch (final NullPointerException exception) {
            System.out.flush();
            final String expected = "3rd argument is null";
            assertThat(outContent.toString(), is(expected + ls));

            assertThat(exception.getMessage(),
                    is(expected));
        }
    }


}