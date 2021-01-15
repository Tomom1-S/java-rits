package ch09.ex10;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LabeledPointTest {
    final LabeledPoint point = new LabeledPoint("label", 123, 45);

    @Test
    public void compareToで等しいと0となる() {
        assertThat(point.compareTo(point), is(0));

        assertThat(point.compareTo(new LabeledPoint("label", 123, 45)), is(0));
    }

    @Test
    public void compareToで正となる() {
        assertTrue(point.compareTo(new LabeledPoint("Label", 123, 45)) > 0);
        assertTrue(point.compareTo(new LabeledPoint("label", 12, 45)) > 0);
        assertTrue(point.compareTo(new LabeledPoint("label", 12, 4)) > 0);
    }

    @Test
    public void compareToで負となる() {
        assertTrue(point.compareTo(new LabeledPoint("labels", 123, 45)) < 0);
        assertTrue(point.compareTo(new LabeledPoint("label", 321, 45)) < 0);
        assertTrue(point.compareTo(new LabeledPoint("label", 123, 54)) < 0);
    }
}