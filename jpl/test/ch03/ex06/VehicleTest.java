package ch03.ex06;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VehicleTest {
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

    @Test
    void Batteryの残量が0のときにstartで例外が発生する() throws Exception {
        String expectMsg = "Some energy sources are empty!";

        Vehicle vehicle = new Vehicle();
        vehicle.battery.setRemainingAmount(0);
        vehicle.gasTank.setRemainingAmount(42);

        Exception e = assertThrows(Exception.class, () -> vehicle.start());
        assertEquals(expectMsg, e.getMessage());
    }

    @Test
    void GasTankの残量が0のときにstartで例外が発生する() throws Exception {
        String expectMsg = "Some energy sources are empty!";

        Vehicle vehicle = new Vehicle();
        vehicle.battery.setRemainingAmount(42);
        vehicle.gasTank.setRemainingAmount(0);

        Exception e = assertThrows(Exception.class, () -> vehicle.start());
        assertEquals(expectMsg, e.getMessage());
    }

    @Test
    void BatteryとGasTankの残量が0でないときは例外が発生しない() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.battery.setRemainingAmount(42.0);
        vehicle.gasTank.setRemainingAmount(42.0);
        try {
            vehicle.start();
        } catch (Exception e) {
            fail();
        }
    }

}