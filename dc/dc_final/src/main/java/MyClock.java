import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.prefs.Preferences;

public class MyClock extends Application {
    @Override
    public void start(final Stage stage) throws Exception {
        final Preferences prefs = Preferences.userNodeForPackage(MyClock.class);
        final Point2D defPoint = calculateDefaultWindowPos();
        final Appearance appearance = new Appearance(
                prefs.getDouble(AppearanceSetting.PrefKey.LOC_X, defPoint.getX()),
                prefs.getDouble(AppearanceSetting.PrefKey.LOC_Y, defPoint.getY()),
                prefs.get(AppearanceSetting.PrefKey.FONT, Font.getDefault().getFamily()),
                prefs.getInt(AppearanceSetting.PrefKey.FONT_SIZE, AppearanceSetting.Default.FONT_SIZE),
                prefs.get(AppearanceSetting.PrefKey.FONT_COLOR, "black"),
                prefs.get(AppearanceSetting.PrefKey.BG_COLOR, "white")
        );

        final FXMLLoader loader = new FXMLLoader(getClass().getResource("my_clock.fxml"));
        final Parent root = loader.load();
        final Controller controller = loader.getController();
        controller.setAppearance(appearance);
        controller.setPrefs(prefs);
        loader.setController(controller);

        // Initialize preferences
        controller.view.setStage(stage);
        controller.view.applyAppearance();

        controller.runTimer();

        final Scene scene = new Scene(root);
        controller.bindMenuBar(stage);

        stage.setTitle("What Time Is It Now?");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setX(appearance.getLocX());
        stage.setY(appearance.getLocY());
        stage.sizeToScene();
        stage.show();

        stage.setOnHidden(e -> {
            controller.shutdown();
            Platform.exit();
        });
        stage.xProperty().addListener((_observable, _oldValue, newValue)
                -> {
            System.out.println(String.format("[X] newValue: %f, getX: %f", newValue.doubleValue(), stage.getX()));
            appearance.setLocX(stage.getX());
        });
        stage.yProperty().addListener((_observable, _oldValue, newValue)
                -> {
            System.out.println(String.format("[Y] newValue: %f, getY: %f", newValue.doubleValue(), stage.getY()));
            appearance.setLocY(stage.getY());
        });
    }

    public Point2D calculateDefaultWindowPos() {
        final Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        return new Point2D(bounds.getMinX(), bounds.getMinY());
    }
}
