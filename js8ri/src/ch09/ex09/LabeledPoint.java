package ch09.ex09;

import java.util.Objects;

public class LabeledPoint {
    private final String label;
    private final int x;
    private final int y;

    public LabeledPoint(String label, int x, int y) {
        this.label = label;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(final Object otherObj) {
        if (this == otherObj) {
            return true;
        }
        if (otherObj == null) {
            return false;
        }
        if (getClass() != otherObj.getClass()) {
            return false;
        }

        final LabeledPoint other = (LabeledPoint) otherObj;
        return Objects.equals(label, other.label) &&
                Objects.equals(x, other.x) &&
                Objects.equals(y, other.y);
    }

    @Override
    public int hashCode(){
        return Objects.hash(label, x, y);
    }
}
