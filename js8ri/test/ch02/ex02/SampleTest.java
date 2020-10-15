package ch02.ex02;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SampleTest {

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
    public void streamCountの正常系() {
        final Sample sample = new Sample();

        final List<String> words = Arrays.asList(new String[]{
                "Ladybird", "ladybird",
                "Fly", "away", "home",
                "Your", "house", "is", "on", "fire",
                "And", "your", "children", "all", "gone",
                "All", "except", "one",
                "And", "that's", "little", "Ann",
                "And", "she", "has", "crept", "under",
                "The", "warming", "pan"
        });
        sample.limitStream(words);

        // 標準出力の内容を取得
        System.out.flush();

        final String expected = "Filtering: Ladybird" + ls
                + "Ladybird" + ls
                + "Filtering: ladybird" + ls
                + "ladybird" + ls
                + "Filtering: Fly" + ls
                + "Filtering: away" + ls
                + "Filtering: home" + ls
                + "Filtering: Your" + ls
                + "Filtering: house" + ls
                + "house" + ls
                + "Filtering: is" + ls
                + "Filtering: on" + ls
                + "Filtering: fire" + ls
                + "Filtering: And" + ls
                + "Filtering: your" + ls
                + "Filtering: children" + ls
                + "children" + ls
                + "Filtering: all" + ls
                + "Filtering: gone" + ls
                + "Filtering: All" + ls
                + "Filtering: except" + ls
                + "except" + ls;
        assertThat(outContent.toString(), is(expected));
    }

}