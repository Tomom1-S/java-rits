package ch01.ex15;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ImplementedLookupTest {

	@Test
	void addで設定した値をfindで取得できる() {
		ImplementedLookup array = new ImplementedLookup();
		array.add("foo", 42);
		assertEquals(42, array.find("foo"));
	}

	@Test
	void nameに関連づけられた値がないときにfindがnullを返す() {
		ImplementedLookup array = new ImplementedLookup();
		array.add("foo", 42);
		assertNull(array.find("bar"));
	}

	@Test
	void removeで値を削除できる() {
		ImplementedLookup array = new ImplementedLookup();
		array.add("foo", 1);
		array.add("bar", 2);
		array.remove("bar");
		assertNull(array.find("bar"));
	}

}
