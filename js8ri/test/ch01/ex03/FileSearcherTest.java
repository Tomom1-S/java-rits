package ch01.ex03;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FileSearcherTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String ls = System.lineSeparator();
    private final String fs = File.separator;

    private final FileSearcher searcher = new FileSearcher();
    private final File dir = new File("test" + fs + "ch01" + fs + "ex03");
    private final String extension = "txt";
    private String expected = "Result:" + ls +
            "src4.TXT" + ls + "src1.txt" + ls + "src3.txt" + ls + "src2.txt" + ls;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void showDirectoriesの正常系() {
        searcher.showFiles(dir.getAbsolutePath(), extension);

        // 標準出力の内容を取得
        System.out.flush();
        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void showDirectoriesWithLambdaの正常系() {
        searcher.showFilesWithLambda(dir.getAbsolutePath(), extension);

        // 標準出力の内容を取得
        System.out.flush();
        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void showDirectoriesWithLambdaで指定された拡張子が大文字のときの正常系() {
        searcher.showFilesWithLambda(dir.getAbsolutePath(), extension.toUpperCase());

        // 標準出力の内容を取得
        System.out.flush();
        assertThat(outContent.toString(), is(expected));
    }

}