package ch20.ex11;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FileSearchTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String ls = System.lineSeparator();
    private final String fs = File.separator;

    private final File dir = new File("test" + fs + "ch20" + fs + "ex11", "src");
    private final File failure = new File("test" + fs + "ch20" + fs + "ex11", "failure");
    private final String suffix = "txt";

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
        FileSearch.main(new String[]{dir.getPath(), suffix});

        // 標準出力の内容を取得
        System.out.flush();
        final String expected ="blackSheep.txt" + ls
                + "ladybird.txt" + ls;

        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void ファイルが見つからない() throws IOException {
        FileSearch.main(new String[]{dir.getPath(), "suffix"});

        // 標準出力の内容を取得
        System.out.flush();
        final String expected ="No files." + ls;

        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void 引数が足りないと例外() {
        try {
            FileSearch.main(new String[]{});
        } catch (Exception expected) {
            assertThat(expected.getMessage(), is("Directory path & suffix are needed!"));
        }
    }

    @Test
    public void nameが不正だと例外() {
        try {
            FileSearch.main(new String[]{failure.getAbsolutePath(), suffix});
        } catch (Exception expected) {
            assertThat(expected.getMessage(), is("Not found: " + failure.getAbsolutePath()));
        }
    }

}