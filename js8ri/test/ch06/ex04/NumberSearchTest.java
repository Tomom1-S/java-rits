package ch06.ex04;

import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class NumberSearchTest {
    final int length = 100 * 1000;

    @Test
    public void findMaxの結果がシングルスレッドの結果と一致する() {
        long[] values = new long[length];
        final Random random = new Random();
        for (int i = 0; i < length; i++) {
            values[i] = random.nextLong();
        }

        final long actual = NumberSearch.findMax(values);
        long expected = Long.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            expected = Math.max(values[i], expected);
        }

        assertThat(actual, is(expected));
    }

    @Test
    public void findMinの結果がシングルスレッドの結果と一致する() {
        long[] values = new long[length];
        final Random random = new Random();
        for (int i = 0; i < length; i++) {
            values[i] = random.nextLong();
        }

        final long actual = NumberSearch.findMin(values);
        long expected = Long.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            expected = Math.min(values[i], expected);
        }

        assertThat(actual, is(expected));
    }
}