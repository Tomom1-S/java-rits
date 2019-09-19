package ch01.ex08;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PointTest {

	@Test
	void setで値をセットできる() throws Exception {
		Point thisPoint = new Point();
		Point thatPoint = new Point();

		thatPoint.x = 1;
		thatPoint.y = 2;
		thisPoint.set(thatPoint);

		assertEquals(1, thisPoint.x);
		assertEquals(2, thisPoint.y);
	}

}
