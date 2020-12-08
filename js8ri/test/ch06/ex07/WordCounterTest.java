package ch06.ex07;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class WordCounterTest {
    @Test
    public void 単語の出現回数をカウントする() {
        final HashMap<String, Long> expected = new HashMap<>();
        expected.put("this", 1L);
        expected.put("that", 1L);
        expected.put("is", 2L);
        expected.put("not", 1L);
        expected.put("a", 2L);
        expected.put("file", 2L);

        final WordCounter counter = new WordCounter("sample/shortText/file1.txt");
        final ConcurrentHashMap<String, Long> actual = counter.countWords();
        assertThat(actual.get("this"), is(expected.get("this")));
        assertThat(actual.get("that"), is(expected.get("that")));
        assertThat(actual.get("is"), is(expected.get("is")));
        assertThat(actual.get("not"), is(expected.get("not")));
        assertThat(actual.get("a"), is(expected.get("a")));
        assertThat(actual.get("file"), is(expected.get("file")));
        // どのファイルにも含まれない単語
        assertThat(actual.containsKey("folder"), is(false));
    }

    @Test
    public void 最も頻出する単語を取得する() {
        final WordCounter counter = new WordCounter("sample/shortText/file1.txt");
        counter.countWords();
        final Map.Entry<String, Long> actual = counter.getMostFrequently();

        assertThat(actual.getKey(), is("is"));
        assertThat(actual.getValue(), is(2L));
    }

}