package ch02.ex09;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SampleTest {
    final Stream<ArrayList<String>> stream;
    final List<String> expected;

    public SampleTest() {
        stream = Stream.of(
                new ArrayList<>(
                        Arrays.asList("12", "3", "456", "78", "90")),
                new ArrayList<>(
                        Arrays.asList("abcd", "efg", "hijkl", "mn")),
                new ArrayList<>(
                        Arrays.asList("O", "PQ", "RSTUV", "W", "X", "YZ"))
        );
        expected = Arrays.asList(new String[]{
                "12", "3", "456", "78", "90",
                "abcd", "efg", "hijkl", "mn",
                "O", "PQ", "RSTUV", "W", "X", "YZ"
        });
    }

    @Test
    public void toArrayList1の正常系() {
        assertThat(Sample.toArrayList1(stream), is(expected));
    }

    @Test
    public void toArrayList2の正常系() {
        assertThat(Sample.toArrayList2(stream), is(expected));
    }

    @Test
    public void toArrayList3の正常系() {
        assertThat(Sample.toArrayList3(stream), is(expected));
    }

}