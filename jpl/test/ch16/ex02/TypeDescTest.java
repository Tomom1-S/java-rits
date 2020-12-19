package ch16.ex02;

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
    public void testOuter() {
        TypeDesc.main(new String[] {"ch16.ex02.Outer$PublicInner"});

        final String expected = "class ch16.ex02.Outer.PublicInner" + ls
                + " is nested in ch16.ex02.Outer" + ls;

        assertThat(outContent.toString(), is(expected));
    }

    // 柴田さん：Java 標準のクラスでテストする例
    @Test
    public void testMapEntry() {
        TypeDesc.main(new String[] {"java.util.Map$Entry"});

        final String expected = "interface java.util.Map.Entry<K, V, \b\b>" + ls
                + " is nested in java.util.Map" + ls;

        assertThat(outContent.toString(), is(expected));
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