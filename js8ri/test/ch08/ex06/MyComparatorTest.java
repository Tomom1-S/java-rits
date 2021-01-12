package ch08.ex06;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import org.junit.Test;

import java.util.Comparator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MyComparatorTest {
    final Comparator<Point2D> point2DComparator = MyComparator.getPoint2DComparator();
    final Comparator<Rectangle2D> rectangle2DComparator = MyComparator.getRectangle2DComparator();

    @Test
    public void getPoint2DComparatorで得たコンパレータが正の数を返す() {
        assertThat(point2DComparator.compare(
                new Point2D(0, 3), new Point2D(-1, 5)),
                is(1));
        assertThat(point2DComparator.compare(
                new Point2D(0, 3), new Point2D(0, 2)),
                is(1));
    }

    @Test
    public void getPoint2DComparatorで得たコンパレータが負の数を返す() {
        assertThat(point2DComparator.compare(
                new Point2D(-1, 5), new Point2D(0, 3)),
                is(-1));
        assertThat(point2DComparator.compare(
                new Point2D(0, 2), new Point2D(0, 3)),
                is(-1));
    }

    @Test
    public void getPoint2DComparatorで得たコンパレータが0を返す() {
        assertThat(point2DComparator.compare(
                new Point2D(42, 2), new Point2D(42, 2)),
                is(0));
    }

    @Test
    public void getRectangle2DComparatorで得たコンパレータが正の数を返す() {
        assertThat(rectangle2DComparator.compare(
                new Rectangle2D(0, 3, 5, 8),
                new Rectangle2D(-3, 3, 5, 8)),
                is(1));
        assertThat(rectangle2DComparator.compare(
                new Rectangle2D(0, 3, 5, 8),
                new Rectangle2D(0, 2, 5, 8)),
                is(1));
        assertThat(rectangle2DComparator.compare(
                new Rectangle2D(0, 3, 5, 8),
                new Rectangle2D(0, 3, 4, 8)),
                is(1));
        assertThat(rectangle2DComparator.compare(
                new Rectangle2D(0, 3, 5, 8),
                new Rectangle2D(0, 3, 5, 7)),
                is(1));
    }

    @Test
    public void getRectangle2DComparatorで得たコンパレータが負の数を返す() {
        assertThat(rectangle2DComparator.compare(
                new Rectangle2D(-3, 3, 5, 8),
                new Rectangle2D(0, 3, 5, 8)),
                is(-1));
        assertThat(rectangle2DComparator.compare(
                new Rectangle2D(0, 3, 5, 8),
                new Rectangle2D(0, 7, 5, 8)),
                is(-1));
        assertThat(rectangle2DComparator.compare(
                new Rectangle2D(0, 3, 5, 8),
                new Rectangle2D(0, 3, 9, 8)),
                is(-1));
        assertThat(rectangle2DComparator.compare(
                new Rectangle2D(0, 3, 5, 8),
                new Rectangle2D(0, 3, 5, 10)),
                is(-1));
    }
}