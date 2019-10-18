package ch02.ex18;

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
	void mainでコマンドラインで指定した所有者の車を作成できる() {
		String[] args = { "John Smith" };
		Vehicle.main(args);

		// 標準出力の内容を取得
		System.out.flush();
		final String actual = byteArrayOutputStream.toString();

		// 期待値を設定
		final String expected = "id=0, speed=0.0, direction=0.0, owner=John Smith" + System.lineSeparator();

		assertThat(actual, is(expected));
	}

}
