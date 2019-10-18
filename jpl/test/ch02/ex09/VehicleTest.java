package ch02.ex09;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class VehicleTest {

	@Test
	void getMaxIdで識別番号の最大値を取得できる() {
		Vehicle.main(new String[] {});
		assertEquals(2, Vehicle.getMaxId());
	}

}
