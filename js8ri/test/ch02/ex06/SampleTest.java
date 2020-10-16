package ch02.ex06;

import org.junit.Test;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SampleTest {

    @Test
    public void improvedCharacterStreamの正常系() {
        final String word = "boat";
        final Stream<Character> actual = Sample.improvedCharacterStream(word);
        final Stream<Character> expected = Sample.characterStream(word);

        assertThat(actual.toArray(), is(expected.toArray()));
    }

}