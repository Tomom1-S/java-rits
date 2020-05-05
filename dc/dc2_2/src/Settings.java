import java.awt.*;
import java.util.ArrayList;

public final class Settings {
    static final class MainFrame {
        static final String TITLE = "What time is it?";
        static final Dimension SIZE = new Dimension(500, 500);
        static Point location = new Point(100, 100);
    }

    static final class Menu {
        static final String TITLE = "Setting";
        static final Dimension SIZE = new Dimension(500, 300);

        static final class Grid {
            static final int DEFAULT_POSITION = 0;
            static final int DEFAULT_WIDTH = 1;
            static final int DEFAULT_HEIGHT = 1;
        }

        static final class Label {
            static final String FONT = "Font";
            static final String FONT_SIZE = "Font Size";
            static final String FONT_COLOR = "Font Color";
            static final String BG_COLOR = "Background Color";
            static final String OK_BUTTON = "OK";
            static final String CANCEL_BUTTON = "Cancel";
        }

        static final int TEXT_FIELD_WIDTH = 10;
        static final int COLOR_FIELD_WIDTH = 100;

        static final Dimension COLOR_ICON_SIZE = new Dimension(10, 10);
        static final ArrayList<Color> COLORS = new ArrayList<>() {{
            add(Color.BLACK);
            add(Color.BLUE);
            add(Color.CYAN);
            add(Color.DARK_GRAY);
            add(Color.GRAY);
            add(Color.GREEN);
            add(Color.LIGHT_GRAY);
            add(Color.MAGENTA);
            add(Color.ORANGE);
            add(Color.PINK);
            add(Color.RED);
            add(Color.WHITE);
            add(Color.YELLOW);
        }};

        public static String getColorName(Color color) {
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
    }

    static final int FONT_SIZE = 50;
    static final double FONT_OCCUPANCY = 0.6; // フォントの占める割合

    static final int INTERVAL = 500;   // 描画の更新間隔[ms]
}
