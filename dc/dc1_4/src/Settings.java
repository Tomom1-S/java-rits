import java.awt.*;

class Settings {
    private String fontName;
    private int fontSize;
    private Color fontColor;
    private Color backgroundColor;

    final String getFontName() {
        return this.fontName;
    }

    final void setFontName(String fontName) {
        this.fontName = fontName;
    }

    final int getFontSize() {
        return this.fontSize;
    }

    final void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    final Color getFontColor() {
        return this.fontColor;
    }

    final void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    final Color getBackgroundColor() {
        return this.backgroundColor;
    }

    final void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
