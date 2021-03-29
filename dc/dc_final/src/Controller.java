import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Timer timer;

    @FXML
    private Text actiontarget;

    @FXML
    private Canvas canvas;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        timer = new Timer(gc);

        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFill(Color.PINK);
    }

    public void handleSubmitButtonAction(final ActionEvent actionEvent) {
        actiontarget.setText("Sign in button pressed");
    }

    public void runTimer() {
        timer.run();
    }
}
