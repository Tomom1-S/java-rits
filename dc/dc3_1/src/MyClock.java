import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MyClock extends Application {
    final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private GraphicsContext gc;

    private final int width = 500;
    private final int height = 300;

    @Override
    public void start(final Stage stage) {
        final LocalTime time = LocalTime.now();
        final String timeText = time.format(formatter);

        final Canvas canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFill(Color.RED);
        gc.setFont(new Font(100));

        final double textX = canvas.getWidth()  / 2;
        final double textY = canvas.getHeight()  / 2;
        draw(timeText, textX, textY);

        // 1秒ごとに行う処理
        final Timeline timer = new Timeline(
                new KeyFrame(
                        Duration.seconds(1),
                        event -> {
                            final String now = LocalTime.now().format(formatter);
                            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                            draw(now, textX, textY);
                        }
                )
        );
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();

        final StackPane pane = new StackPane();
        pane.getChildren().add(canvas);
        pane.setPrefSize(width, height);

        stage.setScene(new Scene(pane));
        stage.setTitle("What time is it?");
        stage.show();
    }

    /**
     * GraphicsContext にテキストを描画
     * @param text 描画するテキスト
     * @param x x座標
     * @param y y座標
     */
    public void draw(final String text, final double x, final double y) {
        gc.fillText(text, x, y);
    }
}
