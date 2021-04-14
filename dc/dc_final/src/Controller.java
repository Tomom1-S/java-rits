import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Timer timer;
    ViewController view;

    @FXML
    private MenuBar menuBar;
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

    public void bindMenuBar(final Stage stage) {
        menuBar.prefWidthProperty().bind(stage.widthProperty());
    }

    @FXML
    public void handleExitAction(final ActionEvent _actionEvent) {
        System.exit(0);
    }

    @FXML
    public void handlePreferenceAction(final ActionEvent _actionEvent) throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/preference_dialog.fxml"));
        final Parent parent = fxmlLoader.load();
        final PreferenceController dialogController = fxmlLoader.getController();
        dialogController.setViewController(view);

        final Scene scene = new Scene(parent, 300, 200);
        final Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
