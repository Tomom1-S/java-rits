package ch06.ex05;

import java.awt.*;

public enum TrafficLight {
    GREEN(Color.green) {
        @Override
        Color getColor() {
            return color;
        }
    },
    YELLOW(Color.yellow) {
        @Override
        Color getColor() {
            return color;
        }
    },
    RED(Color.red) {
        @Override
        Color getColor() {
            return color;
        }
    };

    Color color;

    TrafficLight(Color color) {
        this.color = color;
    }

    abstract Color getColor();
}
