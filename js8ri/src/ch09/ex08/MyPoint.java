package ch09.ex08;

public class MyPoint {
    private final int x;
    private final int y;

    public MyPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // 柴田さん
    public int compareTo(final MyPoint other) {
        if (x < other.x) {
            return -1;
        } else if (x > other.x) {
            return 1;
        }
        if (y < other.y) {
            return -1;
        } else if (y > other.y) {
            return 1;
        }
        return 0;
    }

    // p.224
//    public int compareTo(final MyPoint other) {
//        final int diff = Integer.compare(x, other.x);
//        if (diff != 0) {
//            return diff;
//        }
//        return Integer.compare(y, other.y);
//    }
}
