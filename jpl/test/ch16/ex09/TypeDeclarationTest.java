package ch16.ex09;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TypeDeclarationTest {

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
    public void testInteger() {
        try {
            TypeDeclaration.main(new String[]{"ch16.ex09.Sample"});
        } catch (Exception e) {
            e.printStackTrace();
        }

        final String expected =
                "public class Sample {" + ls
                        + "    public int id;" + ls
                        + "    public String name;" + ls
                        + "    private static int nextId;" + ls
                        + "    public final int PUB_INT;" + ls
                        + "    private final int PVT_INT;" + ls
                        + ls
                        + "    Sample() {...};" + ls
                        + "    public Sample(String arg0) {...};" + ls
                        + ls
                        + "    public String toString() {...};" + ls
                        + "    public String getName() {...};" + ls
                        + "    public void setName(String arg0) {...};" + ls
                        + "    public int addNumbers(int arg0, int arg1) {...};" + ls
                        + "    public int addNumbers(List arg0) {...};" + ls
                        + "    public int addNumbers(int arg0, int arg1, int arg2) {...};" + ls
                        + "    int addPositiveNumbers(int arg0, int arg1) {...};" + ls
                        + "    private int multiplyNumbers(int arg0, int arg1) {...};" + ls
                        + "}" + ls;

        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void クラスが存在しないと例外() {
        try {
            TypeDeclaration.main(new String[]{"java.foo.bar"});
        } catch (Exception expected) {
            assertThat(expected.getMessage(), is("java.lang.ClassNotFoundException: java.foo.bar"));
        }

    }

    @Test
    public void 引数が足りないと例外() {
        try {
            TypeDeclaration.main(new String[]{});
        } catch (Exception expected) {
            assertThat(expected.getMessage(), is("Only one argument is needed!"));
        }
    }

    @Test
    public void 引数が多すぎると例外() {
        try {
            TypeDeclaration.main(new String[]{"foo", "bar"});
        } catch (Exception expected) {
            assertThat(expected.getMessage(), is("Only one argument is needed!"));
        }
    }

}