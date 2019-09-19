package ch01.ex12;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.Test;

class ImprovedFibonacciTest {

	@SuppressWarnings("static-access")
	@Test
	void フィボナッチ数列が配列に保存されている() throws Exception {
		ImprovedFibonacci fibonacci = new ImprovedFibonacci();
		String[] ans = new String[] { "1", "1", "2", "3", "5", "8", "13", "21", "34", "55" };
		
		assertArrayEquals(ans, fibonacci.main());
	}

}
