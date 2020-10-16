package ch23.ex03;

import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CommandExecutorTest {
    private PrintStream defaultPrintStream;
    private ByteArrayOutputStream byteArrayOutputStream;
    private final String ls = System.lineSeparator();

    @BeforeEach
    public void setUp() {
        defaultPrintStream = System.out;
        byteArrayOutputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(new BufferedOutputStream(byteArrayOutputStream)));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(defaultPrintStream);
    }

    @Test
    public void execの結果を表示() throws IOException, InterruptedException {
        CommandExecutor.main(new String[]{"Runtime", "java", "--version"});

        System.out.flush();
        final String actual = byteArrayOutputStream.toString();
        assertThat(actual, containsString("(TM) SE Runtime Environment"));
        assertThat(actual, new IsNot(containsString("Java HotSpot(TM)")));
    }

    @Test
    public void 引数が足りないと例外() {
        try {
            CommandExecutor.main(new String[]{});
        } catch (Exception expected) {
            assertThat(expected.getMessage(), is("More than two arguments are needed!"));
        }

        try {
            CommandExecutor.main(new String[]{"dummy"});
        } catch (Exception expected) {
            assertThat(expected.getMessage(), is("More than two arguments are needed!"));
        }
    }

    @Test
    public void コマンドが存在しないと例外() {
        try {
            CommandExecutor.main(new String[]{"foo", "bar"});
        } catch (Exception expected) {
            assertThat(expected.getMessage(), is("Cannot run program \"bar\": error=2, No such file or directory"));
        }
    }
}
