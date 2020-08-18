package ch22.ex01;

import java.util.Arrays;
import java.util.Formatter;

public class FloatArrayTable {
    private final String ls = System.lineSeparator();

    public void showTable(final float[] floats, final int row) {
        final String pattern = createPattern(row);

        Formatter fm = new Formatter();
        for (int i = 0; i < floats.length; i += row) {
            // subArr が0埋めされないように subArr の長さを指定
            int tail = i + row;
            if (tail > floats.length) {
                tail = floats.length;
            }

            final float[] subArr = Arrays.copyOfRange(floats, i, tail);
            switch (subArr.length) {
                case 1:
                    fm.format(createPattern(1), subArr[0]);
                    break;
                case 2:
                    fm.format(createPattern(2), subArr[0], subArr[1]);
                    break;
                default:
                    fm.format(pattern, subArr[0], subArr[1], subArr[2]);
            }
        }
        System.out.print(fm);
    }

    private String createPattern(final int row) {
        // TODO 縦列のエントリーを揃える
        String pattern = "|";
        for (int i = 0; i < row; i++) {
            pattern += " %.4f |";
        }
        pattern += ls;
        return pattern;
    }
}
