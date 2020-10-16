package ch02.ex01;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class WordCounterTest {

    final WordCounter counter = new WordCounter();
    final List<String> words = Arrays.asList(new String[] {
            "a", "bb", "ccc", "ddddddddddddd", "eeeee",
            "ffffffffffff", "g", "hhhh", "iiiiiiiiiiiiiiiiiiii",
            "jjjjjjjjj", "kkkkkkkkkkkkkkkkkkkk", "llllllll", "mm",
            "nnnnnnnnnnnnn", "oooooo", "pppp", "qqqqqqqqqqqq",
            "rrrrrrrrrrrrrrrrrrrrrrrrr", "sssssssss", "ttttttttt",
            "u", "vvvvvvvvvvv", "wwwwwwwwwww", "xxx", "yy", "z", ""
    });

    @Test
    public void threadCountの正常系() {
        final int actual = counter.threadCount(words);
        assertThat(actual, is(5));
    }

}