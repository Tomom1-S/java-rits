package ch08.ex03;

public class MyMath {
    public static int rem(final int x, final int y) {
        return (x % Math.abs(y) + Math.abs(y)) % Math.abs(y);
    }
}
