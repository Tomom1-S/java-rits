package ch03.ex07;

import org.junit.Test;

import java.util.Comparator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SampleTest {

    @Test
    public void 普通の順番のComparator() {
        Comparator<String> comparator = Sample.createComparator(
                false,
                false,
                false
        );

        assertThat(comparator.compare("abc", "abc"), is(0));
        assertTrue(comparator.compare("abc", "def") < 0);
        assertTrue(comparator.compare("abc", "Abc") > 0);
        assertTrue(comparator.compare("Abc", "abc") < 0);
        assertTrue(comparator.compare("abc", "a b  c") > 0);
    }

    @Test
    public void 大文字小文字を区別しない普通の順番のComparator() {
        Comparator<String> comparator = Sample.createComparator(
                false,
                true,
                false
        );

        assertThat(comparator.compare("abc", "abc"), is(0));
        assertTrue(comparator.compare("abc", "def") < 0);
        assertThat(comparator.compare("abc", "Abc"), is(0));
        assertTrue(comparator.compare("abc", "a b  c") > 0);
    }

    @Test
    public void 空白を除外する普通の順番のComparator() {
        Comparator<String> comparator = Sample.createComparator(
                false,
                false,
                true
        );

        assertThat(comparator.compare("abc", "abc"), is(0));
        assertTrue(comparator.compare("abc", "def") < 0);
        assertTrue(comparator.compare("abc", "Abc") > 0);
        assertTrue(comparator.compare("Abc", "abc") < 0);
        assertThat(comparator.compare("abc", "a b  c"), is(0));
    }

    @Test
    public void 大文字小文字を区別せず空白を除外する普通の順番のComparator() {
        Comparator<String> comparator = Sample.createComparator(
                false,
                true,
                true
        );

        assertThat(comparator.compare("abc", "abc"), is(0));
        assertTrue(comparator.compare("abc", "def") < 0);
        assertThat(comparator.compare("abc", "Abc"), is(0));
        assertThat(comparator.compare("abc", "a b  c"), is(0));
    }

    @Test
    public void 逆順のComparator() {
        Comparator<String> comparator = Sample.createComparator(
                true,
                false,
                false
        );

        assertThat(comparator.compare("abc", "abc"), is(0));
        assertTrue(comparator.compare("abc", "def") > 0);
        assertTrue(comparator.compare("abc", "Abc") < 0);
        assertTrue(comparator.compare("Abc", "abc") > 0);
        assertTrue(comparator.compare("abc", "a b  c") < 0);
    }

    @Test
    public void 大文字小文字を区別しない逆順のComparator() {
        Comparator<String> comparator = Sample.createComparator(
                true,
                true,
                false
        );

        assertThat(comparator.compare("abc", "abc"), is(0));
        assertTrue(comparator.compare("abc", "def") > 0);
        assertThat(comparator.compare("abc", "Abc"), is(0));
        assertTrue(comparator.compare("abc", "a b  c") < 0);
    }

    @Test
    public void 空白を除外する逆順のComparator() {
        Comparator<String> comparator = Sample.createComparator(
                true,
                false,
                true
        );

        assertThat(comparator.compare("abc", "abc"), is(0));
        assertTrue(comparator.compare("abc", "def") > 0);
        assertTrue(comparator.compare("abc", "Abc") < 0);
        assertTrue(comparator.compare("Abc", "abc") > 0);
        assertThat(comparator.compare("abc", "a b  c"), is(0));
    }

    @Test
    public void 大文字小文字を区別せず空白を除外する逆順のComparator() {
        Comparator<String> comparator = Sample.createComparator(
                true,
                true,
                true
        );

        assertThat(comparator.compare("abc", "abc"), is(0));
        assertTrue(comparator.compare("abc", "def") > 0);
        assertThat(comparator.compare("abc", "Abc"), is(0));
        assertThat(comparator.compare("abc", "a b  c"), is(0));
    }

}