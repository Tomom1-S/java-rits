package ch06.ex02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleTest {

    Vehicle vehicle = new Vehicle();
    double prevDirection = 45.5;

    @BeforeEach
    public void setUp() {
        vehicle.setDirection(prevDirection);
    }

    @Test
    void turnで角度を指定して方向を変更できる() {
        double delta = 90;

        vehicle.turn(delta);
        assertEquals(prevDirection + delta, vehicle.getDirection());

        vehicle.turn(-1 * delta);
        assertEquals(prevDirection, vehicle.getDirection());
    }

    @Test
    void turnで向きを指定して方向を変更できる() {
        double delta = 90;

        vehicle.turn(TurnDirection.TURN_LEFT);
        assertEquals(prevDirection - delta, vehicle.getDirection());

        vehicle.turn(TurnDirection.TURN_RIGHT);
        assertEquals(prevDirection, vehicle.getDirection());
    }

}