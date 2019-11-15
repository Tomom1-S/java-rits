package ch07.ex03;

import java.util.Arrays;

public class PascalsTriangle {
    private static final int DEFAULT_DEPTH = 12;
    private static int depth;
    private int[][] pascalsTriangle;

    PascalsTriangle () {
        this(DEFAULT_DEPTH);
    }

    PascalsTriangle (int depth) {
        this.depth = depth;
        pascalsTriangle = new int[this.depth][];
        for (int i = 0; i < this.depth; i++) {
            pascalsTriangle[i] = createLine(i);
        }
    }

    private int[] createLine(int depth) {
        if (depth < 0 || depth >= this.depth) {
            throw new IllegalArgumentException("深さは0から" + (this.depth - 1) + "の間の値を指定してください。");
        }

        int[] line = new int[depth + 1];
        for (int i = 0; i < line.length; i++) {
            if (i == 0 || i == line.length - 1) {
                line[i] = 1;
            } else {
                line[i] = pascalsTriangle[depth - 1][i - 1] + pascalsTriangle[depth - 1][i];
            }
        }
        return line;
    }

    public void show() {
        String str = "";
        for (int i = 0; i < depth; i++) {
            System.out.println(Arrays.toString(pascalsTriangle[i]));
        }
        return;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            depth = DEFAULT_DEPTH;
        } else {
            try {
                depth = Integer.parseInt(args[0]);
            } catch (IllegalArgumentException e) {
                System.out.println("第1引数には正の整数値を指定してください。");
                System.exit(-1);
            }
        }
        PascalsTriangle pt = new PascalsTriangle(depth);
        pt.show();
    }
}
