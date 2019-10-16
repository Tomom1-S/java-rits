package ch03.ex08;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class VehicleTest {

    @Test
    public void cloneでオブジェクトを複製できる() {
        Vehicle org = new Vehicle("testUser");
        org.setSpeed(100);
        org.setDirection(123.4);

        Vehicle copy = org.clone();

        assertThat(copy != org, is(true));
        assertThat(copy.getClass() == org.getClass(), is(true));
        assertThat(copy.getId(), is(org.getId()));
        assertThat(copy.getOwner(), is(org.getOwner()));
        assertThat(copy.getSpeed(), is(org.getSpeed()));
        assertThat(copy.getDirection(), is(org.getDirection()));
    }

}