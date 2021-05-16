import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lombok.Getter;
import lombok.Setter;
import views.Appearance;
import views.AppearanceSetting;
import views.Timer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class Controller implements Initializable {
    private static final String LABEL_PLAY = "Play";
    private static final String LABEL_PAUSE = "Pause";
    private static final FileChooser.ExtensionFilter EXT_FILTER =
            new FileChooser.ExtensionFilter(
                    "WAVE or Au only", "*.wav", "*.au", "*.snd");
    //　https://stackoverflow.com/questions/10645594/what-audio-format-should-i-use-for-java

    private Timer timer;
    private final SoundPlayer player;
    private final FileChooser fileChooser;
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
    private Canvas clockCanvas;
    @FXML
    private TextField musicFilename;
    @FXML
    private Slider musicSlider;

    public Controller() {
        this.player = new SoundPlayer();
        this.fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().add(EXT_FILTER);
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        final GraphicsContext gc = clockCanvas.getGraphicsContext2D();
        timer = new Timer(gc);
        view = new ViewController(clockCanvas, pane);

        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);

        musicSlider.setOnMouseReleased(event -> handleMusicSliderAction(musicSlider.getValue()));
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

    public void handleMusicSelectAction(final ActionEvent _actionEvent) {
        final File file = fileChooser.showOpenDialog(new Stage());
        musicFilename.setText(file.getName());

        try {
            player.loadFile(file);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            return;
        }

        final Timeline timer = new Timeline(new KeyFrame(
                Duration.seconds(1),
                event -> {
                    System.out.println(player.calculatePositionRate());
                    musicSlider.setValue(player.calculatePositionRate());
                }
        ));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    public void handleMusicSliderAction(double value) {
        try {
            player.jumpWithGage(value);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            return;
        }
    }

    public void handleMusicBackAction(final ActionEvent _actionEvent) {
        final double newPos;
        try {
            newPos = player.back();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            return;
        }

        musicSlider.setValue(newPos);
    }

    public void handleMusicPlayAction(final ActionEvent actionEvent) {
        final Button button = (Button) actionEvent.getSource();
        final String text = button.getText();
        if (Objects.equals(text, LABEL_PLAY)) {
            button.setText(LABEL_PAUSE);
        } else if (Objects.equals(text, LABEL_PAUSE)) {
            button.setText(LABEL_PLAY);
        }

        try {
            player.playOrPause();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            return;
        }
    }

    public void handleMusicForwardAction(final ActionEvent _actionEvent) {
        final double newPos;
        try {
            newPos = player.forward();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            return;
        }

        musicSlider.setValue(newPos);
    }
}
