package ch02.ex08;

import org.junit.Test;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SampleTest {

    @Test
    public void zipの正常系() {
        final Stream<String> actual = Sample.zip(
                Stream.of("A", "B", "C", "D", "E"),
                Stream.of("1", "2", "3", "4", "5", "6", "7", "8", "9")
        );

        final String[] expected = new String[] {
                "A", "1", "B", "2", "C", "3", "D", "4", "E", "5"
        };
        assertThat(actual.toArray(), is(expected));

    }

}