package ch02.ex16;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LinkedListTest {

	LinkedList list = new LinkedList();

	@Test
	void getLengthでリスト内の要素の数が取得できる() {
		int[][] data = { { 1, 3, 5 }, { 2, 4, 6 }, { 3, 5, 7 } };
		for (int i = 0; i < data.length; i++) {
			list.setNode(data[i]);
		}

		assertEquals(data.length, list.getLength());
	}

}
