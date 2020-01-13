package ch16.ex03;

import ch03.ex07.Attr;
import ch03.ex07.ColorAttr;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ClassContentsTest {

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
    public void testAttr() {
        ClassContents.main(new String[]{"ch03.ex07.Attr"});

        final String expected = "class ch03.ex07.Attr" + ls
                + " private Object ch03.ex07.Attr.value" + ls
                + " private final String ch03.ex07.Attr.name" + ls
                + " public ch03.ex07.Attr(String)" + ls
                + " public ch03.ex07.Attr(String,java.lang.Object)" + ls
                + " public Object ch03.ex07.Attr.setValue(java.lang.Object)" + ls
                + " public Object ch03.ex07.Attr.getValue()" + ls
                + " public String ch03.ex07.Attr.toString()" + ls
                + " public String ch03.ex07.Attr.getName()" + ls;

        assertThat(outContent.toString(), is(expected));
    }

}