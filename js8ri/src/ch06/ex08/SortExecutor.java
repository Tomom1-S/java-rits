package ch06.ex08;

import java.util.Arrays;
import java.util.Random;

public class SortExecutor {
    public static void main(String[] _args) {
        final int baseSize = 1000;  // 何個刻みで配列を伸ばすか
        final int times = 10; // 何倍になるまで調べるか

        final double[] orgArray = new double[baseSize * times];
        final Random random = new Random();
        for (int i = 0; i < orgArray.length; i++) {
            orgArray[i] = random.nextDouble();
        }

        final int iterate = 10; // 何回分の平均を取るか
        for (int i = 0; i < times; i++) {
            final double[] array = Arrays.copyOf(orgArray, baseSize * (i + 1));
            System.out.println("Array length: " + array.length);

            long start;
            long total;

            // Arrays.sort
            total = 0L;
            for (int j = 0; j < iterate; j++) {
                start = System.nanoTime();
                Arrays.sort(array);
                total += System.nanoTime() - start;
            }
            System.out.println("Arrays.sort: " + (total / iterate) / 1E6 + " msec");

            // Arrays.parallelSort
            total = 0L;
            for (int j = 0; j < iterate; j++) {
                start = System.nanoTime();
                Arrays.parallelSort(array);
                total += System.nanoTime() - start;
            }
            System.out.println("Arrays.parallelSort: " + (total / iterate) / 1E6 + " msec");

            System.out.println();
        }
    }
}
