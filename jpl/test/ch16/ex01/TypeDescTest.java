package ch16.ex01;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TypeDescTest {

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
    public void testHashMap() {
        TypeDesc.main(new String[] {"java.util.HashMap"});

        final String expected = "class java.util.HashMap<K, V, \b\b>" + ls
                + " implements java.util.Map<K, V, \b\b>" + ls
                + " implements java.lang.Cloneable" + ls
                + " implements java.io.Serializable" + ls
                + " extends java.util.AbstractMap<K, V, \b\b>" + ls
                + "  implements java.util.Map<K, V, \b\b>" + ls;

        assertThat(outContent.toString(), is(expected));
    }

}