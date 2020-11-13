package ch03.ex23;

import org.junit.Test;

import static org.junit.Assert.*;

public class PairTest {

    @Test
    public void flatMapの正常系() {
        final Pair<String> pair = new Pair<>("Foo", "Bar");
        final Pair<Character> actual = pair.flatMap(s -> s.charAt(0));

        final Pair<Character> expected = new Pair<>('F', 'B');
        assertTrue(actual.equals(expected));
    }

    @Test
    public void equalsがtrueになるとき() {
        final Pair<String> pair1 = new Pair<>("Foo", "Bar");
        final Pair<String> pair2 = new Pair<>("Foo", "Bar");
        assertTrue(pair1.equals(pair2));
    }

    @Test
    public void equalsがfalseになるとき() {
        final Pair<String> pair1 = new Pair<>("Foo", "Bar");
        final Pair<String> pair2 = new Pair<>("foo", "bar");
        assertFalse(pair1.equals(pair2));
    }

}