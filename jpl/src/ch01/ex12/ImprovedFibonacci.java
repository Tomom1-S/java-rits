package ch01.ex12;

public class ImprovedFibonacci {
	static final int MAX_INDEX = 10;

	public static String[] main() {
		int lo = 1;
		int hi = 1;
		String[] sequence = new String[MAX_INDEX];
		sequence[0] = Integer.toString(lo);

		for (int i = 1; i <= MAX_INDEX - 1; i++) {
			sequence[i] = Integer.toString(hi);
			hi = lo + hi;
			lo = hi - lo;
		}
		return sequence;
	}
}
