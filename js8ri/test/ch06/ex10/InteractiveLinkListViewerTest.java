package ch06.ex10;

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

public class InteractiveLinkListViewerTest {

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
        inContent = new ByteArrayInputStream("http://example.com".getBytes());
        System.setIn(inContent);
        InteractiveLinkListViewer.main(new String[]{"debug"});

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "Enter URL: https://www.iana.org/domains/example" + ls;
        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void 不正なURLのときは何も表示されない() {
        inContent = new ByteArrayInputStream("foo".getBytes());
        System.setIn(inContent);
        InteractiveLinkListViewer.main(new String[]{"debug"});

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "Enter URL: ";
        assertThat(outContent.toString(), is(expected));
    }
}