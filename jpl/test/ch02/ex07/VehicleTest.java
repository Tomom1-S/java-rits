package ch02.ex07;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VehicleTest {
	private PrintStream defaultPrintStream;
	private ByteArrayOutputStream byteArrayOutputStream;

	@BeforeEach
	public void setUp() {
		defaultPrintStream = System.out;
		byteArrayOutputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(new BufferedOutputStream(byteArrayOutputStream)));
	}

	@AfterEach
	public void tearDown() {
		System.setOut(defaultPrintStream);
	}

	@Test
	void mainで作成したオブジェクトが表示される() {
		Vehicle.main(new String[] {});

		// 標準出力の内容を取得
		System.out.flush();
		final String actual = byteArrayOutputStream.toString();

		// 期待値を設定
		final String expected = "Vehicle[id=0,speed=100.0,direction=30.0,owner=Tom]" + System.lineSeparator()
				+ "Vehicle[id=1,speed=165.5,direction=200.5,owner=Bob]" + System.lineSeparator()
				+ "Vehicle[id=2,speed=180.4,direction=60.0,owner=Fred]" + System.lineSeparator();

		assertThat(actual, is(expected));
	}

}
