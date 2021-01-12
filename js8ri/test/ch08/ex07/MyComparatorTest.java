package ch08.ex07;

import org.junit.Test;

import java.time.Period;
import java.util.Arrays;
import java.util.Comparator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MyComparatorTest {
    @Test
    public void reservedNullsFirstが正しい結果を返す() {
        final Person[] actual = new Person[]{
                new Person("abc"), new Person(null), new Person("abb"),
                new Person("Abc"), new Person("abcd"),
                new Person("bcd"), new Person("BCD")
        };
        final Person[] expected = actual.clone();
        Arrays.sort(actual,
                Comparator.comparing(Person::getName, MyComparator.reservedNullsFirst()));
        Arrays.sort(expected,
                Comparator.comparing(Person::getName,
                        Comparator.nullsFirst((Comparator<String>) Comparator.naturalOrder()).reversed()));

        assertThat(actual, is(expected));
    }

}