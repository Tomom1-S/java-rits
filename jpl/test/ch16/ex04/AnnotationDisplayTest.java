package ch16.ex04;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AnnotationDisplayTest {

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
    public void リテンションポリシーがRUNTIMEのアノテーションのみ表示される() {
        AnnotationDisplay.main(new String[]{"ch16.ex04.Foo"});

        final String expected = "class ch16.ex04.Foo" + ls
                + " @ch16.ex04.RuntimeAnnotation(foo=\"FOO\", bar=\"BAR\", revision=@ch16.ex04.Revision(major=1, minor=0))" + ls;

        assertThat(outContent.toString(), is(expected));
    }

}