package ch20.ex05;

import ch20.ex04.LineReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class WordSearchTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String ls = System.lineSeparator();
    private final String fs = File.separator;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void 大文字小文字が一致している場合に正しく検出できる(){
        File src = new File("test" + fs + "ch20" + fs + "ex05", "source.txt");
        try {
            WordSearch.main(new String[] {"all", src.getAbsolutePath()});
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "L1: HUMPTY DUMPTY sat on a wall," + ls
                + "L2: humpty dumpty had a great fall;" + ls
                + "L3: All the king's horses and all the king's men" + ls;

        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void 大文字小文字が一致していなくても正しく検出できる(){
        File src = new File("test" + fs + "ch20" + fs + "ex05", "source.txt");
        try {
            WordSearch.main(new String[] {"Humpty", src.getAbsolutePath()});
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "L1: HUMPTY DUMPTY sat on a wall," + ls
                + "L2: humpty dumpty had a great fall;" + ls
                + "L4: Couldn't put HumptY together again." + ls;

        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void 一致するものが見つからないとき(){
        File src = new File("test" + fs + "ch20" + fs + "ex05", "source.txt");
        try {
            WordSearch.main(new String[] {"foo", src.getAbsolutePath()});
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 標準出力の内容を取得
        System.out.flush();
        final String expected = "foo not found" + ls;

        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void 引数が足りないと例外() {
        try {
            WordSearch.main(new String[]{});
        } catch (IllegalArgumentException expected) {
            assertThat(expected.getMessage(), is("2 arguments are needed!"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}