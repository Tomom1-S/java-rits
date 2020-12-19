package ch02.ex11;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.Test;

import ch02.ex10.Vehicle;

class LinkedListTest {

	@Test
	void toStringでLinkedListを文字列として取得できる() {
		Vehicle toyota = new Vehicle();
		toyota.speed = 100;
		toyota.direction = 30;
		toyota.owner = "Tom";

		Vehicle benz = new Vehicle();
		benz.speed = 165.5;
		benz.direction = 200.5;
		benz.owner = "Bob";

		Vehicle ferrari = new Vehicle();
		ferrari.speed = 180.4;
		ferrari.direction = 60;
		ferrari.owner = "Fred";

		LinkedList list = new LinkedList();
		list.setNode(toyota);
		list.setNode(benz);
		list.setNode(ferrari);

		final String actual = list.toString();
		final String expected = "[id=0, speed=100.0, direction=30.0, owner=Tom]" + System.lineSeparator()
				+ "[id=1, speed=165.5, direction=200.5, owner=Bob]" + System.lineSeparator()
				+ "[id=2, speed=180.4, direction=60.0, owner=Fred]" + System.lineSeparator();

		assertThat(actual, is(expected));
	}

}
