import java.awt.*;

public class Utils {
    public static String colorToName(Color color) {
        if (color.equals(Color.BLACK)) {
            return "Black";
        } else if (color.equals(Color.BLUE)) {
            return "Blue";
        } else if (color.equals(Color.CYAN)) {
            return "Cyan";
        } else if (color.equals(Color.DARK_GRAY)) {
            return "Dark gray";
        } else if (color.equals(Color.GRAY)) {
            return "Gray";
        } else if (color.equals(Color.GREEN)) {
            return "Green";
        } else if (color.equals(Color.LIGHT_GRAY)) {
            return "Light gray";
        } else if (color.equals(Color.MAGENTA)) {
            return "Magenta";
        } else if (color.equals(Color.ORANGE)) {
            return "Orange";
        } else if (color.equals(Color.PINK)) {
            return "Pink";
        } else if (color.equals(Color.RED)) {
            return "Red";
        } else if (color.equals(Color.WHITE)) {
            return "White";
        } else if (color.equals(Color.YELLOW)) {
            return "Yellow";
        }
        return "Unknown Color";
    }

    public static Color nameToColor(String name) {
        switch (name) {
            case "Black":
                return Color.BLACK;
            case "Blue":
                return Color.BLUE;
            case "Cyan":
                return Color.CYAN;
            case "Dark gray":
                return Color.DARK_GRAY;
            case "Gray":
                return Color.GRAY;
            case "Green":
                return Color.GREEN;
            case "Light gray":
                return Color.LIGHT_GRAY;
            case "Magenta":
                return Color.MAGENTA;
            case "Orange":
                return Color.ORANGE;
            case "Pink":
                return Color.PINK;
            case "Red":
                return Color.RED;
            case "White":
                return Color.WHITE;
            case "Yellow":
                return Color.YELLOW;
            default:
                throw new IllegalArgumentException("Color \"" + name + "\" not found.");
        }
    }
}
