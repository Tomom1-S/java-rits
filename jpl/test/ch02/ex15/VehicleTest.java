package ch02.ex15;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VehicleTest {

	Vehicle vehicle = new Vehicle();

	@BeforeEach
	public void setUp() {
		double prevSpeed = 78.9;
		vehicle.setSpeed(prevSpeed);
	}

	@Test
	void changeSpeedでスピードを変更できる() {
		double expected = 45.6;

		vehicle.changeSpeed(expected);

		assertEquals(expected, vehicle.getSpeed());
	}

	@Test
	void changeSpeedでスピードを0に変更できる() {
		double expected = 0;

		vehicle.stop();

		assertEquals(expected, vehicle.getSpeed());
	}

}
