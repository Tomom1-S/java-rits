package utils;

import javafx.scene.paint.Color;

public class ColorHelper {
    public static String nameToColor(final String colorName) {
        return colorName.toLowerCase().replaceAll("\\s+", "");
    }

    public static String colorToHex(final Color color) {
        return String.format("#%02x%02x%02x",
                (int) (color.getRed() * 255),
                (int) (color.getGreen() * 255),
                (int) (color.getBlue() * 255));
    }
}
