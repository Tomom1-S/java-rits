package ch20.ex01;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TranslateByteExecutorTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String ls = System.lineSeparator();

    private final InputStream sysInBackup = System.in;
    private ByteArrayInputStream inContent;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @BeforeEach
    public void init() {
        System.setIn(sysInBackup);  // 標準入力の内容を初期化
    }

    @Test
    public void translateByteの正常系() {
        inContent = new ByteArrayInputStream("abracadabra!".getBytes());
        System.setIn(inContent);

        TranslateByteExecutor.main(new String[]{"b", "B"});

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "aBracadaBra!";

        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void 変換する必要がない() {
        inContent = new ByteArrayInputStream("abracadabra!".getBytes());
        System.setIn(inContent);

        TranslateByteExecutor.main(new String[]{"e", "E"});

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "abracadabra!";

        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void 引数が足りないと例外() {
        try {
            TranslateByteExecutor.main(new String[]{});
        } catch (IllegalArgumentException expected) {
            assertThat(expected.getMessage(), is("2 arguments are needed!"));
        }
    }

}