import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewController {
    private Stage stage;
    private Appearance appearance;
    private final Canvas canvas;
    private final Pane pane;

    private static class textPos {
        static double x;
        static double y;
    }

    public ViewController(final Canvas canvas, final Pane pane) {
        this.canvas = canvas;
        this.pane = pane;
    }

    public void applyAppearance() {
        changeFontFamily(appearance.getFont());
        changeFontColor(appearance.getFontColor());
        changeFontSize(appearance.getFontSize());
        changeBgColor(appearance.getBgColor());
    }

    /**
     * GraphicsContext のフォントを変更
     *
     * @param family フォント名
     */
    void changeFontFamily(final String family) {
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        final Font font = new Font(Font.font(family).getName(), gc.getFont().getSize());
        gc.setFont(font);
        resizeWindowWithFont(font);

        appearance.setFont(family);
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

        appearance.setFontSize(size);
    }

    /**
     * GraphicsContext のフォントカラーを変更
     *
     * @param colorName フォントカラー名
     */
    void changeFontColor(final String colorName) {
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.web(nameToColor(colorName)));

        appearance.setFontColor(colorName);
    }

    /**
     * 背景カラーを変更
     *
     * @param colorName カラー名
     */
    void changeBgColor(final String colorName) {
        pane.setStyle("-fx-background-color: " + nameToColor(colorName) + ";");

        appearance.setBgColor(colorName);
    }

    private String nameToColor(final String colorName) {
        return colorName.toLowerCase().replaceAll("\\s+", "");
    }

    /**
     * フォント(+フォントサイズ)に合わせてウィンドウをリサイズ
     *
     * @param font 現在のフォント
     */
    private void resizeWindowWithFont(final Font font) {
        final String text = "00:00:00";

        final double width = textWidth(font, text) * AppearanceSetting.Default.WINDOW_RATIO;
        final double height = textHeight(font, text) * AppearanceSetting.Default.WINDOW_RATIO;
        canvas.setWidth(width);
        canvas.setHeight(height);
        pane.setPrefWidth(width);
        updateTextPos(width / 2, height / 2);
        stage.sizeToScene();
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
