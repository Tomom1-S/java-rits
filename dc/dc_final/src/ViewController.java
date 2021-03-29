import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ViewController {
    private final Canvas canvas;
    private final Pane pane;

    private static class textPos {
        static double x;
        static double y;
    }

    public ViewController(Canvas canvas, Pane pane) {
        this.canvas = canvas;
        this.pane = pane;
    }

    /**
     * GraphicsContext のフォントを変更
     *
     * @param family フォント名
     */
    void changeFontFamily(final String family) {
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        final Font font = new Font(family, gc.getFont().getSize());
        gc.setFont(font);
        resizeWindowWithFont(font);

//        appearance.font = family;
    }

    /**
     * GraphicsContext のフォントサイズを変更
     *
     * @param size フォントサイズ
     */
    void changeFontSize(final int size) {
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        final Font font = new Font(gc.getFont().getFamily(), size);
        gc.setFont(font);
        resizeWindowWithFont(font);

//        appearance.fontSize = size;
    }

    /**
     * GraphicsContext のフォントカラーを変更
     *
     * @param color フォントカラー
     */
    void changeFontColor(final Color color) {
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);

//        appearance.fontColor = color;
    }

    /**
     * 背景カラーを変更
     *
     * @param colorName カラー名
     */
    void changeBgColor(final String colorName) {
        pane.setStyle("-fx-background-color: " + colorName.toLowerCase() + ";");

//        appearance.bgColor = Settings.COLOR_MAP.get(colorName);
    }

    /**
     * フォント(+フォントサイズ)に合わせてウィンドウをリサイズ
     *
     * @param font 現在のフォント
     */
    private void resizeWindowWithFont(final Font font) {
        final String text = "00:00:00";
//        final double width = textWidth(font, text) * Settings.Window.ratioX;
//        final double height = textHeight(font, text) * Settings.Window.ratioY;

        final double width = textWidth(font, text) * 1.5;
        final double height = textHeight(font, text) * 1.5;
        canvas.setWidth(width);
        canvas.setHeight(height);
        updateTextPos(width / 2, height / 2);
//        this.stage.setScene(new Scene(new VBox(menuBar.bar, pane)));
    }

    private void updateTextPos(final double x, final double y) {
        textPos.x = x;
        textPos.y = y;
    }

    private static double textHeight(final Font font, final String s) {
        final Text text = new Text(s);
        text.setFont(font);
        return text.getBoundsInLocal().getHeight();
    }

    private static double textWidth(final Font font, final String s) {
        final Text text = new Text(s);
        text.setFont(font);
        return text.getBoundsInLocal().getWidth();
    }
}
