package ch06.ex05;

import org.junit.Test;

import java.awt.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TrafficLightTest {

    @Test
    public void testGetColorGreen() {
        assertThat(TrafficLight.GREEN.getColor(), is(Color.green));
    }

    @Test
    public void testGetColorYellow() {
        assertThat(TrafficLight.YELLOW.getColor(), is(Color.yellow));
    }

    @Test
    public void testGetColorRed() {
        assertThat(TrafficLight.RED.getColor(), is(Color.red));
    }

}