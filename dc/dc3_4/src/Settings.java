import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public final class Settings {
    final static class Window {
        final static double ratioX = 1.2;
        final static double ratioY = 1.2;
    }

    final static class Menus {
        final static String APPEARANCE = "Appearance";
        final static String THEME = "Theme";
    }

    final static class MenuItems {
        final static String FONT = "Font";
        final static String FONT_SIZE = "Font Size";
        final static String FONT_COLOR = "Font Color";
        final static String BG_COLOR = "Background Color";
    }

    final static class FontSize {
        final static int DEFAULT_SIZE = 100;
        final static List<Integer> VALUES = Arrays.asList(20, 40, 60, 80, 100, 120, 140);
    }

    final static Map<String, Color> COLOR_MAP = Map.ofEntries(
            entry("Black", Color.BLACK),
            entry("Blue", Color.BLUE),
            entry("Cyan", Color.CYAN),
            entry("Dark blue", Color.DARKBLUE),
            entry("Dark cyan", Color.DARKCYAN),
            entry("Dark gray", Color.DARKGRAY),
            entry("Dark green", Color.DARKGREEN),
            entry("Dark magenta", Color.DARKMAGENTA),
            entry("Dark orange", Color.DARKORANGE),
            entry("Dark red", Color.DARKRED),
            entry("Gray", Color.GRAY),
            entry("Green", Color.GREEN),
            entry("Light blue", Color.LIGHTBLUE),
            entry("Light cyan", Color.LIGHTCYAN),
            entry("Light gray", Color.LIGHTGRAY),
            entry("Light green", Color.LIGHTGREEN),
            entry("Light pink", Color.LIGHTPINK),
            entry("Light yellow", Color.LIGHTYELLOW),
            entry("Magenta", Color.MAGENTA),
            entry("Orange", Color.ORANGE),
            entry("Pink", Color.PINK),
            entry("Red", Color.RED),
            entry("White", Color.WHITE),
            entry("Yellow", Color.YELLOW)
    );
}
