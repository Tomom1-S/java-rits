package ch20.ex10;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class WordCounterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String ls = System.lineSeparator();
    private final String fs = File.separator;

    private final File success = new File("test" + fs + "ch20" + fs + "ex10", "HotCrossBuns.txt");
    private final File failure = new File("test" + fs + "ch20" + fs + "ex10", "failure.txt");

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void ファイルが見つかった() throws IOException {
        WordCounter.main(new String[]{success.getPath()});

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "Give: 1" + ls
                + "Hot-cross: 4" + ls
                + "If: 1" + ls
                + "One: 2" + ls
                + "a: 4" + ls
                + "buns: 4" + ls
                + "daughters: 1" + ls
                + "have: 1" + ls
                + "no: 1" + ls
                + "penny: 4" + ls
                + "sons: 1" + ls
                + "them: 1" + ls
                + "to: 1" + ls
                + "two: 2" + ls
                + "you: 1" + ls
                + "your: 1" + ls;
        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void 引数が足りないと例外() {
        try {
            WordCounter.main(new String[]{});
        } catch (Exception expected) {
            assertThat(expected.getMessage(), is("File path is needed!"));
        }
    }

    @Test
    public void 指定したファイルが見つからない() {
        try {
            WordCounter.main(new String[]{failure.getAbsolutePath()});
        } catch (Exception expected) {
            assertThat(expected.getMessage(), is("Not found: " + failure.getAbsolutePath()));
        }
    }

    @Test
    public void 指定したパスがファイルではない() {
        final File dir = new File("test" + fs + "ch20" + fs + "ex11", "dir");
        try {
            WordCounter.main(new String[]{dir.getPath()});
        } catch (Exception expected) {
            assertThat(expected.getMessage(), is("Not found: " + dir.getPath()));
        }
    }

}