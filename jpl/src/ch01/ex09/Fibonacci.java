package ch01.ex09;

import java.util.Arrays;

public class Fibonacci {
	public static final int SIZE = 10; // 数列の長さ

	/** フィボナッチ数列を最初から10個表示する */
	public static void main(String[] args) {
		int[] sequence = new int[SIZE];
		// 柴田さん：int [] を var と書いても問題なく動く（Java のバージョンに依存）
		// var: 型推論 - 右辺で int[SIZE] と書いているので、勝手に int[] と判断してくれる
		sequence[0] = sequence[1] = 1;
		for (int i = 2; i < SIZE; i++) {
			sequence[i] = sequence[i - 2] + sequence[i - 1];
		}

		// System.out.println("フィボナッチ数列の最初の10個");
		System.out.println(Arrays.toString(sequence));
	}
}
