package ch01.ex06;

public class Fibonacci {
	/** 値が50未満のフィボナッチ数列を表示する */
	public static void main(String[] args) {		
		final String TITLE = "値が50未満のフィボナッチ数列";
		System.out.println(TITLE);
		
		int lo = 1;
		int hi = 1;
		System.out.println(lo);
		while (hi < 50) {
			System.out.println(hi);
			hi = lo + hi;
			lo = hi - lo;
		}
	}
}
