package ch02.ex13;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class VehicleTest {

	Vehicle vehicle = new Vehicle();

	@Test
	void アクセッサーメソッドで値を設定して取得できる() {
		double expectedSpeed = 100.55;
		double expectedDirection = 12.3;
		String expectedOwner = "Tom";

		vehicle.setSpeed(expectedSpeed);
		vehicle.setDirection(expectedDirection);
		vehicle.setOwner(expectedOwner);

		assertEquals(0, vehicle.getId());
		assertEquals(1, vehicle.getNextId());
		assertEquals(expectedSpeed, vehicle.getSpeed());
		assertEquals(expectedDirection, vehicle.getDirection());
		assertEquals(expectedOwner, vehicle.getOwner());
	}

}
