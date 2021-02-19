package ch09.ex10;

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

    // 柴田さん：compareTo と equals の整合が取れていることが理想 (Effective Java p.69)
    // equals が true のときに compareTo は 0
    public int compareTo(final LabeledPoint other) {
        Objects.requireNonNull(other);

        final int labelComparison = Objects.compare(label, other.label, String::compareTo);
        if (labelComparison != 0) {
            return labelComparison;
        } else if (x != other.x) {
            return Integer.compare(x, other.x);
        } else {
            return Integer.compare(y, other.y);
        }
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
    public int hashCode() {
        return Objects.hash(label, x, y);
    }
}
