package ch01.ex07;

public class ImprovedFibonacci {
	static final int MAX_INDEX = 9;

	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		String mark;

		System.out.println(MAX_INDEX + ": " + lo);
		for (int i = MAX_INDEX - 1; i >= 1; i--) {
			if (hi % 2 == 0)
				mark = " *";
			else
				mark = "";
			System.out.println((i - MAX_INDEX +1) + ": " + hi + mark);
			// 柴田さん：表示されるインデックスは昇順にした方がよい
			hi = lo + hi;
			lo = hi - lo;
		}
	}
}
