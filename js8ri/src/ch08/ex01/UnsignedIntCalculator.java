package ch08.ex01;

public class UnsignedIntCalculator {
    public static String add(final int x, final int y) {
        return Integer.toUnsignedString(x + y);
    }

    public static String subtract(final int x, final int y) {
        return Integer.toUnsignedString(x - y);
    }

    public static String[] divide(final int x, final int y) {
        return new String[]{
                String.valueOf(Integer.divideUnsigned(x, y)),
                String.valueOf(Integer.remainderUnsigned(x, y))
        };
    }

    public static int compare(final int x, final int y) {
        return Integer.compareUnsigned(x, y);
    }
}
