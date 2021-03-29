import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Timer timer;
    ViewController view;

    @FXML
    private Text actiontarget;

    @FXML
    private Pane pane;

    @FXML
    private Canvas canvas;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        timer = new Timer(gc);
        view = new ViewController(canvas, pane);

        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFill(Color.PINK);
    }

    public void runTimer() {
        timer.run();
    }

    public void handleExitAction(final ActionEvent _actionEvent) {
        System.exit(0);
    }
}
