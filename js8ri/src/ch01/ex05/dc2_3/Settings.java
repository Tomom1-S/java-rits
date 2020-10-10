package ch01.ex05.dc2_3;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Settings {
    static final int FONT_SIZE = 50;
    static final double FONT_OCCUPANCY = 0.6; // フォントの占める割合

    static final int INTERVAL = 500;   // 描画の更新間隔[ms]

    static final class MainFrame {
        static final Dimension SIZE = new Dimension(400, 200);
        static Point location = new Point(100, 100);
    }

    static final class Menu {
        static final String TITLE = "Setting";

        static final class Label {
            static final String FONT = "Font";
            static final String FONT_SIZE = "Font Size";
            static final String FONT_COLOR = "Font Color";
            static final String BG_COLOR = "Background Color";
            static final String QUIT = "Quit MyClock";
        }

        public final static Map<String, Integer> SIZE_MAP = new LinkedHashMap<>() {{
            put("Small", 30);
            put("Middle", 100);
            put("Large", 200);
        }};

        public final static Map<String, Color> COLOR_MAP = new LinkedHashMap<>() {
            {
                put("Black", Color.BLACK);
                put("Blue", Color.BLUE);
                put("Cyan", Color.CYAN);
                put("Dark gray", Color.DARK_GRAY);
                put("Gray", Color.GRAY);
                put("Green", Color.GREEN);
                put("Light gray", Color.LIGHT_GRAY);
                put("Magenta", Color.MAGENTA);
                put("Orange", Color.ORANGE);
                put("Pink", Color.PINK);
                put("Red", Color.RED);
                put("White", Color.WHITE);
                put("Yellow", Color.YELLOW);
            }
        };
    }
}
