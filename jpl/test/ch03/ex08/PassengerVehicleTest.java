package ch03.ex08;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PassengerVehicleTest {

    @Test
    public void cloneでオブジェクトを複製できる() {
        // x.clone () != x が true であり、x.clone().getClass() == x.getClass() も true であるインスタンスを生成
        PassengerVehicle org = new PassengerVehicle("testUser");
        org.setSpeed(100);
        org.setDirection(123.4);
        org.setSeats(10);
        org.setPassengers(5);

        PassengerVehicle copy = org.clone();

        assertThat(copy != org, is(true));
        assertThat(copy.getClass() == org.getClass(), is(true));
        assertThat(copy.getId(), is(org.getId()));
        assertThat(copy.getOwner(), is(org.getOwner()));
        assertThat(copy.getSpeed(), is(org.getSpeed()));
        assertThat(copy.getDirection(), is(org.getDirection()));
        assertThat(copy.getSeats(), is(org.getSeats()));
        assertThat(copy.getPassengers(), is(org.getPassengers()));
    }

}