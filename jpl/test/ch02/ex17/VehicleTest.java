package ch02.ex17;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VehicleTest {

	Vehicle vehicle = new Vehicle();
	double prevDirection = 45.5;

	@BeforeEach
	public void setUp() {
		vehicle.setDirection(prevDirection);
	}

	@Test
	void turnで角度を指定して方向を変更できる() {
		double delta = 90;

		vehicle.turn(delta);
		assertEquals(prevDirection + delta, vehicle.getDirection());

		vehicle.turn(-1 * delta);
		assertEquals(prevDirection, vehicle.getDirection());
	}

	@Test
	void turnで向きを指定して方向を変更できる() {
		double delta = 90;

		vehicle.turn(Vehicle.TURN_LEFT);
		assertEquals(prevDirection - delta, vehicle.getDirection());

		vehicle.turn(Vehicle.TURN_RIGHT);
		assertEquals(prevDirection, vehicle.getDirection());
	}

}
