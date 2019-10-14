package ch06.ex04;

import java.awt.*;

public enum TrafficLight {
    GREEN(Color.green),
    YELLOW(Color.yellow),
    RED(Color.red);

    Color color;

    TrafficLight(Color color) {
        this.color = color;
    }

    Color getColor() {
        return color;
    }
}
