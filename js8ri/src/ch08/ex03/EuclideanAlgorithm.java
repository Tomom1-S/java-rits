package ch08.ex03;

public class EuclideanAlgorithm {
    public static int useMod(final int x, final int y) {
        if (x == 0 || y == 0) {
            return Math.abs(x + y);
        }

        int a = Math.max(x, y);
        int b = Math.min(x, y);
        int r = a % b;
        while (r != 0) {
            a = b;
            b = r;
            r = a % b;
        }
        return Math.abs(b);
    }

    public static int useFloorMod(final int x, final int y) {
        if (x == 0 || y == 0) {
            return Math.abs(x + y);
        }

        int a = Math.max(x, y);
        int b = Math.min(x, y);
        int r = Math.floorMod(a, b);
        while (r != 0) {
            a = b;
            b = r;
            r = Math.floorMod(a, b);
        }
        return Math.abs(b);
    }

    public static int useRem(final int x, final int y) {
        if (x == 0 || y == 0) {
            return Math.abs(x + y);
        }

        int a = Math.max(x, y);
        int b = Math.min(x, y);
        int r = MyMath.rem(a, b);
        while (r != 0) {
            a = b;
            b = r;
            r = MyMath.rem(a, b);
        }
        return Math.abs(b);
    }
}
