package ch14.ex02;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class SingleLinkQueueTest {

    @Test
    public void size() {
        SingleLinkQueue<String> queue = new SingleLinkQueue<>();

        final int expected = 5;
        for (int i = 0; i < expected; i++) {
            queue.add("element" + i);
        }

        assertThat(queue.size(), is(expected));
    }
}