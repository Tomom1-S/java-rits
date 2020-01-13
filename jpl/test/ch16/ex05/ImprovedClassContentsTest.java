package ch16.ex05;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ImprovedClassContentsTest {

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
    public void testFoo() {
        ImprovedClassContents.main(new String[]{"ch16.ex04.Foo"});

        final String expected = "class ch16.ex04.Foo" + ls
                + " private final java.util.UUID ch16.ex04.Foo.uuid" + ls
                + "  @ch16.ex04.RuntimeAnnotation(foo=\"FOO\", bar=\"BAR\", revision=@ch16.ex04.Revision(major=1, minor=0))" + ls
                + " private final String ch16.ex04.Foo.name" + ls
                + "  @ch16.ex04.RuntimeAnnotation(foo=\"FOO\", bar=\"BAR\", revision=@ch16.ex04.Revision(major=1, minor=0))" + ls
                + " public ch16.ex04.Foo(String)" + ls
                + "  @ch16.ex04.RuntimeAnnotation(foo=\"FOO\", bar=\"BAR\", revision=@ch16.ex04.Revision(major=1, minor=0))" + ls
                + " public java.util.UUID ch16.ex04.Foo.getUuid()" + ls
                + "  @ch16.ex04.RuntimeAnnotation(foo=\"FOO\", bar=\"BAR\", revision=@ch16.ex04.Revision(major=1, minor=0))" + ls
                + " public String ch16.ex04.Foo.getName()" + ls
                + "  @ch16.ex04.RuntimeAnnotation(foo=\"FOO\", bar=\"BAR\", revision=@ch16.ex04.Revision(major=1, minor=0))" + ls
                + " public void ch16.ex04.Foo.showInfo()" + ls
                + "  @ch16.ex04.RuntimeAnnotation(foo=\"FOO\", bar=\"BAR\", revision=@ch16.ex04.Revision(major=1, minor=0))" + ls;

        assertThat(outContent.toString(), is(expected));
    }

}