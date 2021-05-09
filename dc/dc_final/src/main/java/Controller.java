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
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.Setter;
import views.Appearance;
import views.AppearanceSetting;
import views.Timer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class Controller implements Initializable {
    private Timer timer;
    @Getter
    private ViewController view;
    @Setter
    private Preferences prefs;
    private Appearance appearance;

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
    }

    public void runTimer() {
        timer.run();
    }

    public void bindMenuBar(final Stage stage) {
        menuBar.prefWidthProperty().bind(stage.widthProperty());
    }

    public void shutdown() {
        updatePrefs();
        System.exit(0);
    }

    @FXML
    public void handleExitAction(final ActionEvent _actionEvent) {
        shutdown();
    }

    @FXML
    public void handlePreferenceAction(final ActionEvent _actionEvent) throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("preference_dialog.fxml"));
        final Parent parent = fxmlLoader.load();
        final PreferenceController dialogController = fxmlLoader.getController();
        dialogController.setViewController(view);
        dialogController.setupComboBoxes(appearance);

        final Scene scene = new Scene(parent, 300, 200);
        final Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    public void handleParkInfoAction(final ActionEvent _actionEvent) {
        ParkNowController.openWindow(
                appearance.getLocX() + menuBar.getWidth(),
                appearance.getLocY(),
                appearance.getBgColor());
    }

    private void updatePrefs() {
        prefs.putDouble(AppearanceSetting.PrefKey.LOC_X, appearance.getLocX());
        prefs.putDouble(AppearanceSetting.PrefKey.LOC_Y, appearance.getLocY());
        prefs.put(AppearanceSetting.PrefKey.FONT, appearance.getFont());
        prefs.putInt(AppearanceSetting.PrefKey.FONT_SIZE, appearance.getFontSize());
        prefs.put(AppearanceSetting.PrefKey.FONT_COLOR, appearance.getFontColor());
        prefs.put(AppearanceSetting.PrefKey.BG_COLOR, appearance.getBgColor());
    }

    public void setAppearance(final Appearance appearance) {
        this.appearance = appearance;
        this.view.setAppearance(appearance);
    }
}
