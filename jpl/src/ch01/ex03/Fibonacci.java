package ch01.ex03;

public class Fibonacci {
	/** 値が50未満のフィボナッチ数列を表示する */
	public static void main(String[] args) {
		System.out.println("値が50未満のフィボナッチ数列");
		
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
