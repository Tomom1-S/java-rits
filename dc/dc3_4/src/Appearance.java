import javafx.scene.paint.Color;
import javafx.scene.text.Font;

class Appearance {
    String font;
    int fontSize;
    Color fontColor;
    Color bgColor;

    Appearance() {
        font = Font.getDefault().getFamily();
        fontSize = Settings.FontSize.DEFAULT_SIZE;
        fontColor = Color.BLACK;
        bgColor = Color.WHITE;
    }

    Appearance(final String font, final int fontSize, final Color fontColor, final Color bgColor) {
        this.font = font;
        this.fontSize = fontSize;
        this.fontColor = fontColor;
        this.bgColor = bgColor;
    }
}
