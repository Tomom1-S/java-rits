import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timer {
    private final static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final GraphicsContext gc;

    public Timer(final GraphicsContext gc) {
        this.gc = gc;
    }

    public void run() {
        final Timeline timer = new Timeline(
                new KeyFrame(
                        Duration.seconds(1),
                        event -> {
                            final String now = LocalTime.now().format(TIME_FORMATTER);
                            gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
                            draw(now);
                        }
                )
        );
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void draw(final String text) {
        gc.fillText(text,
                gc.getCanvas().getWidth() / 2,
                gc.getCanvas().getHeight() / 2);
    }
}
