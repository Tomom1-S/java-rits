package ch10.ex04;

import java.util.Arrays;

public class Fibonacci {
    public static final int SIZE = 10; // 数列の長さ

    /**
     * フィボナッチ数列を最初から10個表示する
     */
    public static void main(String[] args) {
        int[] sequence = new int[SIZE];
        sequence[0] = sequence[1] = 1;
        int i = 2;
        do {
            sequence[i] = sequence[i - 2] + sequence[i - 1];
            i++;
        } while (i < SIZE);

        System.out.println("フィボナッチ数列の最初の10個");
        System.out.println(Arrays.toString(sequence));
    }
}
