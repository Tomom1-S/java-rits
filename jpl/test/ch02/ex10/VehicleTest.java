package ch02.ex10;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.jupiter.api.Test;

class VehicleTest {

	@Test
	void toStringでフィールドを文字列として取得できる() {
		Vehicle car = new Vehicle();
		car.speed = 100;
		car.direction = 30;
		car.owner = "Cathy";

		final String actual = car.toString();
		final String expected = "id=0, speed=100.0, direction=30.0, owner=Cathy";

		assertThat(actual, is(expected));
	}

}
