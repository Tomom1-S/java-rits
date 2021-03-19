import javafx.scene.paint.Color;

class Appearance {
    String font;
    int fontSize;
    Color fontColor;
    Color bgColor;

    Appearance(final String font, final int fontSize, final Color fontColor, final Color bgColor) {
        this.font = font;
        this.fontSize = fontSize;
        this.fontColor = fontColor;
        this.bgColor = bgColor;
    }
}
