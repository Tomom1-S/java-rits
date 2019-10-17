package ch03.ex01;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.jupiter.api.*;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PassengerVehicleTest {

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
    @Order(1)
    void mainで作成したPassengerVehicleが表示される() {
        PassengerVehicle.main(new String[] {});
        String ls = System.lineSeparator();

        // 標準出力の内容を取得
        System.out.flush();
        final String actual = byteArrayOutputStream.toString();

        // 期待値を設定
        final String expected = "id=0, speed=200.0, direction=40.0, owner=Steven, number of seats=9, number of passengers=2" + ls
                + "id=1, speed=100.0, direction=400.0, owner=Hanna, number of seats=5, number of passengers=4" + ls;

        assertThat(actual, is(expected));
    }

    @Test
    void seatsが負のときにエラーとなる() {
        String expectMsg = "Number of seats should be a positive value!";

        PassengerVehicle testVehicle = new PassengerVehicle("test");
        Exception e = assertThrows(IllegalArgumentException.class, () -> testVehicle.setSeats(-1));
        assertEquals(expectMsg, e.getMessage());
    }

    @Test
    void passengersが負のときにエラーとなる() {
        String expectMsg = "Number of passengers should be a positive value!";

        PassengerVehicle testVehicle = new PassengerVehicle("test");
        Exception e = assertThrows(IllegalArgumentException.class, () -> testVehicle.setPassengers(-1));
        assertEquals(expectMsg, e.getMessage());
    }

    @Test
    void passengersよりもseatsが小さいときにエラーとなる() {
        String expectMsg = "Number of seats should be more than or equal to number of passengers!";

        PassengerVehicle testVehicle = new PassengerVehicle("test");
        testVehicle.setSeats(2);
        testVehicle.setPassengers(2);
        Exception e = assertThrows(IllegalArgumentException.class, () -> testVehicle.setSeats(1));
        assertEquals(expectMsg, e.getMessage());
    }

    @Test
    void seatsよりもpassengersが大きいときにエラーとなる() {
        String expectMsg = "Number of passengers should be less than or equal to number of seats!";

        PassengerVehicle testVehicle = new PassengerVehicle("test");
        testVehicle.setSeats(2);
        Exception e = assertThrows(IllegalArgumentException.class, () -> testVehicle.setPassengers(3));
        assertEquals(expectMsg, e.getMessage());
    }

}