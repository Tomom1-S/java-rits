import java.awt.*;
import java.util.prefs.Preferences;

class MyPrefs {
    static final Label label = new Label();

    private final Preferences prefs;
    private final MyFrame frame;

    public MyPrefs(Class<? extends Frame> cls, MyFrame frame) {
        prefs = Preferences.systemNodeForPackage(cls);
        this.frame = frame;
    }

    // 引数が指定されなければ設定を全て保存
    public void save() {
        prefs.putDouble(label.WIN_LOC_X, frame.getLocation().getX());
        prefs.putDouble(label.WIN_LOC_Y, frame.getLocation().getY());
        prefs.putInt(label.WIN_WIDTH, frame.getWidth());
        prefs.putInt(label.WIN_HEIGHT, frame.getHeight());

        prefs.put(label.SETTING_FONT_NAME, frame.getFontName());
        prefs.putInt(label.SETTING_FONT_SIZE, frame.getFontSize());

        prefs.put(label.SETTING_FONT_COLOR, frame.getFontColor().toString());
        prefs.put(label.SETTING_BACKGROUND_COLOR, frame.getBackColor().toString());
    }

    public void load() {
        frame.setLocation(new Point(
                (int) prefs.getDouble(label.WIN_LOC_Y, 0),
                (int) prefs.getDouble(label.WIN_LOC_Y, 0)
        ));
        frame.setWidth(prefs.getInt(label.WIN_WIDTH, 300));
        frame.setHeight(prefs.getInt(label.WIN_HEIGHT, 300));

        frame.setFontName(prefs.get(label.SETTING_FONT_NAME, "Serif"));
        frame.setFontSize(prefs.getInt(label.SETTING_FONT_SIZE, 60));
        frame.setFontColor(Color.getColor((prefs.get(label.SETTING_FONT_COLOR, "black"))));
        frame.setFontColor(Color.getColor((prefs.get(label.SETTING_BACKGROUND_COLOR, "white"))));
    }

    static class Label {
        static final String WIN_LOC_X = "locationX";
        static final String WIN_LOC_Y = "locationY";
        static final String WIN_WIDTH = "width";
        static final String WIN_HEIGHT = "height";
        static final String SETTING_FONT_NAME = "fontName";
        static final String SETTING_FONT_SIZE = "fontSize";
        static final String SETTING_FONT_COLOR = "fontColor";
        static final String SETTING_BACKGROUND_COLOR = "backgroundColor";
    }
}
