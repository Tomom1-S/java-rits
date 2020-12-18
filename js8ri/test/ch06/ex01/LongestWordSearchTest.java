package ch06.ex01;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LongestWordSearchTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String ls = System.lineSeparator();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void AliceInWonderlandの最大長文字列を表示() {
        // 総単語数 26,441語
        LongestWordSearch.main(new String[]{"sample/AliceInWonderland.txt"});

        System.out.flush();
        final String expected = "Longest word: disappointment" + ls;
        assertThat(outContent.toString(), is(expected));
    }

    @Test
    public void WarAndPeaceの最大長文字列を表示() {
        // 総単語数 566,275語
        LongestWordSearch.main(new String[]{"sample/WarAndPeace.txt"});

        System.out.flush();
        final String expected = "Longest word: characteristically" + ls;
        assertThat(outContent.toString(), is(expected));
    }
}