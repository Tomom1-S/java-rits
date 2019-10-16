package ch03.ex09;

import ch03.ex08.Vehicle;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GarageTest {

    @Test
    public void findVehicleで指定したVehicleのインデックスを取得できる() {
        Vehicle tgt = new Vehicle("Tom");
        tgt.setDirection(20);
        tgt.setSpeed(100);

        Vehicle rest1 = new Vehicle("Bob");
        rest1.setDirection(200.5);
        rest1.setSpeed(165.5);

        Vehicle rest2 = new Vehicle("Molly");
        rest2.setDirection(60);
        rest2.setSpeed(180.4);

        int expected = 0;
        Garage garage = new Garage(tgt, new Vehicle[] {rest1, rest2});
        assertThat(garage.findVehicle(tgt), is(expected));
    }

    @Test
    public void mainでcloneが正しいことを確認できる() {
        assertThat(Garage.main(new String[] {}), is(true));
    }

}