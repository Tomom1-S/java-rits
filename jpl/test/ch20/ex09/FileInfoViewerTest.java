package ch20.ex09;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FileInfoViewerTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String ls = System.lineSeparator();
    private final String fs = File.separator;

    private final File txt = new File("test" + fs + "ch20" + fs + "ex09", "src.txt");
    private final File md = new File("test" + fs + "ch20" + fs + "ex09", "src.md");
    private final File failure = new File("test" + fs + "ch20" + fs + "ex09", "failure.txt");

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void mainの引数が正しく指定されたときにファイル情報が表示される() throws IOException {
        FileInfoViewer.main(new String[]{txt.getPath(), md.getAbsolutePath()});

        // 標準出力の内容を取得
        System.out.flush();
        final String expected ="<File Info>" + ls
                + "Name: src.txt" + ls
                + "Path: test/ch20/ex09/src.txt" + ls
                + "Absolute path: /Users/tomomi/practice/git-practice/jpl/test/ch20/ex09/src.txt" + ls
                + "Canonical path: /Users/tomomi/practice/git-practice/jpl/test/ch20/ex09/src.txt" + ls
                + "Parent: test/ch20/ex09" + ls
                + "Last modified: Sat Mar 07 09:56:53 JST 2020" + ls
                + "Length: 12 bytes" + ls
                + "<File Info>" + ls
                + "Name: src.md" + ls
                + "Path: /Users/tomomi/practice/git-practice/jpl/test/ch20/ex09/src.md" + ls
                + "Absolute path: /Users/tomomi/practice/git-practice/jpl/test/ch20/ex09/src.md" + ls
                + "Canonical path: /Users/tomomi/practice/git-practice/jpl/test/ch20/ex09/src.md" + ls
                + "Parent: /Users/tomomi/practice/git-practice/jpl/test/ch20/ex09" + ls
                + "Last modified: Sat Mar 07 09:59:47 JST 2020" + ls
                + "Length: 79 bytes" + ls;

        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void 引数が足りないと例外() {
        try {
            FileInfoViewer.main(new String[]{});
        } catch (Exception expected) {
            assertThat(expected.getMessage(), is("File path is needed!"));
        }
    }

    @Test
    public void nameが不正だと例外() {
        try {
            FileInfoViewer.main(new String[]{failure.getAbsolutePath()});
        } catch (Exception expected) {
            assertThat(expected.getMessage(), is("Not found: " + failure.getAbsolutePath()));
        }
    }

}