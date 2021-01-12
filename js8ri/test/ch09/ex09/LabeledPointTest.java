package ch09.ex09;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class LabeledPointTest {
    final LabeledPoint point = new LabeledPoint("label", 123, 45);

    @Test
    public void equalsで同じオブジェクトを比較するとtrueとなる() {
        assertTrue(point.equals(point));
    }

    @Test
    public void equalsでnullと比較するとfalseとなる() {
        assertFalse(point.equals(null));
    }

    @Test
    public void equalsで違う型のオブジェクトを比較するとfalseとなる() {
        final Point other = new Point(123, 45);
        assertFalse(point.equals(other));
    }

    @Test
    public void equalsで同じフィールド値の異なるオブジェクトを比較するとtrueとなる() {
        final LabeledPoint other = new LabeledPoint("label", 123, 45);
        assertTrue(point.equals(other));
    }

    @Test
    public void equalsでlabelの異なるオブジェクトを比較するとfalseとなる() {
        final LabeledPoint other = new LabeledPoint("foo", 123, 45);
        assertFalse(point.equals(other));
    }

    @Test
    public void equalsでxの異なるオブジェクトを比較するとfalseとなる() {
        final LabeledPoint other = new LabeledPoint("label", 42, 45);
        assertFalse(point.equals(other));
    }

    @Test
    public void equalsでyの異なるオブジェクトを比較するとfalseとなる() {
        final LabeledPoint other = new LabeledPoint("foo", 123, 42);
        assertFalse(point.equals(other));
    }

    @Test
    public void equalsで同じフィールドがnullのときにtrueとなる() {
        final LabeledPoint one = new LabeledPoint(null, 123, 45);
        final LabeledPoint other = new LabeledPoint(null, 123, 45);
        assertTrue(one.equals(other));
    }

    @Test
    public void equalsであるフィールドがnullのときにfalseとなる() {
        final LabeledPoint one = new LabeledPoint(null, 123, 45);
        final LabeledPoint other = new LabeledPoint("label", 123, 45);
        assertFalse(one.equals(other));
    }

    @Test
    public void hashCodeで同じフィールド値のオブジェクトを比較するとtrueとなる() {
        final int pointHash = point.hashCode();
        final LabeledPoint other = new LabeledPoint("label", 123, 45);
        final int otherHash = other.hashCode();
        assertTrue(pointHash == otherHash);
    }

    @Test
    public void hashCodeで異なるフィールド値のオブジェクトを比較するとfalseとなる() {
        final int pointHash = point.hashCode();
        final LabeledPoint other = new LabeledPoint("label", 321, 45);
        final int otherHash = other.hashCode();
        assertFalse(pointHash == otherHash);
    }
}