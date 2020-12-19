package ch02.ex14;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LinkedListTest {

	LinkedList list = new LinkedList();

	@Test
	void アクセッサーメソッドで値を設定して取得できる() {
		int[][] expectedData = { { 1, 3, 5 }, { 2, 4, 6 } };

		for (int i = 0; i < expectedData.length; i++) {
			list.setNode(expectedData[i]);
		}

		for (int i = 1; list.getNext(i) != null; i++) {
			assertEquals(expectedData[i - 1], list.getData(i));
		}

		assertEquals(expectedData[0], list.getNextData(0));
	}

	@Test
	void アクセッサーメソッドで値を変更して取得できる() {
		int index = 1;
		int[] expectedData = { 3, 5, 7 };

		list.setData(expectedData, index);

		assertEquals(expectedData, list.getData(index));
	}

}
