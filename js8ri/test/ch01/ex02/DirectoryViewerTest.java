package ch01.ex02;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DirectoryViewerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String ls = System.lineSeparator();
    private final String fs = File.separator;

    private final DirectoryViewer viewer = new DirectoryViewer();
    private final File dir = new File(".." + fs + "jpl");
    private String expected = "jpl's subdirectories:" + ls +
            "test" + ls + "bin" + ls + ".settings" + ls + ".gradle" + ls + "lib" + ls + ".idea" + ls + "src" + ls;

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
        viewer.showDirectories(dir.getAbsolutePath());

        // 標準出力の内容を取得
        System.out.flush();
        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void showDirectoriesWithLambdaの正常系() {
        viewer.showDirectoriesWithLambda(dir.getAbsolutePath());

        // 標準出力の内容を取得
        System.out.flush();
        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void showDirectoriesWithMethodReferenceの正常系() {
        viewer.showDirectoriesWithMethodReference(dir.getAbsolutePath());

        // 標準出力の内容を取得
        System.out.flush();
        assertThat(outContent.toString(), is(expected));
    }
}