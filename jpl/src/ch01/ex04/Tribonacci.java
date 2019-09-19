package ch01.ex04;

public class Tribonacci {
	/** 値が100未満のトリボナッチ数列（0-fil型）を表示する */
	public static void main(String[] args) {
		int lo = 0;
		int mi = 0;
		int hi = 1;
		int tmp = 0;
		System.out.println("値が100未満のトリボナッチ数列");
		System.out.println(lo);
		System.out.println(mi);
		while (hi < 100) {
			System.out.println(hi);
			hi = lo + mi + hi;
			tmp = mi;
			mi = hi - mi - lo;
			lo = tmp;
		}
	}
}
