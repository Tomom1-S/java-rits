package ch08.ex06;


import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

import java.util.Comparator;
import java.util.function.ToDoubleFunction;

public class MyComparator {
    public static Comparator<Point2D> getPoint2DComparator() {
        return Comparator.comparingDouble((ToDoubleFunction<Point2D>) p -> p.getX())
                .thenComparingDouble(p -> p.getY());
    }

    public static Comparator<Rectangle2D> getRectangle2DComparator() {
        return Comparator.comparingDouble((ToDoubleFunction<Rectangle2D>) r -> r.getMinX())
                .thenComparingDouble(r -> r.getMinY())
                .thenComparingDouble(r -> r.getMaxX())
                .thenComparingDouble(r -> r.getMaxY());

    }
}
