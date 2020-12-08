package ch06.ex09;

public class Matrix {
    private final int size = 2;
    private long[][] elem = new long[size][size];

    public Matrix() {
        // デフォルトでは単位行列になる
        elem[0][0] = 1L;
        elem[0][1] = 0L;
        elem[1][0] = 0L;
        elem[1][1] = 1L;
    }

    public Matrix(final long a00, final long a01, final long a10, final long a11) {
        elem[0][0] = a00;
        elem[0][1] = a01;
        elem[1][0] = a10;
        elem[1][1] = a11;
    }

    public long get(final int row, final int column) {
        return elem[row][column];
    }

    public Matrix multiply(final Matrix that) {
        final Matrix result = new Matrix();
        result.elem[0][0] = elem[0][0] * that.elem[0][0] + elem[0][1] * that.elem[1][0];
        result.elem[0][1] = elem[0][0] * that.elem[0][1] + elem[0][1] * that.elem[1][1];
        result.elem[1][0] = elem[1][0] * that.elem[0][0] + elem[1][1] * that.elem[1][0];
        result.elem[1][1] = elem[1][0] * that.elem[0][1] + elem[1][1] * that.elem[1][1];
        return result;
    }
}
