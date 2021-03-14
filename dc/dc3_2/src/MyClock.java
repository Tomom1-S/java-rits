import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MyClock extends Application {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private Stage stage;
    private Pane pane = new StackPane();
    private Canvas canvas;
    private GraphicsContext gc;
    private MyMenuBar menuBar = new MyMenuBar(this);

    private static class textPos {
        static double x;
        static double y;
    }

    @Override
    public void start(final Stage stage) {
        this.stage = stage;

        final double width = Settings.FontSize.DEFAULT_SIZE * Settings.Window.ratioX;
        final double height = Settings.FontSize.DEFAULT_SIZE * Settings.Window.ratioY;
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFill(Color.BLACK);
        final Font font = new Font(gc.getFont().getFamily(), Settings.FontSize.DEFAULT_SIZE);
        gc.setFont(font);
        resizeWindowWithFont(font);

        draw(LocalTime.now().format(formatter));

        // 1秒ごとに行う処理
        final Timeline timer = new Timeline(
                new KeyFrame(
                        Duration.seconds(1),
                        event -> {
                            final String now = LocalTime.now().format(formatter);
                            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                            draw(now);
                        }
                )
        );
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

        pane.getChildren().add(canvas);
        pane.setPrefSize(width, height);

        this.stage.setScene(new Scene(new VBox(menuBar.bar, pane)));
        this.stage.setTitle("What time is it?");
        this.stage.show();
    }

    private void updateTextPos(final double x, final double y) {
        textPos.x = x;
        textPos.y = y;
    }

    /**
     * GraphicsContext にテキストを描画
     *
     * @param text 描画するテキスト
     */
    public void draw(final String text) {
        gc.fillText(text, textPos.x, textPos.y);
    }

    private void resizeWindowWithFont(final Font font) {
        final String text = "00:00:00";
        final double width = textWidth(font, text) * Settings.Window.ratioX;
        final double height = textHeight(font, text) * Settings.Window.ratioY;
        canvas.setWidth(width);
        canvas.setHeight(height);
        updateTextPos(width / 2, height / 2);
        this.stage.setScene(new Scene(new VBox(menuBar.bar, pane)));
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

    /**
     * GraphicsContext のフォントを変更
     *
     * @param family フォント名
     */
    void changeFontFamily(final String family) {
        final Font font = new Font(family, gc.getFont().getSize());
        gc.setFont(font);
        resizeWindowWithFont(font);
    }

    /**
     * GraphicsContext のフォントサイズを変更
     *
     * @param size フォントサイズ
     */
    void changeFontSize(final int size) {
        final Font font = new Font(gc.getFont().getFamily(), size);
        gc.setFont(font);
        resizeWindowWithFont(font);
    }

    /**
     * GraphicsContext のフォントカラーを変更
     *
     * @param color フォントカラー
     */
    void changeFontColor(final Color color) {
        gc.setFill(color);
    }

    /**
     * 背景カラーを変更
     *
     * @param colorName カラー名
     */
    void changeBgColor(final String colorName) {
        pane.setStyle("-fx-background-color: " + colorName.toLowerCase() + ";");
    }
}
