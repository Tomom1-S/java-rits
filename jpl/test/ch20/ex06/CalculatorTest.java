package ch20.ex06;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CalculatorTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String ls = System.lineSeparator();
    private final String fs = File.separator;

    private final File success = new File("test" + fs + "ch20" + fs + "ex06", "success.txt");
    private final File invalidName = new File("test" + fs + "ch20" + fs + "ex06", "invalidName.txt");
    private final File invalidOp = new File("test" + fs + "ch20" + fs + "ex06", "invalidOp.txt");
    private final File invalidValue = new File("test" + fs + "ch20" + fs + "ex06", "invalidValue.txt");

    private final File invalidOpOrder = new File("test" + fs + "ch20" + fs + "ex06", "invalidOpOrder.txt");
    private final File invalidValueOrder = new File("test" + fs + "ch20" + fs + "ex06", "invalidValueOrder.txt");

    Calculator calculator = new Calculator();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void 正常系() {
        try {
            calculator.main(new String[]{success.getAbsolutePath()});
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "X = 123.45600" + ls
                +"Y = 123.45670"+ls
                +"Z = 0.23000"+ ls;

        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void 引数が足りないと例外() {
        try {
            calculator.main(new String[]{});
        } catch (Exception expected) {
            assertThat(expected.getMessage(), is("Argument is needed"));
        }
    }

    @Test
    public void nameが不正だと例外() {
        try {
            calculator.main(new String[]{invalidName.getAbsolutePath()});
        } catch (Exception expected) {
            assertThat(expected.getMessage(), is("Invalid contents: foo"));
        }
    }

    @Test
    public void opが不正だと例外() {
        try {
            calculator.main(new String[]{invalidOp.getAbsolutePath()});
        } catch (Exception expected) {
            assertThat(expected.getMessage(), is("Invalid contents: *"));
        }
    }

    @Test
    public void valueが不正だと例外() {
        try {
            calculator.main(new String[]{invalidValue.getAbsolutePath()});
        } catch (Exception expected) {
            assertThat(expected.getMessage(), is("Invalid contents: Xs"));
        }
    }

    @Test
    public void opの位置が不正だと例外() {
        try {
            calculator.main(new String[]{invalidOpOrder.getAbsolutePath()});
        } catch (Exception expected) {
            assertThat(expected.getMessage(), is("Invalid contents: -"));
        }
    }

    @Test
    public void valueの位置が不正だと例外() {
        try {
            calculator.main(new String[]{invalidValueOrder.getAbsolutePath()});
        } catch (Exception expected) {
            assertThat(expected.getMessage(), is("Invalid contents: 42.0"));
        }
    }
}