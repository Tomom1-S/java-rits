package ch06.ex04;

import org.junit.Test;

import java.awt.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TrafficLightTest {

    @Test
    public void testGetColor() {
        assertThat(TrafficLight.GREEN.getColor(), is(Color.green));
        assertThat(TrafficLight.YELLOW.getColor(), is(Color.yellow));
        assertThat(TrafficLight.RED.getColor(), is(Color.red));
    }

}